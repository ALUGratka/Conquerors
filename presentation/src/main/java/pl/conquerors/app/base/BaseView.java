package pl.conquerors.app.base;

import android.content.Context;

public interface BaseView {

    void startLogin();
    void showError(String title, String name);
    Context getContext();

    //loadingView
    void showLoading();
    void hideLoading();
}
