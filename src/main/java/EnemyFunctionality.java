import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.List;


public class EnemyFunctionality extends Component {
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
        speed = tpf * 60 * 2;
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
                //POSSIBLE CODE LOCATION FOR ATTACKING FUNCTION
            }
        }
    }
}
