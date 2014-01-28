package com.ksanur.lightbikes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * User: bobacadodl
 * Date: 1/25/14
 * Time: 9:43 AM
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        LBPlayer player = new LBPlayer(event.getPlayer());
        if (player.load()) {

        } else {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "ERROR- You are already in a LightBikes server!");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        LBPlayer player = new LBPlayer(event.getPlayer());
        if (player.load()) {

        } else {

        }
    }

}
