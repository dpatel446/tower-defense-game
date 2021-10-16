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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
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

    private static IntegerProperty tower1Stored = new SimpleIntegerProperty(0);
    private static IntegerProperty tower2Stored = new SimpleIntegerProperty(0);
    private static IntegerProperty tower3Stored = new SimpleIntegerProperty(0);

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
        //Money******************************************************************************************//
        Text money = FXGL.getUIFactoryService().newText("", Color.BLACK, 22);
        Text moneyText = FXGL.getUIFactoryService().newText("Money: ", Color.BLACK, 22);
        money.setTranslateX(getAppWidth() / 2 + 25);
        money.setTranslateY(25);

        moneyText.setTranslateX(getAppWidth() / 2 - 75);
        moneyText.setTranslateY(25);

        money.textProperty().bind(getWorldProperties().intProperty("money_count").asString());
        //***********************************************************************************************//

        //Store & Storage********************************************************************************//
        VBox towerStorage = new VBox();

        Text storeText = FXGL.getUIFactoryService().newText("Store", Color.BLACK, 22);
        Button tower1 = new Button();
        Button tower2 = new Button();
        Button tower3 = new Button();

        Text storageText = FXGL.getUIFactoryService().newText("Storage", Color.BLACK, 22);
        Button tower1Number = new Button();
        Button tower2Number = new Button();
        Button tower3Number = new Button();
        tower1Number.textProperty().bind(tower1Stored.asString());
        tower2Number.textProperty().bind(tower2Stored.asString());
        tower3Number.textProperty().bind(tower3Stored.asString());

        towerStorage.getChildren().addAll(storeText, tower1, tower2, tower3, storageText, tower1Number, tower2Number, tower3Number);

        towerStorage.setAlignment(Pos.CENTER);
        towerStorage.setBackground(new Background(new BackgroundFill(Color.GOLD, new CornerRadii(0), new Insets(5.0))));
        towerStorage.setTranslateX(getAppWidth() / 2 - 75);
        towerStorage.setTranslateY(50);
        //***********************************************************************************************//

        getGameScene().addUINodes(money, moneyText, towerStorage);
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

    public static GameSettings getGameSettings() {
        return gameSettings;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
