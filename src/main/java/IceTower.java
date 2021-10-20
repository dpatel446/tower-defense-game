import javafx.scene.paint.Color;

public class IceTower extends Tower {

	public IceTower() {
	}
	
	@Override
	public String getDescription() {
		return "A Tower made of ice, shooting snowballs at any enemy in sight";
	}

	@Override
	public Color getColor() { return Color.CYAN; }
}
