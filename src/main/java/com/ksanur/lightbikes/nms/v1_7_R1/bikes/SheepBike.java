package com.ksanur.lightbikes.nms.v1_7_R1.bikes;

import net.minecraft.server.v1_7_R1.World;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 7:24 PM
 */
public class SheepBike extends Bike implements com.ksanur.lightbikes.nms.bikes.SheepBike {
    public SheepBike(World world) {
        super(world);
    }

    public int getColor() {
        return this.datawatcher.getByte(16) & 15;
    }

    public void setColor(int i) {
        byte b0 = this.datawatcher.getByte(16);

        byte b = Byte.valueOf((byte) (b0 & 240 | i & 15));
        this.datawatcher.watch(16, b);
    }

    public boolean isSheared() {
        return (this.datawatcher.getByte(16) & 16) != 0;
    }

    public void setSheared(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 16)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -17)));
        }
    }

    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    protected void makeStepSound() {
        this.makeSound("mob.sheep.step", 0.15F, 1.0F);
    }
}
