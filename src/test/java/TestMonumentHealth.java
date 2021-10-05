import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


	public class TestMonumentHealth {
	    private InitialGameScreen init = new InitialGameScreen();

	    @Override
	    public boolean equals(Object o) {
	        if (o instanceof Rectangle) {
	            return init.getInitHealth().getHeight() == ((Rectangle) o).getHeight() && 
	            	   init.getInitHealth().getWidth() == ((Rectangle) o).getWidth() &&
	            	   init.getInitHealth().getFill() == ((Rectangle) o).getFill();
	        } else {
	            return false;
	        }
	    }
	    
	    @Test
	    public void testMonumentHealthInit() {
	        init.setDifficulty(GameDifficulty.EASY);
	        assertEquals(init.getInitHealth(), new Rectangle(100, 30, Color.GREEN));

	        init.setDifficulty(GameDifficulty.MEDIUM);
	        assertEquals(init.getInitHealth(), new Rectangle(75, 30, Color.ORANGE));

	        init.setDifficulty(GameDifficulty.HARD);
	        assertEquals(init.getInitHealth(), new Rectangle(50, 30, Color.RED));
	    }
	

}
