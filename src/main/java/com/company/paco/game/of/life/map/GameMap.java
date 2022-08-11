package com.company.paco.game.of.life.map;

public class GameMap {
    private char[][] map;
    private int numberOfCellsAlive;
    private String getMapAsString;

    public GameMap() {
    }

    // Getters
    public char[][] getMap() {
        return map;
    }

    public String getMapAsString() {
        return getMapAsString;
    }

    public int getNumberOfCellsAlive() {
        return numberOfCellsAlive;
    }

    public void setMapInfo(char[][] newMap, int numberOfAliveCells, String mapAsString) {
        this.map = newMap;
        this.numberOfCellsAlive = numberOfAliveCells;
        this.getMapAsString = mapAsString;
    }
}
