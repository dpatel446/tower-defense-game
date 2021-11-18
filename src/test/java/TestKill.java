import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKill {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testEnemyKill() throws InterruptedException {
        //TestKill.TestThread thread = new TestKill.TestThread();
        //thread.start();
        //thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test

        init.setDifficulty(GameDifficulty.EASY);
        init.setMoney(500);
        init.setInitHealth();
        init.setIsStarted(false);

        Tower t = new EarthTower();
        t.setLocation(175, 60);
        t.setDamage(50);
        t.setRadius(100);
        t.setDelay(10);
        init.startCombat();
        //init.initGame();
        init.spawnEnemy();
        assertEquals(init.getEnemies().isEmpty(), false);
        //thread.sleep(500);
        t.attack();

        assertEquals(init.getEnemies().isEmpty(), true);
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
