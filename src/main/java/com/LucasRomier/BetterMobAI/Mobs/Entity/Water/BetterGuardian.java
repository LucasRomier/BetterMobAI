package com.LucasRomier.BetterMobAI.Mobs.Entity.Water;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.GuardianAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftGuardian;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BetterGuardian extends BetterMob {

    private int scheduler;

    public BetterGuardian(Guardian guardian) {
        super(guardian);
    }

    public void normalAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.NormalAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGuardian.this.entity.isDead()) {
                        BetterGuardian.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.NormalAttack.Speed"), 10.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            ((CraftGuardian) BetterGuardian.this.entity).getHandle().a(((CraftPlayer) player).getHandle());
                            BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.NormalAttack.NextAttackDelay"));
                        } else {
                            BetterGuardian.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void instantLaserAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Guardian.InstantAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.InstantAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGuardian.this.entity.isDead()) {
                        BetterGuardian.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.InstantAttack.Speed"), 10.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            ((CraftGuardian) BetterGuardian.this.entity).getHandle().a(((CraftPlayer) player).getHandle(), 0.0F, 0.0F);
                            BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.InstantAttack.NextAttackDelay"));
                        } else {
                            BetterGuardian.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void suffocationAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Guardian.SuffocationVibesAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.SuffocationVibesAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGuardian.this.entity.isDead()) {
                        BetterGuardian.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.SuffocationVibesAttack.Speed"), 10.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            player.setRemainingAir(0);
                            BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.SuffocationVibesAttack.NextAttackDelay"));
                        } else {
                            BetterGuardian.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void nailingAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Guardian.NailingVibesAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.NailingVibesAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGuardian.this.entity.isDead()) {
                        BetterGuardian.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.NailingVibesAttack.Speed"), 10.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * MobAI.settings.configuration.getInt("Guardian.NailingVibesAttack.EffectLength"), 4));
                            BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.NailingVibesAttack.NextAttackDelay"));
                        } else {
                            BetterGuardian.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void trackAndKill(final Player player) {
        if (!isBusy()) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.TrackingSpeed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGuardian.this.entity.isDead()) {
                        BetterGuardian.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGuardian.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Guardian.TrackingSpeed"), 10.0D);
                    } else {
                        BetterGuardian.this.track(BetterGuardian.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterGuardian.this.setBusy(false);
                        BetterGuardian.this.randomAttack(player, 0);
                        Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void randomAttack(final Player player, int delay) {
        setBusy(true);
        final GuardianAttack attack = GuardianAttack.values()[this.random.nextInt((GuardianAttack.values()).length)];
        Bukkit.getScheduler().scheduleSyncDelayedTask(MobAI.getInstance(), () -> {
            switch (attack) {
                case NORMAL_ATTACK:
                    BetterGuardian.this.normalAttack(player);
                    return;
                case INSTANT_LASER_ATTACK:
                    BetterGuardian.this.instantLaserAttack(player);
                    return;
                case NAILING_VIBES_ATTACK:
                    BetterGuardian.this.suffocationAttack(player);
                    return;
                case SUFFOCATION_VIBES_ATTACK:
                    BetterGuardian.this.nailingAttack(player);
                    return;
            }
            BetterGuardian.this.normalAttack(player);
        }, (20 * delay));
    }
}