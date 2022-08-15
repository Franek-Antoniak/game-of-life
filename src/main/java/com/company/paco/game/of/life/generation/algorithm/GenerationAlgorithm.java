package com.company.paco.game.of.life.generation.algorithm;

import com.company.paco.game.of.life.record.Pair;

import java.util.function.Function;

/**
 * Generation algorithm has only one important function - calculateNewState()
 * to deliver if cell will be alive in the next generation
 */
public interface GenerationAlgorithm {
    /**
     * Rules - under what conditions cell will be alive
     * 1. If the cell is currently alive:
     * - Cell will be alive only if it has exactly 2 or 3 alive neighbours
     * 2. If the cell is currently dead:
     * - Cell will be alive only if it has exactly 3 alive neighbours.
     * Modulo is used to take into account the neighbours of the cells at the edges of the map.
     *
     * @param map    - current map
     * @param coords - coordinates of the cell to check
     * @return state of the cell at the defined coordinates in the next generation
     */
    static char calculateNewState(char[][] map, Pair coords) {
        int mod = map.length;
        Function<Integer, Integer> modulo = x -> (x + mod) % mod;
        int counter = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (map[modulo.apply(coords.x() + i)][modulo.apply(coords.y() + j)] == 'O') counter++;
            }
        }
        if (map[coords.x()][coords.y()] == 'O') return (counter - 1 == 2 || counter - 1 == 3) ? 'O' : ' ';
        else return counter == 3 ? 'O' : ' ';
    }
}
