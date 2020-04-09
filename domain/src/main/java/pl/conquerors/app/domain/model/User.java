package pl.conquerors.app.domain.model;

import java.io.Serializable;

public class User implements Serializable {
    private long mId;
    private String mNick;
    private String mBorn;
    private String mEmail;
    private int mPoints;
    private int mLevel;

    public User() { }

    public User(long mId, String mNick, String mBorn, String mEmail, int mPoints, int mLevel) {
        this.mId = mId;
        this.mNick = mNick;
        this.mBorn = mBorn;
        this.mEmail = mEmail;
        this.mPoints = mPoints;
        this.mLevel = mLevel;
    }

    public long getId() { return mId; }

    public void setId(long mId) { this.mId = mId; }

    public String getNick() { return mNick; }

    public void setNick(String mNick) { this.mNick = mNick; }

    public String getBorn() { return mBorn; }

    public void setBorn(String mBorn) { this.mBorn = mBorn; }

    public String getEmail() { return mEmail; }

    public void setEmail(String email) { mEmail = email; }

    public int getPoints() { return mPoints; }

    public void setPoints(int points) { mPoints = points; }

    public int getLevel() { return mLevel; }

    public void setLevel(int level) { mLevel = level; }

    @Override
    public String toString() {
        return "User ID:" + mId +
                "\nNick: " + mNick +
                "\nBorn: " + mBorn +
                "\nEmail: " + mEmail +
                "\nPoints: " + mPoints +
                "\nmLevel: " + mLevel +
                "\n\n";
    }
}
