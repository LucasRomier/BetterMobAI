package com.LucasRomier.BetterMobAI.Events;

import com.LucasRomier.BetterMobAI.MobAI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;

import java.util.Objects;

public class PotionSplash implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPotionSplash(PotionSplashEvent event) {
        if (event.getPotion().getShooter() != null &&
                event.getPotion().getShooter() instanceof Entity) {
            boolean b = false;
            for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
                if (s.equals(((Entity) event.getPotion().getShooter()).getType().toString())) {
                    b = true;
                    break;
                }
            }
            boolean world = false;
            for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
                if (s.equals(Objects.requireNonNull(((Entity) event.getPotion().getShooter()).getLocation().getWorld()).getName())) {
                    world = true;
                    break;
                }
            }
            if (b && world && !((Entity) event.getPotion().getShooter()).getType().equals(EntityType.PLAYER))
                event.setCancelled(true);
        }
    }
}





