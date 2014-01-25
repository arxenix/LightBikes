package com.ksanur.lightbikes;

import org.bukkit.OfflinePlayer;

import java.sql.SQLException;

/**
 * User: bobacadodl
 * Date: 1/22/14
 * Time: 11:48 PM
 */
public class LBOfflinePlayer {
    private int id;
    private int wins;
    private int losses;

    private OfflinePlayer player;

    public LBOfflinePlayer(OfflinePlayer player) {
        if (!LightBikes.getSQL().isOpen()) {
            LightBikes.getSQL().open();
        }
        switch (LightBikes.getDatabaseType()) {
            case MySQL:
            case SQLite:
                try {
                    LightBikes.getSQL().prepare("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
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
