package pl.conquerors.app.domain.interactor.createCharacter;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import pl.conquerors.app.domain.model.Character;
import rx.Observable;

public class CreateCharacterUseCase extends ResultUseCase<Void> {

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

    public CreateCharacterUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    private void setCharacterSkillPoints(int characterClass) {
        if (characterClass == 0) {
            mCharisma = 10;
            mIntelligence = 3;
            mAgility = 5;
            mStrength = 2;
        } else if (characterClass == 3) {
            mCharisma = 3;
            mIntelligence = 10;
            mAgility = 2;
            mStrength = 5;
        } else if (characterClass == 1) {
            mCharisma = 5;
            mIntelligence = 3;
            mAgility = 10;
            mStrength = 2;
        } else if (characterClass == 2) {
            mCharisma = 3;
            mIntelligence = 2;
            mAgility = 5;
            mStrength = 10;
        } else {
            System.out.println("Error : wrong class given");
        }
    }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }

    public void setData(final String nickname, final int sex, final int characterClass, final int hair, final int hat,
                        int eyeColor, int blouse, int pants, int shoes, int userId) {
        mLevel = 0;

        mNickname = nickname;
        mSex = sex;
        mCharacterClass = characterClass;
        mHair = hair;
        mHat = hat;
        mEyeColor = eyeColor;
        mBlouse = blouse;
        mPants = pants;
        mShoes = shoes;
        mUserId = userId;

        setCharacterSkillPoints(characterClass);

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
}
