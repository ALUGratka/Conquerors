package pl.conquerors.app.view.everydayPrize;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.PrizeDate;

public interface EverydayPrizeView extends BaseView {
    void onEverydayPrizeSucceeded(int attributeId);

    void showAlreadyGifted();

    void updateCalendar(List<PrizeDate> prizeDates);
}
