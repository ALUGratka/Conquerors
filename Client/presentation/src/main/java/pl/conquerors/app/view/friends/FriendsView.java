package pl.conquerors.app.view.friends;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;

public interface FriendsView extends BaseView {
    Long getUserId();

    void showInvitations(final List<User> invitations);

    void showFriends(final List<User> friends);

    void setInvitationVisible(final boolean visible);

    void setFriendsVisible(final boolean visible);
    void setNoFriendsVisible(final boolean visible);


    void handleError(Throwable error);
}
