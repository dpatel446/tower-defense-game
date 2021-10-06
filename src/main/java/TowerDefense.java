import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TowerDefense extends GameApplication {
    private static StringProperty name = new SimpleStringProperty("");
    private static GameDifficulty difficulty = GameDifficulty.EASY;
    private static IntegerProperty money = new SimpleIntegerProperty(0);
    private static GameSettings gameSettings;

    private static String baseTextField = "Enter your name here";

    @Override
    protected void initSettings(GameSettings gameSettings) {
        TowerDefense.gameSettings = gameSettings;
        gameSettings.setWidth(1050);
        gameSettings.setHeight(700);
        gameSettings.setTitle("Tower Defense");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setGameMenuEnabled(true);
        gameSettings.setSceneFactory(new SceneFactory() {

            @NotNull
            @Override
            public FXGLMenu newMainMenu() {
                return new WelcomeMenu();
            }

            @NotNull
            @Override
            public FXGLMenu newGameMenu() {
                return new ConfigScreen();
            }

        });
    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundRepeat("Map2.png");
        moneyBox(getAppWidth() / 2 - 100, 0);

        Rectangle healthBar = getInitHealth();

        entityBuilder()
                .at(900, 0)
                .view("TechTower.jpg")
                .buildAndAttach();

        entityBuilder()
                .at(900, 100)
                .view(healthBar)
                .buildAndAttach();
    }

    public Rectangle getInitHealth() {
        if (difficulty == GameDifficulty.EASY) {
            return new Rectangle(100, 30, Color.GREEN);
        } else if (difficulty == GameDifficulty.MEDIUM) {
            return new Rectangle(75, 30, Color.ORANGE);
        } else {
            return new Rectangle(50, 30, Color.RED);
        }
    }

    public int getInitMoney() {
        if (difficulty == GameDifficulty.EASY) {
            return 500;
        } else if (difficulty == GameDifficulty.MEDIUM) {
            return 300;
        } else {
            return 100;
        }
    }

    @Override
    protected void initUI() {
        Text money = FXGL.getUIFactoryService().newText("", Color.BLACK, 22);
        Text moneyText = FXGL.getUIFactoryService().newText("Money: ", Color.BLACK, 22);
        money.setTranslateX(getAppWidth() / 2 + 25);
        money.setTranslateY(25);

        moneyText.setTranslateX(getAppWidth() / 2 - 75);
        moneyText.setTranslateY(25);

        money.textProperty().bind(getWorldProperties().intProperty("money_count").asString());
        getGameScene().addUINodes(money, moneyText);
    }

    //Update money_count to show the updated money
    //The integer value is the initial money at setup.
    //Modify it to represent change with the difficulty level.
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("money_count", getInitMoney());
    }

    //Just a background box
    private Entity moneyBox(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .type(EntityType.MONEY)
                .viewWithBBox(new Rectangle(200, 50, Color.GOLD))
                .buildAndAttach();
    }

    public static StringProperty getName() {
        return TowerDefense.name;
    }

    public static void setName(String name) {
        TowerDefense.name.setValue(name);
    }

    public static GameDifficulty getDifficulty() {
        return TowerDefense.difficulty;
    }

    public static void setDifficulty(GameDifficulty difficulty) {
        TowerDefense.difficulty = difficulty;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
