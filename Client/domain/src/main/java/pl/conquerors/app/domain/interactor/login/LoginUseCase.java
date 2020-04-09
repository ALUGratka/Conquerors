package pl.conquerors.app.domain.interactor.login;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class LoginUseCase extends ResultUseCase<Void> {

    public LoginUseCase(ComposedScheduler scheduler) { super(scheduler); }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }
}
