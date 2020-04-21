package pl.conquerors.app.view.profile.my;

import pl.conquerors.app.base.BaseView;

public interface MyProfileView extends BaseView {

    void setUserName(final String name);

    void showEditButton();
    void hideEditButton();
}
