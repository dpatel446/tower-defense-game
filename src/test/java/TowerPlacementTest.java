import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TowerPlacementTest {
    @Test
    public void testOnpath() {
        Rectangle2D forbiddenPath1 = new Rectangle2D(100.0, 0.0, 130.0, 630.0);
        Rectangle2D forbiddenPath2 = new Rectangle2D(210.0, 520.0, 770.0, 130.0);
        Rectangle2D test1 = new Rectangle2D(140, 0, 20, 20);
        Rectangle2D test2 = new Rectangle2D(235, 510, 20, 20);
        boolean testing = false;

        if (test1.getMaxX() < forbiddenPath1.getMaxX()) {
            testing = true;
        }
        Assertions.assertTrue(testing);

        if (test2.getMaxX() > (forbiddenPath2.getMaxX() + forbiddenPath2.getWidth())) {
            testing = true;
        }
        Assertions.assertTrue(testing);
        if (test2.getMaxY() < forbiddenPath2.getMaxY()
                && test2.getMaxY() > forbiddenPath2.getMinX()) {
            testing = true;
        }
        Assertions.assertTrue(testing);

    }
    @Test
    public void testOnmap() {
        Rectangle2D forbiddenPath3 = new Rectangle2D(870.0, 0.0, 130.0, 560.0);
        boolean testing = true;

        Rectangle2D test1 = new Rectangle2D(400, 50, 20, 20);

        if (test1.getMaxX() < forbiddenPath3.getMinX()) {
            testing = false;
        }
        Assertions.assertFalse(testing);

        if (test1.getMaxY() > forbiddenPath3.getMaxY()) {
            testing = false;
        }
        Assertions.assertFalse(testing);

        if (test1.getMaxX() > forbiddenPath3.getMinX()
                && test1.getMaxX() < forbiddenPath3.getMinX() + forbiddenPath3.getWidth()
                && test1.getMinY() > forbiddenPath3.getMaxY()) {
            testing = false;
        }
        Assertions.assertFalse(testing);
    }
}
