package com.LucasRomier.BetterMobAI.Events;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterBlaze;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterGhast;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterWitherSkeleton;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterZombiePigman;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.*;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Water.BetterGuardian;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftZombie;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.Objects;


public class EntityTarget implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityTarget(EntityTargetEvent event) {
        Entity entity = event.getEntity();
        Entity target = event.getTarget();

        if (target != null && target.getType().equals(EntityType.PLAYER)) {
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
            if (b && world) {
                BetterCreeper creeper;
                BetterSkeleton skeleton;
                BetterWitch witch;
                BetterSpider spider;
                BetterCaveSpider caveSpider;
                BetterEnderman enderman;
                BetterGuardian guardian;
                BetterZombiePigman pigman;
                BetterGhast ghast;
                BetterBlaze blaze;
                switch (entity.getType()) {
                    case ZOMBIE:
                        if (!((CraftZombie) entity).getHandle().isBaby()) {
                            BetterZombie zombie = new BetterZombie((Zombie) entity);
                            zombie.trackAndKill((Player) target);
                        }
                        break;
                    case CREEPER:
                        creeper = new BetterCreeper((Creeper) entity);
                        creeper.trackAndKill((Player) target);
                        break;
                    case SKELETON:
                        if (((CraftSkeleton) entity).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
                            BetterWitherSkeleton witherSkeleton = new BetterWitherSkeleton((Skeleton) entity);
                            witherSkeleton.trackAndKill((Player) target);
                            break;
                        }
                        skeleton = new BetterSkeleton((Skeleton) entity);
                        skeleton.getARide();
                        skeleton.trackAndKill((Player) target);
                        break;

                    case WITCH:
                        witch = new BetterWitch((Witch) entity);
                        witch.trackAndKill((Player) target);
                        break;
                    case SPIDER:
                        spider = new BetterSpider((Spider) entity);
                        spider.trackAndKill((Player) target);
                        break;
                    case CAVE_SPIDER:
                        caveSpider = new BetterCaveSpider((CaveSpider) entity);
                        caveSpider.trackAndKill((Player) target);
                        break;
                    case ENDERMAN:
                        enderman = new BetterEnderman((Enderman) entity);
                        enderman.trackAndKill((Player) target);
                        break;
                    case GUARDIAN:
                        guardian = new BetterGuardian((Guardian) entity);
                        guardian.trackAndKill((Player) target);
                        break;
                    case PIG_ZOMBIE:
                        pigman = new BetterZombiePigman((PigZombie) entity);
                        pigman.trackAndKill((Player) target);
                        break;
                    case GHAST:
                        ghast = new BetterGhast((Ghast) entity);
                        ghast.trackAndKill((Player) target);
                        break;
                    case BLAZE:
                        blaze = new BetterBlaze((Blaze) entity);
                        blaze.trackAndKill((Player) target);
                        break;
                }
            }
        }
    }
}





