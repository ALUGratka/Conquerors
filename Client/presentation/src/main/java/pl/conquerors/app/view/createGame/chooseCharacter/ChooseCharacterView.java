package pl.conquerors.app.view.createGame.chooseCharacter;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.Character;

public interface ChooseCharacterView extends BaseView {


    long getUserId();

    void setCharacters(List<Character>characters);

    void startCreateCharacter(boolean visible);
    void setCharacterCard(int index);

    void setPreviousArrowsVisible(boolean visible);
    void setNextArrowVisible(boolean visible);
    void setArrowsVisible(boolean visible);
    void setCharacterCardVisible(boolean visible);
    void setNextButtonVisible(boolean visible);

}
