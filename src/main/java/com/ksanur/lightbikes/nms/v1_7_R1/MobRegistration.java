package com.ksanur.lightbikes.nms.v1_7_R1;

import com.ksanur.lightbikes.nms.NMSMobRegistrationProxy;
import net.minecraft.server.v1_7_R1.Entity;
import net.minecraft.server.v1_7_R1.EntityTypes;

import java.lang.reflect.Field;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 5:30 PM
 */
public class MobRegistration implements NMSMobRegistrationProxy {
    public Field getStringToClassField() throws NoSuchFieldException {
        return EntityTypes.class.getDeclaredField("c");
    }

    public Field getClassToStringField() throws NoSuchFieldException {
        return EntityTypes.class.getDeclaredField("d");
    }

    public Field getClassToIdField() throws NoSuchFieldException {
        return EntityTypes.class.getDeclaredField("f");
    }

    public Field getStringToIdField() throws NoSuchFieldException {
        return EntityTypes.class.getDeclaredField("g");
    }

    public Class getEntityClass() {
        return Entity.class;
    }
}
