package ru.nsu.ccfit.alarkhipov.monkeadventures.view.swing.game;

import ru.nsu.ccfit.alarkhipov.monkeadventures.buttonSignals.ButtonSignal;
import ru.nsu.ccfit.alarkhipov.monkeadventures.controller.swing.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainMenuView {

    private final JFrame frame = new JFrame();
    private JButton startButton;
    private JButton exitButton;
    private JButton aboutButton;
    private JButton scoresButton;

    public MainMenuView(MainMenuController controller) {
        SwingUtilities.invokeLater(() -> {
            Toolkit toolKit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolKit.getScreenSize();
            frame.setTitle("Monke Adventures");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(true);
            frame.setBounds(dimension.width/2-400, dimension.height/2-320, 1920, 1080);
            frame.setLocationRelativeTo(null);

            BackgroundPanel mainPanel = new BackgroundPanel();
            mainPanel.setLayout(new BorderLayout());
            frame.setContentPane(mainPanel);

            JLabel title = new JLabel("MONKE ADVENTURES", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 110));
            title.setForeground(new Color(255, 220, 80));
            title.setBorder(BorderFactory.createEmptyBorder(80, 0, 40, 0));
            mainPanel.add(title, BorderLayout.NORTH);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(4, 1, 0, 25));
            buttonsPanel.setOpaque(false);
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 300, 100, 300));

            startButton = createStyledButton("НАЧАТЬ ПРИКЛЮЧЕНИЕ");
            exitButton = createStyledButton("ВЫЙТИ");
            aboutButton = createStyledButton("О ИГРЕ");
            scoresButton = createStyledButton("РЕКОРД");

            buttonsPanel.add(startButton);
            buttonsPanel.add(aboutButton);
            buttonsPanel.add(scoresButton);
            buttonsPanel.add(exitButton);

            mainPanel.add(buttonsPanel, BorderLayout.CENTER);

            startButton.addActionListener(e -> controller.update(ButtonSignal.START, frame));
            exitButton.addActionListener(e -> controller.update(ButtonSignal.EXIT, frame));
            aboutButton.addActionListener(e -> controller.update(ButtonSignal.ABOUT, frame));
            scoresButton.addActionListener(e -> controller.update(ButtonSignal.SCORES, frame));

            frame.setVisible(true);
        });
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 42));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(34, 139, 34));        // лесной зелёный
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 180, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(34, 139, 34));
            }
        });

        return btn;
    }

    private static class BackgroundPanel extends JPanel {
        private final Image background;

        public BackgroundPanel() {
            background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/backgrounds/background.jpg"))).getImage();
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
