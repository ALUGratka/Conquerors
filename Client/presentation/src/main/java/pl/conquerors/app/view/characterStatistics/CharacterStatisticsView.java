package pl.conquerors.app.view.characterStatistics;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.CharacterStatistics;

public interface CharacterStatisticsView extends BaseView {

    int getCharacterId();

    void updateStatistics(CharacterStatistics characterStatistics);
}
