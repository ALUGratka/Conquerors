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
    private Character.Sex mSex;
    private Character.CharacterClass mCharacterClass;
    private Character.Hair mHair;
    private Character.Hat mHat;
    private Character.EyeColor mEyeColor;
    private Character.Blouse mBlouse;
    private Character.Pants mPants;
    private Character.Shoes mShoes;
    private int mUserId;

    public CreateCharacterUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    private void setCharacterSkillPoints(Character.CharacterClass characterClass) {
        if (characterClass == Character.CharacterClass.Bard) {
            mCharisma = 10;
            mIntelligence = 3;
            mAgility = 5;
            mStrength = 2;
        } else if (characterClass == Character.CharacterClass.Wizard) {
            mCharisma = 3;
            mIntelligence = 10;
            mAgility = 2;
            mStrength = 5;
        } else if (characterClass == Character.CharacterClass.Thief) {
            mCharisma = 5;
            mIntelligence = 3;
            mAgility = 10;
            mStrength = 2;
        } else if (characterClass == Character.CharacterClass.Warrior) {
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

    public void setData(final String nickname, final Character.Sex sex, final Character.CharacterClass characterClass, final Character.Hair hair, final Character.Hat hat,
                        Character.EyeColor eyeColor, Character.Blouse blouse, Character.Pants pants, Character.Shoes shoes, int userId) {
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

    public Character.Sex getmSex() {
        return mSex;
    }

    public Character.CharacterClass getmCharacterClass() {
        return mCharacterClass;
    }

    public Character.Hair getmHair() {
        return mHair;
    }

    public Character.Hat getmHat() {
        return mHat;
    }

    public Character.EyeColor getmEyeColor() {
        return mEyeColor;
    }

    public Character.Blouse getmBlouse() {
        return mBlouse;
    }

    public Character.Pants getmPants() {
        return mPants;
    }

    public Character.Shoes getmShoes() {
        return mShoes;
    }

    public int getmUserId() {
        return mUserId;
    }
}
