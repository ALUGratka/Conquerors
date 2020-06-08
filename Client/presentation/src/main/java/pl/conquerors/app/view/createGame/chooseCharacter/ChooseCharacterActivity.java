package pl.conquerors.app.view.createGame.chooseCharacter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class ChooseCharacterActivity extends BaseActivity implements ChooseCharacterView {

    @BindView(R.id.linearLayout2)
    LinearLayout characterLayout;

    @BindView(R.id.displayCharacterNicknameText)
    TextView characterName;

    @BindView(R.id.displayCharacterClassText)
    TextView characterClassName;

    @BindView(R.id.characterClassImage)
    ImageView characterClassImage;

    @BindView(R.id.displayCurrentCharacterStrength)
    TextView characterStrength;

    @BindView(R.id.displayCurrentCharacterCharisma)
    TextView characterCharisma;

    @BindView(R.id.displayCurrentCharacterAgility)
    TextView characterAgility;

    @BindView(R.id.displayCurrentCharacterIntelligence)
    TextView characterIntelligence;

    @BindView(R.id.previousButton)
    Button previousArrowButton;

    @BindView(R.id.nextButton)
    Button nextArrowButton;

    @BindView(R.id.no_characters_layout)
    LinearLayout noCharacterPopUp;

    @BindView(R.id.forthButton)
    Button forthButton;

    @OnClick(R.id.no_characters_button)
    protected void onNoCharactersButtonClicked(){
        Navigator.startCreateCharacter(this);
        finish();
    }

    @OnClick(R.id.previousButton)
    protected void onPreviousArrowClicked(){
        if(counter!=-1){
            counter--;
        }
        setCharacterCard(counter);
    }

    @OnClick(R.id.nextButton)
    protected void onNextArrowClicked(){
        if(counter!=characters.size()){
            counter++;
        }
        setCharacterCard(counter);
    }

    @OnClick(R.id.backButton)
    protected void onBackButtonClicked(){
        cancelCreatingGame();
    }

    @OnClick(R.id.forthButton)
    protected void onNextButtonClicked(){
        //TODO shared
        Navigator.startChooseOpponent(this);
        finish();
    }

    public List<Character>characters = new ArrayList<>();
    private int counter = 0;

    public static Intent getStartingIntents(Context context){
        return new Intent(context, ChooseCharacterActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_character);

        counter = 0;
        ChooseCharacterPresenter presenter = new ChooseCharacterPresenter();
        presenter.setmView(this);
        presenter.created();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public long getUserId() {
        return SharedPreferenceUtil.getUser(this).getUserId();
    }

    @Override
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void onBackPressed() {
        cancelCreatingGame();
    }



    private void cancelCreatingGame(){
        final String title = getString(R.string.create_event_cancel_title);
        final String message = getString(R.string.create_event_cancel_message);

        DialogUtil.showSimpleDialog(this, title, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                finish();
            }
        }, /* no action, just show negative button */ null);
    }

    @Override
    public void startCreateCharacter(boolean visible) {
        noCharacterPopUp.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setCharacterCard(int index) {
        Character character = characters.get(index);

        characterName.setText(character.getmNickname());
        characterClassName.setText(character.getmCharacterClass());
        characterStrength.setText(String.valueOf(character.getmStrength()));
        characterCharisma.setText(String.valueOf(character.getmCharisma()));
        characterAgility.setText(String.valueOf(character.getmAgility()));
        characterIntelligence.setText(String.valueOf(character.getmIntelligence()));

        setNextArrowVisible(true);
        setPreviousArrowsVisible(true);
        if(counter==0) setPreviousArrowsVisible(false);
        else if(counter==characters.size()-1) setNextArrowVisible(false);
    }

    @Override
    public void setPreviousArrowsVisible(boolean visible) {
        previousArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNextArrowVisible(boolean visible) {
        nextArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
        setCharacterCardVisible(!visible);
    }

    @Override
    public void setArrowsVisible(boolean visible) {
        previousArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
        nextArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    @Override
    public void setCharacterCardVisible(boolean visible) {
        characterLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNextButtonVisible(boolean visible) {
        forthButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
