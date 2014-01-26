package com.ksanur.lightbikes.bikes;

import net.minecraft.server.v1_7_R1.World;

/**
 * User: bobacadodl
 * Date: 1/24/14
 * Time: 11:20 PM
 */
public class OcelotBike extends AgeableBike {
    public OcelotBike(World world) {
        super(world);
    }

    public int getCatType() {
        return this.datawatcher.getByte(18);
    }

    public void setCatType(int i) {
        this.datawatcher.watch(18, (byte) i);
    }

    @Override
    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.a(16, new Byte((byte) 0));
        this.datawatcher.a(18, new Byte((byte) 0));
    }

    @Override
    protected void makeStepSound() {
        this.makeSound("mob.ozelot.step", 0.15F, 1.0F);
    }
}
