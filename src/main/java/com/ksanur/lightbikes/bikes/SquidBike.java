package com.ksanur.lightbikes.bikes;

import net.minecraft.server.v1_7_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftLivingEntity;

import java.lang.reflect.Field;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 3:39 PM
 */
public class SquidBike extends EntitySquid implements Bike {
    Direction currentDirection = Direction.NORTH;
    long lastTurned = 0;

    private float speed = 0.35F;
    private float acceleration = 0.0F;
    private double jumpHeight = 0.5D;

    protected static Field jump = null;
    public SquidBike(World world) {
        super(world);
        try{
            this.jump = EntityLiving.class.getDeclaredField("bd");
            jump.setAccessible(true);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void e(float sideMot, float forMot) {
        //if no passenger, do nothing
        if (this.passenger == null || !(this.passenger instanceof EntityHuman)) {
            super.e(sideMot, forMot);
            this.X = 0.5F;    // Make sure the entity can walk over half slabs, instead of jumping
            return;
        }

        //if passenger is not bobacadodl, do nothing
        /*EntityHuman human = (EntityHuman) this.passenger;
        if (!human.getBukkitEntity().equals(Bukkit.getPlayerExact("bobacadodl"))) {
            // Same as before
            super.e(sideMot, forMot);
            this.X = 0.5F;
            return;
        }*/


        float passengerMot = ((EntityLiving)this.passenger).be;
        if(passengerMot<0){
            //turn right
            turnRight();
        }
        else if(passengerMot>0){
            //turn left
            turnLeft();
        }


        //update yaw and pitch
        this.lastYaw = this.yaw = currentDirection.getYaw();
        this.pitch = this.passenger.pitch * 0.5F;

        // Set the entity's pitch, yaw, head rotation etc.
        this.b(this.yaw, this.pitch); //[url]https://github.com/Bukkit/mc-dev/blob/master/net/minecraft/server/Entity.java#L155-L158[/url]
        this.aP = this.aN = this.yaw;

        this.X = 1.0F;    // The custom entity will now automatically climb up 1 high blocks

        //sideMot = ((EntityLiving) this.passenger).be * 0.5F;
        forMot = 0.4F;//((EntityLiving) this.passenger).bf;
        //if going backwards
        /*if (forMot <= 0.0F) {
            forMot *= 0.25F;// Make backwards slower
        }*/
        //apply motion to entity
        super.e(sideMot, forMot);
        this.i(speed);    // Apply the speed multiplier

        //if reflection successful, and entity on ground, then jump
        if (jump != null && this.onGround) {
            try {
                if (jump.getBoolean(this.passenger)) {
                    jump();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        new Location(world.getWorld(),locX,locY,locZ).getBlock().setType(org.bukkit.Material.STAINED_GLASS_PANE);
    }

    public void c(){
        super.c();
    }

    public void C() {
        //override water breath
        //super.C();
    }

    protected boolean g_() {
        return true;
    }

    public void setSpeed(float speed){
        this.speed=speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration=acceleration;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setDirection(Direction direction){
        this.currentDirection = direction;
        this.lastYaw = this.yaw = direction.getYaw();
        this.b(this.yaw, this.pitch);
        this.aP = this.aN = this.yaw;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void turnRight(){
        long time = System.currentTimeMillis();
        if(time-lastTurned >= 500){
            ((EntityPlayer)this.passenger).getBukkitEntity().playSound(new Location(world.getWorld(), locX, locY, locZ), Sound.NOTE_PLING,1.0f,1.0f);
            lastTurned=time;
            currentDirection = Direction.getRightDirection(currentDirection);
        }
    }

    public void turnLeft(){
        long time = System.currentTimeMillis();
        if(time-lastTurned >= 500){
            ((EntityPlayer)this.passenger).getBukkitEntity().playSound(new Location(world.getWorld(), locX, locY, locZ), Sound.NOTE_PLING,1.0f,1.0f);
            lastTurned=time;
            currentDirection = Direction.getLeftDirection(currentDirection);
        }
    }

    public void jump(){
        this.motY = jumpHeight;  // Used all the time in NMS for entity jumping
    }
}