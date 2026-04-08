package ru.nsu.ccfit.alarkhipov.monkeadventures.view.swing.game;

import ru.nsu.ccfit.alarkhipov.monkeadventures.buttonSignals.ButtonSignal;
import ru.nsu.ccfit.alarkhipov.monkeadventures.controller.swing.MainMenuController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainMenuView {

    private final JFrame frame = new JFrame();
    private JPanel menuPanel;
    private JButton startButton;
    private JButton exitButton;
    private JButton aboutButton;
    private JButton scoresButton;
    private JLabel gameTitleForMenu;

    public MainMenuView(MainMenuController controller){
        SwingUtilities.invokeLater(() -> {
            Toolkit toolKit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolKit.getScreenSize();
            frame.setBounds(dimension.width/2-400, dimension.height/2-320, 1920, 1080);
            frame.setTitle("Monke Adventure");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setResizable(false);
            menuPanel = new JPanel();
            menuPanel.setLayout(new BorderLayout());
            frame.add(menuPanel);

            JPanel panelForTitle = new JPanel(new BorderLayout());
            gameTitleForMenu = new JLabel("Monke Adventure", SwingConstants.CENTER);
            gameTitleForMenu.setFont(new Font("Arial", Font.BOLD, 120));
            gameTitleForMenu.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));
            panelForTitle.add(gameTitleForMenu);
            menuPanel.add(panelForTitle, BorderLayout.NORTH);

            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BorderLayout());

            JPanel topButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            startButton = new JButton("Start");
            startButton.setFont(new Font("Arial", Font.BOLD, 96));
            startButton.addActionListener(e -> controller.update(ButtonSignal.START, this.frame));

            aboutButton = new JButton("About");
            aboutButton.setFont(new Font("Arial", Font.BOLD, 96));
            aboutButton.addActionListener(e -> controller.update(ButtonSignal.ABOUT, this.frame));

            scoresButton = new JButton();
            scoresButton.setLayout(new BoxLayout(scoresButton, BoxLayout.Y_AXIS));
            JLabel line1ForScores = new JLabel("Highest");
            JLabel line2ForScores = new JLabel("Scores");
            line1ForScores.setFont(new Font("Arial", Font.BOLD, 60));
            line2ForScores.setFont(new Font("Arial", Font.BOLD, 60));
            line1ForScores.setAlignmentX(Component.CENTER_ALIGNMENT);
            line2ForScores.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoresButton.add(line1ForScores);
            scoresButton.add(line2ForScores);
            scoresButton.addActionListener(e -> controller.update(ButtonSignal.SCORES, this.frame));

            topButtonsPanel.add(aboutButton);
            topButtonsPanel.add(Box.createRigidArea(new Dimension(80, 30)));
            topButtonsPanel.add(startButton);
            topButtonsPanel.add(Box.createRigidArea(new Dimension(80, 30)));
            topButtonsPanel.add(scoresButton);

            exitButton = new JButton("Exit");
            exitButton.setFont(new Font("Arial", Font.BOLD, 96));
            JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            exitPanel.add(exitButton);
            exitPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
            exitButton.addActionListener(e -> controller.update(ButtonSignal.EXIT, this.frame));

            subPanel.add(topButtonsPanel, BorderLayout.CENTER);
            subPanel.add(exitPanel, BorderLayout.SOUTH);
            subPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
            menuPanel.add(subPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JButton getAboutButton() {
        return aboutButton;
    }

    public void setAboutButton(JButton aboutButton) {
        this.aboutButton = aboutButton;
    }

    public JButton getScoresButton() {
        return scoresButton;
    }

    public void setScoresButton(JButton scoresButton) {
        this.scoresButton = scoresButton;
    }

    public JLabel getGameTitleForMenu() {
        return gameTitleForMenu;
    }

    public void setGameTitleForMenu(JLabel gameTitleForMenu) {
        this.gameTitleForMenu = gameTitleForMenu;
    }

}
