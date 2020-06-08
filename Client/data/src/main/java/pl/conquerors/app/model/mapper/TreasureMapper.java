package pl.conquerors.app.model.mapper;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.Treasure;
import pl.conquerors.app.model.TreasureEntity;

public class TreasureMapper {
    public static Treasure transform (TreasureEntity treasureEntity) {
        Treasure treasure = null;
        if(treasureEntity!=null){
            treasure = new Treasure();
            treasure.setmId(treasureEntity.getId());
            treasure.setmName(treasureEntity.getName());
            treasure.setmDescription(treasureEntity.getDescription());
            treasure.setmAgility(treasureEntity.getAgility());
            treasure.setmCharisma(treasureEntity.getCharisma());
            treasure.setmIntelligence(treasureEntity.getIntelligence());
            treasure.setmStrength(treasureEntity.getStrength());
            treasure.setmSkillPoints(treasureEntity.getSkillPoints());
        }
        return treasure;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Treasure> transform (List<TreasureEntity> treasureEntities) {
        final List<Treasure> treasures = new ArrayList<>();
        treasureEntities.forEach(treasureEntity -> treasures.add(transform(treasureEntity)));
        return treasures;
    }
}
