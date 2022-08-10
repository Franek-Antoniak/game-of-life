package com.company.paco.game.of.life;

import com.company.paco.game.of.life.generation.algorithm.GenerationAlgorithm;

import java.util.Random;

/**
 * Main class for the whole logic of the game
 */
public class GameOfLife {
    private boolean[][] map;
    private final int maxGenerations;
    private int currentGeneration;
    private final int n;
    private final int seed;
    private final GenerationAlgorithm generationAlgorithm;

    public GameOfLife(int n, int seed, int maxGenerations) {
        this.n = n;
        this.seed = seed;
        this.maxGenerations = maxGenerations;
        currentGeneration = 0;
        initializeMap();
        generationAlgorithm = new GenerationAlgorithm(map);
    }

    /**
     * When game is started map is generated through Random class object for the first generation
     */
    private void initializeMap() {
        Random rand = new Random(seed);
        map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = rand.nextBoolean();
            }
        }
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    /**
     * Updates whole map to nextGeneration
     */
    public void updateMap() {
        currentGeneration++;
        boolean[][] newMap = new boolean[n][n];
        generationAlgorithm.setMap(map);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = generationAlgorithm.getState(i, j);
            }
        }
        map = newMap;
    }

    /**
     * Prints current map into console
     * Character 'O' - cell is alive
     * Character '-' - cell is dead
     */
    public void printMap() {
        System.out.println("Generation number " + currentGeneration + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] ? "O" : "-");
                System.out.print("  ");
            }
            System.out.println();
        }
    }
}
