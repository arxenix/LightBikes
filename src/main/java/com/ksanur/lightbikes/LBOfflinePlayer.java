package com.ksanur.lightbikes;

import org.bukkit.OfflinePlayer;

/**
 * User: bobacadodl
 * Date: 1/22/14
 * Time: 11:48 PM
 */
public class LBOfflinePlayer {
    private int id;
    private int wins;
    private int losses;

    private final OfflinePlayer player;

    public LBOfflinePlayer(OfflinePlayer player) {
        this.player = player;
    }

    public boolean load() {
        return true;
    }

    public void unload() {

    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void addWin() {
        ++wins;
    }

    public int getWins() {
        return wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void addLoss() {
        ++losses;
    }

    public int getLosses() {
        return losses;
    }
}
