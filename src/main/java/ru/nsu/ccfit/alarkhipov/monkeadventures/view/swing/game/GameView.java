package ru.nsu.ccfit.alarkhipov.monkeadventures.view.swing.game;

import ru.nsu.ccfit.alarkhipov.monkeadventures.controller.swing.GameController;
import ru.nsu.ccfit.alarkhipov.monkeadventures.observe.Observer;
import ru.nsu.ccfit.alarkhipov.monkeadventures.view.swing.entities.PlayerSwing;
import ru.nsu.ccfit.alarkhipov.monkeadventures.view.swing.weapons.MagicStaffSwing;

import javax.swing.*;

import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameView implements Observer<ArrayList<Float>>  {

    private final JFrame frame = new JFrame();
    private PlayerSwing playerSwing = new PlayerSwing(140);
    MagicStaffSwing staffSwing = new MagicStaffSwing(50, 200);
    private final WorldSwing worldSwing = new WorldSwing();

    public GameView(GameController controller, JFrame mainMenuFrame){

        SwingUtilities.invokeLater(() -> {
            frame.setBounds(mainMenuFrame.getX(), mainMenuFrame.getY(),
                    mainMenuFrame.getWidth(), mainMenuFrame.getHeight());
            frame.setTitle("Monke Adventures");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            playerSwing.setOpaque(false);
            worldSwing.setPlayer(playerSwing);
            worldSwing.setStaff(staffSwing);
            frame.add(worldSwing);
            frame.setResizable(true);
            frame.setVisible(true);
            worldSwing.update(playerSwing.getX(), playerSwing.getY());
        });
    }

    @Override
    public void update(ArrayList<Float> coordinates) {
        if (coordinates == null || coordinates.size() < 2) {
            return;
        }

        float worldX = coordinates.get(0);
        float worldY = coordinates.get(1);
        worldSwing.update(worldX, worldY);
    }

    public PlayerSwing getPlayerSwing() {
        return playerSwing;
    }

    public void setPlayerSwing(PlayerSwing playerSwing) {
        this.playerSwing = playerSwing;
    }

    public JFrame getFrame() {
        return frame;
    }

    public WorldSwing getWorldSwing() {
        return worldSwing;
    }

    public MagicStaffSwing getStaffSwing() {
        return staffSwing;
    }
}
