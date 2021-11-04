import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GarySheppardM4Tests {
    @Test
    public void testPathing() {
        List<Point2D> gamePathing;
        Point2D personalPathing;

        gamePathing = new ArrayList<Point2D>();
        gamePathing.addAll(Arrays.asList(
                new Point2D(0, 0 + 550),
                new Point2D(0 + 770, 0 + 550),
                new Point2D(0 + 770, 0)
        ));

        while (!gamePathing.isEmpty()) {
            personalPathing = gamePathing.remove(0);
            Assertions.assertNotNull(personalPathing);
        }
    }

    @Test
    public void testPathingEnd() {
        boolean doSomething = false;

        List<Point2D> gamePathing;
        Point2D personalPathing;

        gamePathing = new ArrayList<Point2D>();
        gamePathing.addAll(Arrays.asList(
                new Point2D(0, 0 + 550),
                new Point2D(0 + 770, 0 + 550),
                new Point2D(0 + 770, 0)
        ));

        while (!gamePathing.isEmpty()) {
            personalPathing = gamePathing.remove(0);
        }

        if (gamePathing.isEmpty()) {
            doSomething = true;
        }
        Assertions.assertTrue(doSomething);
    }
}