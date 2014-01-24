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
}
