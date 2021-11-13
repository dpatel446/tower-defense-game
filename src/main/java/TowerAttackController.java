import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class TowerAttackController {

    public static void attackController(Tower tower) {
        attackInRange(tower.getDamage(), tower.getLocation(), tower.getRadius());
    }

    public static void attackInRange(int damage, Point2D towerLocation, double radius) {
        ArrayList<Entity> enemies = new ArrayList<>(TowerDefense.getEnemies());

        for (Entity enemy : enemies) {
            if (towerLocation.distance(enemy.getPosition()) <= radius) {
                //damage enemy
                enemy.setProperty("health", enemy.getInt("health") - damage);
                if (enemy.getInt("health") <= 0) {
                    enemy.removeFromWorld(); //bye bye
                    TowerDefense.getEnemies().remove(enemy);
                }
            }
        }
    }

}
