package com.company.paco.game.of.life.Main;

import com.company.paco.game.of.life.mvc.GameOfLifeFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(GameOfLifeFrame::new);
    }
}
