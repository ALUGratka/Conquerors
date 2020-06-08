package pl.conquerors.app.domain.model;

public class Character {
    private int mId;
    private int mLevel;
    private int mCharisma;
    private int mIntelligence;
    private int mAgility;
    private int mStrength;
    private String mNickname;
    private int mSex;
    private int mCharacterClass;
    private int mHair;
    private int mHat;
    private int mEyeColor;
    private int mBlouse;
    private int mPants;
    private int mShoes;
    private int mUserId;
    private int mSkillPoints;

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

    public int getmSex() {
        return mSex;
    }

    public int getmCharacterClass() {
        return mCharacterClass;
    }

    public int getmHair() {
        return mHair;
    }

    public int getmHat() {
        return mHat;
    }

    public int getmEyeColor() {
        return mEyeColor;
    }

    public int getmBlouse() {
        return mBlouse;
    }

    public int getmPants() {
        return mPants;
    }

    public int getmShoes() {
        return mShoes;
    }

    public int getmUserId() {
        return mUserId;
    }

    public int getmSkillPoints() {return mSkillPoints;}


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

    public void setmSkillPoints(int mSkillPoints) {this.mSkillPoints = mSkillPoints;}

    public void setmSex(int mSex) { this.mSex=mSex;
//        if (mSex == 0) {
//            this.mSex = Sex.Man;
//        } else if (mSex == 1) {
//            this.mSex = Sex.Woman;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmCharacterClass(int mCharacterClass) {
        this.mCharacterClass=mCharacterClass;
//        if (mCharacterClass == 0) {
//            this.mCharacterClass = CharacterClass.Bard;
//        } else if (mCharacterClass == 1) {
//            this.mCharacterClass = CharacterClass.Thief;
//        } else if (mCharacterClass == 2) {
//            this.mCharacterClass = CharacterClass.Warrior;
//        } else if (mCharacterClass == 3) {
//            this.mCharacterClass = CharacterClass.Wizard;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmHair(int mHair) {
        this.mHair=mHair;
//        if (mHair == 0) {
//            this.mHair = Hair.Blond;
//        } else if (mHair == 1) {
//            this.mHair = Hair.Brown;
//        } else if (mHair == 2) {
//            this.mHair = Hair.Black;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmHaT(int mHat){
        this.mHat = mHat;
    }

    public void setmHat(int mHat) {
        this.mHat=mHat;
//        if (mHat == 0) {
//            this.mHat = Hat.Hat1;
//        } else if (mHat == 1) {
//            this.mHat = Hat.Hat2;
//        } else if (mHat == 2) {
//            this.mHat = Hat.Hat3;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmEyeColor(int mEyeColor) {
        this.mEyeColor=mEyeColor;
//        if (mEyeColor == 0) {
//            this.mEyeColor = EyeColor.Blue;
//        } else if (mEyeColor == 1) {
//            this.mEyeColor = EyeColor.Brown;
//        } else if (mEyeColor == 2) {
//            this.mEyeColor = EyeColor.Green;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmBlouse(int mBlouse) {
        this.mBlouse=mBlouse;
//        if (mBlouse == 0) {
//            this.mBlouse = Blouse.BlouseBlue;
//        } else if (mBlouse == 1) {
//            this.mBlouse = Blouse.BlouseRed;
//        } else if (mBlouse == 2) {
//            this.mBlouse = Blouse.BlouseYellow;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmPants(int mPants) {
        this.mPants=mPants;
//        if (mPants == 0) {
//            this.mPants = Pants.Pants1;
//        } else if (mPants == 1) {
//            this.mPants = Pants.Pants2;
//        } else if (mPants == 2) {
//            this.mPants = Pants.Pants3;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

    public void setmShoes(int mShoes) {
        this.mShoes=mShoes;
//        if (mShoes == 0) {
//            this.mShoes = Shoes.Shoes1;
//        } else if (mShoes == 1) {
//            this.mShoes = Shoes.Shoes2;
//        } else if (mShoes == 2) {
//            this.mShoes = Shoes.Shoes3;
//        } else {
//            System.out.println("Invalid number given");
//        }
    }

}
