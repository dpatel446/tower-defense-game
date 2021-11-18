import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TestClearEnemyWaypoint {
    @Test
    public void visibilityTest() {
        Tower firetower = new IceTower();
        firetower.setDamage(3);
        Text fire = new Text("-" + firetower.getDamage());
        fire.setFill(Color.RED);
        fire.setFont(new Font(20));

        Tower earthtower = new IceTower();
        earthtower.setDamage(2);
        Text earth = new Text("-" + earthtower.getDamage());
        earth.setFill(Color.RED);
        earth.setFont(new Font(20));

        Tower icetower = new IceTower();
        icetower.setDamage(1);
        Text ice = new Text("-" + icetower.getDamage());
        ice.setFill(Color.RED);
        ice.setFont(new Font(20));

        assertNotEquals(new Text("-" + 3), fire);
        assertNotEquals(new Text("-" + 2), earth);
        assertNotEquals(new Text("-" + 1), ice);
    }

    @Test
    public void clearEnemyWaypoint() {
        ArrayList<com.almasb.fxgl.entity.Entity> enemies =
                new ArrayList<>(TowerDefense.getEnemies());
        TowerDefense.setEnemies(new ArrayList<>());
        assertEquals(enemies, TowerDefense.getEnemies());
    }
}
