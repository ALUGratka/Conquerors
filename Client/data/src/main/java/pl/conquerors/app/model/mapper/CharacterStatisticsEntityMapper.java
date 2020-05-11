package pl.conquerors.app.model.mapper;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.CharacterStatistics;
import pl.conquerors.app.model.CharacterStatisticsEntity;

public class CharacterStatisticsEntityMapper {
    public static CharacterStatistics transform (CharacterStatisticsEntity characterStatisticsEntity) {
        CharacterStatistics characterStatistics = null;
        if(characterStatisticsEntity!=null){
            characterStatistics = new CharacterStatistics();
            characterStatistics.setmAgility(characterStatisticsEntity.getAgility());
            characterStatistics.setmCharisma(characterStatisticsEntity.getCharisma());
            characterStatistics.setmIntelligence(characterStatisticsEntity.getIntelligence());
            characterStatistics.setmStrength(characterStatisticsEntity.getStrength());
            characterStatistics.setmSkillPoints(characterStatisticsEntity.getSkillPoints());
            characterStatistics.setmLevel(characterStatisticsEntity.getLevel());
            characterStatistics.setmNickname(characterStatisticsEntity.getNickname());
            characterStatistics.setmCharacterClass(characterStatisticsEntity.getCharacterClass());
        }
        return characterStatistics;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<CharacterStatistics> transform (List<CharacterStatisticsEntity> characterStatisticsEntities) {
        final List<CharacterStatistics> characterStatistics = new ArrayList<>();
        characterStatisticsEntities.forEach(characterStatisticsEntity ->
                characterStatistics.add(transform(characterStatisticsEntity)));
        return characterStatistics;
    }
}
