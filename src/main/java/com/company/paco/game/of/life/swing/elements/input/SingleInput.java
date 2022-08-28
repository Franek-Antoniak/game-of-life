package com.company.paco.game.of.life.swing.elements.input;

import javax.swing.*;

public class SingleInput {
    private final JTextField input;
    private final JLabel label;

    public SingleInput(JTextField input, JLabel label) {
        this.input = input;
        this.label = label;
    }

    // Getters
    public JTextField getInput() {
        return input;
    }

    public JLabel getLabel() {
        return label;
    }

}
