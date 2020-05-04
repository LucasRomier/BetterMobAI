package com.LucasRomier.BetterMobAI.Events;

import com.LucasRomier.BetterMobAI.MobAI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import java.util.Objects;

public class ExplosionEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityFuse(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();

        boolean b = false;
        for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
            if (s.equals(entity.getType().toString())) {
                b = true;
                break;
            }
        }
        boolean world = false;
        for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
            if (s.equals(Objects.requireNonNull(entity.getLocation().getWorld()).getName())) {
                world = true;

                break;
            }
        }
        if (b && world && entity.getType().equals(EntityType.CREEPER))
            event.setCancelled(false);
    }
}





