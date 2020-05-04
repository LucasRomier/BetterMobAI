package com.LucasRomier.BetterMobAI.Events;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Bosses.BetterGiant;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSkeleton;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Objects;

public class SpawnEvents implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
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
        if (b && world)
            switch (entity.getType()) {
                case CAVE_SPIDER:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("CaveSpider.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("CaveSpider.Health"));
                    break;
                case SPIDER:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Spider.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Spider.Health"));
                    break;
                case CREEPER:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Creeper.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Creeper.Health"));
                    break;
                case ENDERMAN:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Enderman.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Enderman.Health"));
                    break;
                case SKELETON:
                    if (((CraftSkeleton) entity).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
                        ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("WitherSkeleton.Health"));
                        entity.setHealth(MobAI.settings.configuration.getDouble("WitherSkeleton.Health"));
                        break;
                    }
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Skeleton.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Skeleton.Health"));
                    break;

                case ZOMBIE:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Zombie.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Zombie.Health"));
                    break;
                case WITCH:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Witch.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Witch.Health"));
                    break;
                case GUARDIAN:
                    if (((Guardian) entity).isElder()) {
                        ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Guardian.Elder.Health"));
                        entity.setHealth(MobAI.settings.configuration.getDouble("Guardian.Elder.Health"));
                        break;
                    }
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Guardian.Normal.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Guardian.Normal.Health"));
                    break;

                case PIG_ZOMBIE:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("PigmanZombie.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("PigmanZombie.Health"));
                    break;
                case GHAST:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Ghast.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Ghast.Health"));
                    break;
                case BLAZE:
                    ((CraftLivingEntity) entity).setMaxHealth(MobAI.settings.configuration.getDouble("Blaze.Health"));
                    entity.setHealth(MobAI.settings.configuration.getDouble("Blaze.Health"));
                    break;
                case GIANT:
                    new BetterGiant((Giant) entity);
                    break;
            }
    }
}





