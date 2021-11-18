import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifficultyButtonTest {

    private static TowerDefense app = new TowerDefense();



    @Test
    public void testDifficultyButtons() throws InterruptedException {
        //TestThread thread = new TestThread();
        //thread.start();
        //Thread.sleep(3000);
        //Timeout to allow the FXGL application to initialize its services prior to running test
        ConfigScreen configScreen = new ConfigScreen();
        configScreen.getButtonEasy().fire();
        Assertions.assertEquals(TowerDefense.getDifficulty(), GameDifficulty.EASY);
        Assertions.assertEquals(app.getInitMoney(), 500);
        Assertions.assertEquals(app.getInitHealth().getWidth(), 100);

        configScreen.getButtonMedium().fire();
        Assertions.assertEquals(TowerDefense.getDifficulty(), GameDifficulty.MEDIUM);
        Assertions.assertEquals(app.getInitMoney(), 300);
        Assertions.assertEquals(app.getInitHealth().getWidth(), 75);

        configScreen.getButtonHard().fire();
        Assertions.assertEquals(TowerDefense.getDifficulty(), GameDifficulty.HARD);
        Assertions.assertEquals(app.getInitMoney(), 100);
        Assertions.assertEquals(app.getInitHealth().getWidth(), 50);
    }

    private static class TestThread extends Thread {
        @Override
        public void run() {
            String[] str = {"hi"};
            TowerDefense.main(str);
        }
    }
}
