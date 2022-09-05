package com.company.paco.game.of.life;

import com.company.paco.game.of.life.mvc.controller.GameOfLifeController;

/**
 * Main class of application
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameOfLifeController.getControllerInstance()
                .initializeMainFrame();
    }
}
