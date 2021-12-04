import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class TowerAttackController {

    public static void attackController(Tower tower) {
        attackInRange(tower.getDamage(), tower.getLocation(), tower.getRadius());
    }

    public synchronized static void attackInRange(int damage, Point2D towerLocation, double radius) {
        ArrayList<Entity> enemies = new ArrayList<>(TowerDefense.getEnemies());

        for (Entity enemy : enemies) {
            if (towerLocation.distance(enemy.getPosition()) <= radius) {
                //damage enemy
                try {
                    enemy.setProperty("health", enemy.getInt("health") - damage);
                } catch (IllegalArgumentException e) {
                    enemy.setProperty("health", -1);
                    TowerDefense.getEnemies().remove(enemy);
                    continue;
                }
                Text text = new Text(enemy.getX(), enemy.getY(), "-" + damage);
                text.setFont(new Font(20));
                text.setFill(Color.RED);
                TowerDefense.getPane().getChildren().add(text);
                TowerDefense.getFXGameTimer().runOnceAfter(() -> {
                    //text.setVisible(false);
                    TowerDefense.getPane().getChildren().remove(text);
                }, Duration.millis(200));
                //^^really basic enemy hit visual

                if (enemy.getInt("health") <= 0) {
                    enemy.removeFromWorld(); //bye bye
                    TowerDefense.getEnemies().remove(enemy);
                    TowerDefense.setMoney(TowerDefense.getMoney().intValue() + 2);
                    TowerDefense.setEnemiesKilled(TowerDefense.getEnemiesKilled() + 1);
                }
            }
        }
    }

    public static void removeText() {
        TowerDefense.getPane().getChildren()
                .removeIf(obj -> (obj.getClass().toString().equals("text")));
    }

}
