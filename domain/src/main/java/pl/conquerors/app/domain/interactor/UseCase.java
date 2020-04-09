package pl.conquerors.app.domain.interactor;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import rx.Observable;

public abstract class UseCase<T, P> {

    protected ComposedScheduler mComposedScheduler;

    protected UseCase(ComposedScheduler scheduler) {mComposedScheduler = scheduler; }

    protected abstract Observable<T> buildUseCaseObservable(P parameter);

    public Observable<T> execute(P parameter) {

        final Observable<T> observable = buildUseCaseObservable(parameter).compose(mComposedScheduler.<T>applyScheduling());

        return observable;
    }
}
