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

public class CreateCharacterSex extends BaseActivity implements BaseView {

    @BindView(R.id.back_button)
    Button mPreviousScreenButton;

    @BindView(R.id.next_button)
    Button mNextScreenButton;


    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CreateCharacterSex.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_character_sex);
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClicked() {
        Navigator.startCharacterClassSelection(this);
    }

    @OnClick(R.id.back_button)
    public void onPreviousButtonClicked() {
        Navigator.startHome(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
