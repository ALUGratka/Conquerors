package pl.conquerors.app.view.everydayPrize;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;

public class EverydayPrizePresenter extends BasePresenter<EverydayPrizeView> {
    EverydayPrizeUseCase mUseCase;
    public EverydayPrizePresenter(final EverydayPrizeUseCase useCase) { mUseCase = useCase; }

    public void performEverydayPrize() {
    }
}
