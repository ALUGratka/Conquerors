package pl.conquerors.app.domain.model;

import java.io.Serializable;

public class User implements Serializable {
    private long mId;
    private String mNick;
    private String mBorn;

    public long getmId() { return mId; }

    public void setmId(long mId) { this.mId = mId; }

    public String getmNick() { return mNick; }

    public void setmNick(String mNick) { this.mNick = mNick; }

    public String getmBorn() { return mBorn; }

    public void setmBorn(String mBorn) { this.mBorn = mBorn; }
}
