import javafx.scene.paint.Color;

public class EarthTower extends Tower {

    public EarthTower() {
        super("Earth Tower", "Earth", TowerDefense.getDifficulty());
    }

    @Override
    public String getDescription() {
        return "A Tower made of earth, shooting rocks at any enemy in sight";
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}
