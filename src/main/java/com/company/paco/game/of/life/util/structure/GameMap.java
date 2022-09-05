package com.company.paco.game.of.life.util.structure;

/**
 * GameMap info being stored in Model(MVC) used for every info about the game.
 */
public class GameMap {
    private boolean[][] map;
    private int aliveCells = 0;
    private int currGeneration = 0;

    /**
     * Instantiates a new Game map.
     *
     * @param n the n
     */
    public GameMap(int n) {
        map = new boolean[n][n];
    }

    /**
     * Sets map info.
     *
     * @param newMap     the new map
     * @param aliveCells the alive cells
     */
    public void setMapInfo(boolean[][] newMap, int aliveCells) {
        currGeneration++;
        this.map = newMap;
        this.aliveCells = aliveCells;
    }

    /**
     * Get map boolean [ ] [ ].
     *
     * @return the boolean [ ] [ ]
     */
    public boolean[][] getMap() {
        return map;
    }

    /**
     * Gets alive cells.
     *
     * @return the alive cells
     */
    public int getAliveCells() {
        return aliveCells;
    }

    /**
     * Gets curr generation.
     *
     * @return the curr generation
     */
    public int getCurrGeneration() {
        return currGeneration;
    }
}
