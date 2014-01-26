package com.ksanur.lightbikes;

import com.ksanur.lightbikes.bikes.*;
import org.bukkit.entity.EntityType;

/**
 * User: bobacadodl
 * Date: 1/21/14
 * Time: 11:54 PM
 */
public enum BikeType {
    PIG("PigBike", 90, EntityType.PIG, PigBike.class),
    SHEEP("SheepBike", 91, EntityType.SHEEP, SheepBike.class),
    COW("CowBike", 92, EntityType.COW, CowBike.class),
    CHICKEN("ChickenBike", 93, EntityType.CHICKEN, ChickenBike.class),
    SQUID("SquidBike", 94, EntityType.SQUID, SquidBike.class),
    WOLF("WolfBike", 95, EntityType.WOLF, WolfBike.class),
    MOOSHROOM("MooshroomBike", 96, EntityType.MUSHROOM_COW, MooshroomBike.class),
    OCELOT("OcelotBike", 98, EntityType.OCELOT, OcelotBike.class),
    SILVERFISH("SilverfishBike", 60, EntityType.SILVERFISH, SilverfishBike.class),
    BOAT("BoatBike", 41, EntityType.BOAT, BoatBike.class),
    MINECART("MinecartBike", 42, EntityType.MINECART, MinecartBike.class);

    private String name;
    private int id;
    private EntityType type;
    private Class bikeClass;

    private BikeType(String name, int id, EntityType type, Class bikeClass) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.bikeClass = bikeClass;
    }

    public String getName() {
        return name;
    }

    public EntityType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Class getBikeClass() {
        return bikeClass;
    }
}
