package com.ksanur.lightbikes.nms.v1_7_R1.bikes;

import net.minecraft.server.v1_7_R1.World;

/**
 * User: bobacadodl
 * Date: 1/21/14
 * Time: 10:13 PM
 */
public class PigBike extends AgeableBike implements com.ksanur.lightbikes.nms.bikes.PigBike {
    public PigBike(World world) {
        super(world);
    }

    public boolean hasSaddle() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void setSaddle(boolean flag) {
        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) 1));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) 0));
        }
    }

    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }

    protected void makeStepSound() {
        this.makeSound("mob.pig.step", 0.15F, 1.0F);
    }
}
