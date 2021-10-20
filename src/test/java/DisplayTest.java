import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

public class DisplayTest {
    private TowerDefense init = new TowerDefense();
    //Rectangle healthbar = init.getInitHealth();

    @Test
    public void displayMonumentHealth() {
        init.setDifficulty(GameDifficulty.EASY);
        assertEquals(100, init.getInitHealth().getWidth());
        assertEquals(Color.GREEN, init.getInitHealth().getFill());

        init.setDifficulty(GameDifficulty.MEDIUM);
        assertEquals(75, init.getInitHealth().getWidth());
        assertEquals(Color.ORANGE, init.getInitHealth().getFill());

        init.setDifficulty(GameDifficulty.HARD);
        assertEquals(50, init.getInitHealth().getWidth());
        assertEquals(Color.RED, init.getInitHealth().getFill());
    }
}
