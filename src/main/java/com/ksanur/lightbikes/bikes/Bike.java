package com.ksanur.lightbikes.bikes;

import com.ksanur.lightbikes.LightBikes;
import net.minecraft.server.v1_7_R1.*;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

/**
 * User: bobacadodl
 * Date: 1/21/14
 * Time: 10:05 PM
 */
public class Bike extends EntityCreature implements IAnimal {
    Direction currentDirection = Direction.NORTH;
    long lastTurned = 0;
    long turnDelay = 500;

    private float speed = 1.00F;
    private float acceleration = 0.0F;
    private double jumpHeight = 0.8D;
    private DyeColor color = DyeColor.BLUE;

    protected static Field jump = null;

    public Bike(World world) {
        super(world);
        try {
            this.jump = EntityLiving.class.getDeclaredField("bd");
            jump.setAccessible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //set health positive?
        //this.getBukkitEntity().setMaxHealth(20.0);
        //this.setHealth((float) 20.0);
    }

    public void e(float sideMot, float forMot) {
        //if no passenger, do nothing
        if (this.passenger == null || !(this.passenger instanceof EntityHuman)) {
            super.e(sideMot, forMot);
            this.X = 0.5F;    // Make sure the entity can walk over half slabs, instead of jumping
            return;
        }

        float passengerMot = ((EntityLiving) this.passenger).be;
        if (passengerMot < 0) {
            //turn right
            turnRight();
        } else if (passengerMot > 0) {
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

        speed += acceleration;
        //sideMot = ((EntityLiving) this.passenger).be * 0.5F;
        forMot = speed;//((EntityLiving) this.passenger).bf;
        //if going backwards
        /*if (forMot <= 0.0F) {
            forMot *= 0.25F;// Make backwards slower
        }*/
        //this.i(speed);    // Apply the speed multiplier
        //apply motion to entity
        super.e(sideMot, forMot);

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

        createTrack();
    }

    public CraftCreature getBukkitEntity() {
        CraftEntity entity = super.getBukkitEntity();
        if (entity instanceof CraftCreature) {
            return (CraftCreature) entity;
        } else {
            return null;
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
        this.lastYaw = this.yaw = direction.getYaw();
        this.b(this.yaw, this.pitch);
        this.aP = this.aN = this.yaw;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setTrailColor(DyeColor color) {
        this.color = color;
    }

    public DyeColor getTrailColor() {
        return color;
    }

    public void setTurnDelay(long turnDelay) {
        this.turnDelay = turnDelay;
    }

    public long getTurnDelay() {
        return turnDelay;
    }

    public void turnRight() {
        long time = System.currentTimeMillis();
        if (time - lastTurned >= turnDelay) {
            ((EntityPlayer) this.passenger).getBukkitEntity().playSound(new Location(world.getWorld(), locX, locY, locZ), Sound.NOTE_PLING, 1.0f, 1.0f);
            lastTurned = time;
            currentDirection = Direction.getRightDirection(currentDirection);
        }
    }

    public void turnLeft() {
        long time = System.currentTimeMillis();
        if (time - lastTurned >= turnDelay) {
            ((EntityPlayer) this.passenger).getBukkitEntity().playSound(new Location(world.getWorld(), locX, locY, locZ), Sound.NOTE_PLING, 1.0f, 1.0f);
            lastTurned = time;
            currentDirection = Direction.getLeftDirection(currentDirection);
        }
    }

    public void jump() {
        this.motY = jumpHeight;  // Used all the time in NMS for entity jumping
    }

    public void createTrack() {
        final Location track = new Location(world.getWorld(), locX, locY, locZ);
        new BukkitRunnable() {
            public void run() {
                Block block = track.getBlock();
                block.setType(org.bukkit.Material.STAINED_GLASS_PANE);
                block.setData(color.getWoolData());
            }
        }.runTaskLater(LightBikes.getInstance(), 5L);
    }

    @Override
    public ItemStack be() {
        return null;
    }

    @Override
    public ItemStack getEquipment(int i) {
        return null;
    }

    @Override
    public void setEquipment(int i, ItemStack itemStack) {
    }

    @Override
    public ItemStack[] getEquipment() {
        return new ItemStack[0];
    }
}
