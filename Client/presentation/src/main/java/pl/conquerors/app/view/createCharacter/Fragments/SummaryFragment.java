package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseFragment;
import pl.conquerors.app.util.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends BaseFragment {

    @BindView(R.id.displayCharacterNicknameText)
    TextView nickname;

    @BindView(R.id.displayCharacterClassText)
    TextView characterClassText;

    @BindView(R.id.displayClassStrength)
    TextView strength;

    @BindView(R.id.displayCharacterCharisma)
    TextView charisma;

    @BindView(R.id.displayCharacterAgility)
    TextView agility;

    @BindView(R.id.displayCharacterIntelligence)
    TextView intelligence;

    //image views
    @BindView(R.id.summaryFragmentSexImageView)
    ImageView sexImageView;

    @BindView(R.id.summaryFragmentClassImageView)
    ImageView classImageView;

//    @BindView(R.id.summaryFragmentHatImageView)
//    ImageView hatImageView;
//
//    @BindView(R.id.summaryFragmentHairImageView)
//    ImageView hairImageView;

    @BindView(R.id.summaryFragmentEyeColorImageView)
    ImageView eyeColorImageView;

    @BindView(R.id.summaryFragmentBlouseImageView)
    ImageView blouseImageView;

    @BindView(R.id.summaryFragmentPantsImageView)
    ImageView pantsImageView;

    @BindView(R.id.summaryFragmentShoesImageView)
    ImageView shoesImageView;

    public SummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    void setWomanAppearance(int hair, int hat, int eyeColor,
                            int blouse, int pants, int shoes) {
        // to do hair
        // to do hat
        // eye color
        if (eyeColor == 0) {
            eyeColorImageView.setImageResource(R.drawable.eyes_blue);
        } else if (eyeColor == 1) {
            eyeColorImageView.setImageResource(R.drawable.eyes_brown);
        } else if (eyeColor == 2) {
            eyeColorImageView.setImageResource(R.drawable.eyes_green);
        }
        // blouse
        if (blouse == 0) {
            blouseImageView.setImageResource(R.drawable.female_tshirt_blue);
        } else if (blouse == 1) {
            blouseImageView.setImageResource(R.drawable.female_tshirt_red);
        } else if (blouse == 2) {
            blouseImageView.setImageResource(R.drawable.female_tshirt_yellow);
        }
        // pants
        if (pants == 0) {
            pantsImageView.setImageResource(R.drawable.female_pants_blue);
        } else if (pants == 1) {
            pantsImageView.setImageResource(R.drawable.female_pants_gray);
        } else if (pants == 2) {
            pantsImageView.setImageResource(R.drawable.female_pants_yellow);
        }
        // shoes
        if (shoes == 0) {
            shoesImageView.setImageResource(R.drawable.female_shoes_pink);
        } else if (shoes == 1) {
            shoesImageView.setImageResource(R.drawable.female_shoes_red);
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
            eyeColorImageView.setImageResource(R.drawable.eyes_blue);
        } else if (eyeColor == 1) {
            eyeColorImageView.setImageResource(R.drawable.eyes_brown);
        } else if (eyeColor == 2) {
            eyeColorImageView.setImageResource(R.drawable.eyes_green);
        }
        // blouse
        if (blouse == 0) {
            blouseImageView.setImageResource(R.drawable.male_tshirt_blue);
        } else if (blouse == 1) {
            blouseImageView.setImageResource(R.drawable.male_tshirt_red);
        } else if (blouse == 2) {
            blouseImageView.setImageResource(R.drawable.male_tshirt_yellow);
        }
        // pants
        if (pants == 0) {
            pantsImageView.setImageResource(R.drawable.male_pants_brown);
        } else if (pants == 1) {
            pantsImageView.setImageResource(R.drawable.male_pants_gray);
        } else if (pants == 2) {
            pantsImageView.setImageResource(R.drawable.male_pants_red);
        }
        // shoes
        if (shoes == 0) {
            shoesImageView.setImageResource(R.drawable.male_shoes_gray);
        } else if (shoes == 1) {
            shoesImageView.setImageResource(R.drawable.male_shoes_red);
        } else if (shoes == 2) {
            shoesImageView.setImageResource(R.drawable.male_shoes_white);
        }
    }

    void setCurrentSexAndAppearanceImages(int sex, int hair, int hat, int eyeColor,
                                          int blouse, int pants, int shoes) {
        if (sex == 1) { // woman
            sexImageView.setImageResource(R.drawable.female_256);
            setWomanAppearance(hair, hat, eyeColor, blouse, pants, shoes);
        } else if (sex == 0) { // man
            sexImageView.setImageResource(R.drawable.male_256);
            setManAppearance(hair, hat, eyeColor, blouse, pants, shoes);
        }
    }

    void setCurrentCharacterClassImage(int characterClass) {
        if (characterClass == 0) {
            classImageView.setImageResource(R.drawable.note);
        } else if (characterClass == 1) {
            classImageView.setImageResource(R.drawable.sword);
        } else if (characterClass == 2) {
            classImageView.setImageResource(R.drawable.warrior_shield);
        } else if (characterClass == 3) {
            classImageView.setImageResource(R.drawable.wand);
        }
    }

    void setCurrentStatisticsAndNickname(String arg_nickname, int arg_class){
        nickname.setText(arg_nickname);

        if (arg_class == 0) {
            characterClassText.setText(getString(R.string.radio_bard));
            strength.setText("2");
            charisma.setText("10");
            agility.setText("5");
            intelligence.setText("3");
        } else if (arg_class == 1) {
            characterClassText.setText(getString(R.string.radio_thief));
            strength.setText("2");
            charisma.setText("5");
            agility.setText("10");
            intelligence.setText("3");
        } else if (arg_class == 2) {
            characterClassText.setText(getString(R.string.radio_warrior));
            strength.setText("10");
            charisma.setText("3");
            agility.setText("5");
            intelligence.setText("2");
        } else {
            characterClassText.setText(getString(R.string.radio_wizard));
            strength.setText("5");
            charisma.setText("3");
            agility.setText("2");
            intelligence.setText("10");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int sex = SharedPreferenceUtil.getCharacterSex(view.getContext());
        int characterClass = SharedPreferenceUtil.getCharacterClass(view.getContext());
        int hair = SharedPreferenceUtil.getCharacterHair(view.getContext());
        int hat = SharedPreferenceUtil.getCharacterHat(view.getContext());
        int eyeColor = SharedPreferenceUtil.getCharacterEyeColor(view.getContext());
        int blouse = SharedPreferenceUtil.getCharacterBlouse(view.getContext());
        int pants = SharedPreferenceUtil.getCharacterPants(view.getContext());
        int shoes = SharedPreferenceUtil.getCharacterShoes(view.getContext());

        setCurrentSexAndAppearanceImages(sex, hair, hat, eyeColor, blouse, pants, shoes);
        setCurrentCharacterClassImage(characterClass);

        String arg_nickname = SharedPreferenceUtil.getCharacterName(view.getContext());
        int arg_class = SharedPreferenceUtil.getCharacterClass(view.getContext());

        setCurrentStatisticsAndNickname(arg_nickname, arg_class);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
