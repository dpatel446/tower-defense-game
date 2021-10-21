import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    public void testTransaction() throws InterruptedException {
        TransactionTest.TestThread thread = new TransactionTest.TestThread();
        thread.start();
        Thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test

        assertEquals(TowerDefense.getDifficulty(), GameDifficulty.EASY);

        int smoney = TowerDefense.getMoney().intValue();
        int emoney = TowerDefense.getInitMoney();


        assertEquals(emoney, smoney);

        Tower t = new EarthTower();
        TowerDefense.transaction(t);
        emoney = emoney - t.getCost();
        int cmoney = TowerDefense.getMoney().intValue();

        assertEquals(cmoney, emoney);

        //Checks if the method returns false, in case of insufficient funds
        TowerDefense.setMoney(0);
        assertEquals(false, TowerDefense.transaction(t));
    }

    @Test
    public void testDiffTransaction() throws InterruptedException {
        TransactionTest.TestThread thread = new TransactionTest.TestThread();
        thread.start();
        Thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test

        TowerDefense.setDifficulty(GameDifficulty.MEDIUM);
        TowerDefense.setMoney(300);
        assertEquals(TowerDefense.getDifficulty(), GameDifficulty.MEDIUM);

        int smoney = TowerDefense.getMoney().intValue();
        int emoney = TowerDefense.getInitMoney();
        assertEquals(emoney, smoney);

        Tower t = new IceTower();
        TowerDefense.transaction(t);
        emoney = emoney - t.getCost();
        int cmoney = TowerDefense.getMoney().intValue();
        assertEquals(cmoney, emoney);

        TowerDefense.setDifficulty(GameDifficulty.HARD);
        TowerDefense.setMoney(100);
        assertEquals(TowerDefense.getDifficulty(), GameDifficulty.HARD);
        t = new FireTower();
        TowerDefense.transaction(t);
        emoney = TowerDefense.getInitMoney();
        emoney = emoney - t.getCost();
        cmoney = TowerDefense.getMoney().intValue();
        assertEquals(cmoney, emoney);
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
