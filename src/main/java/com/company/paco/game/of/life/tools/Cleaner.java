package com.company.paco.game.of.life.tools;


import java.io.IOException;

/**
 * Class with one method to clean terminal screen
 */
public class Cleaner {

    private Cleaner() {
        // private constructor to prevent instantiation
    }

    public static void terminalClearConsole() {
        sleepConsole();
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO()
                    .start()
                    .waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * It removes redundant boilerplate code - everytime someone want to use Thread.sleep() there is
     * try catch or throw
     */
    private static void sleepConsole() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}