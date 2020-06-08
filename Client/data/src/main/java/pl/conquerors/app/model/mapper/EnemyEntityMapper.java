package pl.conquerors.app.model.mapper;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.Enemy;
import pl.conquerors.app.domain.model.Treasure;
import pl.conquerors.app.model.EnemyEntity;


public class EnemyEntityMapper {

    public static Enemy transform (EnemyEntity enemyEntity) {
        Enemy enemy = null;
        if(enemyEntity!=null){
            enemy = new Enemy();
            enemy.setmId(enemyEntity.getId());
            enemy.setmName(enemyEntity.getName());
            enemy.setmDescription(enemyEntity.getDescription());
            enemy.setmAgility(enemyEntity.getAgility());
            enemy.setmCharisma(enemyEntity.getCharisma());
            enemy.setmIntelligence(enemyEntity.getIntelligence());
            enemy.setmStrength(enemyEntity.getStrength());
        }
        return enemy;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Enemy> transform (List<EnemyEntity> enemyEntities) {
        final List<Enemy> enemies = new ArrayList<>();
        enemyEntities.forEach(enemyEntity -> enemies.add(transform(enemyEntity)));
        return enemies;
    }

}
