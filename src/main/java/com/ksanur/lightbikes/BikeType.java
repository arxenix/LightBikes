package com.ksanur.lightbikes;

import com.ksanur.lightbikes.bikes.*;
import org.bukkit.entity.EntityType;

/**
 * User: bobacadodl
 * Date: 1/21/14
 * Time: 11:54 PM
 */
public enum BikeType {
    PIG("Pig", 90, EntityType.PIG, PigBike.class),
    SHEEP("Sheep", 91, EntityType.SHEEP, SheepBike.class),
    COW("Cow", 92, EntityType.COW, CowBike.class),
    CHICKEN("Chicken", 93, EntityType.CHICKEN, ChickenBike.class),
    SQUID("Squid", 94, EntityType.SQUID, SquidBike.class);

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
