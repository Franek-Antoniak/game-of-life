package com.company.paco.game.of.life.mvc;

import javax.swing.*;
import java.awt.*;

public class GOFMainFrame extends JFrame {
    private GameOfLifeController controller;
    private boolean isInputSet = false;
    private GameOfLifeModel model;
    private String gameInString;

    public GOFMainFrame() {
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
                int n = Integer.parseInt(nValue.getText().replaceAll("\\s+", ""));
                int maxGenerations = Integer.parseInt(genValue.getText().replaceAll("\\s+", ""));
                model = new GameOfLifeModel(n, maxGenerations);
                controller = new GameOfLifeController(this, model);
                model.setController(controller);
                isInputSet = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Complete the fields with positive numbers", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        new Thread(() -> {
            while (true) {
                if(isInputSet) {
                    model.start();
                    break;
                }
                sleepConsole();
            }
        }).start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (isInputSet) {
            int y = 270;
            for (String line : gameInString.split("\n"))
                g.drawString(line, 190, y += g.getFontMetrics().getHeight());
        }
    }

    protected JButton addButton(String text, Rectangle bounds) {
        JButton button = new JButton(text);
        button.setBounds(bounds);
        button.setBackground(Color.GREEN);
        add(button);
        return button;
    }

    protected JTextField addTextAndInput(String text, Rectangle bounds) {
        addLabel(text, bounds);
        Rectangle textRectangle = new Rectangle(bounds.x + bounds.width, bounds.y, bounds.width, bounds.height);
        return addJTextField(textRectangle);
    }

    protected JTextField addJTextField(Rectangle bounds) {
        JTextField textField = new JTextField();
        textField.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        add(textField);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    protected void addLabel(String text, Rectangle bounds) {
        JLabel nameLabel = new JLabel();
        nameLabel.setText(text);
        nameLabel.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(nameLabel);
    }

    public void printGame(String gameInString) {
        this.gameInString = gameInString;
        repaint();
        sleepConsole();
    }

    private static void sleepConsole() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
