package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class TreasureAchievementEntity {

    @SerializedName("gameplayId")
    private int gamePlayId;

    @SerializedName("treasureId")
    private int treasureId;

    @SerializedName("obtainedByCharacterId")
    private int obtainedByCharacterId;

    @SerializedName("objectPositionX")
    private String  objectPositionX;

    @SerializedName("objectPositionY")
    private String  objectPositionY;


    public int getGamePlayId() {
        return gamePlayId;
    }

    public void setGamePlayId(int gamePlayId) {
        this.gamePlayId = gamePlayId;
    }

    public int getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(int treasureId) {
        this.treasureId = treasureId;
    }

    public int getObtainedByCharacterId() {
        return obtainedByCharacterId;
    }

    public void setObtainedByCharacterId(int obtainedByCharacterId) {
        this.obtainedByCharacterId = obtainedByCharacterId;
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

    public TreasureAchievementEntity(int gamePlayId, int treasureId, String objectPositionX, String objectPositionY) {
        this.gamePlayId = gamePlayId;
        this.treasureId = treasureId;
        this.objectPositionX = objectPositionX;
        this.objectPositionY = objectPositionY;
    }

    public TreasureAchievementEntity() {}
}
