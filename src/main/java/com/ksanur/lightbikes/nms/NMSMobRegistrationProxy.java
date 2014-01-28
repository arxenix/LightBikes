package com.ksanur.lightbikes.nms;

import java.lang.reflect.Field;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 5:29 PM
 */
public interface NMSMobRegistrationProxy {
    public Field getStringToClassField() throws NoSuchFieldException;

    public Field getClassToStringField() throws NoSuchFieldException;

    public Field getClassToIdField() throws NoSuchFieldException;

    public Field getStringToIdField() throws NoSuchFieldException;

    public Class getEntityClass();
}
