package com.ksanur.lightbikes;

import org.bukkit.OfflinePlayer;

import java.io.File;

/**
 * User: bobacadodl
 * Date: 1/22/14
 * Time: 11:48 PM
 */
public class LBOfflinePlayer {
    File playerFile;
    private int id;
    private int wins;
    private int losses;

    private OfflinePlayer player;

    public LBOfflinePlayer(OfflinePlayer player) {
        if (LightBikes.getSQL().isOpen()) {
            switch (LightBikes.getDatabaseType()) {
                case MySQL:

                    break;
                case SQLite:

                    break;
            }
        }
    }

    public int getWins() {
        return 0;
    }

    public int getLosses() {
        return 0;
    }

    public int getPoints() {
        return 0;
    }


}
