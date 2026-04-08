package ru.nsu.ccfit.alarkhipov.monkeadventures.view.swing.entities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlayerSwing extends JPanel {
    private final Image playerImage;
    private final int size;

    public PlayerSwing(int size) {
        this.size = size;
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/monkePlayer.png")));
        this.playerImage = icon.getImage();

        setPreferredSize(new Dimension(size, size));
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (playerImage != null) {
            Graphics2D g2d = (Graphics2D) graphics;

            int drawX = (getWidth() - size) / 2;
            int drawY = (getHeight() - size) / 2;

            g2d.drawImage(playerImage, drawX, drawY, size, size, this);
        }
    }

}
