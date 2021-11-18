import org.junit.jupiter.api.Test;
import javafx.geometry.Point2D;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnemyHealthAndDamage {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testEnemyDamage() {
        Tower tempFire = new FireTower();
        Tower tempEarth = new EarthTower();
        Tower tempIce = new IceTower();

        tempFire.setDamage(5);
        assertEquals(tempFire.getDamage(), 5);

        tempEarth.setDamage(35);
        assertEquals(tempEarth.getDamage(), 35);

        tempIce.setDamage(95);
        assertEquals(tempIce.getDamage(), 95);
    }

    @Test
    public void testEnemyDamageDisplay() throws InterruptedException {
        //TestEnemyHealthAndDamage.TestThread thread = new TestEnemyHealthAndDamage.TestThread();
        //thread.start();
        //thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test

        init.setDifficulty(GameDifficulty.EASY);
        init.setMoney(500);
        init.setInitHealth();
        init.setIsStarted(false);

        Tower tempFire = new FireTower();
        Tower tempEarth = new EarthTower();
        Tower tempIce = new IceTower();

        tempFire.setLocation(50,  50);
        assertEquals(tempFire.getLocation(), new Point2D(50, 50));

        tempEarth.setLocation(75,  25);
        assertEquals(tempEarth.getLocation(), new Point2D(75, 25));

        tempIce.setLocation(35,  15);
        assertEquals(tempIce.getLocation(), new Point2D(35, 15));
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
