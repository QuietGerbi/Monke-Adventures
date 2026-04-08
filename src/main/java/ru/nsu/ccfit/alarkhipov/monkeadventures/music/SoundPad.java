package ru.nsu.ccfit.alarkhipov.monkeadventures.music;

import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SoundPad {
    private Player currentPlayer;
    private Thread musicThread;
    private String currentTrackPath;
    private List<String> playlist1 = new ArrayList<>();
    private List<String> playlist2 = new ArrayList<>();
    private boolean isLooping = true;
    private boolean isMuted = false;
    private boolean isBossFight = false;

    public void setPlaylist1(List<String> tracks, boolean shuffle) {
        this.playlist1 = new ArrayList<>(tracks);
        if (shuffle) Collections.shuffle(this.playlist1);
    }

    public void setPlaylist2(List<String> tracks, boolean shuffle) {
        this.playlist2 = new ArrayList<>(tracks);
        if (shuffle) Collections.shuffle(this.playlist2);
    }

    public void start() {
        musicThread = new Thread(() -> {
            int trackIndex = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (isMuted) {
                        Thread.sleep(500);
                        continue;
                    }
                    String trackToPlay = isBossFight ? playlist2.get(trackIndex) : playlist1.get(trackIndex);
                    currentTrackPath = trackToPlay;

                    try{
                        playSingleTrack(trackToPlay);
                    } catch (Exception e) {
                        throw new InterruptedException();
                    }

                    if (!isBossFight && !isMuted) {
                        trackIndex = (trackIndex + 1) % playlist1.size();
                    }
                    if (isBossFight && !isMuted) {
                        trackIndex = (trackIndex + 1) % playlist2.size();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        musicThread.setDaemon(true);
        musicThread.start();
    }

    public void toggleMute() {
        isMuted = !isMuted;
        if (isMuted) {
            stopCurrentTrack();
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    private void playSingleTrack(String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path);
        BufferedInputStream bis = new BufferedInputStream(is);
        currentPlayer = new Player(bis);
        currentPlayer.play();
    }

    public void switchToBossMusic() {
        isBossFight = true;
        stopCurrentTrack();
    }

    public void stopCurrentTrack() {
        if (currentPlayer != null) {
            currentPlayer.close();
        }
    }

    public void stopAll() {
        isMuted = true;

        if (currentPlayer != null) {
            currentPlayer.close();
        }

        if (musicThread != null) {
            musicThread.interrupt();
        }
    }
}