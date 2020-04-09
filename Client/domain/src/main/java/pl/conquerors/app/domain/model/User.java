package pl.conquerors.app.domain.model;

import java.io.Serializable;

public class User implements Serializable {

    private long mId;
    private String mNick;
    private String mEmail;
    private String mPassword;
    private String mBorn;

    public long getmId() { return mId; }

    public void setmId(long mId) { this.mId = mId; }

    public String getmNick() { return mNick; }

    public void setmNick(String mNick) { this.mNick = mNick; }

    public String getmEmail() { return mEmail; }

    public void setmEmail(String mEmail) { this.mEmail = mEmail; }

    public String getmPassword() { return mPassword; }

    public void setmPassword(String mPassword) { this.mPassword = mPassword; }

    public String getmBorn() { return mBorn; }

    public void setmBorn(String mBorn) { this.mBorn = mBorn; }
}
