package com.company.paco.game.of.life;

import com.company.paco.game.of.life.generation.algorithm.GenerationAlgorithm;
import com.company.paco.game.of.life.map.GameMap;
import com.company.paco.game.of.life.tools.Cleaner;

import java.util.Random;

/**
 * Main class for the whole logic of the game
 */
public class GameOfLife {
    private final int maxGenerations;
    private final int n;
    private final int seed;
    private final GenerationAlgorithm generationAlgorithm;
    GameMap gameMap;
    private int currentGeneration;

    public GameOfLife(int n, int seed, int maxGenerations) {
        this.n = n;
        this.seed = seed;
        this.maxGenerations = maxGenerations;
        currentGeneration = 0;
        initializeMap();
        generationAlgorithm = new GenerationAlgorithm(gameMap);
    }

    /**
     * When game is started map is generated through Random class object for the first generation
     */
    private void initializeMap() {
        Random rand = new Random(seed);
        char[][] map = new char[n][n];
        StringBuilder mapAsString = new StringBuilder();
        int numberOfAliveCells = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = rand.nextBoolean() ? 'O' : ' ';
                numberOfAliveCells += map[i][j] == 'O' ? 1 : 0;
                mapAsString.append(map[i][j]).append(" ");
            }
            mapAsString.append("\n");
        }
        gameMap = new GameMap();
        gameMap.setMapInfo(map, numberOfAliveCells, mapAsString.toString());
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    /**
     * Updates whole map to nextGeneration
     */
    private void updateMap() {
        currentGeneration++;
        int numberOfAliveCells = 0;
        StringBuilder mapAsString = new StringBuilder();
        char[][] newMap = new char[n][n];
        generationAlgorithm.setMap(gameMap.getMap());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = generationAlgorithm.getNewState(i, j);
                numberOfAliveCells += newMap[i][j] == 'O' ? 1 : 0;
                mapAsString.append(newMap[i][j]).append(" ");
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
        for (int i = 0; i < maxGenerations; i++) {
            printMap();
            updateMap();
        }
    }
}
