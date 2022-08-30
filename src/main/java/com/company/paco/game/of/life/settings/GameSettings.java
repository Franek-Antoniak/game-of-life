package com.company.paco.game.of.life.settings;

/**
 * Storage for game settings.
 */
public class GameSettings {
    private Speed speed = Speed.SUPER_FAST3;
    private boolean isStopped = false;

    private int rows = 900;

    /**
     * Instantiates a new Game settings.
     */
    public GameSettings() {
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Speed Enum for controlling the speed of the game.
     */
    public enum Speed {
        SLOW1(1000),
        SLOW2(900),
        SLOW3(800),
        MEDIUM1(700),
        MEDIUM2(600),
        MEDIUM3(500),
        FAST1(400),
        FAST2(300),
        FAST3(200),
        SUPER_FAST1(100),
        SUPER_FAST2(50),
        SUPER_FAST3(0);
        private final int speed;

        Speed(int speed) {
            this.speed = speed;
        }

        public int getValue() {
            return speed;
        }
    }

}
