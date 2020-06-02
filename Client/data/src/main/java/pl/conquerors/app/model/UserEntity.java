package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

import pl.conquerors.app.domain.model.User;

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

    public UserEntity(User user) {
        this.userId = (int)user.getUserId();
        this.userName = user.getUserNick();
        this.email = user.getUserEmail();
        this.password = user.getUserPassword();
        this.born = user.getmBorn();
    }

    public UserEntity(String email,String userName, String password, String born) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.born = born;
    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserEntity(String email) {
        this.email = email;
    }

    public UserEntity(int userId, String email, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

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
