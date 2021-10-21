import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TowerCostAndTypeTest {
    private TowerDefense init = new TowerDefense();
    GameDifficulty difficulty;

    @Test
    public void TestTowerCost() {
        Tower fire = new FireTower();
        Tower ice = new IceTower();
        Tower earth = new EarthTower();

        if (difficulty == GameDifficulty.EASY) {
            assertEquals(fire.getCost(), 10);
            assertEquals(ice.getCost(), 10);
            assertEquals(earth.getCost(), 10);
        }

        else if (difficulty == GameDifficulty.MEDIUM) {
            assertEquals(fire.getCost(), 20);
            assertEquals(ice.getCost(), 20);
            assertEquals(earth.getCost(), 20);
        }    	

        else if (difficulty == GameDifficulty.HARD){
            assertEquals(fire.getCost(), 30);
            assertEquals(ice.getCost(), 30);
            assertEquals(earth.getCost(), 30);
        }
    }

    @Test
    public void TestTowerType() {
        Tower fire = new FireTower();
    	assertEquals(fire.getDescription(), "A Tower made of fire, shooting fireballs at any enemy in sight");

    	Tower ice = new IceTower();
    	assertEquals(ice.getDescription(), "A Tower made of ice, shooting snowballs at any enemy in sight");

        Tower earth = new EarthTower();
        assertEquals(earth.getDescription(), "A Tower made of earth, shooting rocks at any enemy in sight");
    }

}
