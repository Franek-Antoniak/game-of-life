package com.company.paco.game.of.life;

import com.company.paco.game.of.life.generation.algorithm.GenerationAlgorithm;
import com.company.paco.game.of.life.map.GameMap;
import com.company.paco.game.of.life.tools.Cleaner;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * Main class for the whole logic of the game
 */
public class GameOfLife {
    private final int maxGenerations;
    private final int n;
    private final int seed;
    private final GenerationAlgorithm generationAlgorithm;
    private final GameMap gameMap;
    private int currentGeneration = 0;

    public GameOfLife(int n, int seed, int maxGenerations) {
        this.n = n;
        this.seed = seed;
        this.maxGenerations = maxGenerations;
        gameMap = new GameMap(n);
        generationAlgorithm = new GenerationAlgorithm(gameMap);
        initializeMap();
    }

    /**
     * When game is started map is generated through Random class object
     */
    private void initializeMap() {
        Random rand = new Random(seed);
        updateMap((x, y) -> rand.nextBoolean() ? 'O' : ' ');
    }

    /**
     * Updates whole map to nextGeneration with usage of GenerationAlgorithm class
     */
    private void updateMap() {
        currentGeneration++;
        updateMap(generationAlgorithm::getNewState);
    }

    /**
     * The function updates the map depending on the lambda function given to it.
     * The function reduces code repetition.
     */
    private void updateMap(BiFunction<Integer, Integer, Character> getNewCellState) {
        StringBuilder mapAsString = new StringBuilder();
        char[][] newMap = new char[n][n];
        int numberOfAliveCells = 0;
        generationAlgorithm.setMap(gameMap.getMap());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = getNewCellState.apply(i, j);
                numberOfAliveCells += newMap[i][j] == 'O' ? 1 : 0;
                mapAsString.append(newMap[i][j])
                           .append(" ");
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
    private void printMap() {
        Cleaner.terminalClearConsole();
        System.out.println("Generation number " + currentGeneration + ":");
        System.out.println("Number of alive cells: " + gameMap.getNumberOfCellsAlive() + " out of " + n * n);
        System.out.println(gameMap.getMapAsString());
    }

    public void start() {
        for (int i = 0; i <= maxGenerations; i++) {
            printMap();
            updateMap();
        }
    }
}
