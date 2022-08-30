package com.company.paco.game.of.life.Main;

import com.company.paco.game.of.life.controller.GameOfLifeController;

public class Main {
    public static void main(String[] args) {
        GameOfLifeController.getInstance()
                .initializeMainFrame();
    }
}
