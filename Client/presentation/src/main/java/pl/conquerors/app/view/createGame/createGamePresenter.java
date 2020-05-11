package pl.conquerors.app.view.createGame;

import android.os.Build;
import android.support.annotation.RequiresApi;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.CharacterStatistics;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.CharacterStatisticsEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static pl.conquerors.app.model.mapper.CharacterStatisticsEntityMapper.*;

public class createGamePresenter extends BasePresenter<createGameView> {
    public void getCharacterInfo(){
        int characterId = 1;
        Call<CharacterStatisticsEntity> call = RestClient.getInstance().getCharacterStatistic(characterId);
        call.enqueue(new Callback<CharacterStatisticsEntity> (){

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CharacterStatisticsEntity> call, Response<CharacterStatisticsEntity> response) {
                CharacterStatistics info = transform(response.body());
                System.out.println(info);
            }

            @Override
            public void onFailure(Call<CharacterStatisticsEntity> call, Throwable t) {
                System.out.println("fdasfsd");
            }
        });
    }
}
