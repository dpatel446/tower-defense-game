import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayerCombat {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testAttack() {
        init.setDifficulty(GameDifficulty.EASY);
        init.setMoney(500);
        init.setInitHealth();


        assertEquals(100, init.getHealth().intValue());

        init.attackMonument(5);
        assertEquals(95, init.getHealth().intValue());


        init.setDifficulty(GameDifficulty.MEDIUM);
        init.setInitHealth();

        assertEquals(75, init.getHealth().intValue());

        init.attackMonument(5);
        assertEquals(70, init.getHealth().intValue());

        init.setDifficulty(GameDifficulty.HARD);
        init.setInitHealth();

        assertEquals(50, init.getHealth().intValue());

        init.attackMonument(5);
        assertEquals(45, init.getHealth().intValue());
    }

    @Test
    public void testCombatStart() throws InterruptedException {

        //TestCombat.TestThread thread = new TestCombat.TestThread();
        //thread.start();
        //thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test


        init.setDifficulty(GameDifficulty.EASY);
        init.setIsStarted(false);
        init.setMoney(500);
        init.setInitHealth();

        assertEquals(false, init.getIsStarted());

        init.startCombat();

        assertEquals(true, init.getIsStarted());
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
