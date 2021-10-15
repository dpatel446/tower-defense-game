import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class TestMonumentHealth {
    private TowerDefense init = new TowerDefense();

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rectangle) {
            return init.getInitHealth().getHeight() == ((Rectangle) o).getHeight()
                    && init.getInitHealth().getWidth() == ((Rectangle) o).getWidth()
                    && init.getInitHealth().getFill() == ((Rectangle) o).getFill();
        } else {
            return false;
        }
    }


    @Test
    public void testMonumentHealth2() {
        init.setDifficulty(GameDifficulty.EASY);
        assertThat(init.getInitHealth().getWidth() == 100 && init.getInitHealth().getHeight() == 30
                && init.getInitHealth().getFill() == Color.GREEN, is(true));

        init.setDifficulty(GameDifficulty.MEDIUM);
        assertThat(init.getInitHealth().getWidth() == 75 && init.getInitHealth().getHeight() == 30
                && init.getInitHealth().getFill() == Color.ORANGE, is(true));

        init.setDifficulty(GameDifficulty.HARD);
        assertThat(init.getInitHealth().getWidth() == 50 && init.getInitHealth().getHeight() == 30
                && init.getInitHealth().getFill() == Color.RED, is(true));
    }
}