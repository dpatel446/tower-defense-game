import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMonumentDamaged {
    @Test
    public void testMonumentDamage() {
        int startingHealth = 100;
        int numAttacks = 10;
        TowerDefense.setHealth(startingHealth);
        Assertions.assertEquals(startingHealth, TowerDefense.getHealth().intValue());
        for (int i = 0; i < numAttacks; i++) {
            TowerDefense.attackMonument(5); // -5 hp per attack
            Assertions.assertEquals(startingHealth - (5 * (i + 1)),
                    TowerDefense.getHealth().intValue());
        }
    }

    @Test
    public void testMonumentDestroyed() {
        TowerDefense.setHealth(0);
        GruntEnemyFunctionality test = new GruntEnemyFunctionality();
        Assertions.assertTrue(test.towerDead());
        TowerDefense.setHealth(4);
        TowerDefense.attackMonument(5);
        Assertions.assertTrue(test.towerDead());
        TowerDefense.setHealth(5);
        TowerDefense.attackMonument(5);
        Assertions.assertTrue(test.towerDead());
    }
}
