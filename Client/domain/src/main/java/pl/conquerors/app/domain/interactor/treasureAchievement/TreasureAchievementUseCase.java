package pl.conquerors.app.domain.interactor.treasureAchievement;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class TreasureAchievementUseCase extends ResultUseCase<Void> {
    protected TreasureAchievementUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }
}
