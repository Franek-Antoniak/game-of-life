package com.company.paco.game.of.life.mvc;

import com.company.paco.game.of.life.generation.algorithm.GenerationAlgorithm;
import com.company.paco.game.of.life.map.GameMap;
import com.company.paco.game.of.life.record.Pair;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * Class with the most abstract implementation of the game logic.
 */
public class GameOfLifeModel {
    private final int maxGenerations;
    private final int n;
    private final Random random = new Random();
    private final GameMap gameMap;
    private int currentGeneration = 0;
    private GameOfLifeController gameOfLifeController;

    public GameMap getGameMap() {
        return gameMap;
    }

    public GameOfLifeModel(int n, int maxGenerations) {
        this.n = n;
        this.maxGenerations = maxGenerations;
        this.gameMap = new GameMap(n);
    }

    public void setController(GameOfLifeController gameOfLifeController) {
        this.gameOfLifeController = gameOfLifeController;
    }

    /**
     * When game is started map is generated through Random class object
     */
    private void initializeMap() {
        updateMap((x, y) -> random.nextBoolean() ? 'O' : ' ');
    }

    /**
     * Updates curren map to the next generation with usage of Generation Algorithm
     */
    private void updateMap() {
        currentGeneration++;
        updateMap(GenerationAlgorithm::calculateNewState);
    }

    /**
     * The function updates the map depending on the lambda function given to it.
     * The function reduces code repetition.
     */
    private void updateMap(BiFunction<char[][], Pair, Character> getNewCellState) {
        StringBuilder mapAsString = new StringBuilder();
        char[][] newMap = new char[n][n];
        int numberOfAliveCells = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = getNewCellState.apply(gameMap.getMap(), new Pair(i, j));
                numberOfAliveCells += newMap[i][j] == 'O' ? 1 : 0;
                mapAsString.append(newMap[i][j])
                        .append("    ");
            }
            mapAsString.append("\n");
        }
        gameMap.setMapInfo(newMap, numberOfAliveCells, mapAsString.toString());
    }

    /**
     * Prints current map into console
     * Character 'O' - cell is alive
     * Character ' ' - cell is dead
     */
    private String getGameInString() {
        return "Generation number " +
                currentGeneration +
                ":\n" +
                "Number of alive cells: " +
                gameMap.getNumberOfCellsAlive() +
                " out of " +
                n * n +
                "\n" +
                gameMap.getMapAsString();
    }

    public void start() {
        initializeMap();
        for (int i = 0; i <= maxGenerations; i++) {
            gameOfLifeController.printGame(getGameInString());
            updateMap();
        }
    }
}
