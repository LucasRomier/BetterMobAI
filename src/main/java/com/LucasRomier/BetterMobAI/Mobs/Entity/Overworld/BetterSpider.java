package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.SpiderAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSpider;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BetterSpider
        extends BetterMob {
    private int scheduler;

    public BetterSpider(Spider spider) {
        super(spider);
    }

    public void normalAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.NormalAttack.Speed"), 3.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSpider.this.entity.isDead()) {
                        BetterSpider.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.NormalAttack.Speed"), 3.0D);
                    } else {
                        BetterSpider.this.track(BetterSpider.this.entity.getLocation(), 0.0F, 0.0D);
                        ((CraftSpider) BetterSpider.this.entity).getHandle().a(((CraftPlayer) player).getHandle());
                        player.damage(MobAI.settings.configuration.getDouble("Spider.NormalAttack.Damage"));
                        BetterSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("Spider.NormalAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void poisonAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Spider.PoisonAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.PoisonAttack.Speed"), 3.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSpider.this.entity.isDead()) {
                        BetterSpider.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.PoisonAttack.Speed"), 3.0D);
                    } else {
                        BetterSpider.this.track(BetterSpider.this.entity.getLocation(), 0.0F, 0.0D);
                        ((CraftSpider) BetterSpider.this.entity).getHandle().a(((CraftPlayer) player).getHandle());
                        player.damage(MobAI.settings.configuration.getDouble("Spider.PoisonAttack.Damage"));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * MobAI.settings.configuration.getInt("Spider.PoisonAttack.PoisoningTime"), 1));
                        BetterSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("Spider.PoisonAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void skyAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Spider.SkyAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.SkyAttack.Speed"), 3.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSpider.this.entity.isDead()) {
                        BetterSpider.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.SkyAttack.Speed"), 3.0D);
                    } else {
                        BetterSpider.this.track(BetterSpider.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterSpider.this.entity.teleport(BetterSpider.this.entity.getLocation().clone().add(0.0D, 20.0D, 0.0D));
                        BetterSpider.this.entity.setNoDamageTicks(10);
                        ((CraftSpider) BetterSpider.this.entity).getHandle().a(((CraftPlayer) player).getHandle());
                        player.damage(MobAI.settings.configuration.getDouble("Spider.SkyAttack.Damage"));
                        BetterSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("Spider.SkyAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void webAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("Spider.WebAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.WebAttack.Speed"), 3.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSpider.this.entity.isDead()) {
                        BetterSpider.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.WebAttack.Speed"), 3.0D);
                    } else {
                        BetterSpider.this.track(BetterSpider.this.entity.getLocation(), 0.0F, 0.0D);
                        Snowball snowball = (Snowball) BetterSpider.this.entity.getWorld().spawnEntity(BetterSpider.this.entity.getLocation().clone().add(0.0D, 0.2D, 0.0D), EntityType.SNOWBALL);
                        snowball.setVelocity(BetterSpider.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D).subtract(player.getLocation().clone()).toVector().multiply(3));
                        player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.COBWEB);
                        BetterSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("Spider.WebAttack.NextAttackDelay"));
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void trackAndKill(final Player player) {
        if (!isBusy()) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.TrackingSpeed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterSpider.this.entity.isDead()) {
                        BetterSpider.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterSpider.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Spider.TrackingSpeed"), 10.0D);
                    } else {
                        BetterSpider.this.track(BetterSpider.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterSpider.this.setBusy(false);
                        BetterSpider.this.randomAttack(player, 0);
                        Bukkit.getScheduler().cancelTask(BetterSpider.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void randomAttack(final Player player, int delay) {
        setBusy(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(MobAI.getInstance(), () -> {
            SpiderAttack attack = SpiderAttack.values()[BetterSpider.this.random.nextInt((SpiderAttack.values()).length)];
            switch (attack) {
                case NORMAL_ATTACK:
                    BetterSpider.this.normalAttack(player);
                    return;
                case POISON_ATTACK:
                    BetterSpider.this.poisonAttack(player);
                    return;
                case SKY_ATTACK:
                    BetterSpider.this.skyAttack(player);
                    return;
                case WEB_ATTACK:
                    BetterSpider.this.webAttack(player);
                    return;
            }
            BetterSpider.this.normalAttack(player);
        }, (20 * delay));
    }
}





