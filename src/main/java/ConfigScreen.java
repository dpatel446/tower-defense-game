import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.StringBinding;
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
    private String baseTextField = "Enter your name here";

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

        Button btnEasy = new Button("Easy");

        btnEasy.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setDifficulty(GameDifficulty.EASY);
            }
        );

        Button btnMedium = new Button("Medium");

        btnMedium.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setDifficulty(GameDifficulty.MEDIUM);
            }
        );

        Button btnHard = new Button("Hard");

        btnHard.setOnAction(
            (ActionEvent e) -> {
                TowerDefense.setDifficulty(GameDifficulty.HARD);
            }
        );

        Label lblPlayer = new Label("Player Name: ");
        Label lblBoundPlayer = new Label();
        //Label lblDifficulty = new Label("Difficulty Selected: ");
        //Label lblBoundDifficulty = new Label();
        HBox playerBox = new HBox(lblPlayer, lblBoundPlayer);
        //HBox difficultyBox = new HBox(lblDifficulty, lblBoundDifficulty);

        lblBoundPlayer.textProperty().bind(TowerDefense.getName());
        //lblBoundDifficulty.textProperty().bind(TowerDefense.difficulty);

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
