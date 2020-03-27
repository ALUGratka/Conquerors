package pl.conquerors.app.domain.interactor;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import rx.Observable;

public abstract class ResultUseCase<T> extends UseCase<T, Void> {

    protected ResultUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    protected abstract Observable<T> buildUseCaseObservable();

    @Override
    protected Observable<T> buildUseCaseObservable(final Void parameter) {
        return buildUseCaseObservable();
    }

    public Observable<T> execute() {
        return execute(null);
    }
}
