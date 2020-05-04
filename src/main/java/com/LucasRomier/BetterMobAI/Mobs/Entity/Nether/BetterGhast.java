package com.LucasRomier.BetterMobAI.Mobs.Entity.Nether;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.GhastAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import net.minecraft.server.v1_15_R1.Entity;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftGhast;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BetterGhast extends BetterMob {

    private int scheduler;

    public BetterGhast(Ghast ghast) {
        super(ghast);
    }


    public void moveTo(Location location, float speed) {
        ((CraftGhast) this.entity).getHandle().getNavigation().a(location.getX(), location.getY() + 1.0D, location.getZ(), speed);
    }

    public void normalAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) MobAI.getInstance(), new Runnable() {
                boolean b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.NormalAttack.Speed"), 20.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGhast.this.entity.isDead()) {
                        BetterGhast.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.NormalAttack.Speed"), 20.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            ((CraftGhast) BetterGhast.this.entity).getHandle().a(((CraftPlayer) player).getHandle());
                            BetterGhast.this.randomAttack(player, MobAI.settings.configuration.getInt("Ghast.NormalAttack.NextAttackDelay"));
                        } else {
                            BetterGhast.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void highSpeedAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Ghast.HighSpeedAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.HighSpeedAttack.Speed"), 20.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGhast.this.entity.isDead()) {
                        BetterGhast.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.HighSpeedAttack.Speed"), 20.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            ((CraftGhast) BetterGhast.this.entity).getHandle().a((Entity) ((CraftPlayer) player).getHandle(), 1.0F, 1.0F);
                            BetterGhast.this.randomAttack(player, MobAI.settings.configuration.getInt("Ghast.HighSpeedAttack.NextAttackDelay"));
                        } else {
                            BetterGhast.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void multiAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Ghast.MultiAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.MultiAttack.Speed"), 20.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGhast.this.entity.isDead()) {
                        BetterGhast.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.MultiAttack.Speed"), 20.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            for (int i = 0; i < 4; i++) {
                                ((CraftGhast) BetterGhast.this.entity).getHandle().a(((CraftPlayer) player).getHandle());
                            }
                            BetterGhast.this.randomAttack(player, MobAI.settings.configuration.getInt("Ghast.MultiAttack.NextAttackDelay"));
                        } else {
                            BetterGhast.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void trackAndKill(final Player player) {
        if (!isBusy()) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.TrackingSpeed"), 30.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGhast.this.entity.isDead()) {
                        BetterGhast.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGhast.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Ghast.TrackingSpeed"), 30.0D);
                    } else {
                        BetterGhast.this.track(BetterGhast.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterGhast.this.setBusy(false);
                        BetterGhast.this.randomAttack(player, 0);
                        Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void randomAttack(final Player player, int delay) {
        setBusy(true);
        final GhastAttack attack = GhastAttack.values()[this.random.nextInt((GhastAttack.values()).length)];
        Bukkit.getScheduler().scheduleSyncDelayedTask(MobAI.getInstance(), () -> {
            switch (attack) {
                case NORMAL_ATTACK:
                    BetterGhast.this.normalAttack(player);
                    return;
                case HIGH_SPEED_FIRE_BALL_ATTACK:
                    BetterGhast.this.highSpeedAttack(player);
                    return;
                case MULTY_FIRE_BALL_ATTACK:
                    BetterGhast.this.multiAttack(player);
                    return;
            }
            BetterGhast.this.normalAttack(player);
        }, (20 * delay));
    }
}





