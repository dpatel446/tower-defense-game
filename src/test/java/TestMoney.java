import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestMoney {
    private TowerDefense init = new TowerDefense();

    @Test
    public void testMoneyInit() {
        init.setDifficulty(GameDifficulty.EASY);
        assertThat(init.getInitMoney() == 500, is(true));

        init.setDifficulty(GameDifficulty.MEDIUM);
        assertThat(init.getInitMoney() == 300, is(true));

        init.setDifficulty(GameDifficulty.HARD);
        assertThat(init.getInitMoney() == 100, is(true));
    }
}
