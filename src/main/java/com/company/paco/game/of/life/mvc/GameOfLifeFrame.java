package com.company.paco.game.of.life.mvc;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeFrame extends JFrame {
    private final GameOfLifeController controller = new GameOfLifeController(this);
    private GameOfLifeModel model;

    public GameOfLifeFrame() {
        super("Game of Life");
        initializeWindow();
        addIntInputs();
    }

    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void addIntInputs() {
        String text1 = "Enter n: ", text2 = "Enter max generations: ";
        Rectangle firstBound = new Rectangle(180, 100, 250, 30);
        JTextField nValue = addTextAndInput(text1, firstBound);
        Rectangle secondBound = new Rectangle(180, 150, 250, 30);
        JTextField genValue = addTextAndInput(text2, secondBound);
        JButton startButton = addButton("Send values", new Rectangle(480, 200, 150, 30));
        startButton.addActionListener(e -> {
            try {
                int n = Integer.parseInt(nValue.getText());
                int maxGenerations = Integer.parseInt(genValue.getText());
                model = new GameOfLifeModel(n, maxGenerations);
                model.start();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Complete the fields with positive numbers", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JButton addButton(String text, Rectangle bounds) {
        JButton button = new JButton(text);
        button.setBounds(bounds);
        button.setBackground(Color.GREEN);
        add(button);
        return button;
    }

    private JTextField addTextAndInput(String text, Rectangle bounds) {
        addLabel(text, bounds);
        Rectangle textRectangle = new Rectangle(bounds.x + bounds.width, bounds.y, bounds.width, bounds.height);
        return addJTextField(textRectangle);
    }

    private JTextField addJTextField(Rectangle bounds) {
        JTextField textField = new JTextField();
        textField.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        add(textField);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    private void addLabel(String text, Rectangle bounds) {
        JLabel nameLabel = new JLabel();
        nameLabel.setText(text);
        nameLabel.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(nameLabel);
    }
}
