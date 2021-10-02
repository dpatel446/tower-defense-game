package com.aawal7.myGame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class InitialGameScreen extends GameApplication{

	@Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1050);
        settings.setHeight(700);
        settings.setTitle("Initial Game Screen");
    }

    @Override
    protected void initGame() {
    	
    	Rectangle healthBarEasy = new Rectangle(100, 30, Color.GREEN);
    	Rectangle healthBarMedium = new Rectangle(75, 30, Color.ORANGE);
    	Rectangle healthBarHard = new Rectangle(50, 30, Color.RED);
    	
    	
        FXGL.entityBuilder()
                .at(900, 100)
                .view("TechTower.jpg")
                .buildAndAttach();

        FXGL.entityBuilder()
                .at(900, 50)
                .view(healthBarEasy)
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
