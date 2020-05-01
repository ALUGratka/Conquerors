package pl.conquerors.app.view.everydayPrize;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;
import pl.conquerors.app.domain.model.PrizeDate;
import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.model.mapper.EveryDayPrizeMapper.transform;

public class EverydayPrizePresenter extends BasePresenter<EverydayPrizeView> {
    EverydayPrizeUseCase mUseCase;
    public EverydayPrizePresenter(final EverydayPrizeUseCase useCase) { mUseCase = useCase; }
    final int userId = 1;
    public void performEverydayPrize() {
        Call<PrizeDateEntity> call = RestClient.getInstance().createPrizeDate(new PrizeDateEntity(userId));

        call.enqueue(new Callback<PrizeDateEntity>() {
            @Override
            public void onResponse(Call<PrizeDateEntity> call, Response<PrizeDateEntity> response) {
                if(!response.isSuccessful()){
                    Log.e("Everyday Prize", "Code: "+response.code());
                    if (response.code()==403)
                        mView.showAlreadyGifted();
                    return;
                }
                mView.onEverydayPrizeSucceeded();
            }

            @Override
            public void onFailure(Call<PrizeDateEntity> call, Throwable t) {
                mView.hideLoading();
                Log.e("Everyday Prize", t.getMessage());
            }
        });
    }

    public void getEverydayPrize() {
        int user = 1;
        Call<List<PrizeDateEntity>> call = RestClient.getInstance().getPrizeDate(user);

        call.enqueue(new Callback<List<PrizeDateEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<PrizeDateEntity>> call, Response<List<PrizeDateEntity>> response) {
                List<PrizeDate> prizeDates = transform(response.body());
                mView.updateCalendar(prizeDates);
            }
            @Override
            public void onFailure(Call<List<PrizeDateEntity>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}