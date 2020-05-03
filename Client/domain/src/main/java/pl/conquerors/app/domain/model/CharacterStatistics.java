package pl.conquerors.app.domain.model;

public class CharacterStatistics {
    private int mId;
    private int mLevel;
    private int mCharisma;
    private int mIntelligence;
    private int mAgility;
    private int mStrength;
    private int mSkillPoints;
    private String mNickname;
    private String mCharacterClass;

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

    public int getmSkillPoints() {
        return mSkillPoints;
    }

    public void setmId(int mId) {
        this.mId = mId;
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

    public void setmSkillPoints(int mSkillPoints) {
        this.mSkillPoints = mSkillPoints;
    }

    public String getmNickname() {
        return mNickname;
    }

    public void setmNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public String getmCharacterClass() {
        return mCharacterClass;
    }

    public void setmCharacterClass(String mCharacterClass) {
        this.mCharacterClass = mCharacterClass;
    }
}
