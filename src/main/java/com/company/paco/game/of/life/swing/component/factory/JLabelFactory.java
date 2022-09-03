package com.company.paco.game.of.life.swing.component.factory;

import javax.swing.*;
import java.awt.*;

public class JLabelFactory {
    public static JLabel createJLabel(String text) {
        JLabel textField = new JLabel(text);
        Font font = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(font);
        return textField;
    }
}
