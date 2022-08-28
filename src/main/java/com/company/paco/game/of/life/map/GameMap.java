package com.company.paco.game.of.life.map;

public class GameMap {
    private boolean[][] map;

    public GameMap(int n) {
        map = new boolean[n][n];
    }

    public void setMapInfo(boolean[][] newMap) {
        this.map = newMap;
    }

    public boolean[][] getMap() {
        return map;
    }
}
