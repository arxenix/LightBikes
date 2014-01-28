package com.ksanur.lightbikes.nms.v1_7_R1;

import com.ksanur.lightbikes.nms.NMSWorldRegistrationProxy;
import net.minecraft.server.v1_7_R1.World;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 7:02 PM
 */
public class WorldRegistration implements NMSWorldRegistrationProxy {
    public World getNMSWorld(org.bukkit.World world) {
        return ((CraftWorld) world).getHandle();
    }

    public Class getNMSWorldClass() {
        return World.class;
    }
}
