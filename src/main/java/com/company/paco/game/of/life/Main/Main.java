package com.company.paco.game.of.life.Main;

import com.company.paco.game.of.life.mvc.GameOfLifeController;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        GameOfLifeController.getInstance()
                .initializeMainFrame();
    }
}
