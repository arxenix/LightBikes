package com.ksanur.lightbikes.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * User: bobacadodl
 * Date: 1/28/14
 * Time: 8:49 PM
 */
@SerializableAs("Location")
public class SerializableLocation implements ConfigurationSerializable {
    private World world;
    private double x, y, z;
    private float yaw, pitch;

    public SerializableLocation(Location location) {
        this.world = location.getWorld();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public SerializableLocation(Map<String, Object> input) {
        this.world = Bukkit.getWorld((String) input.get("world"));
        this.x = (Double) input.get("x");
        this.y = (Double) input.get("y");
        this.z = (Double) input.get("z");
        this.yaw = (Float) input.get("yaw");
        this.pitch = (Float) input.get("pitch");
    }

    public Location toLocation() {
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Map<String, Object> serialize() {
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("world", world.getName());
        output.put("x", x);
        output.put("y", y);
        output.put("z", z);
        output.put("yaw", yaw);
        output.put("pitch", pitch);
        return output;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
