package com.company.paco.game.of.life.swing.elements.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Subclass of JPanel that is used to display the game map.
 */
public class GraphicGameMap extends JPanel {
    private boolean[][] cells;

    /**
     * Instantiates a new Graphic game map.
     *
     * @param rows how many rows and columns the map has
     */
    public GraphicGameMap(int rows) {
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
    /**
     * Draws the separators between the rows and columns of the map.
     *
     * @param g Graphics object used to draw the separators
     */
    private void drawMapSeparators(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int pxCell = getHeight() / cells.length;
        int starter = getWidth() / 2 - (cells.length * pxCell) / 2;
        g2d.setStroke(new BasicStroke(0));
        for (int i = 0; i <= cells.length; i++) {
            g2d.drawLine(starter, pxCell * i, starter + pxCell * cells.length, pxCell * i);
            g2d.drawLine(starter + pxCell * i, 0, starter + pxCell * i, pxCell * cells.length);
        }
    }

    /**
     * Paint cells based on the cells array.
     *
     * @param g Graphic component - map of cells
     */
    public void paintCells(Graphics g) {
        int pxCell = getHeight() / cells.length;
        int starter = getWidth() / 2 - (cells.length * pxCell) / 2;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j]) {
                    g.setColor(Color.BLACK);
                    g.fillRect(starter + j * pxCell, i * pxCell, pxCell, pxCell);
                }
            }
        }
    }
}
