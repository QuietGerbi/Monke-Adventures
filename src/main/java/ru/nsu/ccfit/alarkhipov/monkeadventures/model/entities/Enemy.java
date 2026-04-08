package ru.nsu.ccfit.alarkhipov.monkeadventures.model.entities;

import ru.nsu.ccfit.alarkhipov.monkeadventures.observe.Observable;

import java.util.ArrayList;

public class Enemy extends Observable<ArrayList<Float>> implements Entity{
    private int curHP=100;
    private float speed=2f;
    private final int damage = 2;
    private float x, y = 0f;
    private float hitboxRadius = 40f;
    private int experienceValue = 20;

    private final float worldWidth = 8000f;
    private final float worldHeight = 8000f;

    public Enemy(float spawnX, float spawnY) {
        this.x = spawnX;
        this.y = spawnY;
    }

    @Override
    public void update(Player player){
        float dx = player.getX() - this.x;
        float dy = player.getY() - this.y;

        float distance = (float) Math.hypot(dx, dy);

        if (distance > 5) {
            x += (dx / distance) * speed;
            y += (dy / distance) * speed;
        }

        if (this.x < -worldWidth/2) {
            this.x += worldWidth;
        }
        if (this.x > worldWidth/2) {
            this.x -= worldWidth;
        }
        if (this.y < -worldHeight/2) {
            this.y += worldHeight;
        }
        if (this.y > worldHeight/2) {
            this.y -= worldHeight;
        }

        ArrayList<Float> coords = new ArrayList<>();
        coords.add(this.x);
        coords.add(this.y);
        notify(coords);
    }

    @Override
    public void setPosition(float newX, float newY){}

    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
    }

    public float getHitboxRadius() {
        return hitboxRadius;
    }

    public void setHitboxRadius(float hitboxRadius) {
        this.hitboxRadius = hitboxRadius;
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        this.curHP -= damage;
    }

    public boolean isDead() {
        return curHP <= 0;
    }

    public int getCurHP() {
        return curHP;
    }

    public void setCurHP(int curHP) {
        this.curHP = curHP;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
