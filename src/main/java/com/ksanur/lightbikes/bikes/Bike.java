package com.ksanur.lightbikes.bikes;

import net.minecraft.server.v1_7_R1.EntityHuman;
import net.minecraft.server.v1_7_R1.EntityLiving;
import net.minecraft.server.v1_7_R1.ItemStack;
import net.minecraft.server.v1_7_R1.World;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 5:42 PM
 */
public interface Bike {
    public void setDirection(Direction d);
    public Direction getDirection();
    public void setSpeed(float speed);
    public float getSpeed();
    public void setAcceleration(float acceleration);
    public float getAcceleration();
    public void turnRight();
    public void turnLeft();
    public void jump();
    //public void setColor();
    //public Color getColor();
}
