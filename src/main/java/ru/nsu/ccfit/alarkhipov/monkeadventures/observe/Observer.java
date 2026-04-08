package ru.nsu.ccfit.alarkhipov.monkeadventures.observe;

public interface Observer<C> {
    void update(C context);
}
