package com.company.paco.game.of.life.util;

public class SleepTimer {
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
