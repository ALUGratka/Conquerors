package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.navigation.Navigator;

public class CreateCharacterAppearance extends BaseActivity implements BaseView {

    @BindView(R.id.back_button)
    Button mPreviousScreenButton;

    @BindView(R.id.next_button)
    Button mNextScreenButton;


    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CreateCharacterAppearance.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_character_appearance);
    }

    @OnClick(R.id.back_button)
    public void onPreviousButtonClicked() {
        Navigator.startCharacterClassSelection(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
