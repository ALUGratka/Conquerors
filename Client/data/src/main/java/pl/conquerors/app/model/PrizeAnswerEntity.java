package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class PrizeAnswerEntity {
    @SerializedName("attributeId")
    private int attributeId;

    public int getAttribute() {
        return attributeId;
    }

    public void setAttribute(int attribute) {
        this.attributeId = attribute;
    }
}
