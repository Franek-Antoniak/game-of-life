package com.company.paco.game.of.life.map;

public class GameMap {
    private boolean[][] map;
    private int numberOfCellsAlive;

    public GameMap(int n) {
        map = new boolean[n][n];
    }
    public void setMapInfo(boolean[][] newMap, int numberOfAliveCells) {
        this.map = newMap;
        this.numberOfCellsAlive = numberOfAliveCells;
    }

    public boolean[][] getMap() {
        return map;
    }

    public int getNumberOfCellsAlive() {
        return numberOfCellsAlive;
    }
}
