import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class WelcomeMenu extends FXGLMenu {
    public WelcomeMenu() {
        super(MenuType.MAIN_MENU);

        Text introText = new Text("Team 83's Tower Defense Game");
        WelcomeMenuButton btnStart = new WelcomeMenuButton("Start",
            () -> getController().gotoGameMenu());
        WelcomeMenuButton btnExit = new WelcomeMenuButton("Exit", () -> fireExit());

        VBox welcome = new VBox(15,
                introText,
                btnStart,
                btnExit);
        welcome.setAlignment(Pos.CENTER);

        getContentRoot().getChildren().addAll(welcome);
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

    private static class WelcomeMenuButton extends StackPane {
        private String name;
        private Runnable action;

        private Text text;

        public WelcomeMenuButton(String name, Runnable action) {
            this.name = name;
            this.action = action;

            text = getUIFactoryService().newText(name, Color.BLACK, 16);

            setAlignment(Pos.CENTER);

            setOnMouseClicked(e -> {
                action.run();
            });

            getChildren().add(text);
        }
    }
}