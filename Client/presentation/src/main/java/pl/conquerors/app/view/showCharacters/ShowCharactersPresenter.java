package pl.conquerors.app.view.showCharacters;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.model.mapper.CharacterEntityMapper.transform;

public class ShowCharactersPresenter extends BasePresenter<ShowCharactersView> {

    CreateCharacterUseCase mUseCase;

    public ShowCharactersPresenter(CreateCharacterUseCase useCase) {
        mUseCase = useCase;
    }

    @Override
    public void resume() {
        super.resume();
        loadCharactersData();
    }

    private void loadCharactersData() {
        int userId = mView.getUserId();
        getCharacters(userId);
    }

    public void getCharacters(int userId) {
        Call<List<CharacterEntity>> call = RestClient.getInstance().getCharacter(userId);

        call.enqueue(new Callback<List<CharacterEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CharacterEntity>> call, Response<List<CharacterEntity>> response) {
                List<Character> characters = transform(response.body());
                mView.showCharacters(characters);
            }

            @Override
            public void onFailure(Call<List<CharacterEntity>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
