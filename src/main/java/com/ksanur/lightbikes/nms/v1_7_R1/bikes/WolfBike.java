package com.ksanur.lightbikes.nms.v1_7_R1.bikes;

import net.minecraft.server.v1_7_R1.BlockCloth;
import net.minecraft.server.v1_7_R1.World;
import org.bukkit.DyeColor;

/**
 * User: bobacadodl
 * Date: 1/24/14
 * Time: 10:06 PM
 */
//https://github.com/Bukkit/mc-dev/blob/master/net/minecraft/server/EntityWolf.java
public class WolfBike extends AgeableBike implements com.ksanur.lightbikes.nms.bikes.WolfBike {
    public WolfBike(World world) {
        super(world);
    }

    public boolean isTamed() {
        return (this.datawatcher.getByte(16) & 4) != 0;
    }

    public void setTamed(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -5)));
        }
    }

    public void setAngry(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public boolean isAngry() {
        return (this.datawatcher.getByte(16) & 2) != 0;
    }

    public void setCollarColor(DyeColor dc) {
        if (isTamed()) {
            byte colour = dc.getWoolData();
            this.datawatcher.watch(20, colour);
        }
    }

    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.a(17, "");
        this.datawatcher.a(16, new Byte((byte) 0));
        this.datawatcher.a(18, new Float(this.getHealth()));
        this.datawatcher.a(19, new Byte((byte) 0));
        this.datawatcher.a(20, new Byte((byte) BlockCloth.b(1)));
    }
}
