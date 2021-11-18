import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class GarySheppardM5Tests {
    @Test
    public void testEnemyAttacks() {
        TowerDefense.setHealth(100);

        TowerDefense.attackMonument(2);
        Assertions.assertEquals(98, TowerDefense.getHealth().intValue());

        TowerDefense.attackMonument(20);
        Assertions.assertEquals(78, TowerDefense.getHealth().intValue());

        TowerDefense.attackMonument(5);
        Assertions.assertEquals(73, TowerDefense.getHealth().intValue());
    }

    @Test
    public void testRandomEnemies() {
        Integer[] listEnemies = new Integer[10];

        Random rand = new Random();
        int enemyTicket = rand.nextInt(3);
        for (int i = 0; i < 10; i++) {
            Integer enemy = null;
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
                //hi
            }
        }

        for (Integer enemy: listEnemies) {
            Assertions.assertNotNull(enemy);
        }
    }
}