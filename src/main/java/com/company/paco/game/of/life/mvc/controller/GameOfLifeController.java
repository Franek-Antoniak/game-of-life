package com.company.paco.game.of.life.mvc.controller;

import com.company.paco.game.of.life.mvc.model.GameOfLifeModel;
import com.company.paco.game.of.life.mvc.view.GameOfLifeView;
import com.company.paco.game.of.life.util.settings.GameSettings;
import lombok.Getter;

import javax.swing.*;

/**
 * MVC controller class. It is responsible for initializing the game and starting the game.
 */
public class GameOfLifeController {
    @Getter
    private final static GameOfLifeController controllerInstance = new GameOfLifeController();
    @Getter
    private final GameSettings gameSettings = new GameSettings();
    private final GameOfLifeModel gameOfLifeModel = new GameOfLifeModel(GameSettings.getRows());
    private GameOfLifeView mainFrame;


    //  Private constructor for singleton pattern
    private GameOfLifeController() {

    }

    /**
     * Initializes the main frame of the game.
     */
    public void initializeMainFrame() {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new GameOfLifeView();
            startTheGame();
        });
    }

    // Starts the game - initializes map borders and starts the game loop.
    // In a loop the game is updated and the frame is repainted.
    private void startTheGame() {
        var gameMap = gameOfLifeModel.getGameMap();
        new Thread(() -> {
            // TODO: implement flag for game loop
            while (true) {
                mainFrame.updateGameFrame(gameMap);
                gameOfLifeModel.nextGeneration();
                try {
                    Thread.sleep(gameSettings.getSpeed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
