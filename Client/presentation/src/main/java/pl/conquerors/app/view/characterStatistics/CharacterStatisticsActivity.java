package pl.conquerors.app.view.characterStatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.characterStatistics.CharacterStatisticsUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.CharacterStatistics;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class CharacterStatisticsActivity extends BaseActivity implements CharacterStatisticsView{

    @BindView(R.id.displayCurrentCharacterStrength)
    TextView strength;

    @BindView(R.id.displayCurrentCharacterCharisma)
    TextView charisma;

    @BindView(R.id.displayCurrentCharacterAgility)
    TextView agility;

    @BindView(R.id.displayCurrentCharacterIntelligence)
    TextView intelligence;

    @BindView(R.id.displayCharacterNicknameText)
    TextView nickname;

    @BindView(R.id.displayCharacterClassText)
    TextView characterClass;

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

    CharacterStatisticsPresenter mCharacterStatisticsPresenter;
    static private int characterId;

    public static Intent getStartingIntents(Context context, final int id) {
        characterId = id;
        return new Intent(context, CharacterStatisticsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        CharacterStatisticsUseCase statisticsUseCase = new CharacterStatisticsUseCase(new AndroidComposedScheduler());

        mCharacterStatisticsPresenter = new CharacterStatisticsPresenter(statisticsUseCase);
        mCharacterStatisticsPresenter.setmView(this);
        mCharacterStatisticsPresenter.getCharacterStatistic();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public int getCharacterId() {
        return characterId;
    }

    @Override
    public void updateStatistics(Character character) {
        strength.setText(String.valueOf(character.getmStrength()));
        charisma.setText(String.valueOf(character.getmCharisma()));
        agility.setText(String.valueOf(character.getmAgility()));
        intelligence.setText(String.valueOf(character.getmIntelligence()));
        nickname.setText(character.getmNickname());
        setCurrentCharacterClassImage(character.getmCharacterClass());

        int sex = character.getmSex();
        int hair = character.getmHair();
        int hat = character.getmHat();
        int eyeColor = character.getmEyeColor();
        int blouse = character.getmBlouse();
        int pants = character.getmPants();
        int shoes = character.getmShoes();

        setCurrentSexAndAppearanceImages(sex, hair, hat, eyeColor, blouse, pants, shoes);

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
            characterClass.setText("BARD");
        } else if (characterClassNum == 1) {
            characterClassImage.setImageResource(R.drawable.sword);
            characterClass.setText("THIEF");
        } else if (characterClassNum == 2) {
            characterClassImage.setImageResource(R.drawable.warrior_shield);
            characterClass.setText("WARRIOR");
        } else if (characterClassNum == 3) {
            characterClassImage.setImageResource(R.drawable.wand);
            characterClass.setText("WIZARD");
        }
    }

}
