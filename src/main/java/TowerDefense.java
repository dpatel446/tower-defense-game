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
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TowerDefense extends GameApplication {
    private static StringProperty name = new SimpleStringProperty("");
    private static GameDifficulty difficulty = GameDifficulty.EASY;
    private static IntegerProperty money = new SimpleIntegerProperty(0);
    private static GameSettings gameSettings;

    private static String baseTextField = "Enter your name here";

    private static IntegerProperty earthTowerTokens = new SimpleIntegerProperty(0);
    private static IntegerProperty fireTowerTokens = new SimpleIntegerProperty(0);
    private static IntegerProperty iceTowerTokens = new SimpleIntegerProperty(0);

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

    public static int getInitMoney() {
        if (difficulty == GameDifficulty.EASY) {
            return 500;
        } else if (difficulty == GameDifficulty.MEDIUM) {
            return 300;
        } else {
            return 100;
        }
    }

    public void setInitMoney() {
        if (difficulty == GameDifficulty.EASY) {
            TowerDefense.money.setValue(500);
        } else if (difficulty == GameDifficulty.MEDIUM) {
            TowerDefense.money.setValue(300);
        } else {
            TowerDefense.money.setValue(100);
        }
    }

    public static IntegerProperty getMoney() {
        return TowerDefense.money;
    }

    //Makes the transaction, returns true if it was a success and false if not enough money.
    public static boolean transaction(Tower t) {
        if (getMoney().intValue()>=t.getCost()) {
            TowerDefense.money.setValue(getMoney().intValue()-t.getCost());
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void initUI() {
        //Money******************************************************************************************//
        Text moneyDisplay = FXGL.getUIFactoryService().newText("", Color.BLACK, 22);
        Text moneyText = FXGL.getUIFactoryService().newText("Money: $", Color.BLACK, 22);
        moneyDisplay.setTranslateX(getAppWidth() / 2 + 25);
        moneyDisplay.setTranslateY(25);

        moneyText.setTranslateX(getAppWidth() / 2 - 75);
        moneyText.setTranslateY(25);

        moneyDisplay.textProperty().bind(TowerDefense.money.asString());
        setInitMoney();
        //***********************************************************************************************//

        //Store & Storage********************************************************************************//
        VBox towerStorage = new VBox();

        Tower iceTowerObject = new IceTower();
        Tower earthTowerObject = new EarthTower();
        Tower fireTowerObject = new FireTower();

        Text storeText = FXGL.getUIFactoryService().newText("Store", Color.BLACK, 22);
        Button iceTower = new Button("$" + iceTowerObject.getCost(), new Rectangle(20.0, 20.0, iceTowerObject.getColor()));
        Button earthTower = new Button("$" + earthTowerObject.getCost(), new Rectangle(20.0, 20.0, earthTowerObject.getColor()));
        Button fireTower = new Button("$" + fireTowerObject.getCost(), new Rectangle(20.0, 20.0, fireTowerObject.getColor()));

        iceTower.setOnAction(
            (ActionEvent e) -> {
                if (transaction(iceTowerObject)) {
                    iceTowerTokens.setValue(iceTowerTokens.intValue() + 1);
                    TowerDefense.money.setValue(money.intValue() - iceTowerObject.getCost());
                }
            }
        );

        earthTower.setOnAction(
            (ActionEvent e) -> {
                if (transaction(earthTowerObject)) {
                    earthTowerTokens.setValue(earthTowerTokens.intValue() + 1);
                    TowerDefense.money.setValue(money.intValue() - earthTowerObject.getCost());
                }
            }
        );

        fireTower.setOnAction(
            (ActionEvent e) -> {
                if (transaction(fireTowerObject)) {
                    fireTowerTokens.setValue(fireTowerTokens.intValue() + 1);
                    TowerDefense.money.setValue(money.intValue() - fireTowerObject.getCost());
                }
            }
        );

        Text storageText = FXGL.getUIFactoryService().newText("Storage", Color.BLACK, 22);
        Button iceTowerStored = new Button("", new Rectangle(20.0, 20.0, iceTowerObject.getColor()));
        Button earthTowerStored = new Button("", new Rectangle(20.0, 20.0, earthTowerObject.getColor()));
        Button fireTowerStored = new Button("", new Rectangle(20.0, 20.0, fireTowerObject.getColor()));
        earthTowerStored.textProperty().bind(earthTowerTokens.asString());
        fireTowerStored.textProperty().bind(fireTowerTokens.asString());
        iceTowerStored.textProperty().bind(iceTowerTokens.asString());

        towerStorage.getChildren().addAll(storeText, iceTower, earthTower, fireTower, storageText, iceTowerStored, earthTowerStored, fireTowerStored);

        towerStorage.setAlignment(Pos.CENTER);
        towerStorage.setBackground(new Background(new BackgroundFill(Color.GOLD, new CornerRadii(0), new Insets(5.0))));
        towerStorage.setTranslateX((getAppWidth() / 2) - 50);
        towerStorage.setTranslateY(50);
        //***********************************************************************************************//

        getGameScene().addUINodes(moneyDisplay, moneyText, towerStorage);

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

    public static void setMoney(int m) {
        TowerDefense.money.setValue(m);
    }

    public static GameSettings getGameSettings() {
        return gameSettings;
    }

    public static void main(String[] args) {
        launch(args);
    }
}