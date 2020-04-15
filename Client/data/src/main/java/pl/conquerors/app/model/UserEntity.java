package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("id")
    private int userId;

    @SerializedName("username")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("birthDate")
    private String born;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBorn() {
        return born;
    }
}
