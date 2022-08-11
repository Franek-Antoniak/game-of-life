package com.company.paco.game.of.life.generation.algorithm;

import com.company.paco.game.of.life.map.GameMap;

/**
 * Generation algorithm has only one important function - getState()
 * to deliver if cell will be alive in the next generation
 */
public class
GenerationAlgorithm {
    private char[][] map;

    public GenerationAlgorithm(GameMap map) {
        this.map = map.getMap();
    }

    /**
     * Rules - under what conditions cell will be alive
     * 1. If the cell is currently alive:
     * - Cell will be alive only if it has exactly 2 or 3 alive neighbours
     * 2. If the cell is currently dead:
     * - Cell will be alive only if it has exactly 3 alive neighbours.
     * Modulo is used to take into account the neighbours of the cells at the edges of the map.
     *
     * @param i - first index in the map
     * @param j - second index in the map
     * @return state of the cell placed in (i, j) in next generation
     */
    public char getNewState(int i, int j) {
        int modulo = map.length;
        int counter = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (map[(k + modulo) % modulo][(l + modulo) % modulo] == 'O') {
                    counter++;
                }
            }
        }
        if (map[i][j] == 'O') {
            return (counter - 1 == 2 || counter - 1 == 3) ? 'O' : ' ';
        }
        else {
            return counter == 3 ? 'O' : ' ';
        }
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
