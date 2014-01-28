package com.ksanur.lightbikes.nms.bikes;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 8:09 PM
 */
public interface WolfBike extends Bike {
    public boolean isTamed();

    public void setTamed(boolean tamed);

    public boolean isAngry();

    public void setAngry(boolean angry);
}
