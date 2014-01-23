package com.ksanur.lightbikes;

import com.ksanur.lightbikes.bikes.BikePig;
import com.ksanur.lightbikes.bikes.SheepBike;
import org.bukkit.entity.EntityType;

/**
 * User: bobacadodl
 * Date: 1/21/14
 * Time: 11:54 PM
 */
public enum BikeType {
    PIG("Pig",90, EntityType.SKELETON, BikePig.class),;

    private String name;
    private int id;
    private EntityType type;
    private Class bikeClass;
    private BikeType(String name, int id, EntityType type, Class bikeClass){
        this.name=name;
        this.id=id;
        this.type=type;
        this.bikeClass=bikeClass;
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
