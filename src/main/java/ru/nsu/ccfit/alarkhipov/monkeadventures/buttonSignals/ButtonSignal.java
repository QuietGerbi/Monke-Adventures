package ru.nsu.ccfit.alarkhipov.monkeadventures.buttonSignals;

public enum ButtonSignal {
    EXIT(1),
    START(2),
    ABOUT(3),
    SCORES(4);


    final int sig;

    ButtonSignal(int sig) {
        this.sig = sig;
    }
}