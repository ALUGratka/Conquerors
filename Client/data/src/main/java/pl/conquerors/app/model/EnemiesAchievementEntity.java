package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class EnemiesAchievementEntity {
    @SerializedName("gameplayId")
    private int gamePlayId;

    @SerializedName("enemyId")
    private int enemyId;

    @SerializedName("defeatedByCharacterId")
    private int defeatedByCharacterId;

    @SerializedName("objectPositionX")
    private String  objectPositionX;

    @SerializedName("objectPositionY")
    private String  objectPositionY;

    public EnemiesAchievementEntity(int gamePlayId, int enemyId, String objectPositionX, String objectPositionY) {
        this.gamePlayId = gamePlayId;
        this.enemyId = enemyId;
        this.objectPositionX = objectPositionX;
        this.objectPositionY = objectPositionY;
    }

    public int getGamePlayId() {
        return gamePlayId;
    }

    public void setGamePlayId(int gamePlayId) {
        this.gamePlayId = gamePlayId;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public int getDefeatedByCharacterId() {
        return defeatedByCharacterId;
    }

    public void setDefeatedByCharacterId(int defeatedByCharacterId) {
        this.defeatedByCharacterId = defeatedByCharacterId;
    }

    public String getObjectPositionX() {
        return objectPositionX;
    }

    public void setObjectPositionX(String objectPositionX) {
        this.objectPositionX = objectPositionX;
    }

    public String getObjectPositionY() {
        return objectPositionY;
    }

    public void setObjectPositionY(String objectPositionY) {
        this.objectPositionY = objectPositionY;
    }
}
