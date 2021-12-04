import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameController;

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
                    TowerDefense.setMoney(TowerDefense.getMoney().intValue() + 2);
                    TowerDefense.setEnemiesKilled(TowerDefense.getEnemiesKilled() + 1);
                    if (enemy.getBoolean("boss")) {
                        TowerDefense.getFXGameTimer().runOnceAfter(() -> {
                            Consumer<Boolean> consumer2 = ConsumerInterfaceExample::handleInput;
                            showConfirm("YOU WIN" +"\n" +
                                    "Enemies Killed: " + TowerDefense.getEnemiesKilled() + "\n"+
                                    "Money Gained: " + 2*TowerDefense.getEnemiesKilled() + "\n"+
                                    String.format("Game Time: %.2f", TowerDefense.getGameTime()) + "\n"+
                                    "RESTART?", consumer2);
                        }, Duration.millis(250));
                    }
                    enemy.removeFromWorld(); //bye bye
                    TowerDefense.getEnemies().remove(enemy);

                }
            }
        }
    }

    public static void removeText() {
        TowerDefense.getPane().getChildren()
                .removeIf(obj -> (obj.getClass().toString().equals("text")));
    }
    private static class ConsumerInterfaceExample {
        static void handleInput(boolean input) {
            if (input) {
                TowerAttackController.removeText();
                TowerDefense.setIsStarted(false);
                TowerDefense.setBossSpawned(false);
                TowerDefense.setEnemiesKilled(0);
                for (Tower t : TowerDefense.getTowers()) {
                    t.setDelay(0L);
                }
                TowerDefense.setTowers(new ArrayList<Tower>());
                //clear waypoints list of enemies
                TowerDefense.setEnemies(new ArrayList<Entity>());
                getGameController().gotoMainMenu();
            } else {
                getGameController().exit();
            }
        }
    }

}
