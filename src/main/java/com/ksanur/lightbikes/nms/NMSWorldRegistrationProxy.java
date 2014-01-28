package com.ksanur.lightbikes.nms;

/**
 * User: bobacadodl
 * Date: 1/27/14
 * Time: 7:01 PM
 */
public interface NMSWorldRegistrationProxy {
    public Object getNMSWorld(org.bukkit.World world);

    public Class getNMSWorldClass();
}
