package pl.conquerors.app.domain.model;

public class Enemy {
    private int mId;
    private String mName;
    private String mDescription;
    private int mCharisma;
    private int mIntelligence;
    private int mAgility;
    private int mStrength;

    public String getmDescription() {
        return mDescription;
    }

    public void setmStrength(int mStrength) {
        this.mStrength = mStrength;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmIntelligence(int mIntelligence) {
        this.mIntelligence = mIntelligence;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmCharisma(int mCharisma) {
        this.mCharisma = mCharisma;
    }

    public void setmAgility(int mAgility) {
        this.mAgility = mAgility;
    }

    public String getmName() {
        return mName;
    }

    public int getmStrength() {
        return mStrength;
    }

    public int getmIntelligence() {
        return mIntelligence;
    }

    public int getmId() {
        return mId;
    }

    public int getmCharisma() {
        return mCharisma;
    }

    public int getmAgility() {
        return mAgility;
    }
}
