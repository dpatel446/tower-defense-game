import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.time.Timer;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TowerDefense extends GameApplication {
    private static final TowerDefenseFactory TOWER_DEFENSE_FACTORY = new TowerDefenseFactory();

    private static StringProperty name = new SimpleStringProperty("");
    private static GameDifficulty difficulty = GameDifficulty.EASY;
    private static IntegerProperty money = new SimpleIntegerProperty(0);
    private static IntegerProperty health = new SimpleIntegerProperty(0);
    private static BooleanProperty isStarted = new SimpleBooleanProperty(false);
    private static GameSettings gameSettings;
    private static BooleanProperty bossSpawned = new SimpleBooleanProperty(false);



    private static String baseTextField = "Enter your name here";

    private static IntegerProperty iceTowerTokens = new SimpleIntegerProperty(0);
    private static IntegerProperty earthTowerTokens = new SimpleIntegerProperty(0);
    private static IntegerProperty fireTowerTokens = new SimpleIntegerProperty(0);

    private static Tower towerSelected = null;
    private static List<Point2D> path = new ArrayList<>();
    private static Button testIceStore;
    private static Button testEarthStore;
    private static Button testFireStore;
    private static Button testIceStored;
    private static Button testEarthStored;
    private static Button testFireStored;
    private static ArrayList<Tower> towerType = new ArrayList<>();
    private static boolean testing = false;
    private Point2D origin = new Point2D(175, 45);
    private static ArrayList<Tower> towers = new ArrayList<>();
    private static ArrayList<Entity> enemies = new ArrayList<>();
    private static int enemiesKilled = 0;
    private static Point2D debugLocation = null;

    private static Pane pane;

    private static Timer gameTimer;

    public static int getInitMoney() {
        if (difficulty == GameDifficulty.EASY) {
            return 500;
        } else if (difficulty == GameDifficulty.MEDIUM) {
            return 300;
        } else {
            return 100;
        }
    }

    public static IntegerProperty getMoney() {
        return TowerDefense.money;
    }

    public static void setMoney(int m) {
        TowerDefense.money.setValue(m);
    }

    public static boolean getIsStarted() {
        return TowerDefense.isStarted.getValue().booleanValue();
    }

    public static void setIsStarted(boolean b) {
        TowerDefense.isStarted.setValue(b);
    }

    public static boolean getbossSpawned() {
        return TowerDefense.bossSpawned.getValue().booleanValue();
    }

    public static void setBossSpawned(boolean b) {
        TowerDefense.bossSpawned.setValue(b);
    }

    public static IntegerProperty getHealth() {
        return TowerDefense.health;
    }

    public static void setHealth(int m) {
        TowerDefense.health.setValue(m);
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

    public static List<Point2D> getPath() {
        return new ArrayList<>(path);
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

    public static double getGameTime() { return TowerDefense.gameTimer.getNow(); }

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

    public static ArrayList<Tower> getTowersType() {
        return towerType;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void attackMonument(int damage) {
        TowerDefense.setHealth(TowerDefense.getHealth().intValue() - damage);
    }

    public static void setTesting(boolean testing) {
        TowerDefense.testing = testing;
    }

    public static void setDebugLocation(Point2D location) {
        TowerDefense.debugLocation = location;
    }

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
        setInitHealth();
        gameTimer = getGameTimer();

        Rectangle healthBar = getInitHealth();

        healthBar.widthProperty().bind(TowerDefense.health);

        path = new ArrayList<Point2D>();

        path.addAll(Arrays.asList(
                new Point2D(origin.getX(), origin.getY() + 550),
                new Point2D(origin.getX() + 770, origin.getY() + 550),
                new Point2D(origin.getX() + 770, origin.getY())
        ));
        pane = getGameScene().getContentRoot();
        iceTowerTokens.setValue(0);
        earthTowerTokens.setValue(0);
        fireTowerTokens.setValue(0);
        towers.clear();
        enemies.clear();
        TowerDefense.setEnemiesKilled(0);

        entityBuilder()
                .at(900, 0)
                .view("TechTower.jpg")
                .buildAndAttach();

        entityBuilder()
                .at(900, 100)
                .view(healthBar)
                .buildAndAttach();
    }

    public void spawnEnemy() {
        Random rand = new Random();
        int enemyTicket = rand.nextInt(3);
        Entity enemy = null;
        if (bossSpawned.getValue()) {
            return;
        }
        if (enemiesKilled > 12 & !bossSpawned.getValue()) {
            enemy = spawn("boss enemy", origin.getX(), origin.getY());
            enemy.setProperty("health", 50);
            enemy.setProperty("boss", true);
            enemies.add(enemy);
            setBossSpawned(true);
            return;
        }
        switch (enemyTicket) {
        case 0:
            enemy = spawn("grunt enemy", origin.getX(), origin.getY());
            enemy.setProperty("health", 5);
            enemy.setProperty("boss", false);
            enemies.add(enemy);
            break;
        case 1:
            enemy = spawn("brute enemy", origin.getX(), origin.getY());
            enemy.setProperty("health", 10);
            enemy.setProperty("boss", false);
            enemies.add(enemy);
            break;
        case 2:
            enemy = spawn("tactical enemy", origin.getX(), origin.getY());
            enemy.setProperty("health", 8);
            enemy.setProperty("boss", false);
            enemies.add(enemy);
            break;
        default:
            //checkstyle
        }
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

    public void setInitMoney() {
        if (difficulty == GameDifficulty.EASY) {
            TowerDefense.money.setValue(500);
        } else if (difficulty == GameDifficulty.MEDIUM) {
            TowerDefense.money.setValue(300);
        } else {
            TowerDefense.money.setValue(100);
        }
    }

    public void setInitHealth() {
        if (difficulty == GameDifficulty.EASY) {
            TowerDefense.health.setValue(100);
        } else if (difficulty == GameDifficulty.MEDIUM) {
            TowerDefense.health.setValue(75);
        } else {
            TowerDefense.health.setValue(50);
        }
    }

    @Override
    protected void initUI() {
        Tower iceTowerObject = new IceTower();
        Tower earthTowerObject = new EarthTower();
        Tower fireTowerObject = new FireTower();
        towerType.add(iceTowerObject);
        towerType.add(earthTowerObject);
        towerType.add(fireTowerObject);

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

        Button startGame = new Button("Start Game");
        startGame.setTranslateX((getAppWidth() / 2) - 50);
        startGame.setTranslateY(300);
        startGame.setOnAction(
            (ActionEvent e) -> {
                if (!TowerDefense.isStarted.getValue().booleanValue()) {
                    startCombat();
                }
            }
        );
        getGameScene().addUINodes(moneyDisplay, moneyText, towerStorage, startGame);

    }

    public void startCombat() {
        getGameTimer().runAtInterval(
                this::spawnEnemy,
                Duration.seconds(1.5)
        );
        setIsStarted(true);
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
                upgradeTower();
            }
        }, MouseButton.PRIMARY);
    }

    public void upgradeTower() {
        if (towers.size() <= 0) {
            return;
        }
        if (towerSelected == null || ((towerSelected instanceof IceTower)
                    && (iceTowerTokens.getValue() <= 0)) || ((towerSelected instanceof EarthTower)
                && (earthTowerTokens.getValue() <= 0)) || ((towerSelected instanceof FireTower)
                && (fireTowerTokens.getValue() <= 0))) {
            ArrayList<Tower> inUpgradeRange = new ArrayList<>();
            Point2D mouseLocation;
            if (TowerDefense.testing) {
                mouseLocation = debugLocation;
            } else {
                mouseLocation = getInput().getMousePositionWorld();
            }
            for (Tower tower : towers) {
                if (tower.getLocation().distance(mouseLocation) <= 20.0) {
                    if (money.getValue() >= tower.getCost()) {
                        inUpgradeRange.add(tower);
                    } else {
                        break;
                    }
                }
            }
            if (inUpgradeRange.size() == 0) {
                return;
            }
            double minDistance = Double.MAX_VALUE;
            Tower closest = null;
            for (Tower tower : inUpgradeRange) {
                if (tower.getLocation().distance(mouseLocation) < minDistance) {
                    closest = tower;
                }
            }
            if (closest == null) {
                throw new UnknownError("Closest tower had distance greater than Double.MAX_VALUE");
            } else {
                if (money.getValue() >= closest.getCost()) {
                    closest.setDamage(closest.getDamage() + 1);
                    money.setValue(money.getValue() - closest.getCost());
                }

            }
        }
    }

    private void towerSpawner() {
        if (towerSelected != null) {
            if ((towerSelected instanceof IceTower)
                    && (iceTowerTokens.getValue() > 0)) {
                Tower tempIceTower = new IceTower();
                double x = getInput().getMouseXWorld();
                double y = getInput().getMouseYWorld();
                spawn("ice tower",
                        new SpawnData(x, y)
                                .put("color", tempIceTower.getColor())
                );
                tempIceTower.setLocation(x + 20, y + 20);
                tempIceTower.setDelay(1000); //figure out delay
                tempIceTower.setDamage(1); //figure out damage
                tempIceTower.setRadius(125); //figure out radius
                //Circle circle = new Circle(tempIceTower.getRadius(),Color.CYAN);
                //circle.setCenterX(tempIceTower.getLocation().getX());
                //circle.setCenterY(tempIceTower.getLocation().getY());
                //getGameScene().getContentRoot().getChildren().add(circle);
                getGameTimer().runAtInterval(
                        tempIceTower::attack,
                        Duration.millis(tempIceTower.getDelay())
                );
                towers.add(tempIceTower);
                iceTowerTokens.setValue(iceTowerTokens.getValue() - 1);
            } else if ((towerSelected instanceof EarthTower)
                    && (earthTowerTokens.getValue() > 0)) {
                Tower tempEarthTower = new EarthTower();
                double x = getInput().getMouseXWorld();
                double y = getInput().getMouseYWorld();
                spawn("earth tower",
                        new SpawnData(x, y)
                                .put("color", tempEarthTower.getColor())
                );
                tempEarthTower.setLocation(x, y);
                tempEarthTower.setLocation(x + 20, y + 20);
                tempEarthTower.setDelay(2000); //figure out delay
                tempEarthTower.setDamage(2); //figure out damage
                tempEarthTower.setRadius(200); //figure out radius
                //Circle circle = new Circle(tempEarthTower.getRadius(),Color.BLACK);
                //circle.setCenterX(tempEarthTower.getLocation().getX());
                //circle.setCenterY(tempEarthTower.getLocation().getY());
                //getGameScene().getContentRoot().getChildren().add(circle);
                getGameTimer().runAtInterval(
                        tempEarthTower::attack,
                        Duration.millis(tempEarthTower.getDelay())
                );
                towers.add(tempEarthTower);
                earthTowerTokens.setValue(earthTowerTokens.getValue() - 1);
            } else if ((towerSelected instanceof FireTower)
                    && (fireTowerTokens.getValue() > 0)) {
                Tower tempFireTower = new FireTower();
                double x = getInput().getMouseXWorld();
                double y = getInput().getMouseYWorld();
                spawn("fire tower",
                        new SpawnData(x, y)
                                .put("color", tempFireTower.getColor())
                );
                tempFireTower.setLocation(x + 20, y + 20);
                tempFireTower.setDelay(2000); //figure out delay
                tempFireTower.setDamage(3); //figure out damage
                tempFireTower.setRadius(150); //figure out radius
                //Circle circle = new Circle(tempFireTower.getRadius(),Color.RED);
                //circle.setCenterX(tempFireTower.getLocation().getX());
                //circle.setCenterY(tempFireTower.getLocation().getY());
                //getGameScene().getContentRoot().getChildren().add(circle);
                getGameTimer().runAtInterval(
                        tempFireTower::attack,
                        Duration.millis(tempFireTower.getDelay())
                );
                towers.add(tempFireTower);
                fireTowerTokens.setValue(fireTowerTokens.getValue() - 1);
            } else {
                return;
            }
        }
    }

    public static Pane getPane() {
        return pane;
    }

    public static ArrayList<Tower> getTowers() {
        return towers;
    }

    public static ArrayList<Entity> getEnemies() {
        return enemies;
    }

    public static void setTowers(ArrayList<Tower> towers) {
        TowerDefense.towers = towers;
    }

    public static void setEnemies(ArrayList<Entity> enemies) {
        TowerDefense.enemies = enemies;
    }

    public static int getEnemiesKilled() {
        return enemiesKilled;
    }

    public static void setEnemiesKilled(int kill) {
        TowerDefense.enemiesKilled = kill;
    }

    public static Timer getFXGameTimer() {
        return gameTimer;
    }

    //Just a background box
    private Entity moneyBox(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .type(EntityType.MONEY)
                .viewWithBBox(new Rectangle(200, 50, Color.GOLD))
                .buildAndAttach();
    }
}
