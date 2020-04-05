package pl.conquerors.app.view.createCharacter;

import android.graphics.Canvas;
import android.graphics.Rect;

public class CreateCharacterPlayer implements CreateCharacterDisplay {

    // to represent player
    private Rect rectangle;

    private String nickname;

    private enum Sex {
        Man, Woman
    }

    private enum CharacterClass {
        Bard, Warrior, Thief, Wizard
    }

    private enum AppearanceHair {
        Blond, Brown, Black
    }

    private enum AppearanceEyeColor {
        Blue, Brown, Green
    }

    private enum AppearanceHat {
        Hat1, Hat2
    }

    private enum AppearanceBlouse {
        Blouse1, Blouse2
    }

    private enum AppearancePants {
        Pants1, Pants2
    }

    private enum AppearanceShoes {
        Shoe1, Shoe2
    }


    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }
}
