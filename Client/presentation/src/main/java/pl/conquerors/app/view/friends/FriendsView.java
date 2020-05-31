package pl.conquerors.app.view.friends;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;

public interface FriendsView extends BaseView {
    Long getUserId();

    void showFriends(List<User> friends);
    void handleError(Throwable error);
}
