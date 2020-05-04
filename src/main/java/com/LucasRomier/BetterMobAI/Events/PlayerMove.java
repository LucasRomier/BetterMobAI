package com.LucasRomier.BetterMobAI.Events;

import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSkeleton;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;


public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.ADVENTURE) && player.getGameMode().equals(GameMode.SURVIVAL))
            for (Entity entity : player.getNearbyEntities(10.0D, 10.0D, 10.0D)) {
                double distance2D = Math.sqrt(Math.pow(player.getLocation().getX() - entity.getLocation().getX(), 2.0D) +
                        Math.pow(player.getLocation().getZ() - entity.getLocation().getZ(), 2.0D));
                Vector vector = entity.getLocation().subtract(player.getLocation()).toVector().setY(-1);
                vector = vector.multiply(0.02D * Math.sqrt(Math.abs(10.0D - distance2D)));
                vector.setY(-9.81D);

                if (entity.getType().equals(EntityType.SKELETON)) {
                    if (((CraftSkeleton) entity).getSkeletonType() == Skeleton.SkeletonType.NORMAL && !((LivingEntity) entity).hasPotionEffect(PotionEffectType.SLOW))
                        entity.setVelocity(vector);
                    continue;
                }
                if (entity.getType().equals(EntityType.WITCH) && !((LivingEntity) entity).hasPotionEffect(PotionEffectType.SLOW))
                    entity.setVelocity(vector);
            }
    }
}





