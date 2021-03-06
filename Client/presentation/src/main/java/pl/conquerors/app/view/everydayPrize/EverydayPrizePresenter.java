package pl.conquerors.app.view.everydayPrize;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.PrizeDate;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.PrizeAnswerEntity;
import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.model.mapper.CharacterEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import pl.conquerors.app.util.SharedPreferenceUtil;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.model.mapper.EveryDayPrizeMapper.transform;

public class EverydayPrizePresenter extends BasePresenter<EverydayPrizeView> {
    EverydayPrizeUseCase mUseCase;
    public EverydayPrizePresenter(final EverydayPrizeUseCase useCase) { mUseCase = useCase; }
    public void performEverydayPrize(int userId, int characterId) {
        Call<PrizeAnswerEntity> call = RestClient.getInstance().createPrizeDate(new PrizeDateEntity(userId, characterId));

        call.enqueue(new Callback<PrizeAnswerEntity>() {
            @Override
            public void onResponse(Call<PrizeAnswerEntity> call, Response<PrizeAnswerEntity> response) {
                if(!response.isSuccessful()){
                    Log.e("Everyday Prize", "Code: "+response.code());
                    if (response.code()==403)
                        mView.showAlreadyGifted();
                    return;
                }
                PrizeAnswerEntity prize = response.body();
                int attributeId = prize.getAttribute();
                mView.onEverydayPrizeSucceeded(attributeId);
            }

            @Override
            public void onFailure(Call<PrizeAnswerEntity> call, Throwable t) {
                mView.hideLoading();
                Log.e("Everyday Prize", t.getMessage());
            }
        });
    }

    public void getEverydayPrize(int userId) {
        Call<List<PrizeDateEntity>> call = RestClient.getInstance().getPrizeDate(userId);

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

    public void getCharacters(int userId) {
        Call<List<CharacterEntity>> call = RestClient.getInstance().getCharacter(userId);

        call.enqueue(new Callback<List<CharacterEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CharacterEntity>> call, Response<List<CharacterEntity>> response) {
                List<Character> characters = CharacterEntityMapper.transform(response.body());
                mView.openDialog(characters);
            }

            @Override
            public void onFailure(Call<List<CharacterEntity>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}