import com.almasb.fxgl.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class GarySheppardM6Tests {
    @Test
    public void testBossAttack() {
        TowerDefense.setHealth(100);

        TowerDefense.attackMonument(100);
        Assertions.assertEquals(0, TowerDefense.getHealth().intValue());
    }

    @Test
    public void testBossSpawn() {
        Integer[] listEnemies = new Integer[10];
        int enemiesKilled = 0;

        Random rand = new Random();
        int enemyTicket = rand.nextInt(3);
        for (int i = 0; i < 10; i++) {
            Integer enemy;
            if (enemiesKilled < 7) {
                enemy = 3;
                listEnemies[i] = enemy;
            }
            switch (enemyTicket) {
                case 0:
                    enemy = 0;
                    listEnemies[i] = enemy;
                    break;
                case 1:
                    enemy = 1;
                    listEnemies[i] = enemy;
                    break;
                case 2:
                    enemy = 2;
                    listEnemies[i] = enemy;
                    break;
                default:
                    //bruh
            }
        }

        for (Integer enemy: listEnemies) {
            Assertions.assertNotNull(enemy);
        }
    }
}