package com.company.paco.game.of.life.mvc.controller;

import com.company.paco.game.of.life.mvc.model.GameOfLifeModel;
import com.company.paco.game.of.life.mvc.view.GameOfLifeView;
import com.company.paco.game.of.life.util.SleepTimerUtil;
import com.company.paco.game.of.life.util.settings.GameSettings;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
            mainFrame = new GameOfLifeView(GameSettings.getRows());
            startTheGame();
        });
    }

    // Starts the game - initializes map borders and starts the game loop.
    // In a loop the game is updated and the frame is repainted.
    private void startTheGame() {
        var gameMap = gameOfLifeModel.getGameMap();
        new Thread(() -> {
            while (!gameSettings.isStopped()) {
                mainFrame.updateGameFrame(gameMap);
                gameOfLifeModel.nextGeneration();
                SleepTimerUtil.sleep(gameSettings.getSpeed());
            }
        }).start();
    }

    public void resumeGame(ActionEvent actionEvent) {
        gameSettings.setStopped(false);
        startTheGame();
    }

    public void pauseGame(ActionEvent actionEvent) {
        gameSettings.setStopped(true);
    }

    public void resetGame(ActionEvent actionEvent) {
        gameSettings.setStopped(true);
        gameOfLifeModel = new GameOfLifeModel(GameSettings.getRows());
        JPanel panel = mainFrame.getSettingsPanelComponents()
                .get(0);
        ((JToggleButton) panel.getComponent(0)).setSelected(false);
        ((JToggleButton) panel.getComponent(1)).setSelected(true);
        mainFrame.updateGameFrame(gameOfLifeModel.getGameMap());
    }
}
