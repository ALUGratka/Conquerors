package pl.conquerors.app.domain.model;

import java.util.List;

public class User {

    private long userId;
    private String userNick;
    private String userEmail;
    private String userPassword;
    private String mBorn;
    private String userPoints;

    private boolean canInvite;
    private boolean canIgnore;
    private boolean canRemove;
    private boolean canAccept;

    private boolean isFriend;

    private List<Character>characters;

    public long getUserId() { return userId; }

    public void setUserId(long userId) { this.userId = userId; }

    public String getUserNick() { return userNick; }

    public void setUserNick(String userNick) { this.userNick = userNick; }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getmBorn() { return mBorn; }

    public void setmBorn(String mBorn) { this.mBorn = mBorn; }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(final boolean friend) { isFriend = friend; }

    public String getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(String userPoints) {
        this.userPoints = userPoints;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public boolean canInvite() {
        return canInvite;
    }

    public void setCanInvite(boolean canInvite) {
        this.canInvite = canInvite;
    }

    public boolean canIgnore() {
        return canIgnore;
    }

    public void setCanIgnore(boolean canIgnore) {
        this.canIgnore = canIgnore;
    }

    public boolean canRemove() {
        return canRemove;
    }

    public void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }

    public boolean canAccept() {
        return canAccept;
    }

    public void setCanAccept(boolean canAccept) {
        this.canAccept = canAccept;
    }
}
