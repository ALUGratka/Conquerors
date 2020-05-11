package pl.conquerors.app.domain.interactor.createGame;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class createGameUseCase extends ResultUseCase {

    protected createGameUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }

    @Override
    protected Observable buildUseCaseObservable(Object parameter) {
        return null;
    }
}
