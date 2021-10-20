import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;

public class TransactionTest {

    @Test
    public void testTransactionMain() {
        TowerDefense.main(new String[0]);
        int smoney = TowerDefense.getMoney().intValue();
        int emoney = TowerDefense.getInitMoney();
        assertThat(smoney == 500, is(true));

        Tower t = new EarthTower();
        TowerDefense.transaction(t);
        emoney = emoney - t.getCost();
        int cmoney = TowerDefense.getMoney().intValue();
        assertEquals(cmoney, emoney);
        assertThat(cmoney==450, is(true));
    }
}
