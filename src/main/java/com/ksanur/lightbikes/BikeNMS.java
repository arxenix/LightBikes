package com.ksanur.lightbikes;

import com.ksanur.lightbikes.bikes.Bike;
import net.minecraft.server.v1_7_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 4:12 PM
 */
public class BikeNMS {
    protected static Field mapStringToClassField, mapClassToStringField, mapClassToIdField, mapStringToIdField;

    //protected static Field mapIdToClassField;
    static {
        try {
            mapStringToClassField = net.minecraft.server.v1_7_R1.EntityTypes.class.getDeclaredField("c");
            mapClassToStringField = net.minecraft.server.v1_7_R1.EntityTypes.class.getDeclaredField("d");
            //mapIdtoClassField = net.minecraft.server.v1_7_R1.EntityTypes.class.getDeclaredField("e");
            mapClassToIdField = net.minecraft.server.v1_7_R1.EntityTypes.class.getDeclaredField("f");
            mapStringToIdField = net.minecraft.server.v1_7_R1.EntityTypes.class.getDeclaredField("g");

            mapStringToClassField.setAccessible(true);
            mapClassToStringField.setAccessible(true);
            //mapIdToClassField.setAccessible(true);
            mapClassToIdField.setAccessible(true);
            mapStringToIdField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static Bike spawnBike(BikeType bikeType, Location loc) {
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();

        //get class SquidBike.class
        Class bikeClass = bikeType.getBikeClass();
        if (bikeClass != null) {
            try {
                //get constructor- public SquidBike(world);
                Constructor bikeCtor = bikeClass.getConstructor(World.class);
                //create nms entity - ex new SquidBike(world);
                Bike bike = (Bike) bikeCtor.newInstance(nmsWorld);
                //tp the entity
                bike.setPosition(loc.getX(), loc.getY(), loc.getZ());
                nmsWorld.addEntity(bike);

                return bike;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static void addCustomEntity(Class entityClass, String name, int id) {
        if (mapStringToClassField != null && mapStringToIdField != null && mapClassToStringField != null && mapClassToIdField != null) {
            try {
                Map mapStringToClass = (Map) mapStringToClassField.get(null);
                Map mapStringToId = (Map) mapStringToIdField.get(null);
                Map mapClassToString = (Map) mapClassToStringField.get(null);
                Map mapClassToId = (Map) mapClassToIdField.get(null);

                mapStringToClass.put(name, entityClass);
                mapStringToId.put(name, id);
                mapClassToString.put(entityClass, name);
                mapClassToId.put(entityClass, id);

                mapStringToClassField.set(null, mapStringToClass);
                mapStringToIdField.set(null, mapStringToId);
                mapClassToStringField.set(null, mapClassToString);
                mapClassToIdField.set(null, mapClassToId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
