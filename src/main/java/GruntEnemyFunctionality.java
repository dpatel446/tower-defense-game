import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameController;

public class GruntEnemyFunctionality extends Component {
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
        speed = tpf * 150;
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
                if (!isDead()) {
                    TowerDefense.attackMonument(2);
                }
                if (isDead()) {
                    Consumer<Boolean> consumer1 = ConsumerInterfaceExample::handleInput;
                    showConfirm("Restart?", consumer1);
                }
            }
        }
    }

    public boolean isDead() {
        return TowerDefense.getHealth().intValue() <= 0;
    }

    private static class ConsumerInterfaceExample {
        static void handleInput(boolean input) {
            if (input) {
                TowerDefense.setIsStarted(false);
                //clear waypoints list of enemies
                getGameController().gotoMainMenu();
            } else {
                getGameController().exit();
            }
        }
    }
}