package pl.conquerors.app.view.everydayPrize;

import pl.conquerors.app.base.BaseView;

public interface EverydayPrizeView extends BaseView {
    void getPrize();
    void showPrizeView(Integer points);

    void onEverydayPrizeSucceeded();

    void showAlreadyGifted();
}
