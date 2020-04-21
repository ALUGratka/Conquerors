package pl.conquerors.app.view.createCharacter;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;

public class CreateCharacterPresenter extends BasePresenter<CreateCharacterView> {
    CreateCharacterUseCase createCharacterUseCase;
    public CreateCharacterPresenter(final CreateCharacterUseCase useCase) { createCharacterUseCase = useCase; }

    public void performCharacterCreation(){
    }

}
