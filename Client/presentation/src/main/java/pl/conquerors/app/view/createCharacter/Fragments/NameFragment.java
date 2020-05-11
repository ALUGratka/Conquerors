package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseFragment;
import pl.conquerors.app.util.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.\
 */
public class NameFragment extends BaseFragment {

    @BindView(R.id.characterNickname)
    EditText characterNickname;

    //image views
    @BindView(R.id.nameFragmentSexImageView)
    ImageView sexImageView;

    @BindView(R.id.nameFragmentClassImageView)
    ImageView classImageView;

//    @BindView(R.id.nameFragmentHatImageView)
//    ImageView hatImageView;
//
//    @BindView(R.id.nameFragmentHairImageView)
//    ImageView hairImageView;

    @BindView(R.id.nameFragmentEyeColorImageView)
    ImageView eyeColorImageView;

    @BindView(R.id.nameFragmentBlouseImageView)
    ImageView blouseImageView;

    @BindView(R.id.nameFragmentPantsImageView)
    ImageView pantsImageView;

    @BindView(R.id.nameFragmentShoesImageView)
    ImageView shoesImageView;

    public NameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
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
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferenceUtil.setCharacterName(view.getContext(), getNickname());

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

    }

    public String getNickname() {
        String nickname = characterNickname.getText().toString();
        SharedPreferenceUtil.setCharacterName(this.getContext(), nickname);
        return nickname;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}
