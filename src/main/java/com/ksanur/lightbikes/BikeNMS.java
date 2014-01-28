package com.ksanur.lightbikes;

import com.ksanur.lightbikes.nms.NMSBlockProxy;
import com.ksanur.lightbikes.nms.NMSMobRegistrationProxy;
import com.ksanur.lightbikes.nms.NMSWorldRegistrationProxy;
import com.ksanur.lightbikes.nms.bikes.Bike;
import org.bukkit.Location;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 4:12 PM
 */
public class BikeNMS {
    private static NMSMobRegistrationProxy mobRegistration;
    protected static Field mapStringToClassField, mapClassToStringField, mapClassToIdField, mapStringToIdField;

    static {
        try {
            String packageVersion = getPackageVersion();
            Class<?> mobRegistrationClass = Class.forName("com.ksanur.lightbikes.nms." + packageVersion + ".MobRegistration");

            if (NMSMobRegistrationProxy.class.isAssignableFrom(mobRegistrationClass)) {
                mobRegistration = ((NMSMobRegistrationProxy) mobRegistrationClass.newInstance());

                mapStringToClassField = mobRegistration.getStringToClassField();
                mapClassToStringField = mobRegistration.getClassToStringField();
                mapClassToIdField = mobRegistration.getClassToIdField();
                mapStringToIdField = mobRegistration.getStringToIdField();

                mapStringToClassField.setAccessible(true);
                mapClassToStringField.setAccessible(true);
                mapClassToIdField.setAccessible(true);
                mapStringToIdField.setAccessible(true);
            }
        } catch (Exception e) {
            failReflection();
        }
    }

    protected static Bike spawnBike(BikeType bikeType, Location loc) {
        String packageVersion = getPackageVersion();
        try {
            Class<?> worldRegistrationClass = Class.forName("com.ksanur.lightbikes.nms." + packageVersion + ".WorldRegistration");
            if (NMSWorldRegistrationProxy.class.isAssignableFrom(worldRegistrationClass)) {
                NMSWorldRegistrationProxy worldRegistration = ((NMSWorldRegistrationProxy) worldRegistrationClass.newInstance());
                Object nmsWorld = worldRegistration.getNMSWorld(loc.getWorld());

                Class bikeClass = Class.forName("com.ksanur.lightbikes.nms." + packageVersion + ".bikes." + bikeType.getName());
                if (Bike.class.isAssignableFrom(bikeClass)) {
                    Class<?> nmsWorldClass = worldRegistration.getNMSWorldClass();
                    Constructor bikeCtor = bikeClass.getConstructor(nmsWorldClass);
                    Object bikeObject = bikeCtor.newInstance(nmsWorld);

                    Bike bike = (Bike) bikeObject;
                    bike.setPosition(loc.getX(), loc.getY(), loc.getZ());

                    Class entityClass = mobRegistration.getEntityClass();

                    Method addEntity = nmsWorldClass.getDeclaredMethod("addEntity", entityClass);
                    addEntity.invoke(nmsWorld, bikeObject);

                    return bike;
                } else {
                    LightBikes.getInstance().getLogger().severe("Reflection failed! Failed to spawn bike!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            failReflection();
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static void registerBike(BikeType bikeType) {
        String packageVersion = getPackageVersion();
        try {
            Class bikeClass = Class.forName("com.ksanur.lightbikes.nms." + packageVersion + ".bikes." + bikeType.getName());

            Map mapStringToClass = (Map) mapStringToClassField.get(null);
            Map mapStringToId = (Map) mapStringToIdField.get(null);
            Map mapClassToString = (Map) mapClassToStringField.get(null);
            Map mapClassToId = (Map) mapClassToIdField.get(null);

            mapStringToClass.put(bikeType.getName(), bikeClass);
            mapStringToId.put(bikeType.getName(), bikeType.getId());
            mapClassToString.put(bikeClass, bikeType.getName());
            mapClassToId.put(bikeClass, bikeType.getId());

            mapStringToClassField.set(null, mapStringToClass);
            mapStringToIdField.set(null, mapStringToId);
            mapClassToStringField.set(null, mapClassToString);
            mapClassToIdField.set(null, mapClassToId);
        } catch (Exception e) {
            e.printStackTrace();
            failReflection();
        }
    }

    public static void hookBlocks() {
        String packageVersion = getPackageVersion();
        try {
            Class<?> lightPaneClass = Class.forName("com.ksanur.lightbikes.nms." + packageVersion + ".LightPane");
            if (NMSBlockProxy.class.isAssignableFrom(lightPaneClass)) {
                ((NMSBlockProxy) lightPaneClass.newInstance()).hook();
            }
        } catch (Exception e) {
            e.printStackTrace();
            failReflection();
        }
    }

    private static String getPackageVersion() {
        try {
            Class<?> serverClass = LightBikes.getInstance().getServer().getClass();
            if (serverClass.getName().contains("CraftServer")) {
                String packageName = serverClass.getPackage().getName();
                int dotIndex = packageName.lastIndexOf('.');
                if (dotIndex != -1) {
                    return packageName.substring(dotIndex + 1);
                }
            }
        } catch (NoClassDefFoundError ignore) {
        }
        LightBikes.getInstance().getLogger().warning("You seem to have a pretty modified version of CraftBukkit. " +
                "Could not get Package Version from CraftServer's package. Falling back to alternative implementation. " +
                "If you experience any further errors with this plugins, these will almost definitely be caused by this. " +
                "To fix this, get CraftBukkit or Spigot.");

        return null;
    }

    private static void failReflection() {
        LightBikes.getInstance().getLogger().severe("Reflection failed! This is an unsupported minecraft version! " +
                "Please wait for an update of this plugin to be released, or downgrade. " +
                "The plugin will now be disabled....");
        LightBikes.getInstance().getPluginLoader().disablePlugin(LightBikes.getInstance());
    }
}
