package pl.conquerors.app.domain.interactor.createCharacter;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class CreateCharacterUseCase extends ResultUseCase<Void> {
    public CreateCharacterUseCase(ComposedScheduler scheduler) { super(scheduler); }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return null;
    }
}
