package com.company.paco.game.of.life.controller;

import com.company.paco.game.of.life.model.GameOfLifeModel;
import com.company.paco.game.of.life.settings.GameSettings;
import com.company.paco.game.of.life.view.GameOfLifeView;

import javax.swing.*;

/**
 * MVC controller class. It is responsible for initializing the game and starting the game.
 */
public class GameOfLifeController {
    private final static GameOfLifeController controllerInstance = new GameOfLifeController();
    private final GameSettings gameSettings = new GameSettings();
    private final GameOfLifeModel gameOfLifeModel = new GameOfLifeModel(gameSettings.getRows());
    private GameOfLifeView mainFrame;

    /**
     * Private constructor for singleton pattern
     */
    private GameOfLifeController() {

    }

    /**
     * @return the instance of singleton object.
     */
    public static GameOfLifeController getInstance() {
        return controllerInstance;
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

    /**
     * Starts the game - initializes map borders and starts the game loop.
     * In a loop the game is updated and the frame is repainted.
     */
    private void startTheGame() {
        var gameMap = gameOfLifeModel.getGameMap();
        new Thread(() -> {
            // TODO: implement flag for game loop
            while (true) {
                mainFrame.updateGameInfo(gameMap);
                mainFrame.updateGamePanel(gameMap.getMap());
                gameOfLifeModel.nextGeneration();
                try {
                    Thread.sleep(gameSettings.getSpeed()
                            .getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public GameSettings getSettings() {
        return gameSettings;
    }
}
