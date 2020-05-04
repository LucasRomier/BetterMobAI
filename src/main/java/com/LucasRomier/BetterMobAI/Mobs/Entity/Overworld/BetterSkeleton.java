package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.SkeletonAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.Map;

public class BetterSkeleton extends BetterMob {

    public static Map<String, String> lastArrow;
    private int scheduler;

    public BetterSkeleton(Skeleton skeleton) {
        super(skeleton);
    }

    public void normalAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.NormalAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
                        BetterSkeleton.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.NormalAttack.Speed"), 10.0D);
                    } else {

                        BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
                        Arrow arrow = (Arrow) BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
                        Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
                        arrow.setShooter(BetterSkeleton.this.entity);
                        BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "None");
                        arrow.setVelocity(vector.multiply(1));
                        BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.NormalAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void burningArrowAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Skeleton.BurningArrowAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.BurningArrowAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
                        BetterSkeleton.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.BurningArrowAttack.Speed"), 10.0D);
                    } else {

                        BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
                        Arrow arrow = (Arrow) BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
                        Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
                        arrow.setFireTicks(200);
                        arrow.setShooter(BetterSkeleton.this.entity);
                        BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "Burning");
                        arrow.setVelocity(vector.multiply(1));
                        BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.BurningArrowAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                    }
                }
            },0L, 5L);
        }
    }

    public void poisonedArrowAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Skeleton.PoisonArrowAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.PoisonArrowAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
                        BetterSkeleton.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.PoisonArrowAttack.Speed"), 10.0D);
                    } else {

                        BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
                        Arrow arrow = (Arrow) BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
                        Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
                        BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "Poisoned");
                        arrow.setShooter(BetterSkeleton.this.entity);
                        arrow.setVelocity(vector.multiply(1));
                        BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.PoisonArrowAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void nailingArrowAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Skeleton.NailingArrowAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.NailingArrowAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
                        BetterSkeleton.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.NailingArrowAttack.Speed"), 10.0D);
                    } else {

                        BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
                        Arrow arrow = (Arrow) BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
                        Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
                        BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "Nailing");
                        arrow.setShooter(BetterSkeleton.this.entity);
                        arrow.setVelocity(vector.multiply(1));
                        BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.NailingArrowAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void getARide() {
        if (MobAI.settings.configuration.getBoolean("Skeleton.GetARide.Disable")) {
            return;
        }
        int j = MobAI.settings.configuration.getInt("Skeleton.GetARide.Radius");
        for (Entity e : this.entity.getNearbyEntities(j, j, j)) {
            if (e.getType().equals(EntityType.SPIDER)) {
                if (e instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) e;
                    if (livingEntity.getPassenger() == null) {
                        livingEntity.addPassenger(this.entity);
                        BetterSpider spider = new BetterSpider((Spider) e);
                        spider.setBusy(true);
                        break;
                    }
                }
                continue;
            }
            if (e.getType().equals(EntityType.CAVE_SPIDER) &&
                    e instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) e;
                if (livingEntity.getPassenger() == null) {
                    livingEntity.addPassenger(this.entity);
                    BetterCaveSpider spider = new BetterCaveSpider((CaveSpider) e);
                    spider.setBusy(true);
                    break;
                }
            }
        }
    }


    public void arrowRainAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Skeleton.ArrowRain.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.ArrowRain.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
                        BetterSkeleton.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.ArrowRain.Speed"), 10.0D);
                    } else {

                        BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "None");
                        for (int i = 0; i < MobAI.settings.configuration.getInt("Skeleton.ArrowRain.Arrows"); i++) {
                            Arrow arrow = (Arrow) BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
                            Vector vector;
                            switch (i) {
                                case 0:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.3D, 1.0D, 0.0D)).toVector();
                                    break;
                                case 1:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.3D, 1.0D, 0.3D)).toVector();
                                    break;
                                case 2:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
                                    break;
                                case 3:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(-0.3D, 1.0D, 0.0D)).toVector();
                                    break;
                                case 4:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(-0.3D, 1.0D, -0.3D)).toVector();
                                    break;
                                case 5:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(-0.3D, 1.0D, 0.3D)).toVector();
                                    break;
                                case 6:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.3D, 1.0D, -0.3D)).toVector();
                                    break;
                                default:
                                    vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
                                    break;
                            }
                            arrow.setShooter(BetterSkeleton.this.entity);
                            arrow.setVelocity(vector.multiply(1));
                        }
                        BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.ArrowRain.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void trackAndKill(final Player player) {
        if (!isBusy()) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.TrackingSpeed"), 15.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
                        BetterSkeleton.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSkeleton.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Skeleton.TrackingSpeed"), 15.0D);
                    } else {
                        BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterSkeleton.this.setBusy(false);
                        BetterSkeleton.this.randomAttack(player, 0);
                        Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void randomAttack(final Player player, int delay) {
        setBusy(true);
        getARide();
        Bukkit.getScheduler().scheduleSyncDelayedTask(MobAI.getInstance(), () -> {
            SkeletonAttack attack = SkeletonAttack.values()[BetterSkeleton.this.random.nextInt((SkeletonAttack.values()).length)];
            switch (attack) {
                case NORMAL_ATTACK:
                    BetterSkeleton.this.normalAttack(player);
                    return;
                case ARROW_RAIN_ATTACK:
                    if (BetterSkeleton.this.random.nextInt(10) < 4) {
                        BetterSkeleton.this.arrowRainAttack(player);
                    }
                    return;
                case BURNING_ARROW_ATTACK:
                    BetterSkeleton.this.burningArrowAttack(player);
                    return;
                case NAILING_ARROW_ATTACK:
                    BetterSkeleton.this.nailingArrowAttack(player);
                    return;
                case POISONED_ARROW_ATTACK:
                    BetterSkeleton.this.poisonedArrowAttack(player);
                    return;
            }
            BetterSkeleton.this.normalAttack(player);
        }, (20 * delay));
    }
}





