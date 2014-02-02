package com.ksanur.lightbikes.commands;

import com.ksanur.lightbikes.LBArena;
import com.ksanur.lightbikes.LightBikes;
import mondocommand.CallInfo;
import mondocommand.dynamic.Sub;

/**
 * User: bobacadodl
 * Date: 1/29/14
 * Time: 4:18 PM
 */
public class ArenaCommand {
    @Sub(name = "create", usage = "<arena> (min) (max)", description = "Create an arena",
            permission = "lightbikes.admin.create", minArgs = 1)
    public void create(CallInfo call) {
        String name = call.getArg(0);
        int min = 4;
        int max = 4;
        try {
            min = call.getIntArg(1);
            max = call.getIntArg(2);
        } catch (NumberFormatException ex) {
            call.reply("{RED}Invalid min and max size!");
            return;
        } catch (Exception ex) {
        }
        LBArena arena = LightBikes.getArena(name);
        if (arena == null) {
            arena = new LBArena(name, min, max);
            LightBikes.addArena(arena);
            call.reply("{GREEN}Arena {AQUA}%s {GREEN}successfully created!");
        } else {
            call.reply("{RED}The arena {AQUA}%s {RED}already exists!");
        }
    }

    @Sub(name = "delete", usage = "<arena>", description = "Delete an arena",
            permission = "lightbikes.admin.delete", minArgs = 1)
    public void delete(CallInfo call) {

    }

    @Sub(name = "setLobby", usage = "<arena>", description = "Set the lobby of an arena",
            permission = "lightbikes.admin.setlobby", minArgs = 1)
    public void setLobby(CallInfo call) {

    }

    @Sub(name = "addSpawn", usage = "<arena>", description = "Add a spawn location to an arena",
            permission = "lightbikes.admin.addspawn", minArgs = 1)
    public void addSpawn(CallInfo call) {

    }

    @Sub(name = "clearSpawns", usage = "<arena>", description = "Clear all spawn locations for an arena",
            permission = "lightbikes.admin.clearspawns", minArgs = 1)
    public void clearSpawns(CallInfo call) {

    }

    @Sub(name = "enable", usage = "<arena>", description = "Enable an arena",
            permission = "lightbikes.admin.enable", minArgs = 1)
    public void enable(CallInfo call) {

    }

    @Sub(name = "disable", usage = "<arena>", description = "Disable an arena",
            permission = "lightbikes.admin.disable", minArgs = 1)
    public void disable(CallInfo call) {

    }
}
