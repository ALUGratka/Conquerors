package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class UserRelationshipEntity {

    @SerializedName("user1ID")
    private long user1Id;

    @SerializedName("user2ID")
    private long user2Id;

    @SerializedName("canDelete")
    private Boolean canDelete;

    @SerializedName("canInvite")
    private Boolean canInvite;

    @SerializedName("canAccept")
    private Boolean canAccept;

    @SerializedName("canReject")
    private Boolean canReject;

    @SerializedName("canUninvite")
    private Boolean canUninvit;

    public long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(long user1Id) {
        this.user1Id = user1Id;
    }

    public long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(long user2Id) {
        this.user2Id = user2Id;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean getCanInvite() {
        return canInvite;
    }

    public void setCanInvite(Boolean canInvite) {
        this.canInvite = canInvite;
    }

    public Boolean getCanAccept() {
        return canAccept;
    }

    public void setCanAccept(Boolean canAccept) {
        this.canAccept = canAccept;
    }

    public Boolean getCanReject() {
        return canReject;
    }

    public void setCanReject(Boolean canReject) {
        this.canReject = canReject;
    }

    public Boolean getCanUninvit() {
        return canUninvit;
    }

    public void setCanUninvit(Boolean canUninvit) {
        this.canUninvit = canUninvit;
    }
}
