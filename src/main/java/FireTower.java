import javafx.scene.paint.Color;

public class FireTower extends Tower {

	public FireTower() {
	}
	
	@Override
	public String getDescription() {
		return "A Tower made of fire, shooting fireballs at any enemy in sight";
	}

	@Override
	public Color getColor() { return Color.RED; }
}
