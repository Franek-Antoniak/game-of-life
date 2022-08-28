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
        boolean[][] newMap = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = getNewCellState.apply(gameMap.getMap(), new Pair(i, j));
            }
        }
        gameMap.setMapInfo(newMap);
    }
}
