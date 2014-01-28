package com.ksanur.lightbikes.nms.bikes;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 8:07 PM
 */
public interface SheepBike extends Bike {
    public void setSheared(boolean sheared);

    public boolean isSheared();

    public void setColor(int color);

    public int getColor();
}
