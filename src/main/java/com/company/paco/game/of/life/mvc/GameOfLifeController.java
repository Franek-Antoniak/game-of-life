package com.company.paco.game.of.life.mvc;

public class GameOfLifeController {
    private final GOFMainFrame GOFMainFrame;
    private GameOfLifeModel gameOfLifeModel;

    public GameOfLifeController(GOFMainFrame GOFMainFrame, GameOfLifeModel gameOfLifeModel) {
        this.GOFMainFrame = GOFMainFrame;
        this.gameOfLifeModel = gameOfLifeModel;
    }

    public void printGame(String gameInString) {
        GOFMainFrame.printGame(gameInString);
    }
}
