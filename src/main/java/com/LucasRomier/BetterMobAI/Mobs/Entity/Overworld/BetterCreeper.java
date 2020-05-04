package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.CreeperAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.Random;

public class BetterCreeper extends BetterMob {

    private int scheduler;

    public BetterCreeper(Creeper creeper) {
        super(creeper);
    }

    public void normalAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.NormalAttack.Speed"), 4.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterCreeper.this.entity.isDead()) {
                        BetterCreeper.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.NormalAttack.Speed"), 4.0D);
                    } else {
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void implosionExplosionAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Creeper.ImplosionExplosionAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.ImplosionExplosionAttack.Speed"), 4.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterCreeper.this.entity.isDead()) {
                        BetterCreeper.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.ImplosionExplosionAttack.Speed"), 4.0D);
                    } else {
                        int j = MobAI.settings.configuration.getInt("Creeper.ImplosionExplosionAttack.ImplosionRadius");
                        for (Entity e : BetterCreeper.this.entity.getNearbyEntities(j, j, j)) {
                            if (e instanceof LivingEntity) {
                                Vector vector = BetterCreeper.this.entity.getLocation().subtract(e.getLocation()).toVector();
                                e.setVelocity(vector);
                            }
                        }
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void chargedCreeperAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Creeper.ChargedCreeperAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.entity.getWorld().strikeLightning(this.entity.getLocation());
            ((Creeper) this.entity).setPowered(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.ChargedCreeperAttack.Speed"), 4.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterCreeper.this.entity.isDead()) {
                        BetterCreeper.this.setBusy(false);
                        ((Creeper) BetterCreeper.this.entity).setPowered(false);
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.ChargedCreeperAttack.Speed"), 4.0D);
                    } else {
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void chargedSuperCreeperAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.entity.getWorld().strikeLightning(this.entity.getLocation());
            ((Creeper) this.entity).setPowered(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.ChargedSuperCreeperAttack.Speed"), 4.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterCreeper.this.entity.isDead()) {
                        BetterCreeper.this.setBusy(false);
                        ((Creeper) BetterCreeper.this.entity).setPowered(false);
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.ChargedSuperCreeperAttack.Speed"), 4.0D);
                    } else {
                        for (int i = 0; i < BetterCreeper.this.random.nextInt(3) + 3; i++) {

                            Location location = BetterCreeper.this.entity.getLocation().clone().add((BetterCreeper.this.random.nextInt(6) - 3), 0.0D, ((new Random()).nextInt(6) - 3));
                            Creeper creeper = (Creeper) BetterCreeper.this.entity.getWorld().spawnEntity(location, EntityType.CREEPER);
                            (new BetterCreeper(creeper)).normalAttack(player);
                        }
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void trackAndKill(final Player player) {
        if (!isBusy()) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.TrackingSpeed"), 7.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterCreeper.this.entity.isDead()) {
                        BetterCreeper.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterCreeper.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Creeper.TrackingSpeed"), 7.0D);
                    } else {
                        BetterCreeper.this.track(BetterCreeper.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterCreeper.this.setBusy(false);
                        BetterCreeper.this.randomAttack(player, 0);
                        Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void randomAttack(final Player player, int delay) {
        setBusy(true);
        final CreeperAttack attack = CreeperAttack.values()[this.random.nextInt((CreeperAttack.values()).length)];
        Bukkit.getScheduler().scheduleSyncDelayedTask(MobAI.getInstance(), () -> {
            switch (attack) {
                case NORMAL_ATTACK:
                    BetterCreeper.this.normalAttack(player);
                    return;
                case CHARGED_CREEPER_ATTACK:
                    BetterCreeper.this.chargedCreeperAttack(player);
                    return;
                case CHARGED_SUPER_CREEPER_ATTACK:
                    BetterCreeper.this.chargedSuperCreeperAttack(player);
                    return;
                case IMPLOSION_EXPLOSION_ATTACK:
                    BetterCreeper.this.implosionExplosionAttack(player);
                    return;
            }
            BetterCreeper.this.normalAttack(player);
        }, (20 * delay));
    }
}





