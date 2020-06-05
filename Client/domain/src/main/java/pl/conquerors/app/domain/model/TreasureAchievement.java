package pl.conquerors.app.domain.model;

public class TreasureAchievement {
    private int mGamePlayId;
    private int mTreasureId;
    private int mObtainedByCharacterId;
    private String  mObjectPositionX;
    private String  mObjectPositionY;

    public int getmGamePlayId() {
        return mGamePlayId;
    }

    public void setmGamePlayId(int mGamePlayId) {
        this.mGamePlayId = mGamePlayId;
    }

    public int getmTreasureId() {
        return mTreasureId;
    }

    public void setmTreasureId(int mTreasureId) {
        this.mTreasureId = mTreasureId;
    }

    public int getmObtainedByCharacterId() {
        return mObtainedByCharacterId;
    }

    public void setmObtainedByCharacterId(int mObtainedByCharacterId) {
        this.mObtainedByCharacterId = mObtainedByCharacterId;
    }

    public String getmObjectPositionX() {
        return mObjectPositionX;
    }

    public void setmObjectPositionX(String mObjectPositionX) {
        this.mObjectPositionX = mObjectPositionX;
    }

    public String getmObjectPositionY() {
        return mObjectPositionY;
    }

    public void setmObjectPositionY(String mObjectPositionY) {
        this.mObjectPositionY = mObjectPositionY;
    }
}
