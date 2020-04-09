package pl.conquerors.app.domain.executor;

import rx.Observable;

public interface ComposedScheduler {
    <T> Observable.Transformer<T, T> applyScheduling();
}
