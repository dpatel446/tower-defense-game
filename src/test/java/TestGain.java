import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGain {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testMoneyGain() throws InterruptedException {

        //TestGain.TestThread thread = new TestGain.TestThread();
        //thread.start();
        //thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test

        init.setDifficulty(GameDifficulty.EASY);
        init.setMoney(500);
        init.setInitHealth();
        init.setIsStarted(false);

        Tower t = new FireTower();
        t.setLocation(175, 60);
        t.setDamage(50);
        t.setRadius(100);
        init.startCombat();
        init.initGame();
        init.spawnEnemy();
        //thread.sleep(500);
        TowerAttackController.attackController(t);

        assertEquals(502, TowerDefense.getMoney().intValue());

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
