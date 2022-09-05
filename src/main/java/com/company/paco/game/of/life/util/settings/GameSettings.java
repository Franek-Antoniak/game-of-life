package com.company.paco.game.of.life.util.settings;

import lombok.Getter;
import lombok.Setter;

/**
 * Storage for game settings.
 */
public class GameSettings {
    @Getter
    private static final int rows = 150;
    @Getter
    @Setter
    private int speed = 50;
    @Getter
    @Setter
    private boolean isStopped = false;

    /**
     * Instantiates a new Game settings.
     */
    public GameSettings() {
    }
}
