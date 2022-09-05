package com.company.paco.game.of.life.mvc.view;

import com.company.paco.game.of.life.mvc.controller.GameOfLifeController;
import com.company.paco.game.of.life.swing.component.graphic.GameMapGraphic;
import com.company.paco.game.of.life.util.settings.GameSettings;
import com.company.paco.game.of.life.util.structure.GameMap;
import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * MVC View class for the Game of Life. This class is responsible for creating the GUI and updating it.
 */
public class GameOfLifeView extends JFrame {
    private final static int FRAME_WIDTH = 1010;
    private final static int FRAME_HEIGHT = 815;
    private final static Font frameFont = new Font("San Francisco", Font.PLAIN, 20);
    private final JPanel settingsPanel = new JPanel();
    @Getter
    private final ArrayList<JPanel> settingsPanelComponents = new ArrayList<>();
    private final GameOfLifeController controller = GameOfLifeController.getControllerInstance();
    private JPanel gameGraphicPanel = new JPanel();


    /**
     * Instantiates a new Game of life view.
     */
    public GameOfLifeView() {
        super("Game of Life");
        initializeSettingsPanel();
        initializeGamePanel();
        initializeWindow();
    }


    private void initializeSettingsPanel() {
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setMaximumSize(new Dimension(210, 200));
        initializedSettingsPanelComponents();
    }

    // Initializes components in the settings panel.
    private void initializedSettingsPanelComponents() {
        initializeControlButtons();
        initializeGameInfo();
        initializeSpeedSlider();
    }

    private void initializeControlButtons() {
        JPanel controlButtonsPanel = new JPanel();
        controlButtonsPanel.setLayout(new BoxLayout(controlButtonsPanel, BoxLayout.X_AXIS));
        String[] buttonNames = {"resume", "pause", "reset"};
        ActionListener[] buttonActions = {(x) -> controller.resumeTheGame(), (x) -> controller.pauseTheGame(), (x) -> controller.restartTheGame()};
        ButtonGroup bg = new ButtonGroup();
        try {
            for (int i = 0; i < 3; i++) {
                AbstractButton button;
                if (i == 2) button = new JButton();
                else bg.add(button = new JToggleButton());
                button.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("" + buttonNames[i] + ".png")))
                        .getScaledInstance(30, 30,
                                Image.SCALE_SMOOTH)));
                controlButtonsPanel.add(button);
                button.setBackground(Color.WHITE);
                button.setFocusPainted(false);
                button.addActionListener(buttonActions[i]);
            }
            ((JToggleButton) controlButtonsPanel.getComponent(0)).setSelected(true);
            settingsPanel.add(controlButtonsPanel);
            settingsPanelComponents.add(controlButtonsPanel);
        } catch (IOException e) {
            throw new RuntimeException("Error while loading images for control buttons.");
        }
    }

    private void initializeGameInfo() {
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outerPanel.setMaximumSize(new Dimension(200, 30));
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.Y_AXIS));
        Font font = new Font(frameFont.getFontName(), Font.BOLD, frameFont.getSize());
        JLabel generationLabel = new JLabel("Generation #");
        generationLabel.setLayout(new FlowLayout());
        generationLabel.setFont(font);
        JLabel aliveLabel = new JLabel("Alive: ");
        aliveLabel.setFont(font);
        gameInfoPanel.add(generationLabel);
        gameInfoPanel.add(aliveLabel);
        outerPanel.add(gameInfoPanel);
        settingsPanel.add(outerPanel);
        settingsPanelComponents.add(gameInfoPanel);
    }

    private void initializeSpeedSlider() {
        JPanel sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel speedLabel = new JLabel("Speed: " + speedSlider.getValue());
        textPanel.add(speedLabel);
        speedLabel.setFont(new Font(frameFont.getFontName(), frameFont.getStyle(), 18));
        sliderPanel.add(textPanel);
        sliderPanel.add(speedSlider);
        settingsPanel.add(sliderPanel);
    }

    private void initializeGamePanel() {
        int rows = GameSettings.getRows();
        gameGraphicPanel = new GameMapGraphic(rows);
    }

    /**
     * Updates the game panel with the new game map. In short, it repaints the game map based on the provided parameters.
     *
     * @param map boolean map with state of cells
     */
    private void updateGamePanel(boolean[][] map) {
        ((GameMapGraphic) gameGraphicPanel).updateImage(map);
    }

    // Initializes the window. Sets the size of the window and adds the main panels to the window.
    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout();
        setVisible(true);
    }


    // Sets the layout of the window. Window is divided into two panels: game panel and info panel.
    // Game panel is on the bottom and info panel is on the top. They are on the same x-axis.
    private void setLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(settingsPanel)
                .addComponent(gameGraphicPanel));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(settingsPanel)
                .addComponent(gameGraphicPanel));
    }

    /**
     * Updates the view with the new game map.
     *
     * @param gameMap the current game map
     */
    public void updateGameFrame(GameMap gameMap) {
        updateGamePanel(gameMap.getMap());
        updateSettingsPanel(gameMap.getCurrGeneration(), gameMap.getAliveCells());
    }


    private void updateSettingsPanel(int currGeneration, int aliveCells) {
        JPanel jPanel = settingsPanelComponents.get(1);
        ((JLabel) jPanel.getComponent(0)).setText("Generation #" + currGeneration);
        ((JLabel) jPanel.getComponent(1)).setText("Alive: " + aliveCells);
    }
}
