package pl.conquerors.app.view.friends.profile;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;

public interface FriendProfileView extends BaseView {

    Long getUserId();

    void showProfileDetails(final User user);

    void setProfileActions(final boolean visible);

    void onActionComplete(String title, String message);

    void setCurrentUserId(final long id);

    void setupActionButton(final User user);
}
