import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TowerMenuTest {

    @Test
    public void testShopTowersAttributes() {
        new TowerDefense().initUI();
        ArrayList<Tower> towers = TowerDefense.getTowers();
        for (Tower tower: towers) {
            Assertions.assertNotNull(tower);
            Assertions.assertNotNull(tower.getName());
            Assertions.assertNotNull(tower.getDescription());
        }
    }

    @Test
    public void testShopTowersDifference() {
        ArrayList<Tower> towers = TowerDefense.getTowers();
        Set<String> descriptions = new HashSet<String>();
        for (Tower tower: towers) {
            Assertions.assertNotNull(tower);
            Assertions.assertNotNull(tower.getDescription());
            descriptions.add(tower.getDescription());
        }
        Assertions.assertTrue(descriptions.size() >= 3);
    }
}
