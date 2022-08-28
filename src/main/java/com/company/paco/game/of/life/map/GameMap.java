package com.company.paco.game.of.life.map;

public class GameMap {
    private boolean[][] map;
    private int numberOfCellsAlive;
    private String getMapAsString;

    public GameMap(int n) {
        map = new boolean[n][n];
    }

    // Getters
    public boolean[][] getMap() {
        return map;
    }

    public void setMapInfo(boolean[][] newMap, int numberOfAliveCells, String mapAsString) {
        this.map = newMap;
        this.numberOfCellsAlive = numberOfAliveCells;
        this.getMapAsString = mapAsString;
    }
}
