import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameController;

public class BruteEnemyFunctionality extends Component {
    private List<Point2D> gamePathing;
    private Point2D personalPathing;
    private double speed;

    @Override
    public void onAdded() {
        gamePathing = TowerDefense.getPath();
        personalPathing = gamePathing.remove(0);
    }

    @Override
    public void onUpdate(double tpf) {
        speed = tpf * 100;
        Point2D velocity = personalPathing
                .subtract(entity.getPosition())
                .normalize()
                .multiply(speed);
        entity.translate(velocity);

        if (personalPathing.distance(entity.getPosition()) < speed) {
            entity.setPosition(personalPathing);

            if (!gamePathing.isEmpty()) {
                personalPathing = gamePathing.remove(0);
            } else {
                entity.removeFromWorld();
                gamePathing.removeAll(gamePathing);
                if (!towerDead()) {
                    TowerDefense.attackMonument(20);
                }
                if (towerDead()) {
                    Consumer<Boolean> consumer1 = ConsumerInterfaceExample::handleInput;
                    showConfirm("Restart?", consumer1);
                }
            }
        }
    }

    public boolean towerDead() {
        return TowerDefense.getHealth().intValue() <= 0;
    }

    private static class ConsumerInterfaceExample {
        static void handleInput(boolean input) {
            if (input) {
                TowerDefense.setIsStarted(false);
                for (Tower t : TowerDefense.getTowers()) {
                    t.setDelay(0L);
                }
                TowerDefense.setTowers(new ArrayList<Tower>());
                //clear waypoints list of enemies
                getGameController().gotoMainMenu();
            } else {
                getGameController().exit();
            }
        }
    }
}