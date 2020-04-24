package pl.conquerors.app.view.everydayPrize;

import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;
import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
}