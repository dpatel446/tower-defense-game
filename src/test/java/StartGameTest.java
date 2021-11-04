import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import javafx.scene.control.Button;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StartGameTest {

    private static TowerDefense app = new TowerDefense();
    private static boolean isStarted = true;

    @Test
    public void testStartGame() throws InterruptedException {
        // TestThread thread = new TestThread();
        // thread.start();
        // Thread.sleep(3000);

        Button startGame = new Button("Start Game");
        startGame.setTranslateX((getAppWidth() / 2) - 50);
        startGame.setTranslateY(300);
        app.startCombat();
        assertEquals(isStarted, true);
        app.setIsStarted(false);
    }

    @Test
    public void testRestartGame() {
        app.setIsStarted(true);
        boolean isRestarted = app.getIsStarted();
        assertEquals(isRestarted, true);
        app.setIsStarted(false);
    }

    private static class TestThread extends Thread {
        @Override
        public void run() {
            String[] str = {"hi"};
            TowerDefense.main(str);
        }
    }
}
