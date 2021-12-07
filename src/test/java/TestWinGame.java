import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWinGame {
    private GameDifficulty difficulty;

    @Test
    public void testWinGame() {
        Tower fire = new FireTower();
        Tower ice = new IceTower();
        Tower earth = new EarthTower();

        if (difficulty == GameDifficulty.EASY) {
            assertEquals(fire.getCost(), 10);
            assertEquals(ice.getCost(), 10);
            assertEquals(earth.getCost(), 10);
        } else if (difficulty == GameDifficulty.MEDIUM) {
            assertEquals(fire.getCost(), 20);
            assertEquals(ice.getCost(), 20);
            assertEquals(earth.getCost(), 20);
        } else if (difficulty == GameDifficulty.HARD) {
            assertEquals(fire.getCost(), 30);
            assertEquals(ice.getCost(), 30);
            assertEquals(earth.getCost(), 30);
        }
    }

    @Test
    public void testGameStats() {
        Tower tempFire = new FireTower();
        Tower tempEarth = new EarthTower();
        Tower tempIce = new IceTower();

        tempFire.setDamage(5);
        assertEquals(tempFire.getDamage(), 5);

        tempEarth.setDamage(35);
        assertEquals(tempEarth.getDamage(), 35);

        tempIce.setDamage(95);
        assertEquals(tempIce.getDamage(), 95);
    }
}

