package com.company.paco.game.of.life.swing.component.graphic;

import javax.swing.*;
import java.awt.*;

/**
 * Subclass of JPanel that is used to display the game map.
 */
public class GameMapGraphic extends JPanel {
    private static final int cellSize = 5;
    private boolean[][] cells;

    /**
     * Instantiates a new Graphic game map.
     *
     * @param rows how many rows and columns the map has
     */
    public GameMapGraphic(int rows) {
        super();
        cells = new boolean[rows][rows];
        //Create black border around the map
    }

    /**
     * Updates graphic of the game map.
     *
     * @param cells boolean array with the current life state of the cells
     */
    public void updateImage(boolean[][] cells) {
        this.cells = cells;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cells == null) {
            return;
        }
        drawMapSeparators(g);
        paintCells(g);
    }

    // Draws the separators between the rows and columns of the map.
    private void drawMapSeparators(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(0));
        for (int i = 0; i <= cells.length; i++) {
            g2d.drawLine(0, i * cellSize, cells.length * cellSize, i * cellSize);
            g2d.drawLine(i * cellSize, 0, i * cellSize, cells.length * cellSize);
        }
    }

    /**
     * Paint cells based on the cells array.
     *
     * @param g Graphic component - map of cells
     */
    public void paintCells(Graphics g) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j]) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
