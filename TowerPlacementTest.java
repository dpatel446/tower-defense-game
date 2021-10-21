import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TowerPlacementTest {
    @Test
    public void testOnMap() {
        Rectangle2D forbiddenPath1 = new Rectangle2D(100.0, 0.0, 130.0, 630.0);
        Rectangle2D forbiddenPath2 = new Rectangle2D(210.0, 520.0, 770.0, 130.0);
        Rectangle2D forbiddenPath3 = new Rectangle2D(870.0, 0.0, 130.0, 560.0);
        Rectangle2D test1 = new Rectangle2D(300, 0, 20, 20);
        boolean testing = false;

        if ((test1.getMaxX() < forbiddenPath3.getMinX() || test1.getMinX() > forbiddenPath3.getMaxX())
                && (test1.getMinX() > forbiddenPath1.getMaxX() || test1.getMaxX() < forbiddenPath1.getMinX())
                && (test1.getMaxY() < forbiddenPath2.getMinX() || test1.getMinY() > forbiddenPath2.getMaxY())) {
            testing = true;
        }
        Assertions.assertTrue(testing);
    }

    @Test
    public void testOnPath() {
        Rectangle2D forbiddenPath1 = new Rectangle2D(100.0, 0.0, 130.0, 630.0);
        Rectangle2D forbiddenPath2 = new Rectangle2D(210.0, 520.0, 770.0, 130.0);
        Rectangle2D forbiddenPath3 = new Rectangle2D(870.0, 0.0, 130.0, 560.0);
        boolean testing = true;

        Rectangle2D test1 = new Rectangle2D(115, 50, 20, 20);

        if ((test1.getMaxX() > forbiddenPath1.getMinX() && test1.getMaxX() < forbiddenPath1.getMaxX())
                || (test1.getMaxX() > forbiddenPath1.getMinX() && test1.getMinX() < forbiddenPath1.getMaxX())
                || (test1.getMaxX() > forbiddenPath3.getMinX() && test1.getMaxX() < forbiddenPath3.getMaxX())
                || (test1.getMinY() > forbiddenPath2.getMinY() || test1.getMaxY() < forbiddenPath2.getMaxY())) {
            testing = false;
        }
        Assertions.assertFalse(testing);
    }
}
