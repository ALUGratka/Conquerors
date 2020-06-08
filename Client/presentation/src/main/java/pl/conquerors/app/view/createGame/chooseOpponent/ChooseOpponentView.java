package pl.conquerors.app.view.createGame.chooseOpponent;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;

public interface ChooseOpponentView extends BaseView {
    Long getUserId();

    void showFriends(final List<User> friends);

    void setOpponent();
}
