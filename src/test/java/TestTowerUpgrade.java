import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.geometry.Point2D;
import java.util.ArrayList;

public class TestTowerUpgrade {

	@Test
	public void upgradeTowerTest() {
		TowerDefense.setTesting(true);
		TowerDefense.setDebugLocation(new Point2D(0, 0));

		ArrayList<Tower> towers = new ArrayList<>();
		IceTower tempIceTower = new IceTower();
		tempIceTower.setLocation(10, 10);
        tempIceTower.setDelay(1000);
        tempIceTower.setDamage(1);
        tempIceTower.setRadius(125);
        towers.add(tempIceTower);
		TowerDefense.setTowers(towers);

		TowerDefense.setMoney(100);
		(new TowerDefense()).upgradeTower();

		Assertions.assertEquals(TowerDefense.getTowers().get(0).getDamage(), 2);
		TowerDefense.setTesting(false);
	}

	@Test
	public void locationResolutionTest() {
		TowerDefense.setTesting(true);
		TowerDefense.setDebugLocation(new Point2D(0, 0));

		ArrayList<Tower> towers = new ArrayList<>();
		IceTower tempIceTower = new IceTower();
		tempIceTower.setLocation(10, 10);
		tempIceTower.setDelay(1000);
		tempIceTower.setDamage(1);
		tempIceTower.setRadius(125);
		towers.add(tempIceTower);

		IceTower tempIceTower2 = new IceTower();
		tempIceTower2.setLocation(15, 15);
		tempIceTower2.setDelay(1000);
		tempIceTower2.setDamage(1);
		tempIceTower2.setRadius(125);
		towers.add(tempIceTower2);
		TowerDefense.setTowers(towers);

		TowerDefense.setMoney(100);
		(new TowerDefense()).upgradeTower();

		Assertions.assertEquals(2, TowerDefense.getTowers().get(0).getDamage());
		Assertions.assertEquals(1, TowerDefense.getTowers().get(1).getDamage());
		TowerDefense.setTesting(false);
	}
}