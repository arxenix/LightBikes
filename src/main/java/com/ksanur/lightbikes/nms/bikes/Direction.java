package com.ksanur.lightbikes.nms.bikes;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 9:11 PM
 */
public enum Direction {
    NORTH(180),
    EAST(270),
    SOUTH(0),
    WEST(90);

    private final float yaw;

    private Direction(float yaw) {
        this.yaw = yaw;
    }

    public float getYaw() {
        return yaw;
    }

    public static Direction fromYaw(float yaw) {
        if (yaw <= 45 || yaw > 315) {
            return SOUTH;
        } else if (yaw > 45 && yaw <= 135) {
            return WEST;
        } else if (yaw > 135 && yaw <= 225) {
            return NORTH;
        } else if (yaw > 225 && yaw <= 315) {
            return EAST;
        } else {
            return NORTH;
        }
    }

    public static Direction getRightDirection(Direction d) {
        switch (d) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
        }
        return null;
    }

    public static Direction getLeftDirection(Direction d) {
        switch (d) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
        }
        return null;
    }
}
