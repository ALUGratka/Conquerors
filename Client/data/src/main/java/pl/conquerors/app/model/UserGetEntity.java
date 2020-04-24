package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class UserGetEntity {

    @SerializedName("email")
    String email;

    public UserGetEntity(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
