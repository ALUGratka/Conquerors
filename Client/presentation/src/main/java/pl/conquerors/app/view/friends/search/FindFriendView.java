package pl.conquerors.app.view.friends.search;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;

public interface FindFriendView extends BaseView {
    String getSearchQuery();

    void showSearchResult(List<User> result);

    void showNoResultsMessage();
}
