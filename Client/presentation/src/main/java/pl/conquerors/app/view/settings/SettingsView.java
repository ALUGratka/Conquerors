package pl.conquerors.app.view.settings;

import pl.conquerors.app.base.BaseView;

public interface SettingsView extends BaseView {


    void showChangeEmail();
    void showChangePassword();
    void showRemoveAccount();

    void onRemoveAccountSucceeded();
}
