package com.ksanur.lightbikes;

import com.ksanur.lightbikes.util.SerializableLocation;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.*;

/**
 * User: bobacadodl
 * Date: 1/26/14
 * Time: 9:07 PM
 */
@SerializableAs("LBArena")
public class LBArena implements ConfigurationSerializable {
    private String name;
    private int minSize, maxSize;

    private SerializableLocation lobbyLocation;
    private List<SerializableLocation> spawnLocations = new ArrayList<SerializableLocation>();

    private Queue<LBPlayer> players = new ArrayDeque<LBPlayer>();

    private boolean enabled;

    public LBArena(String name, int minSize, int maxSize) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;

        this.enabled = false;
    }

    public LBArena(Map<String, Object> input) {
        try {
            name = (String) input.get("name");
            minSize = (Integer) input.get("minSize");
            maxSize = (Integer) input.get("maxSize");

            if (input.containsKey("lobbyLocation")) {
                lobbyLocation = (SerializableLocation) input.get("lobbyLocation");
            }

            if (input.containsKey("spawnLocations")) {
                HashMap<String, SerializableLocation> inputSpawnLocations = (HashMap<String, SerializableLocation>) input.get("spawnLocations");
                for (SerializableLocation serializableLocation : inputSpawnLocations.values()) {
                    spawnLocations.add(serializableLocation);
                }
            }

            enabled = (Boolean) input.get("enabled");
        } catch (Exception ex) {
            ex.printStackTrace();
            LightBikes.getInstance().getLogger().severe("Failed to parse arena! Please check your config!");
        }
    }

    public String getName() {
        return name;
    }

    public List<SerializableLocation> getSpawnLocations() {
        return spawnLocations;
    }

    public void addSpawnLocation(SerializableLocation spawnLocation) {
        spawnLocations.add(spawnLocation);
    }

    public void clearSpawnLocations() {
        spawnLocations.clear();
    }

    public SerializableLocation getLobbyLocation() {
        return lobbyLocation;
    }

    public void setLobbyLocation(SerializableLocation lobbyLocation) {
        this.lobbyLocation = lobbyLocation;
    }

    public void joinPlayer(LBPlayer lbPlayer) {
        players.add(lbPlayer);
    }

    public void kickPlayer() {
        players.remove();
    }

    public void removePlayer(LBPlayer lbPlayer) {
        players.remove(lbPlayer);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean setEnabled(boolean enabled) {
        //if setting enabled
        if (enabled) {
            //check if ready
            if (isReady()) {
                //set enabled
                this.enabled = true;
                return true;
            } else {
                return false;
            }
        } else { //if setting disabled
            this.enabled = false;
            return true;
        }
    }

    private boolean isReady() {
        //check if lobbyLocation is set and enough spawn locations
        return (lobbyLocation != null && spawnLocations.size() >= minSize);
    }

    public Map<String, Object> serialize() {
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("name", name);
        output.put("minSize", minSize);
        output.put("maxSize", maxSize);
        if (lobbyLocation != null) {
            output.put("lobbyLocation", lobbyLocation);
        }
        if (spawnLocations.size() > 0) {
            output.put("spawnLocations", spawnLocations);
        }
        output.put("enabled", enabled);
        return output;
    }
}
