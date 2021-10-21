import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class ConfigScreen extends FXGLMenu {
    private String baseTextField = "Enter your name here";

    private RadioButton buttonEasy;
    private RadioButton buttonMedium;
    private RadioButton buttonHard;

    public ConfigScreen() {
        super(MenuType.GAME_MENU);

        HBox renamePanel = new HBox(5);
        TextField renameInput = new TextField();
        renameInput.setPromptText(baseTextField);
        Button btnEnter = new Button("Enter");

        btnEnter.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setName(renameInput.getText());
            }
        );
        renamePanel.getChildren().addAll(renameInput, btnEnter);

        RadioButton btnEasy = new RadioButton("Easy");
        buttonEasy = btnEasy;

        btnEasy.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setDifficulty(GameDifficulty.EASY);
            }
        );

        RadioButton btnMedium = new RadioButton("Medium");
        buttonMedium = btnMedium;

        btnMedium.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setDifficulty(GameDifficulty.MEDIUM);
            }
        );

        RadioButton btnHard = new RadioButton("Hard");
        buttonHard = btnHard;

        btnHard.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setDifficulty(GameDifficulty.HARD);
            }
        );

        ToggleGroup difficultyGroup = new ToggleGroup();
        btnEasy.setToggleGroup(difficultyGroup);
        btnMedium.setToggleGroup(difficultyGroup);
        btnHard.setToggleGroup(difficultyGroup);

        Label lblPlayer = new Label("Player Name: ");
        Label lblBoundPlayer = new Label();
        HBox playerBox = new HBox(lblPlayer, lblBoundPlayer);

        lblBoundPlayer.textProperty().bind(TowerDefense.getName());

        ConfigScreenButton btnProceed = new ConfigScreenButton("Proceed");

        btnProceed.setOnAction(
            () -> {
                if ((TowerDefense.getName().getValue() != null)
                        && (!(TowerDefense.getName().getValue().isEmpty()))
                        && (!(TowerDefense.getName().getValue().trim().isEmpty()))) {
                    fireNewGame();
                }
            }
        );

        VBox initialScreen = new VBox(5, renamePanel, btnEasy, btnMedium, btnHard, playerBox);
        initialScreen.getChildren().addAll(btnProceed);
        initialScreen.setAlignment(Pos.CENTER);

        getContentRoot().getChildren().addAll(initialScreen);
    }



    @NotNull
    @Override
    protected Button createActionButton(@NotNull StringBinding stringBinding,
                                        @NotNull Runnable runnable) {
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

    public RadioButton getButtonEasy() {
        return buttonEasy;
    }

    public RadioButton getButtonMedium() {
        return buttonMedium;
    }

    public RadioButton getButtonHard() {
        return buttonHard;
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
