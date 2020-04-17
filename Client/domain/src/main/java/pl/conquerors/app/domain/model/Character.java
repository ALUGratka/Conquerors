package pl.conquerors.app.domain.model;

public class Character {
    /**
     * enums
     **/
    private enum Sex {
        Man, Woman
    }

    private enum CharacterClass {
        Bard, Warrior, Thief, Wizard
    }

    private enum Hair {
        Blond, Brown, Black
    }

    private enum EyeColor {
        Blue, Brown, Green
    }

    private enum Hat {
        Hat1, Hat2, Hat3
    }

    private enum Blouse {
        BlouseBlue, BlouseRed, BlouseYellow
    }

    private enum Pants {
        Pants1, Pants2, Pants3
    }

    private enum Shoes {
        Shoes1, Shoes2, Shoes3
    }

    /**
     * variables
     **/

    private int mId;
    private int mLevel;
    private int mCharisma;
    private int mIntelligence;
    private int mAgility;
    private int mStrength;
    private String mNickname;
    private Sex mSex;
    private CharacterClass mCharacterClass;
    private Hair mHair;
    private EyeColor mEyeColor;
    private Blouse mBlouse;
    private Pants mPants;
    private Shoes mShoes;
    private int mUserId;

    /**
     * getters
     **/

    public int getmId() {
        return mId;
    }

    public int getmLevel() {
        return mLevel;
    }

    public int getmCharisma() {
        return mCharisma;
    }

    public int getmIntelligence() {
        return mIntelligence;
    }

    public int getmAgility() {
        return mAgility;
    }

    public int getmStrength() {
        return mStrength;
    }

    public String getmNickname() {
        return mNickname;
    }

    public Sex getmSex() {
        return mSex;
    }

    public CharacterClass getmCharacterClass() {
        return mCharacterClass;
    }

    public Hair getmHair() {
        return mHair;
    }

    public EyeColor getmEyeColor() {
        return mEyeColor;
    }

    public Blouse getmBlouse() {
        return mBlouse;
    }

    public Pants getmPants() {
        return mPants;
    }

    public Shoes getmShoes() {
        return mShoes;
    }

    public int getmUserId() {
        return mUserId;
    }


    /**
     * setters
     **/

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public void setmNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public void setmLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public void setmCharisma(int mCharisma) {
        this.mCharisma = mCharisma;
    }

    public void setmIntelligence(int mIntelligence) {
        this.mIntelligence = mIntelligence;
    }

    public void setmAgility(int mAgility) {
        this.mAgility = mAgility;
    }

    public void setmStrength(int mStrength) {
        this.mStrength = mStrength;
    }

    public void setmSex(int mSex) {
        if (mSex == 0) {
            this.mSex = Sex.Man;
        } else if (mSex == 1) {
            this.mSex = Sex.Woman;
        } else {
            System.out.println("Invalid number given");
        }
    }

    public void setmCharacterClass(int mCharacterClass) {
        if (mCharacterClass == 0) {
            this.mCharacterClass = CharacterClass.Bard;
        } else if (mCharacterClass == 1) {
            this.mCharacterClass = CharacterClass.Warrior;
        } else if (mCharacterClass == 2) {
            this.mCharacterClass = CharacterClass.Thief;
        } else if (mCharacterClass == 3) {
            this.mCharacterClass = CharacterClass.Wizard;
        } else {
            System.out.println("Invalid number given");
        }
    }

    public void setmHair(int mHair) {
        if (mHair == 0) {
            this.mHair = Hair.Blond;
        } else if (mHair == 1) {
            this.mHair = Hair.Brown;
        } else if (mHair == 2) {
            this.mHair = Hair.Black;
        } else {
            System.out.println("Invalid number given");
        }
    }

    public void setmEyeColor(int mEyeColor) {
        if (mEyeColor == 0) {
            this.mEyeColor = EyeColor.Blue;
        } else if (mEyeColor == 1) {
            this.mEyeColor = EyeColor.Brown;
        } else if (mEyeColor == 2) {
            this.mEyeColor = EyeColor.Green;
        } else {
            System.out.println("Invalid number given");
        }
    }

    public void setmBlouse(int mBlouse) {
        if (mBlouse == 0) {
            this.mBlouse = Blouse.BlouseBlue;
        } else if (mBlouse == 1) {
            this.mBlouse = Blouse.BlouseRed;
        } else if (mBlouse == 2) {
            this.mBlouse = Blouse.BlouseYellow;
        } else {
            System.out.println("Invalid number given");
        }
    }

    public void setmPants(int mPants) {
        if (mPants == 0) {
            this.mPants = Pants.Pants1;
        } else if (mPants == 1) {
            this.mPants = Pants.Pants2;
        } else if (mPants == 2) {
            this.mPants = Pants.Pants3;
        } else {
            System.out.println("Invalid number given");
        }
    }

    public void setmShoes(int mShoes) {
        if (mShoes == 0) {
            this.mShoes = Shoes.Shoes1;
        } else if (mShoes == 1) {
            this.mShoes = Shoes.Shoes2;
        } else if (mShoes == 2) {
            this.mShoes = Shoes.Shoes3;
        } else {
            System.out.println("Invalid number given");
        }
    }

}
