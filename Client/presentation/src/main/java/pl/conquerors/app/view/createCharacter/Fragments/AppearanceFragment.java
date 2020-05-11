package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseFragment;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.util.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppearanceFragment extends BaseFragment {

    //image views
    @BindView(R.id.appearanceFragmentSexImageView)
    ImageView sexImageView;

    @BindView(R.id.appearanceFragmentClassImageView)
    ImageView classImageView;

//    @BindView(R.id.appearanceFragmentHatImageView)
//    ImageView hatImageView;
//
//    @BindView(R.id.appearanceFragmentHairImageView)
//    ImageView hairImageView;

    @BindView(R.id.appearanceFragmentEyeColorImageView)
    ImageView eyeColorImageView;

    @BindView(R.id.appearanceFragmentBlouseImageView)
    ImageView blouseImageView;

    @BindView(R.id.appearanceFragmentPantsImageView)
    ImageView pantsImageView;

    @BindView(R.id.appearanceFragmentShoesImageView)
    ImageView shoesImageView;

    // buttons
    @BindView(R.id.hair_back_arrow_button)
    Button hairBackArrowButton;

    @BindView(R.id.hair_next_arrow_button)
    Button hairNextArrowButton;

    @BindView(R.id.eye_color_back_arrow_button)
    Button eyeColorBackArrowButton;

    @BindView(R.id.eye_color_next_arrow_button)
    Button eyeColorNextArrowButton;

    @BindView(R.id.hat_back_arrow_button)
    Button hatBackArrowButton;

    @BindView(R.id.hat_next_arrow_button)
    Button hatNextArrowButton;

    @BindView(R.id.blouse_back_arrow_button)
    Button blouseBackArrowButton;

    @BindView(R.id.blouse_next_arrow_button)
    Button blouseNextArrowButton;

    @BindView(R.id.pants_back_arrow_button)
    Button pantsBackArrowButton;

    @BindView(R.id.pants_next_arrow_button)
    Button pantsNextArrowButton;

    @BindView(R.id.boots_back_arrow_button)
    Button shoesBackArrowButton;

    @BindView(R.id.boots_next_arrow_button)
    Button shoesNextArrowButton;

    int hairCurrent = 0;
    int eyeColorCurrent = 0;
    int hatCurrent = 0;
    int blouseCurrent = 0;
    int pantsCurrent = 0;
    int shoesCurrent = 0;

    int sex;

    public AppearanceFragment() {
        // Required empty public constructor
    }

    void setCurrentSexImage(int sex){
        if (sex == 1) {
            sexImageView.setImageResource(R.drawable.female_256);
        } else if (sex == 0) {
            sexImageView.setImageResource(R.drawable.male_256);
        }
    }

    void setCurrentCharacterClassImage(int characterClass){
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

        sex = SharedPreferenceUtil.getCharacterSex(view.getContext());
        int characterClass = SharedPreferenceUtil.getCharacterClass(view.getContext());

        setCurrentSexImage(sex);
        setCurrentCharacterClassImage(characterClass);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appearance, container, false);
    }


    @OnClick(R.id.hair_back_arrow_button)
    public void onHairBackArrowButtonClicked() {
        if (hairCurrent == 0) {
            SharedPreferenceUtil.setCharacterHair(this.getContext(), 2);
            hairCurrent = 2;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hairCurrent == 1) {
            SharedPreferenceUtil.setCharacterHair(this.getContext(), 0);
            hairCurrent = 0;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hairCurrent == 2) {
            SharedPreferenceUtil.setCharacterHair(this.getContext(), 1);
            hairCurrent = 1;
            //hairImageView.setImageResource(R.drawable.);
        }
    }

    @OnClick(R.id.hair_next_arrow_button)
    public void onHairNextArrowButtonClicked() {
        if (hairCurrent == 0) {
            SharedPreferenceUtil.setCharacterHair(this.getContext(), 1);
            hairCurrent = 1;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hairCurrent == 1) {
            SharedPreferenceUtil.setCharacterHair(this.getContext(), 2);
            hairCurrent = 2;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hairCurrent == 2) {
            SharedPreferenceUtil.setCharacterHair(this.getContext(), 0);
            hairCurrent = 0;
            //hairImageView.setImageResource(R.drawable.);
        }
    }

    @OnClick(R.id.eye_color_back_arrow_button)
    public void onEyeColorBackArrowButtonClicked() {
        if (eyeColorCurrent == 0) {
            SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 2);
            eyeColorCurrent = 2;
            eyeColorImageView.setImageResource(R.drawable.eyes_green);
        } else if (eyeColorCurrent == 1) {
            SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 0);
            eyeColorCurrent = 0;
            eyeColorImageView.setImageResource(R.drawable.eyes_blue);
        } else if (eyeColorCurrent == 2) {
            SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 1);
            eyeColorCurrent = 1;
            eyeColorImageView.setImageResource(R.drawable.eyes_brown);
        }
    }

    @OnClick(R.id.eye_color_next_arrow_button)
    public void onEyeColorNextArrowButtonClicked() {
        if (eyeColorCurrent == 0) {
            SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 1);
            eyeColorCurrent = 1;
            eyeColorImageView.setImageResource(R.drawable.eyes_brown);
        } else if (eyeColorCurrent == 1) {
            SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 2);
            eyeColorCurrent = 2;
            eyeColorImageView.setImageResource(R.drawable.eyes_green);
        } else if (eyeColorCurrent == 2) {
            SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 0);
            eyeColorCurrent = 0;
            eyeColorImageView.setImageResource(R.drawable.eyes_blue);
        }
    }

    // handle hat change

    @OnClick(R.id.hat_back_arrow_button)
    public void onHatBackArrowButtonClicked() {
        if (hatCurrent == 0) {
            SharedPreferenceUtil.setCharacterHat(this.getContext(), 2);
            hatCurrent = 2;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hatCurrent == 1) {
            SharedPreferenceUtil.setCharacterHat(this.getContext(), 0);
            hatCurrent = 0;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hatCurrent == 2) {
            SharedPreferenceUtil.setCharacterHat(this.getContext(), 1);
            hatCurrent = 1;
            //hairImageView.setImageResource(R.drawable.);
        }
    }

    @OnClick(R.id.hat_next_arrow_button)
    public void onHatNextArrowButtonClicked() {
        if (hatCurrent == 0) {
            SharedPreferenceUtil.setCharacterHat(this.getContext(), 1);
            hatCurrent = 1;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hatCurrent == 1) {
            SharedPreferenceUtil.setCharacterHat(this.getContext(), 2);
            hatCurrent = 2;
            //hairImageView.setImageResource(R.drawable.);
        } else if (hatCurrent == 2) {
            SharedPreferenceUtil.setCharacterHat(this.getContext(), 0);
            hatCurrent = 0;
            //hairImageView.setImageResource(R.drawable.);
        }
    }

    // handle blouse change

    @OnClick(R.id.blouse_back_arrow_button)
    public void onBlouseBackArrowButtonClicked() {
        if (blouseCurrent == 0) {
            SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 2);
            blouseCurrent = 2;
            if(sex == 1){ // woman
                blouseImageView.setImageResource(R.drawable.female_tshirt_yellow);
            }else{  //man
                blouseImageView.setImageResource(R.drawable.male_tshirt_yellow);
            }
        } else if (blouseCurrent == 1) {
            SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 0);
            blouseCurrent = 0;
            if(sex == 1){ // woman
                blouseImageView.setImageResource(R.drawable.female_tshirt_blue);
            }else{  //man
                blouseImageView.setImageResource(R.drawable.male_tshirt_blue);
            }
        } else if (blouseCurrent == 2) {
            SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 1);
            blouseCurrent = 1;
            if(sex == 1){ // woman
                blouseImageView.setImageResource(R.drawable.female_tshirt_red);
            }else{  //man
                blouseImageView.setImageResource(R.drawable.male_tshirt_red);
            }
        }
    }

    @OnClick(R.id.blouse_next_arrow_button)
    public void onBlouseNextArrowButtonClicked() {
        if (blouseCurrent == 0) {
            SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 1);
            blouseCurrent = 1;
            if(sex == 1){ // woman
                blouseImageView.setImageResource(R.drawable.female_tshirt_red);
            }else{  //man
                blouseImageView.setImageResource(R.drawable.male_tshirt_red);
            }
        } else if (blouseCurrent == 1) {
            SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 2);
            blouseCurrent = 2;
            if(sex == 1){ // woman
                blouseImageView.setImageResource(R.drawable.female_tshirt_yellow);
            }else{  //man
                blouseImageView.setImageResource(R.drawable.male_tshirt_yellow);
            }
        } else if (blouseCurrent == 2) {
            SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 0);
            blouseCurrent = 0;
            if(sex == 1){ // woman
                blouseImageView.setImageResource(R.drawable.female_tshirt_blue);
            }else{  //man
                blouseImageView.setImageResource(R.drawable.male_tshirt_blue);
            }
        }
    }

    // handle pants change

    @OnClick(R.id.pants_back_arrow_button)
    public void onPantsBackArrowButtonClicked() {
        if (pantsCurrent == 0) {
            SharedPreferenceUtil.setCharacterPants(this.getContext(), 2);
            pantsCurrent = 2;
            if(sex == 1){ // woman
                pantsImageView.setImageResource(R.drawable.female_pants_gray);
            }else{  //man
                pantsImageView.setImageResource(R.drawable.male_pants_gray);
            }
        } else if (pantsCurrent == 1) {
            SharedPreferenceUtil.setCharacterPants(this.getContext(), 0);
            pantsCurrent = 0;
            if(sex == 1){ // woman
                pantsImageView.setImageResource(R.drawable.female_pants_blue);
            }else{  //man
                pantsImageView.setImageResource(R.drawable.male_pants_brown);
            }
        } else if (pantsCurrent == 2) {
            SharedPreferenceUtil.setCharacterPants(this.getContext(), 1);
            pantsCurrent = 1;
            if(sex == 1){ // woman
                pantsImageView.setImageResource(R.drawable.female_pants_yellow);
            }else{  //man
                pantsImageView.setImageResource(R.drawable.male_pants_red);
            }
        }
    }

    @OnClick(R.id.pants_next_arrow_button)
    public void onPantsNextArrowButtonClicked() {
        if (pantsCurrent == 0) {
            SharedPreferenceUtil.setCharacterPants(this.getContext(), 1);
            pantsCurrent = 1;
            if(sex == 1){ // woman
                pantsImageView.setImageResource(R.drawable.female_pants_gray);
            }else{  //man
                pantsImageView.setImageResource(R.drawable.male_pants_gray);
            }
        } else if (pantsCurrent == 1) {
            SharedPreferenceUtil.setCharacterPants(this.getContext(), 2);
            pantsCurrent = 2;
            if(sex == 1){ // woman
                pantsImageView.setImageResource(R.drawable.female_pants_yellow);
            }else{  //man
                pantsImageView.setImageResource(R.drawable.male_pants_red);
            }
        } else if (pantsCurrent == 2) {
            SharedPreferenceUtil.setCharacterPants(this.getContext(), 0);
            pantsCurrent = 0;
            if(sex == 1){ // woman
                pantsImageView.setImageResource(R.drawable.female_pants_blue);
            }else{  //man
                pantsImageView.setImageResource(R.drawable.male_pants_brown);
            }
        }
    }

    // handle shoes change

    @OnClick(R.id.boots_back_arrow_button)
    public void onShoesBackArrowButtonClicked() {
        if (shoesCurrent == 0) {
            SharedPreferenceUtil.setCharacterShoes(this.getContext(), 2);
            shoesCurrent = 2;
            if(sex == 1){ // woman
                // NO SHOES!!!! >:(
                //shoesImageView.setImageResource(R.drawable.female_shoes_pink);
            }else{  //man
                shoesImageView.setImageResource(R.drawable.male_shoes_white);
            }
        } else if (shoesCurrent == 1) {
            SharedPreferenceUtil.setCharacterShoes(this.getContext(), 0);
            pantsCurrent = 0;
            if(sex == 1){ // woman
                shoesImageView.setImageResource(R.drawable.female_shoes_pink);
            }else{  //man
                shoesImageView.setImageResource(R.drawable.male_shoes_gray);
            }
        } else if (shoesCurrent == 2) {
            SharedPreferenceUtil.setCharacterShoes(this.getContext(), 1);
            pantsCurrent = 1;
            if(sex == 1){ // woman
                shoesImageView.setImageResource(R.drawable.female_shoes_red);
            }else{  //man
                shoesImageView.setImageResource(R.drawable.male_shoes_red);
            }
        }
    }

    @OnClick(R.id.boots_next_arrow_button)
    public void onShoesNextArrowButtonClicked() {
        if (shoesCurrent == 0) {
            SharedPreferenceUtil.setCharacterShoes(this.getContext(), 1);
            shoesCurrent = 1;
            if(sex == 1){ // woman
                shoesImageView.setImageResource(R.drawable.female_shoes_red);
            }else{  //man
                shoesImageView.setImageResource(R.drawable.male_shoes_red);
            }
        } else if (shoesCurrent == 1) {
            SharedPreferenceUtil.setCharacterShoes(this.getContext(), 2);
            pantsCurrent = 2;
            if(sex == 1){ // woman
                // NO SHOES!!!! >:(
                //shoesImageView.setImageResource(R.drawable.female_shoes_pink);
            }else{  //man
                shoesImageView.setImageResource(R.drawable.male_shoes_white);
            }
        } else if (shoesCurrent == 2) {
            SharedPreferenceUtil.setCharacterShoes(this.getContext(), 0);
            pantsCurrent = 0;
            if(sex == 1){ // woman
                shoesImageView.setImageResource(R.drawable.female_shoes_pink);
            }else{  //man
                shoesImageView.setImageResource(R.drawable.male_shoes_gray);
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
