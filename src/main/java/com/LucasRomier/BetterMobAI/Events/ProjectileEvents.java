package com.LucasRomier.BetterMobAI.Events;

import com.LucasRomier.BetterMobAI.MobAI;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.Objects;

public class ProjectileEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        boolean b = false;
        for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
            if (s.equals(projectile.getType().toString())) {
                b = true;
                break;
            }
        }
        boolean world = false;
        for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
            if (s.equals(Objects.requireNonNull(projectile.getLocation().getWorld()).getName())) {
                world = true;

                break;
            }
        }
        if (world && b) {
            if (projectile.getType().equals(EntityType.SKELETON)) {
                event.setCancelled(true);
            } else if (projectile.getType().equals(EntityType.GHAST)) {
                event.setCancelled(true);
            } else if (projectile.getType().equals(EntityType.WITCH)) {
                event.setCancelled(true);
            } else if (projectile.getType().equals(EntityType.SPLASH_POTION)) {
                if (projectile.getShooter() instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity) projectile.getShooter();
                    assert e != null;
                    if (!e.getType().equals(EntityType.PLAYER)) {
                        event.setCancelled(true);
                    }
                }
            } else if (projectile.getType().equals(EntityType.ARROW)) {
                if (projectile.getShooter() instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity) projectile.getShooter();
                    assert e != null;
                    if (!e.getType().equals(EntityType.PLAYER)) {
                        event.setCancelled(true);
                    }
                }
            } else if (projectile.getType().equals(EntityType.FIREBALL) && projectile.getShooter() instanceof LivingEntity) {
                LivingEntity e = (LivingEntity) projectile.getShooter();
                assert e != null;
                if (!e.getType().equals(EntityType.PLAYER) &&
                        e.getType().equals(EntityType.GHAST))
                    event.setCancelled(true);
            }
        }
    }
}





