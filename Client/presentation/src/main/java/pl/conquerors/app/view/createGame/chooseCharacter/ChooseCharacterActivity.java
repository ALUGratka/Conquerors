package pl.conquerors.app.view.createGame.chooseCharacter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

    @BindView(R.id.statisticsSexImageView)
    ImageView characterSexImage;

    @BindView(R.id.statisticsClassImageView)
    ImageView characterClassImage;

    @BindView(R.id.statisticsHairImageView)
    ImageView characterHairImage;

    @BindView(R.id.statisticsHatImageView)
    ImageView characterHatImage;

    @BindView(R.id.statisticsEyeColorImageView)
    ImageView characterEyeColorImage;

    @BindView(R.id.statisticsPantsImageView)
    ImageView characterPantsImage;

    @BindView(R.id.statisticsBlouseImageView)
    ImageView characterBlouseImage;

    @BindView(R.id.statisticsShoesImageView)
    ImageView characterShoesImage;

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
        SharedPreferenceUtil.setGameCharacter(this, characters.get(counter));
        Navigator.startChooseOpponent(this);
        Log.i("character", SharedPreferenceUtil.getGameCharacter(this).getmNickname());
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

        if(character.getmCharacterClass()==0){
            characterClassName.setText(getResources().getString(R.string.radio_bard));
        }
        else if(character.getmCharacterClass()==1){
            characterClassName.setText(getResources().getString(R.string.radio_thief));
        }
        else if(character.getmCharacterClass()==2) {
            characterClassName.setText(getResources().getString(R.string.radio_warrior));
        }
        else if(character.getmCharacterClass()==3){
            characterClassName.setText(getResources().getString(R.string.radio_wizard));
        }

        characterStrength.setText(String.valueOf(character.getmStrength()));
        characterCharisma.setText(String.valueOf(character.getmCharisma()));
        characterAgility.setText(String.valueOf(character.getmAgility()));
        characterIntelligence.setText(String.valueOf(character.getmIntelligence()));

        //character image
        int sex = character.getmSex();
        int hair = character.getmHair();
        int hat = character.getmHat();
        int eyeColor = character.getmEyeColor();
        int blouse = character.getmBlouse();
        int pants = character.getmPants();
        int shoes = character.getmShoes();
        setCurrentCharacterClassImage(character.getmCharacterClass());
        setCurrentSexAndAppearanceImages(sex, hair, hat, eyeColor, blouse, pants, shoes);

        setNextArrowVisible(true);
        setPreviousArrowsVisible(true);
        if(counter==0) setPreviousArrowsVisible(false);
        if(counter==characters.size()-1) setNextArrowVisible(false);
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

    void setWomanAppearance(int hair, int hat, int eyeColor,
                            int blouse, int pants, int shoes) {
        // to do hair
        // to do hat
        // eye color
        if (eyeColor == 0) {
            characterEyeColorImage.setImageResource(R.drawable.eyes_blue);
        } else if (eyeColor == 1) {
            characterEyeColorImage.setImageResource(R.drawable.eyes_brown);
        } else if (eyeColor == 2) {
            characterEyeColorImage.setImageResource(R.drawable.eyes_green);
        }
        // blouse
        if (blouse == 0) {
            characterBlouseImage.setImageResource(R.drawable.female_tshirt_blue);
        } else if (blouse == 1) {
            characterBlouseImage.setImageResource(R.drawable.female_tshirt_red);
        } else if (blouse == 2) {
            characterBlouseImage.setImageResource(R.drawable.female_tshirt_yellow);
        }
        // pants
        if (pants == 0) {
            characterPantsImage.setImageResource(R.drawable.female_pants_blue);
        } else if (pants == 1) {
            characterPantsImage.setImageResource(R.drawable.female_pants_gray);
        } else if (pants == 2) {
            characterPantsImage.setImageResource(R.drawable.female_pants_yellow);
        }
        // shoes
        if (shoes == 0) {
            characterShoesImage.setImageResource(R.drawable.female_shoes_pink);
        } else if (shoes == 1) {
            characterShoesImage.setImageResource(R.drawable.female_shoes_red);
        } else if (shoes == 2) {
            // no shoes >:(
            //shoesImageView.setImageResource();
        }
    }

    void setManAppearance(int hair, int hat, int eyeColor,
                          int blouse, int pants, int shoes) {
        // to do hair
        // to do hat
        // eye color
        if (eyeColor == 0) {
            characterEyeColorImage.setImageResource(R.drawable.eyes_blue);
        } else if (eyeColor == 1) {
            characterEyeColorImage.setImageResource(R.drawable.eyes_brown);
        } else if (eyeColor == 2) {
            characterEyeColorImage.setImageResource(R.drawable.eyes_green);
        }
        // blouse
        if (blouse == 0) {
            characterBlouseImage.setImageResource(R.drawable.male_tshirt_blue);
        } else if (blouse == 1) {
            characterBlouseImage.setImageResource(R.drawable.male_tshirt_red);
        } else if (blouse == 2) {
            characterBlouseImage.setImageResource(R.drawable.male_tshirt_yellow);
        }
        // pants
        if (pants == 0) {
            characterPantsImage.setImageResource(R.drawable.male_pants_brown);
        } else if (pants == 1) {
            characterPantsImage.setImageResource(R.drawable.male_pants_gray);
        } else if (pants == 2) {
            characterPantsImage.setImageResource(R.drawable.male_pants_red);
        }
        // shoes
        if (shoes == 0) {
            characterShoesImage.setImageResource(R.drawable.male_shoes_gray);
        } else if (shoes == 1) {
            characterShoesImage.setImageResource(R.drawable.male_shoes_red);
        } else if (shoes == 2) {
            characterShoesImage.setImageResource(R.drawable.male_shoes_white);
        }
    }

    void setCurrentSexAndAppearanceImages(int sex, int hair, int hat, int eyeColor,
                                          int blouse, int pants, int shoes) {
        if (sex == 1) { // woman
            characterSexImage.setImageResource(R.drawable.female_256);
            setWomanAppearance(hair, hat, eyeColor, blouse, pants, shoes);
        } else if (sex == 0) { // man
            characterSexImage.setImageResource(R.drawable.male_256);
            setManAppearance(hair, hat, eyeColor, blouse, pants, shoes);
        }
    }

    void setCurrentCharacterClassImage(int characterClassNum) {
        if (characterClassNum == 0) {
            characterClassImage.setImageResource(R.drawable.note);
        } else if (characterClassNum == 1) {
            characterClassImage.setImageResource(R.drawable.sword);
        } else if (characterClassNum == 2) {
            characterClassImage.setImageResource(R.drawable.warrior_shield);
        } else if (characterClassNum == 3) {
            characterClassImage.setImageResource(R.drawable.wand);
        }
    }
}
