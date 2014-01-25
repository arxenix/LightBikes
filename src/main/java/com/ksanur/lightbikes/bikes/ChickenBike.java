package com.ksanur.lightbikes.bikes;

import net.minecraft.server.v1_7_R1.World;

/**
 * User: bobacadodl
 * Date: 1/22/14
 * Time: 10:05 PM
 */
public class ChickenBike extends Bike {
    public ChickenBike(World world) {
        super(world);
    }

    protected void makeStepSound() {
        this.makeSound("mob.chicken.step", 0.15F, 1.0F);
    }
}
