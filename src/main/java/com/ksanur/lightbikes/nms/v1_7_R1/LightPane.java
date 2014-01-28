package com.ksanur.lightbikes.nms.v1_7_R1;

import com.ksanur.lightbikes.LBPlayer;
import com.ksanur.lightbikes.LightBikes;
import com.ksanur.lightbikes.nms.NMSBlockProxy;
import net.minecraft.server.v1_7_R1.*;
import org.bukkit.entity.Player;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 5:02 PM
 */
public class LightPane extends BlockStainedGlassPane implements NMSBlockProxy {
    private Object backupOriginal;

    @Override
    public void a(World world, int i, int j, int k, Entity entity, float f) {
        //super is empty
        if (!(entity instanceof EntityPlayer)) {
            return;
        }

        Player player = ((EntityPlayer) entity).getBukkitEntity();
        LBPlayer lbPlayer = LightBikes.getLBPlayer(player);
        if (lbPlayer != null && lbPlayer.isInGame()) {
            lbPlayer.crash();
        }
    }

    public void hook() { //net.minecraft.server.v1_7_R1.Block#119
        backupOriginal = net.minecraft.server.v1_7_R1.Block.e(160);
        LightBikes.getInstance().getLogger().info("Ignore the following warning message about duplicate IDs:");
        this.c(0.3F); //Returns a Block and we can't access protected methods on that :/
        this.a(k); //Same here
        this.c("thinStainedGlass"); //Guess what
        this.d("glass");
        Block.REGISTRY.a(160, "stained_glass_pane", this);
    }

    public void unhook() {
        LightBikes.getInstance().getLogger().info("Ignore the following warning message about duplicate IDs:");
        Block.REGISTRY.a(160, "stained_glass_pane", backupOriginal); //Good thing we made a backup ;)
        backupOriginal = null; //memory and stuff
        LightBikes.getInstance().getLogger().info("Successfully removed proxy!"); //Yay
    }
}
