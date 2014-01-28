package com.ksanur.lightbikes;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: bobacadodl
 * Date: 1/23/14
 * Time: 8:23 PM
 */
public class LBPlayer extends LBOfflinePlayer {
    private final Player player;
    private LBGame game;

    protected LBPlayer(Player player) {
        super(player);
        this.player = player;
    }

    public void joinGame(LBGame game) {

    }

    public boolean isInGame(LBGame game) {
        return this.game.equals(game);
    }

    public boolean isInGame() {
        return game != null;
    }

    public LBGame getGame() {
        return game;
    }

    public void crash() {

    }

    public boolean canUseBike(BikeType type) {
        String typeString = type.toString().toLowerCase();
        return player.hasPermission("lightbikes.bikes." + typeString);
    }

    public boolean load() {
        try {
            switch (LightBikes.getDatabaseType()) {
                case MySQL:
                case SQLite:
                    //load
                    PreparedStatement createUserStatement = LightBikes.getSQL().prepare("INSERT IGNORE INTO `players` SET" +
                            "`uuid` = '?'," +
                            "`player` = ?," +
                            "`wins` = ?," +
                            "`losses` = ?;");
                    createUserStatement.setString(1, player.getUniqueId().toString());
                    createUserStatement.setString(2, player.getName());
                    createUserStatement.setInt(3, wins);
                    createUserStatement.setInt(4, losses);
                    LightBikes.getSQL().insert(createUserStatement);

                    PreparedStatement queryUserStatement = LightBikes.getSQL().prepare("SELECT * FROM `players` WHERE `player` = '?';");
                    queryUserStatement.setString(1, player.getName());
                    ResultSet resultSet = LightBikes.getSQL().query(queryUserStatement);
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
}
