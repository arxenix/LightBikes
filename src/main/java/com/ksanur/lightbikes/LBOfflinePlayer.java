package com.ksanur.lightbikes;

import org.bukkit.OfflinePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: bobacadodl
 * Date: 1/22/14
 * Time: 11:48 PM
 */
public class LBOfflinePlayer {
    protected int wins;
    protected int losses;

    private final OfflinePlayer player;

    public LBOfflinePlayer(OfflinePlayer player) {
        this.player = player;
    }

    public boolean load() {
        try {
            switch (LightBikes.getDatabaseType()) {
                case MySQL:
                case SQLite:
                    PreparedStatement preparedStatement = LightBikes.getSQL().prepare("SELECT * FROM `players` WHERE `player` = '?';");
                    preparedStatement.setString(1, player.getName());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int wins = resultSet.getInt("wins");
                        int losses = resultSet.getInt("losses");
                        this.wins = wins;
                        this.losses = losses;
                        return true;
                    } else {
                        return false;
                    }
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
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
