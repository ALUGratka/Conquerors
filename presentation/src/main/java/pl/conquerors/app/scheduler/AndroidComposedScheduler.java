package pl.conquerors.app.scheduler;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AndroidComposedScheduler implements ComposedScheduler {

    @Override
    public <T> Observable.Transformer<T, T> applyScheduling() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
