package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PrizeDateEntity {
    /**
     * variables
     **/

    @SerializedName("userId")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PrizeDateEntity(int userId) {
        this.userId = userId;
    }
}
