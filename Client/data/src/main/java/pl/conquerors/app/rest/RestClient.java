package pl.conquerors.app.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.conquerors.data.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String API_URL = "http://192.168.99.105:8080/";
    private static RestService service;

    public static RestService getInstance(){
        if(service==null){
            service = initService();
        }
        return service;
    }

    private static RestService initService(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        final Interceptor cache_interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request().newBuilder().addHeader("Connection", "close").build();
                return chain.proceed(request);
            }
        };

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(cache_interceptor)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(false)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestService service = retrofit.create(RestService.class);
        return service;
    }
}
