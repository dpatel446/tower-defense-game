import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class ConfigScreen extends FXGLMenu {
    public String baseTextField = "Enter your name here";

    public ConfigScreen() {
        super(MenuType.GAME_MENU);

        HBox renamePanel = new HBox(5);
        TextField renameInput = new TextField();
        renameInput.setPromptText(baseTextField);
        Button btnEnter = new Button("Enter");

        btnEnter.setOnAction(
                (ActionEvent e) -> {
                    TowerDefense.name.setValue(renameInput.getText());
                }
        );
        renamePanel.getChildren().addAll(renameInput, btnEnter);

        Button btnEasy = new Button("Easy");

        btnEasy.setOnAction(
                (ActionEvent e) -> {
                    TowerDefense.difficulty = GameDifficulty.EASY;
                }
        );

        Button btnMedium = new Button("Medium");

        btnMedium.setOnAction(
                (ActionEvent e) -> {
                    TowerDefense.difficulty = GameDifficulty.MEDIUM;
                }
        );

        Button btnHard = new Button("Hard");

        btnHard.setOnAction(
                (ActionEvent e) -> {
                    TowerDefense.difficulty = GameDifficulty.HARD;
                }
        );

        Label lblPlayer = new Label("Player Name: ");
        Label lblBoundPlayer = new Label();
        //Label lblDifficulty = new Label("Difficulty Selected: ");
        //Label lblBoundDifficulty = new Label();
        HBox playerBox = new HBox(lblPlayer, lblBoundPlayer);
        //HBox difficultyBox = new HBox(lblDifficulty, lblBoundDifficulty);

        lblBoundPlayer.textProperty().bind(TowerDefense.name);
        //lblBoundDifficulty.textProperty().bind(TowerDefense.difficulty);

        ConfigScreenButton btnProceed = new ConfigScreenButton("Proceed");

        btnProceed.setOnAction(
                () -> {
                    if ((TowerDefense.name.getValue() != null)
                            && (!(TowerDefense.name.getValue().isEmpty()))
                            && (!(TowerDefense.name.getValue().trim().isEmpty()))) {
                        fireNewGame();
                    }
                }
        );

        VBox initialScreen = new VBox(5, renamePanel, btnEasy, btnMedium, btnHard, playerBox, btnProceed);
        initialScreen.setAlignment(Pos.CENTER);

        getContentRoot().getChildren().addAll(initialScreen);
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull StringBinding stringBinding, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull String s, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createBackground(double v, double v1) {
        return new Rectangle(1050, 700, Color.LIGHTSLATEGREY);
    }

    @NotNull
    @Override
    protected Node createProfileView(@NotNull String s) {
        return new Rectangle();
    }

    @NotNull
    @Override
    protected Node createTitleView(@NotNull String s) {
        return new Rectangle();
    }

    @NotNull
    @Override
    protected Node createVersionView(@NotNull String s) {
        return new Rectangle();
    }

    private static class ConfigScreenButton extends StackPane {
        private String name;
        private Runnable action;

        private Text text;

        public ConfigScreenButton(String name) {
            this.name = name;
            this.action = action;

            text = getUIFactoryService().newText(name, Color.BLACK, 16);

            setAlignment(Pos.CENTER);

            setOnMouseClicked(e -> {
                action.run();
            });

            getChildren().add(text);
        }

        public ConfigScreenButton(String name, Runnable action) {
            this.name = name;
            this.action = action;

            text = getUIFactoryService().newText(name, Color.BLACK, 16);

            setAlignment(Pos.CENTER);

            setOnMouseClicked(e -> {
                action.run();
            });

            getChildren().add(text);
        }

        public void setOnAction(Runnable action) {
            this.action = action;
        }
    }
}
