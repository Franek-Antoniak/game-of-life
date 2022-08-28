package com.company.paco.game.of.life.swing.elements.input;

import java.awt.*;

public class ConstrainFactory {
    public static GridBagConstraints createConstrain(int x, int y, int width, int height,
                                                     GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        return constraints;
    }
}
