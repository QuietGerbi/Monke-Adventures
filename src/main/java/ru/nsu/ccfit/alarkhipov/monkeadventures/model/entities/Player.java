package ru.nsu.ccfit.alarkhipov.monkeadventures.model.entities;

import ru.nsu.ccfit.alarkhipov.monkeadventures.model.weapons.MagicStaff;
import ru.nsu.ccfit.alarkhipov.monkeadventures.model.weapons.Weapon;
import ru.nsu.ccfit.alarkhipov.monkeadventures.observe.Observable;

import java.util.ArrayList;

public class Player extends Observable<ArrayList<Float>> implements Entity {

    private int maxHP=100;
    private float maxSpeed=100f;

    private int currentExp = 0;
    private int expToNextLevel = 100;
    private int level = 1;

    private int curHP=100;
    private float curSpeed=5f;

    private float x, y = 0f;
    private final float hitboxRadius = 50f;

    private int kills=0;
    private float timeAlive=0f;

    private final Weapon weapon = new MagicStaff();

    private final float worldWidth = 8000f;
    private final float worldHeight = 8000f;

    @Override
    public void setPosition(float newX, float newY){
        this.x = newX;
        this.y = newY;

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

        update(this);
    }

    @Override
    public void update(Player player) {
        ArrayList<Float> coords = new ArrayList<>();
        coords.add(player.x);
        coords.add(player.y);
        notify(coords);
    }

    public void addExperience(int amount) {
        currentExp += amount;

        while (currentExp >= expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        currentExp -= expToNextLevel;
        level++;
        expToNextLevel = (int)(expToNextLevel * 1.35);

        weapon.setDamage(weapon.getDamage() + 2);

        maxHP += 25;
        curHP = maxHP;
    }

    public void heal(int amount) {
        curHP = Math.min(curHP + amount, maxHP);
    }

    public void takeDamage(int damage) {
        this.curHP -= damage;
    }

    public boolean isDead() {
        return curHP <= 0;
    }

    public float getHitboxRadius() {
        return hitboxRadius;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Integer maxHP) {
        this.maxHP = maxHP;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getCurHP() {
        return curHP;
    }

    public void setCurHP(Integer curHP) {
        this.curHP = curHP;
    }

    public float getCurSpeed() {
        return curSpeed;
    }

    public void setCurSpeed(float curSpeed) {
        this.curSpeed = curSpeed;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public float getTimeAlive() {
        return timeAlive;
    }

    public void setTimeAlive(float timeAlive) {
        this.timeAlive = timeAlive;
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
