import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;


public class InitialGameScreen extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1050);
        gameSettings.setHeight(700);
    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundRepeat("Map2.png");
        moneyBox(getAppWidth() / 2 - 100, 0);
    }

    @Override
    protected void initUI() {
        Text money = getUIFactoryService().newText("", Color.BLACK, 22);
        Text moneyText = getUIFactoryService().newText("Money: ", Color.BLACK, 22);
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
        vars.put("money_count", 100);
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
