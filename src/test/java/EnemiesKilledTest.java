import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemiesKilledTest {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testKillCounter() throws InterruptedException {

        //Uncomment the following lines out to run the tests individually
        //EnemiesKilledTest.TestThread thread = new EnemiesKilledTest.TestThread();
        //thread.start();
        //thread.sleep(3000);

        //Timeout to allow the FXGL application to initialize its services prior to running test

        init.setDifficulty(GameDifficulty.EASY);
        init.setMoney(500);
        init.setInitHealth();
        init.setIsStarted(false);
        init.setEnemiesKilled(0);

        Tower t = new FireTower();
        t.setLocation(175, 60);
        t.setDamage(50);
        t.setRadius(100);
        init.startCombat();
        //init.initGame();
        init.spawnEnemy();
        //thread.sleep(500);
        TowerAttackController.attackController(t);

        assertEquals(1, TowerDefense.getEnemiesKilled());

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
