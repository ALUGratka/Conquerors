package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.Character;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppearanceFragment extends Fragment {

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

    Character.Hair hairCurrent = Character.Hair.Blond;
    Character.EyeColor eyeColorCurrent = Character.EyeColor.Blue;
    Character.Hat hatCurrent = Character.Hat.Hat1;
    Character.Blouse blouseCurrent = Character.Blouse.BlouseBlue;
    Character.Pants pantsCurrent = Character.Pants.Pants1;
    Character.Shoes shoesCurrent = Character.Shoes.Shoes1;

    Character.Sex sex;
    Character.CharacterClass characterClass;

    public AppearanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int arg_sex = getArguments().getInt("sex");
        int arg_class = getArguments().getInt("class");

        if (arg_sex == 0) {
            sex = Character.Sex.Man;
        } else {
            sex = Character.Sex.Woman;
        }

        if (arg_class == 0) {
            characterClass = Character.CharacterClass.Bard;
        } else if (arg_class == 1) {
            characterClass = Character.CharacterClass.Thief;
        } else if (arg_class == 2) {
            characterClass = Character.CharacterClass.Warrior;
        } else {
            characterClass = Character.CharacterClass.Wizard;
        }

        return inflater.inflate(R.layout.fragment_appearance, container, false);
    }

    // handle hair change

    @OnClick(R.id.hair_back_arrow_button)
    public void onHairBackArrowButtonClicked() {
        if (hairCurrent.equals(Character.Hair.Blond)) {
            hairCurrent = Character.Hair.Black;
            // change view on canvas
        } else if (hairCurrent.equals(Character.Hair.Brown)) {
            hairCurrent = Character.Hair.Blond;
            // change view on canvas
        } else if (hairCurrent.equals(Character.Hair.Black)) {
            hairCurrent = Character.Hair.Brown;
            // change view on canvas
        }
    }

    @OnClick(R.id.hair_next_arrow_button)
    public void onHairNextArrowButtonClicked() {
        if (hairCurrent.equals(Character.Hair.Blond)) {
            hairCurrent = Character.Hair.Brown;
            // change view on canvas
        } else if (hairCurrent.equals(Character.Hair.Brown)) {
            hairCurrent = Character.Hair.Black;
            // change view on canvas
        } else if (hairCurrent.equals(Character.Hair.Black)) {
            hairCurrent = Character.Hair.Blond;
            // change view on canvas
        }
    }

    // handle eye color change

    @OnClick(R.id.eye_color_back_arrow_button)
    public void onEyeColorBackArrowButtonClicked() {
        if (eyeColorCurrent.equals(Character.EyeColor.Blue)) {
            eyeColorCurrent = Character.EyeColor.Green;
            // change view on canvas
        } else if (eyeColorCurrent.equals(Character.EyeColor.Brown)) {
            eyeColorCurrent = Character.EyeColor.Blue;
            // change view on canvas
        } else if (eyeColorCurrent.equals(Character.EyeColor.Green)) {
            eyeColorCurrent = Character.EyeColor.Brown;
            // change view on canvas
        }
    }

    @OnClick(R.id.eye_color_next_arrow_button)
    public void onEyeColorNextArrowButtonClicked() {
        if (eyeColorCurrent.equals(Character.EyeColor.Blue)) {
            eyeColorCurrent = Character.EyeColor.Brown;
            // change view on canvas
        } else if (eyeColorCurrent.equals(Character.EyeColor.Brown)) {
            eyeColorCurrent = Character.EyeColor.Green;
            // change view on canvas
        } else if (eyeColorCurrent.equals(Character.EyeColor.Green)) {
            eyeColorCurrent = Character.EyeColor.Blue;
            // change view on canvas
        }
    }

    // handle hat change

    @OnClick(R.id.hat_back_arrow_button)
    public void onHatBackArrowButtonClicked() {
        if (hatCurrent.equals(Character.Hat.Hat1)) {
            hatCurrent = Character.Hat.Hat3;
            // change view on canvas
        } else if (hatCurrent.equals(Character.Hat.Hat2)) {
            hatCurrent = Character.Hat.Hat1;
            // change view on canvas
        } else if (hatCurrent.equals(Character.Hat.Hat3)) {
            hatCurrent = Character.Hat.Hat2;
            // change view on canvas
        }
    }

    @OnClick(R.id.hat_next_arrow_button)
    public void onHatNextArrowButtonClicked() {
        if (hatCurrent.equals(Character.Hat.Hat1)) {
            hatCurrent = Character.Hat.Hat2;
            // change view on canvas
        } else if (hatCurrent.equals(Character.Hat.Hat2)) {
            hatCurrent = Character.Hat.Hat3;
            // change view on canvas
        } else if (hatCurrent.equals(Character.Hat.Hat3)) {
            hatCurrent = Character.Hat.Hat1;
            // change view on canvas
        }
    }

    // handle blouse change

    @OnClick(R.id.blouse_back_arrow_button)
    public void onBlouseBackArrowButtonClicked() {
        if (blouseCurrent.equals(Character.Blouse.BlouseBlue)) {
            blouseCurrent = Character.Blouse.BlouseYellow;
            // change view on canvas
        } else if (blouseCurrent.equals(Character.Blouse.BlouseRed)) {
            blouseCurrent = Character.Blouse.BlouseBlue;
            // change view on canvas
        } else if (blouseCurrent.equals(Character.Blouse.BlouseYellow)) {
            blouseCurrent = Character.Blouse.BlouseRed;
            // change view on canvas
        }
    }

    @OnClick(R.id.blouse_next_arrow_button)
    public void onBlouseNextArrowButtonClicked() {
        if (blouseCurrent.equals(Character.Blouse.BlouseBlue)) {
            blouseCurrent = Character.Blouse.BlouseYellow;
            // change view on canvas
        } else if (blouseCurrent.equals(Character.Blouse.BlouseRed)) {
            blouseCurrent = Character.Blouse.BlouseBlue;
            // change view on canvas
        } else if (blouseCurrent.equals(Character.Blouse.BlouseYellow)) {
            blouseCurrent = Character.Blouse.BlouseRed;
            // change view on canvas
        }
    }

    // handle pants change

    @OnClick(R.id.pants_back_arrow_button)
    public void onPantsBackArrowButtonClicked() {
        if (pantsCurrent.equals(Character.Pants.Pants1)) {
            pantsCurrent = Character.Pants.Pants3;
            // change view on canvas
        } else if (pantsCurrent.equals(Character.Pants.Pants2)) {
            pantsCurrent = Character.Pants.Pants1;
            // change view on canvas
        } else if (pantsCurrent.equals(Character.Pants.Pants3)) {
            pantsCurrent = Character.Pants.Pants2;
            // change view on canvas
        }
    }

    @OnClick(R.id.pants_next_arrow_button)
    public void onPantsNextArrowButtonClicked() {
        if (pantsCurrent.equals(Character.Pants.Pants1)) {
            pantsCurrent = Character.Pants.Pants2;
            // change view on canvas
        } else if (pantsCurrent.equals(Character.Pants.Pants2)) {
            pantsCurrent = Character.Pants.Pants3;
            // change view on canvas
        } else if (pantsCurrent.equals(Character.Pants.Pants3)) {
            pantsCurrent = Character.Pants.Pants1;
            // change view on canvas
        }
    }

    // handle shoes change

    @OnClick(R.id.boots_back_arrow_button)
    public void onShoesBackArrowButtonClicked() {
        if (shoesCurrent.equals(Character.Shoes.Shoes1)) {
            shoesCurrent = Character.Shoes.Shoes3;
            // change view on canvas
        } else if (shoesCurrent.equals(Character.Shoes.Shoes2)) {
            pantsCurrent = Character.Pants.Pants1;
            // change view on canvas
        } else if (shoesCurrent.equals(Character.Shoes.Shoes3)) {
            pantsCurrent = Character.Pants.Pants2;
            // change view on canvas
        }
    }

    @OnClick(R.id.boots_next_arrow_button)
    public void onShoesNextArrowButtonClicked() {
        if (shoesCurrent.equals(Character.Shoes.Shoes1)) {
            shoesCurrent = Character.Shoes.Shoes2;
            // change view on canvas
        } else if (shoesCurrent.equals(Character.Shoes.Shoes2)) {
            pantsCurrent = Character.Pants.Pants3;
            // change view on canvas
        } else if (shoesCurrent.equals(Character.Shoes.Shoes3)) {
            pantsCurrent = Character.Pants.Pants1;
            // change view on canvas
        }
    }

    // getters

    public Character.Hair getHairCurrent() {
        return hairCurrent;
    }

    public Character.EyeColor getEyeColorCurrent() {
        return eyeColorCurrent;
    }

    public Character.Hat getHatCurrent() {
        return hatCurrent;
    }

    public Character.Blouse getBlouseCurrent() {
        return blouseCurrent;
    }

    public Character.Pants getPantsCurrent() {
        return pantsCurrent;
    }

    public Character.Shoes getShoesCurrent() {
        return shoesCurrent;
    }
}
