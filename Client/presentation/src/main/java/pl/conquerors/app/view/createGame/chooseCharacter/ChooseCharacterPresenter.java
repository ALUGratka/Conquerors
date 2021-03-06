package pl.conquerors.app.view.createGame.chooseCharacter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.mapper.CharacterEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCharacterPresenter extends BasePresenter<ChooseCharacterView> {

    @Override
    public void resume() {
        super.resume();
        attemptToLoadAllCharacters();
    }

    private void attemptToLoadAllCharacters() {
        final long userId = mView.getUserId();

        Call<List<CharacterEntity>>call = RestClient.getInstance().getCharacter((int)userId);

        call.enqueue(new Callback<List<CharacterEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CharacterEntity>> call, Response<List<CharacterEntity>> response) {
                List<Character> characters = CharacterEntityMapper.transform(response.body());
                Log.i("characters_size", String.valueOf(characters.size()));
                if(characters.isEmpty()){
                    mView.startCreateCharacter(true);
                    mView.setCharacterCardVisible(false);
                    mView.setNextButtonVisible(false);
                }
                else {
                    mView.setCharacterCardVisible(true);
                    if(characters.size()==1) mView.setArrowsVisible(false);
                    mView.setCharacters(characters);
                    mView.setCharacterCard(0);
                }
            }

            @Override
            public void onFailure(Call<List<CharacterEntity>> call, Throwable t) {
                handleError(t);
            }
        });
    }

    void attemptToLoadCharacterCard(final int counter) {
        mView.setCharacterCard(counter);
    }
}
