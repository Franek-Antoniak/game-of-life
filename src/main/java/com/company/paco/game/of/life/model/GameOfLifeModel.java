package com.company.paco.game.of.life.model;

import com.company.paco.game.of.life.data.formats.record.Pair;
import com.company.paco.game.of.life.generation.algorithm.GenerationAlgorithm;
import com.company.paco.game.of.life.map.GameMap;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * MVC(Model) class for the Game of Life. It stores the game map and has functions to initialize and to update it to
 * the next generation.
 */
public class GameOfLifeModel {
    private final Random random = new Random();
    private GameMap gameMap;

    public GameOfLifeModel(int n) {
        initializeMap(n);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * When game is started map is generated through Random class object
     */
    private void initializeMap(int n) {
        gameMap = new GameMap(n);
        updateMap((x, y) -> random.nextBoolean());
    }

    /**
     * Updates curren map to the next generation with usage of Generation Algorithm
     */
    public void nextGeneration() {
        updateMap(GenerationAlgorithm::calculateNewState);
    }

    /**
     * The function updates the map depending on the lambda function given to it.
     * The function reduces code repetition.
     */
    private void updateMap(BiFunction<boolean[][], Pair, Boolean> getNewCellState) {
        int n = gameMap.getMap().length;
        int aliveCells = 0;
        boolean[][] newMap = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = getNewCellState.apply(gameMap.getMap(), new Pair(i, j));
                aliveCells += newMap[i][j] ? 1 : 0;
            }
        }
        gameMap.setMapInfo(newMap, aliveCells);
    }
}
