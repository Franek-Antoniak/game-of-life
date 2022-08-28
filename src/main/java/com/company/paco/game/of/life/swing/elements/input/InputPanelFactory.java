package com.company.paco.game.of.life.swing.elements.input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputPanelFactory {

    private InputPanelFactory() {
    }

    public static JPanel createTwoInputs(String text, String text2, ActionListener actionListener) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        SingleInput input1 = createSingleInput(text);
        SingleInput input2 = createSingleInput(text2);
        JButton button = createButton(actionListener);
        GridBagConstraints c = getDefaultConstraints();
        inputPanel.add(input1.getLabel(), ConstrainFactory.createConstrain(0, 0, 2, 1, c));
        inputPanel.add(input1.getInput(), ConstrainFactory.createConstrain(2, 0, 1, 1, c));
        inputPanel.add(input2.getLabel(), ConstrainFactory.createConstrain(0, 1, 2, 1, c));
        inputPanel.add(input2.getInput(), ConstrainFactory.createConstrain(2, 1, 1, 1, c));
        inputPanel.add(button, ConstrainFactory.createConstrain(0, 2, 3, 1, c));
        return inputPanel;
    }

    private static GridBagConstraints getDefaultConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        return constraints;
    }

    private static SingleInput createSingleInput(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(150, 30));
        input.setFont(new Font("Arial", Font.PLAIN, 16));
        input.setHorizontalAlignment(JTextField.CENTER);
        return new SingleInput(input, label);
    }

    private static JButton createButton(ActionListener actionListener) {
        JButton startButton = new JButton("Send values");
        startButton.addActionListener(actionListener);
        startButton.setBackground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setRequestFocusEnabled(false);
        UIManager.put("Button.select", new Color(200,200,200));
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return startButton;
    }

}
