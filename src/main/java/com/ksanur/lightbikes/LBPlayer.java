package com.ksanur.lightbikes;

import org.bukkit.entity.Player;

/**
 * User: bobacadodl
 * Date: 1/23/14
 * Time: 8:23 PM
 */
public class LBPlayer extends LBOfflinePlayer {
    private final Player player;

    public LBPlayer(Player player) {
        super(player);
        this.player = player;
    }

    public boolean load() {
        if (super.load()) {
            //load

            return true;
        } else {
            return false;
        }
    }

    public void unload() {

    }

    public boolean canUseBike(BikeType type) {
        String typeString = type.toString().toLowerCase();
        return player.hasPermission("lightbikes.bikes." + typeString);
    }
}
