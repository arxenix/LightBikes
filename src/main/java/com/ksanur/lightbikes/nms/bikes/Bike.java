package com.ksanur.lightbikes.nms.bikes;

import org.bukkit.DyeColor;
import org.bukkit.entity.Creature;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 7:32 PM
 */
public interface Bike {
    public Creature getBukkitEntity();

    public void setPosition(double x, double y, double z);

    public void setSpeed(float speed);

    public float getSpeed();

    public void setAcceleration(float acceleration);

    public float getAcceleration();

    public void setDirection(Direction direction);

    public Direction getDirection();

    public void setTrailColor(DyeColor color);

    public DyeColor getTrailColor();

    public void setTurnDelay(long turnDelay);

    public long getTurnDelay();

    public void turnRight();

    public void turnLeft();

    public void jump();
}
