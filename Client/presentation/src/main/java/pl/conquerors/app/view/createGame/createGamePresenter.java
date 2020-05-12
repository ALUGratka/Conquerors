package pl.conquerors.app.view.createGame;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.CharacterStatistics;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.CharacterStatisticsEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.model.mapper.CharacterEntityMapper.transform;
import static pl.conquerors.app.model.mapper.CharacterStatisticsEntityMapper.*;

public class createGamePresenter extends BasePresenter<createGameView> {
    public void getCharacters(int userId) {

    }
}
