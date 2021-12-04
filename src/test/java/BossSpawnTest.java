import com.almasb.fxgl.entity.Entity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BossSpawnTest {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testSpawn() throws InterruptedException {

        //Uncomment the following lines out to run the tests individually
        //BossSpawnTest.TestThread thread = new BossSpawnTest.TestThread();
        //thread.start();
        //thread.sleep(3000);

        //Timeout to allow the FXGL application to initialize its services prior to running test

        init.setDifficulty(GameDifficulty.EASY);
        init.setMoney(500);
        init.setInitHealth();
        init.setIsStarted(false);
        init.startCombat();
        TowerDefense.setEnemies(new ArrayList<Entity>());
        //init.initGame();
        init.setBossSpawned(false);
        init.setEnemiesKilled(16);
        init.spawnEnemy();
        //thread.sleep(500);

        assertEquals(true,init.getEnemies().get(0).getBoolean("boss"));

    }

    private static class TestThread extends Thread {
        @Override
        public void run() {
            String[] str = {""};
            TowerDefense.setDifficulty(GameDifficulty.EASY);
            TowerDefense.setMoney(500);
            TowerDefense.main(str);
        }
    }
}
