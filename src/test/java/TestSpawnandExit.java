import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSpawnandExit {

    private static boolean exit = false;
    private Point2D origin = new Point2D(175, 45);

    @Test
    public void testExit() {
        TowerDefense.setHealth(0);
        boolean chooseExit = true;
        if (!(TowerDefense.getHealth().intValue() > 0) && chooseExit) {
            exit = true;
        }
        Assertions.assertTrue(exit);
    }

    @Test
    public void testSpawn() {
        Point2D enemy = new Point2D(175, 45);
        TowerDefense.setIsStarted(true);
        Assertions.assertEquals(enemy, origin);
        Assertions.assertTrue(TowerDefense.getIsStarted());
        TowerDefense.setIsStarted(false);
    }
}
