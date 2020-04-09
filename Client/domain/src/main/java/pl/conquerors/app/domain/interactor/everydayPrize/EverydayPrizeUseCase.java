package pl.conquerors.app.domain.interactor.everydayPrize;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class EverydayPrizeUseCase extends ResultUseCase<Void> {
    public EverydayPrizeUseCase(ComposedScheduler scheduler) { super(scheduler); }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }
}
