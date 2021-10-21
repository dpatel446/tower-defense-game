import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TowerDefense extends GameApplication {
    private static final TowerDefenseFactory TOWER_DEFENSE_FACTORY = new TowerDefenseFactory();

    private static StringProperty name = new SimpleStringProperty("");
    private static GameDifficulty difficulty = GameDifficulty.EASY;
    private static IntegerProperty money = new SimpleIntegerProperty(0);
    private static GameSettings gameSettings;

    private static String baseTextField = "Enter your name here";

    private static IntegerProperty iceTowerTokens = new SimpleIntegerProperty(0);
    private static IntegerProperty earthTowerTokens = new SimpleIntegerProperty(0);
    private static IntegerProperty fireTowerTokens = new SimpleIntegerProperty(0);

    private static Tower towerSelected = null;

    private static Button testIceStore;
    private static Button testEarthStore;
    private static Button testFireStore;
    private static Button testIceStored;
    private static Button testEarthStored;
    private static Button testFireStored;

    private static ArrayList<Tower> towers = new ArrayList<Tower>();

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
        getGameWorld().addEntityFactory(TOWER_DEFENSE_FACTORY);
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
        if (getMoney().intValue() >= t.getCost()) {
            TowerDefense.money.setValue(getMoney().intValue() - t.getCost());
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void initUI() {
        Tower iceTowerObject = new IceTower();
        Tower earthTowerObject = new EarthTower();
        Tower fireTowerObject = new FireTower();
        towers.add(iceTowerObject);
        towers.add(earthTowerObject);
        towers.add(fireTowerObject);

        //Money******************************************************************************//
        Text moneyDisplay = FXGL.getUIFactoryService().newText("", Color.BLACK, 22);
        Text moneyText = FXGL.getUIFactoryService().newText("Money: $", Color.BLACK, 22);
        moneyDisplay.setTranslateX(getAppWidth() / 2 + 25);
        moneyDisplay.setTranslateY(25);

        moneyText.setTranslateX(getAppWidth() / 2 - 75);
        moneyText.setTranslateY(25);

        moneyDisplay.textProperty().bind(TowerDefense.money.asString());
        setInitMoney();
        //***********************************************************************************//

        //Store & Storage********************************************************************//
        VBox towerStorage = new VBox();

        Rectangle iceTowerGraphic = new Rectangle(20.0, 20.0, iceTowerObject.getColor());
        Rectangle earthTowerGraphic = new Rectangle(20.0, 20.0, earthTowerObject.getColor());
        Rectangle fireTowerGraphic = new Rectangle(20.0, 20.0, fireTowerObject.getColor());

        Text storeText = FXGL.getUIFactoryService().newText("Store", Color.BLACK, 22);
        Button iceTower = new Button("$" + iceTowerObject.getCost(), iceTowerGraphic);
        testIceStore = iceTower;
        Button earthTower = new Button("$" + earthTowerObject.getCost(), earthTowerGraphic);
        testEarthStore = earthTower;
        Button fireTower = new Button("$" + fireTowerObject.getCost(), fireTowerGraphic);
        testFireStore = fireTower;

        iceTower.setOnAction(
            (ActionEvent e) -> {
                if (transaction(iceTowerObject)) {
                    iceTowerTokens.setValue(iceTowerTokens.intValue() + 1);
                    TowerDefense.money.setValue(money.intValue());
                }
            }
        );

        String iceTHover = iceTowerObject.getName() + ": " + iceTowerObject.getDescription();
        iceTower.setTooltip(new Tooltip(iceTHover));

        earthTower.setOnAction(
            (ActionEvent e) -> {
                if (transaction(earthTowerObject)) {
                    earthTowerTokens.setValue(earthTowerTokens.intValue() + 1);
                    TowerDefense.money.setValue(money.intValue());
                }
            }
        );

        String earthTHover = earthTowerObject.getName() + ": " + earthTowerObject.getDescription();
        earthTower.setTooltip(new Tooltip(earthTHover));

        fireTower.setOnAction(
            (ActionEvent e) -> {
                if (transaction(fireTowerObject)) {
                    fireTowerTokens.setValue(fireTowerTokens.intValue() + 1);
                    TowerDefense.money.setValue(money.intValue());
                }
            }
        );

        String fireTHover = fireTowerObject.getName() + ": " + fireTowerObject.getDescription();
        fireTower.setTooltip(new Tooltip(fireTHover));

        Text storageText = FXGL.getUIFactoryService().newText("Storage", Color.BLACK, 22);
        Button iceTowerStored = new Button("", iceTowerGraphic);
        Button earthTowerStored = new Button("", earthTowerGraphic);
        Button fireTowerStored = new Button("", fireTowerGraphic);
        iceTowerStored.textProperty().bind(iceTowerTokens.asString());
        earthTowerStored.textProperty().bind(earthTowerTokens.asString());
        fireTowerStored.textProperty().bind(fireTowerTokens.asString());

        iceTowerStored.setOnAction(
            (ActionEvent e) -> {
                towerSelected = iceTowerObject;
            }
        );

        earthTowerStored.setOnAction(
            (ActionEvent e) -> {
                towerSelected = earthTowerObject;
            }
        );

        fireTowerStored.setOnAction(
            (ActionEvent e) -> {
                towerSelected = fireTowerObject;
            }
        );

        towerStorage.getChildren().add(storeText);
        towerStorage.getChildren().addAll(iceTower, earthTower, fireTower);
        towerStorage.getChildren().add(storageText);
        towerStorage.getChildren().addAll(iceTowerStored, earthTowerStored, fireTowerStored);

        towerStorage.setAlignment(Pos.CENTER);
        CornerRadii storageCR = new CornerRadii(0);
        Insets storageIS = new Insets(5.0);
        BackgroundFill storageBGFill = new BackgroundFill(Color.GOLD, storageCR, storageIS);
        Background storageBackground = new Background(storageBGFill);
        towerStorage.setBackground(storageBackground);
        towerStorage.setTranslateX((getAppWidth() / 2) - 50);
        towerStorage.setTranslateY(50);
        //***************************************************************************************//

        getGameScene().addUINodes(moneyDisplay, moneyText, towerStorage);

    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Spawn Tower") {
            private Rectangle2D forbiddenPath1 = new Rectangle2D(100.0, 0.0, 130.0, 630.0);
            private Rectangle2D forbiddenPath2 = new Rectangle2D(210.0, 520.0, 770.0, 130.0);
            private Rectangle2D forbiddenPath3 = new Rectangle2D(870.0, 0.0, 130.0, 560.0);

            @Override
            protected void onActionBegin() {
                if (!(forbiddenPath1.contains(input.getMousePositionWorld()))) {
                    if (!(forbiddenPath2.contains(input.getMousePositionWorld()))) {
                        if (!(forbiddenPath3.contains(input.getMousePositionWorld()))) {
                            towerSpawner();
                        }
                    }
                }
            }
        }, MouseButton.PRIMARY);
    }

    private void towerSpawner() {
        if (towerSelected != null) {
            if ((towerSelected instanceof IceTower)
                && (iceTowerTokens.getValue() > 0)) {
                Tower tempIceTower = new IceTower();
                spawn("ice tower",
                        new SpawnData(getInput().getMouseXWorld(), getInput().getMouseYWorld())
                                .put("color", tempIceTower.getColor())
                );
                iceTowerTokens.setValue(iceTowerTokens.getValue() - 1);
            } else if ((towerSelected instanceof EarthTower)
                && (earthTowerTokens.getValue() > 0)) {
                Tower tempEarthTower = new EarthTower();
                spawn("earth tower",
                        new SpawnData(getInput().getMouseXWorld(), getInput().getMouseYWorld())
                                .put("color", tempEarthTower.getColor())
                );
                earthTowerTokens.setValue(earthTowerTokens.getValue() - 1);
            } else if ((towerSelected instanceof FireTower)
                && (fireTowerTokens.getValue() > 0)) {
                Tower tempFireTower = new FireTower();
                spawn("fire tower",
                        new SpawnData(getInput().getMouseXWorld(), getInput().getMouseYWorld())
                                .put("color", tempFireTower.getColor())
                );
                fireTowerTokens.setValue(fireTowerTokens.getValue() - 1);
            } else {
                return;
            }
        }
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

    public static IntegerProperty getIceTowerTokens() {
        return iceTowerTokens;
    }

    public static IntegerProperty getEarthTowerTokens() {
        return earthTowerTokens;
    }

    public static IntegerProperty getFireTowerTokens() {
        return fireTowerTokens;
    }

    public static Button getTestIceStore() {
        return testIceStore;
    }

    public static Button getTestEarthStore() {
        return testEarthStore;
    }

    public static Button getTestFireStore() {
        return testFireStore;
    }

    public static Button getTestIceStored() {
        return testIceStored;
    }

    public static Button getTestEarthStored() {
        return testEarthStored;
    }

    public static Button getTestFireStored() {
        return testFireStored;
    }

    public static ArrayList<Tower> getTowers() {
        return towers;
    }

    public static void main(String[] args) {
        launch(args);
    }
}