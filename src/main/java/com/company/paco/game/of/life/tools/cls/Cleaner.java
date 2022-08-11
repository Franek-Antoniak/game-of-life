package com.company.paco.game.of.life.tools.cls;


import java.io.IOException;

/**
 * Cleaner use Grep Console plugin from INTELLIJ
 * Pattern matching in Grep console match "Clear Console" line and
 * clear whole console when it appears
 */
public class Cleaner {
    public static void grepClearConsole() {
        // Waiting 2 seconds for user to read the console before cleaning it
        sleepConsole(1000);
        // Clearing the console through INTELLIJ plugin named Grep console
        System.out.println();
        System.out.print("Clear Console");
        // Waiting for plugin to clear the console
        sleepConsole(20);
    }

    public static void terminalClearConsole() {
        sleepConsole(300);
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
     *
     * @param millis time in milliseconds
     */
    private static void sleepConsole(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}