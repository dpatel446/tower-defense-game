import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameStats {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testEnemiesKilled() {
        init.setDifficulty(GameDifficulty.MEDIUM);
        init.setIsStarted(false);
        TowerDefense.setEnemiesKilled(0);
        Tower t = new FireTower();
        t.setLocation(175, 60);
        t.setDamage(100);
        t.setRadius(500);
        TowerDefense.setEnemiesKilled(TowerDefense.getEnemiesKilled() + 1);
        assertEquals(1, TowerDefense.getEnemiesKilled());

    }
    @Test
    public void testMoneyGained() {
        TowerDefense.setMoney(500);
        TowerDefense.setEnemiesKilled(0);
        TowerDefense.setEnemiesKilled(0);
        Tower t = new FireTower();
        t.setLocation(175, 60);
        t.setDamage(100);
        t.setRadius(500);
        TowerDefense.setEnemiesKilled(TowerDefense.getEnemiesKilled() + 1);
        int gain = TowerDefense.getEnemiesKilled() * 2;
        assertEquals(2, gain);
    }

}
