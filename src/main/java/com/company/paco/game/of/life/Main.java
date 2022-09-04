package com.company.paco.game.of.life;

import com.company.paco.game.of.life.mvc.controller.GameOfLifeController;

public class Main {
    public static void main(String[] args) {
        GameOfLifeController.getControllerInstance()
                .initializeMainFrame();
    }
}
