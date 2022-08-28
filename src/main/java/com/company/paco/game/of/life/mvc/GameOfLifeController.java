package com.company.paco.game.of.life.mvc;

public class GameOfLifeController {
    private final static GameOfLifeController controllerInstance = new GameOfLifeController();
    private GOFMainFrame GOFMainFrame;
    private GameOfLifeModel gameOfLifeModel;

    private GameOfLifeController() {
    }

    public static GameOfLifeController getInstance() {
        return controllerInstance;
    }

    public void initializeMainFrame() {
        GOFMainFrame = new GOFMainFrame();
    }

    public void startTheGame(int n, int maxGenerations) {
        gameOfLifeModel = new GameOfLifeModel(n);
        GOFMainFrame.initializeGamePanel(n);
        new Thread(() -> {
            for (int i = 0; i < maxGenerations; i++) {
                GOFMainFrame.rePaintGamePanel(n, gameOfLifeModel.getGameMap()
                        .getMap());
                gameOfLifeModel.nextGeneration();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
