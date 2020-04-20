package pl.conquerors.app.view.createCharacter;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.view.everydayPrize.EverydayPrizeView;

public class CreateCharacterPresenter extends BasePresenter<EverydayPrizeView> {
    CreateCharacterUseCase createCharacterUseCase;
    public CreateCharacterPresenter(final CreateCharacterUseCase useCase) { createCharacterUseCase = useCase; }

}
