package pl.conquerors.app.domain.model;

public class EnemiesAchievement {
    private int mGamePlayId;
    private int mEnemyId;
    private int mDefeatedByCharacterId;
    private String  mObjectPositionX;
    private String  mObjectPositionY;

    public int getmGamePlayId() {
        return mGamePlayId;
    }

    public void setmGamePlayId(int mGamePlayId) {
        this.mGamePlayId = mGamePlayId;
    }

    public int getmEnemyId() {
        return mEnemyId;
    }

    public void setmEnemyId(int mEnemyId) {
        this.mEnemyId = mEnemyId;
    }

    public int getmDefeatedByCharacterId() {
        return mDefeatedByCharacterId;
    }

    public void setmDefeatedByCharacterId(int mDefeatedByCharacterId) {
        this.mDefeatedByCharacterId = mDefeatedByCharacterId;
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
