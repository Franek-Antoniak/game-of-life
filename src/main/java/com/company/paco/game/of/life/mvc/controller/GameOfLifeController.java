package com.company.paco.game.of.life.mvc.controller;

import com.company.paco.game.of.life.mvc.model.GameOfLifeModel;
import com.company.paco.game.of.life.mvc.view.GameOfLifeView;
import com.company.paco.game.of.life.util.SleepTimer;
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
    private GameOfLifeModel gameOfLifeModel = new GameOfLifeModel(GameSettings.getRows());
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

    /**
     * Restarts the game.
     */
    public void restartTheGame() {
        pauseTheGame();
        gameOfLifeModel = new GameOfLifeModel(GameSettings.getRows());
        mainFrame.updateGameFrame(gameOfLifeModel.getGameMap());
        ((JToggleButton) mainFrame.getSettingsPanelComponents()
                .get(0)
                .getComponent(1)).setSelected(true);
        ((JToggleButton) mainFrame.getSettingsPanelComponents()
                .get(0)
                .getComponent(0)).setSelected(false);
        startTheGame();
    }

    /**
     * Resumes the game
     */
    public void resumeTheGame() {
        gameSettings.setStopped(false);
        startTheGame();
    }

    /**
     * Stops the game
     */
    public void pauseTheGame() {
        gameSettings.setStopped(true);
    }


    // Starts the game - initializes map borders and starts the game loop.
    // In a loop the game is updated and the frame is repainted.
    private void startTheGame() {
        var gameMap = gameOfLifeModel.getGameMap();
        Runnable runnable = () -> {
            while (!gameSettings.isStopped()) {
                mainFrame.updateGameFrame(gameMap);
                gameOfLifeModel.nextGeneration();
                SleepTimer.sleep(gameSettings.getSpeed());
            }
        };
        Thread gameThread = new Thread(runnable);
        gameThread.start();
    }
}
