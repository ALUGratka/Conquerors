package pl.conquerors.app.domain.interactor.enemiesAchievement;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class EnemiesAchievementUseCase  extends ResultUseCase<Void> {
    protected EnemiesAchievementUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }
}
