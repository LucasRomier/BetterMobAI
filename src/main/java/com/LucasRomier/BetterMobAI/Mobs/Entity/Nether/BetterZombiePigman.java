package com.LucasRomier.BetterMobAI.Mobs.Entity.Nether;

import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.ZombiePigmanAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BetterZombiePigman extends BetterMob {

    private int scheduler;
    private int secondary;

    public BetterZombiePigman(PigZombie pigZombie) {
        super(pigZombie);
    }

    public void normalAttack(final Player player) {
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.NormalAttack.Speed"), 1.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
                        BetterZombiePigman.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.NormalAttack.Speed"), 1.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            player.damage(MobAI.settings.configuration.getDouble("PigmanZombie.NormalAttack.Damage"));
                            BetterZombiePigman.this.randomAttack(player, MobAI.settings.configuration.getInt("PigmanZombie.NormalAttack.NextAttackDelay"));
                        } else {
                            BetterZombiePigman.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void lightningAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("PigmanZombie.LightningAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.LightningAttack.Speed"), 1.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
                        BetterZombiePigman.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.LightningAttack.Speed"), 1.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            BetterZombiePigman.this.entity.getWorld().strikeLightning(BetterZombiePigman.this.entity.getLocation());
                            player.damage(MobAI.settings.configuration.getDouble("PigmanZombie.LightningAttack.Damage"));
                            BetterZombiePigman.this.randomAttack(player, MobAI.settings.configuration.getInt("PigmanZombie.LightningAttack.NextAttackDelay"));
                        } else {
                            BetterZombiePigman.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    public void swordAttack(final Player player) {
        if (MobAI.settings.configuration.getBoolean("PigmanZombie.SwordAttack.Disable")) {
            normalAttack(player);
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.SwordAttack.Speed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
                        BetterZombiePigman.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.SwordAttack.Speed"), 10.0D);
                    } else {
                        if (!player.isDead() && player.isOnline()) {
                            BetterZombiePigman.this.throwSword(player);
                        } else {
                            BetterZombiePigman.this.setBusy(false);
                        }
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    private void throwSword(final Player player) {
        if (MobAI.settings.configuration.getBoolean("PigmanZombie.ThrowSwordAttack.Disable")) {
            normalAttack(player);
            return;
        }
        final Item item = this.entity.getWorld().dropItem(this.entity.getEyeLocation().clone().add(0.0D, 0.2D, 0.0D), new ItemStack(Material.GOLDEN_SWORD));
        Vector vector = player.getLocation().subtract(this.entity.getLocation()).toVector();
        item.setVelocity(vector.multiply(0.5D));
        this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), () -> {
            if (item.isOnGround()) {
                item.remove();
                Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.secondary);
                return;
            }
            for (Entity entity : item.getNearbyEntities(0.2D, 0.2D, 0.2D)) {
                if (entity.getType().equals(EntityType.PLAYER)) {
                    item.remove();
                    player.damage(MobAI.settings.configuration.getDouble("PigmanZombie.ThrowSwordAttack.Damage"));
                    BetterZombiePigman.this.randomAttack(player, MobAI.settings.configuration.getInt("PigmanZombie.ThrowSwordAttack.NextAttackDelay"));
                    Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.secondary);
                    break;
                }
            }
        }, 0L, 1L);
    }


    public void trackAndKill(final Player player) {
        if (!isBusy()) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.TrackingSpeed"), 10.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
                        BetterZombiePigman.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterZombiePigman.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("PigmanZombie.TrackingSpeed"), 10.0D);
                    } else {
                        BetterZombiePigman.this.track(BetterZombiePigman.this.entity.getLocation(), 0.0F, 0.0D);
                        BetterZombiePigman.this.setBusy(false);
                        BetterZombiePigman.this.randomAttack(player, 0);
                        Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }


    public void randomAttack(final Player player, int delay) {
        setBusy(true);
        final ZombiePigmanAttack attack = ZombiePigmanAttack.values()[this.random.nextInt((ZombiePigmanAttack.values()).length)];
        Bukkit.getScheduler().scheduleSyncDelayedTask(MobAI.getInstance(), () -> {
            switch (attack) {
                case NORMAL_ATTACK:
                    BetterZombiePigman.this.normalAttack(player);
                    return;
                case LIGHTNING_ATTACK:
                    BetterZombiePigman.this.lightningAttack(player);
                    return;
                case SWORD_THROW_ATTACK:
                    BetterZombiePigman.this.swordAttack(player);
                    return;
            }
            BetterZombiePigman.this.normalAttack(player);
        }, (20 * delay));
    }
}





