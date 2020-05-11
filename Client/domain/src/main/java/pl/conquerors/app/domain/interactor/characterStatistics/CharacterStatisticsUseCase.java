package pl.conquerors.app.domain.interactor.characterStatistics;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class CharacterStatisticsUseCase extends ResultUseCase<Void> {
    public CharacterStatisticsUseCase(ComposedScheduler scheduler) { super(scheduler); }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }
}
