package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

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

    @BindView(R.id.radioWomen)
    RadioButton radioWomen;

    @BindView(R.id.radioMan)
    RadioButton radioMan;

    @BindView(R.id.characterView)
    ImageView characterView;

    private static int [] images = {R.drawable.female_256, R.drawable.male_256};

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

    @OnClick(R.id.radioWomen)
    public void onRadioWomenButtonSelected() {
        characterView.setImageResource(images[0]);
    }

    @OnClick(R.id.radioMan)
    public void onRadioManButtonSelected() {
        characterView.setImageResource(images[1]);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
