package pl.conquerors.app.model.mapper;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.TreasureAchievement;
import pl.conquerors.app.model.TreasureAchievementEntity;

public class TreasureAchievementMapper {
    public static TreasureAchievement transform (TreasureAchievementEntity treasureAchievementEntity) {
        TreasureAchievement treasureAchievement = null;
        if(treasureAchievementEntity!=null){
            treasureAchievement = new TreasureAchievement();
            treasureAchievement.setmGamePlayId(treasureAchievementEntity.getGamePlayId());
            treasureAchievement.setmObjectPositionX(treasureAchievementEntity.getObjectPositionX());
            treasureAchievement.setmObjectPositionY(treasureAchievementEntity.getObjectPositionY());
            treasureAchievement.setmObtainedByCharacterId(treasureAchievementEntity.getObtainedByCharacterId());
            treasureAchievement.setmTreasureId(treasureAchievementEntity.getTreasureId());
        }
        return treasureAchievement;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<TreasureAchievement> transform (List<TreasureAchievementEntity> treasureAchievementEntities) {
        final List<TreasureAchievement> treasureAchievements = new ArrayList<>();
        treasureAchievementEntities.forEach(treasureAchievementEntity ->
                treasureAchievements.add(transform(treasureAchievementEntity)));
        return treasureAchievements;
    }
}
