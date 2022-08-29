package com.company.paco.game.of.life.mvc;

import com.company.paco.game.of.life.swing.elements.input.InputPanelFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GOFMainFrame extends JFrame {
    private final JPanel gamePanel = new JPanel();
    private final JPanel inputPanel = new JPanel();

    public GOFMainFrame() {
        super("Game of Life");
        initializeInputPanel();
        initializeWindow();
    }

    public void initializeGamePanel(int n) {
        // Create blank game map
        gamePanel.setLayout(new GridLayout(n, n));
        gamePanel.setPreferredSize(new Dimension(800, 800));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                JPanel tempPanel = new JPanel();
                tempPanel.setMinimumSize(new Dimension(1, 1));
                tempPanel.setBackground(Color.WHITE);
                tempPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gamePanel.add(tempPanel);
            }
        }
        validate();
    }

    public void rePaintGamePanel(int n, boolean[][] gameMap) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                gamePanel.getComponent(i * n + j)
                        .setBackground(gameMap[i][j] ? Color.BLACK : Color.WHITE);
        repaint();
    }

    private void initializeInputPanel() {
        inputPanel.add(
                InputPanelFactory.createTwoInputs("Enter number of rows and columns: ",
                        "Enter number of generations: "));
        Border margin = BorderFactory.createEmptyBorder(5, 0, 5, 0);
        inputPanel.setBorder(margin);
    }

    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout();
        setVisible(true);
    }

    private void setLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(inputPanel, GroupLayout.Alignment.CENTER)
                .addComponent(gamePanel, GroupLayout.Alignment.CENTER));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(inputPanel)
                .addComponent(gamePanel));

    }
}
