package pl.conquerors.app.view.showCharacters;

import java.util.List;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.Character;

public interface ShowCharactersView extends BaseView {
    public void showCharacters(List<Character> characters);
}