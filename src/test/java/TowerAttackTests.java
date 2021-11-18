import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.almasb.fxgl.entity.Entity;

public class TowerAttackTests {
    @Test
    public void attackDifferenceTest() {
        setupTowers();
        Set<Integer> attacks = new HashSet<>();
        Set<Double> radii = new HashSet<>();

        for (Tower tower : TowerDefense.getTowers()) {
            attacks.add(tower.getDamage());
            radii.add(tower.getRadius());
        }

        Assertions.assertEquals(3, attacks.size());
        Assertions.assertEquals(3, radii.size());
        TowerDefense.setTowers(new ArrayList<Tower>());
    }

    @Test
    public void towerDamageTest() {
        Entity enemy = new Entity();
        enemy.setProperty("health", 10);
        enemy.setPosition(80, 80);

        setupTowers();
        ArrayList<Entity> enemies = new ArrayList<>();
        enemies.add(enemy);
        TowerDefense.setEnemies(enemies);

        for (Tower tower : TowerDefense.getTowers()) {
            try {
                TowerAttackController.attackController(tower);
            } catch (NullPointerException e) {
                continue; //ignoring failure to paste text since that's expected here
            }
        }

        Assertions.assertEquals(10 - 3 - 2 - 1, enemy.getInt("health"));
    }

    private void setupTowers() {
        double x = 100;
        double y = 100;
        Tower tempFireTower = new FireTower();
        tempFireTower.setLocation(x - 20, y - 20);
        tempFireTower.setDelay(2000); //figure out delay
        tempFireTower.setDamage(3); //figure out damage
        tempFireTower.setRadius(150); //figure out radius

        Tower tempEarthTower = new EarthTower();
        tempEarthTower.setLocation(x, y);
        tempEarthTower.setLocation(x - 20, y - 20);
        tempEarthTower.setDelay(2000);
        tempEarthTower.setDamage(2);
        tempEarthTower.setRadius(200);

        Tower tempIceTower = new IceTower();
        tempIceTower.setLocation(x - 20, y - 20);
        tempIceTower.setDelay(1000); //figure out delay
        tempIceTower.setDamage(1); //figure out damage
        tempIceTower.setRadius(125);

        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(tempFireTower);
        towers.add(tempEarthTower);
        towers.add(tempIceTower);

        TowerDefense.setTowers(towers);
    }
}