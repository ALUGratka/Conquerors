package pl.conquerors.app.model.mapper;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.EnemiesAchievement;
import pl.conquerors.app.model.EnemiesAchievementEntity;

public class EnemiesAchievementEntityMapper {
    public static EnemiesAchievement transform (EnemiesAchievementEntity enemiesAchievementEntity) {
        EnemiesAchievement enemiesAchievement = null;
        if(enemiesAchievementEntity!=null){
            enemiesAchievement = new EnemiesAchievement();
            enemiesAchievement.setmGamePlayId(enemiesAchievementEntity.getGamePlayId());
            enemiesAchievement.setmObjectPositionX(enemiesAchievementEntity.getObjectPositionX());
            enemiesAchievement.setmObjectPositionY(enemiesAchievementEntity.getObjectPositionY());
            enemiesAchievement.setmDefeatedByCharacterId(enemiesAchievementEntity.getDefeatedByCharacterId());
            enemiesAchievement.setmEnemyId(enemiesAchievementEntity.getEnemyId());
        }
        return enemiesAchievement;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<EnemiesAchievement> transform (List<EnemiesAchievementEntity> enemiesAchievementEntities) {
        final List<EnemiesAchievement> enemiesAchievements = new ArrayList<>();
        enemiesAchievementEntities.forEach(enemiesAchievementEntity ->
                enemiesAchievements.add(transform(enemiesAchievementEntity)));
        return enemiesAchievements;
    }
}
