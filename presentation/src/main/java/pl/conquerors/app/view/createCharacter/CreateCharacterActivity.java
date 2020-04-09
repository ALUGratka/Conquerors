package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;

public class CreateCharacterActivity extends BaseActivity implements CreateCharacterView {

    CreateCharacterPresenter mCreateCharacterPresenter;

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CreateCharacterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_character_appearance);


//        mCreateCharacterPresenter = new CreateCharacterPresenter(); //cos tam use case?
//        mCreateCharacterPresenter.setmView(this);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
