package pl.conquerors.app.view.characterStatistics;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.characterStatistics.CharacterStatisticsUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.CharacterStatistics;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.CharacterStatisticsEntity;
import pl.conquerors.app.model.mapper.CharacterEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static pl.conquerors.app.model.mapper.CharacterStatisticsEntityMapper.*;

import static android.support.constraint.Constraints.TAG;

public class CharacterStatisticsPresenter extends BasePresenter<CharacterStatisticsView> {
    CharacterStatisticsUseCase mUseCase;
    public CharacterStatisticsPresenter(final CharacterStatisticsUseCase useCase) { mUseCase = useCase; }


    public void getCharacterStatistic() {
        int character = mView.getCharacterId();
        Call<CharacterEntity> call = RestClient.getInstance().getCharacterStatistic(character);

        call.enqueue(new Callback<CharacterEntity>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CharacterEntity> call,
                                   Response<CharacterEntity> response) {
                Character character = CharacterEntityMapper.transform(response.body());
                mView.updateStatistics(character);
            }
            @Override
            public void onFailure(Call<CharacterEntity> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
