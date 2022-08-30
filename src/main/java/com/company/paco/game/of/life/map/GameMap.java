package com.company.paco.game.of.life.map;

/**
 * GameMap info being stored in Model(MVC) used for every info about the game.
 */
public class GameMap {
    private boolean[][] map;
    private int aliveCells = 0;
    private int currGeneration = 0;

    public GameMap(int n) {
        map = new boolean[n][n];
    }

    public void setMapInfo(boolean[][] newMap, int aliveCells) {
        currGeneration++;
        this.map = newMap;
        this.aliveCells = aliveCells;
    }

    public boolean[][] getMap() {
        return map;
    }

    public int getAliveCells() {
        return aliveCells;
    }

    public int getCurrGeneration() {
        return currGeneration;
    }
}
