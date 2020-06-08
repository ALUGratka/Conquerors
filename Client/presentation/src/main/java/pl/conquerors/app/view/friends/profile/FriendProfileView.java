package pl.conquerors.app.view.friends.profile;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.domain.model.UserRelationship;

public interface FriendProfileView extends BaseView {

    User getUser();
    void setUser(User user);

    void showProfileDetails(final User user);

    void setProfileActions(final boolean visible);

    void onActionComplete(String title, String message);

    Long getCurrentUserId();

    void setupActionButton(final UserRelationship usersRelationship);

    void setNumberOfCharacters(int numberOfCharacters);
}
