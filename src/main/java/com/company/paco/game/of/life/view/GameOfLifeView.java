package com.company.paco.game.of.life.view;

import com.company.paco.game.of.life.controller.GameOfLifeController;
import com.company.paco.game.of.life.map.GameMap;
import com.company.paco.game.of.life.swing.component.factories.JLabelFactory;
import com.company.paco.game.of.life.swing.elements.graphics.GraphicGameMap;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * MVC View class for the Game of Life. This class is responsible for creating the GUI and updating it.
 */
public class GameOfLifeView extends JFrame {
    private final JPanel infoPanel = new JPanel();
    private JPanel gamePanel = new JPanel();

    public GameOfLifeView() {
        super("Game of Life");
        initializeInfoPanel();
        initializeGamePanel();
        initializeWindow();
    }

    /**
     * Initializes the info panel. InfoPanel stores information about the current generation and the number of alive cells.
     */
    private void initializeInfoPanel() {
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        JLabel generationInfo = JLabelFactory.createJLabel("Generation #0");
        JLabel cellsInfo = JLabelFactory.createJLabel("Cells alive: " + 0);
        Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoPanel.add(generationInfo, margin);
        infoPanel.add(cellsInfo, margin);
    }

    /**
     * Initializes the game panel. GamePanel is the panel that contains the graphic representation of the game map.
     */
    private void initializeGamePanel() {
        int rows = GameOfLifeController.getInstance()
                .getSettings()
                .getRows();
        gamePanel = new GraphicGameMap(rows);
    }

    /**
     * Updates the game panel with the new game map. In short, it repaints the game map based on the provided parameters.
     *
     * @param map boolean map with state of cells
     */
    public void updateGamePanel(boolean[][] map) {
        ((GraphicGameMap) gamePanel).updateImage(map);
    }

    /**
     * Initializes the window. Sets the size of the window and adds the main panels to the window.
     */
    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1060, 1060);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout();
        setVisible(true);
    }

    /**
     * Sets the layout of the window. Window is divided into two panels: game panel and info panel.
     * Game panel is on the bottom and info panel is on the top. They are on the same x-axis.
     */
    private void setLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(infoPanel, GroupLayout.Alignment.CENTER)
                .addComponent(gamePanel, GroupLayout.Alignment.CENTER));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(infoPanel)
                .addComponent(gamePanel));

    }

    /**
     * Updates text info about the game in the info panel.
     *
     * @param gameMap the game map
     */
    public void updateGameInfo(GameMap gameMap) {
        ((JLabel) infoPanel.getComponent(0)).setText("Generation #" + gameMap.getCurrGeneration());
        ((JLabel) infoPanel.getComponent(1)).setText("Cells alive: " + gameMap.getAliveCells());
    }
}
