package com.ksanur.lightbikes.bikes;

import net.minecraft.server.v1_7_R1.World;

/**
 * User: bobacadodl
 * Date: 1/24/14
 * Time: 11:21 PM
 */
public class SilverfishBike extends Bike {
    public SilverfishBike(World world) {
        super(world);
    }

    protected void makeStepSound() {
        this.makeSound("mob.silverfish.step", 0.15F, 1.0F);
    }
}
