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
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class RestClient {

    private static final String API_URL = "http://192.168.99.108:8080/";
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

        final Interceptor cache_interceptor = chain -> {

            Request request = chain.request().newBuilder()
                    .addHeader("Content-Type","application/json")/*
                    .addHeader("Content-Length",String.valueOf(100))*/
                    .addHeader("Connection", "close").build();
            return chain.proceed(request);
        };

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(cache_interceptor)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(false)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RestService.class);
    }
}
