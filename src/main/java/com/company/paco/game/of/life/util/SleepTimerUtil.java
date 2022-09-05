package com.company.paco.game.of.life.util;

/**
 * Util class for usage of Thread.sleep() without try catch
 */
public class SleepTimerUtil {
    /**
     * Thread.sleep for time in milliseconds
     *
     * @param time the time in milliseconds
     */
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
