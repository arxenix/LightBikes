package com.ksanur.lightbikes;

import org.bukkit.entity.EntityType;

/**
 * User: bobacadodl
 * Date: 1/21/14
 * Time: 11:54 PM
 */
public enum BikeType {
    PIG("PigBike", 90, EntityType.PIG),
    SHEEP("SheepBike", 91, EntityType.SHEEP),
    COW("CowBike", 92, EntityType.COW),
    CHICKEN("ChickenBike", 93, EntityType.CHICKEN),
    SQUID("SquidBike", 94, EntityType.SQUID),
    WOLF("WolfBike", 95, EntityType.WOLF),
    MOOSHROOM("MooshroomBike", 96, EntityType.MUSHROOM_COW),
    OCELOT("OcelotBike", 98, EntityType.OCELOT),
    SILVERFISH("SilverfishBike", 60, EntityType.SILVERFISH);

    private String name;
    private int id;
    private EntityType type;

    private BikeType(String name, int id, EntityType type) {
        this.name = name;
        this.id = id;
        this.type = type;
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
}
