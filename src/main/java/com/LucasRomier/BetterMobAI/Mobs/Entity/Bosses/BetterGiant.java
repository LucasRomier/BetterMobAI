package com.LucasRomier.BetterMobAI.Mobs.Entity.Bosses;

import com.LucasRomier.BetterMobAI.API.Reflection;
import com.LucasRomier.BetterMobAI.MobAI;
import com.LucasRomier.BetterMobAI.Mobs.Attacks.GiantAttack;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftGiant;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class BetterGiant extends BetterMob {

    private static List<BetterGiant> betterGiants = new ArrayList<>();
    private List<Zombie> zombies;
    private int protect;
    private int scheduler;
    private int boulder;
    private int main;
    private EntityGiantZombie entityGiantZombie;
    private GiantAttack nextAttack;
    private int cyclesToWait;

    public BetterGiant(Giant giant) {
        super(giant);
        this.entityGiantZombie = ((CraftGiant) this.entity).getHandle();
        clearGoals();
        defaultZombieGoals();

        this.cyclesToWait = 0;
        mainLoop();

        this.zombies = new ArrayList<>();
        doProtection();

        betterGiants.add(this);
    }

    public static void init() {
        for (org.bukkit.World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType().equals(EntityType.GIANT)) {
                    new BetterGiant((Giant) entity);
                }
            }
        }
    }

    public static void stop() {
        for (BetterGiant betterGiant : betterGiants) {
            for (Iterator<Zombie> iterator = betterGiant.zombies.iterator(); iterator.hasNext(); ) {
                iterator.next().remove();
                iterator.remove();
            }
        }
    }

    private void mainLoop() {
        this.main = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    Iterator<Zombie> iterator = zombies.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().remove();
                        iterator.remove();
                        Zombie zombie = (Zombie) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ZOMBIE);
                        zombie.setBaby(true);
                    }
                    Bukkit.getScheduler().cancelTask(main);
                }
                if (nearby().size() > 0) if (nextAttack != null) {
                    switch (nextAttack) {
                        case EARTHQUAKE:
                            earthquake();
                            break;
                        case LIGHTNING_STRIKER:
                            lightningStriker();
                            break;
                        case THROW_BOULDER:
                            throwBoulder();
                            break;
                        case THROW_MINI_ZOMBIE:
                            throwMiniZombie();
                            break;
                    }
                    cyclesToWait = MobAI.settings.configuration.getInt("Giant." + nextAttack.getName() + ".NextAttackDelay");
                    nextAttack = null;
                } else if (!isBusy()) {
                    if (cyclesToWait <= 0) {
                        randomAttack();
                    } else {
                        for (Entity e : entity.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
                            if (e.getType().equals(EntityType.PLAYER)) {
                                Vector vector = entity.getLocation().toVector().subtract(e.getLocation().toVector()).normalize();
                                e.setVelocity(vector.multiply(-1).setY(1));
                            }
                        }
                        cyclesToWait--;
                    }
                }
            }
        }, 0L, 20L);
    }

    private void randomAttack() {
        this.nextAttack = GiantAttack.values()[this.random.nextInt((GiantAttack.values()).length)];
        while (this.zombies.size() <= 0 && this.nextAttack.equals(GiantAttack.THROW_MINI_ZOMBIE)) {
            this.nextAttack = GiantAttack.values()[this.random.nextInt((GiantAttack.values()).length)];
        }
    }

    private void earthquake() {
        final Player player = nearest();
        if (player == null)
            return;
        if (MobAI.settings.configuration.getBoolean("Giant.Earthquake.Disable")) {
            return;
        }

        final double radius = MobAI.settings.configuration.getDouble("Giant.Earthquake.Radius");

        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) MobAI.getInstance(), new Runnable() {
                boolean b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.Earthquake.Speed"), 30.0D);
                boolean handling = false;

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGiant.this.entity.isDead()) {
                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.Earthquake.Speed"), 30.0D);
                    } else {
                        BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
                        if (!this.handling) {
                            BetterGiant.this.entity.setNoDamageTicks(80);
                            BetterGiant.this.entity.setVelocity(new Vector(0, 2, 0));
                            this.handling = true;
                        }

                        if (BetterGiant.this.entity.isOnGround() && this.handling) {
                            for (Entity e : BetterGiant.this.entity.getNearbyEntities(radius, radius, radius)) {
                                if (e.getType().equals(EntityType.PLAYER)) {
                                    Vector vector = BetterGiant.this.entity.getLocation().toVector().subtract(e.getLocation().toVector()).normalize();
                                    e.setVelocity(vector.multiply(-1).setY(1));
                                    ((Player) e).damage(MobAI.settings.configuration.getDouble("Giant.Earthquake.Damage"));
                                }
                            }
                            BetterGiant.this.setBusy(false);
                            Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                        }
                    }
                }
            }, 0L, 5L);
        }
    }

    private void lightningStriker() {
        final Player player = nearest();
        if (player == null)
            return;
        if (MobAI.settings.configuration.getBoolean("Giant.LightningStriker.Disable")) {
            return;
        }

        final double radius = MobAI.settings.configuration.getDouble("Giant.LightningStriker.Radius");

        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.LightningStriker.Speed"), 30.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGiant.this.entity.isDead()) {
                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.LightningStriker.Speed"), 30.0D);
                    } else {
                        BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
                        for (Entity e : BetterGiant.this.entity.getNearbyEntities(radius, radius, radius)) {
                            if (e.getType().equals(EntityType.PLAYER)) {
                                e.getWorld().strikeLightning(e.getLocation());
                            }
                        }
                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    private void throwBoulder() {
        final Player player = nearest();
        if (player == null)
            return;
        if (MobAI.settings.configuration.getBoolean("Giant.ThrowBoulder.Disable")) {
            return;
        }

        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.Speed"), 50.0D);

                public void run() {
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGiant.this.entity.isDead()) {
                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.Speed"), 50.0D);
                        Location location = BetterGiant.this.entity.getLocation();
                        location.setYaw(player.getLocation().getYaw() * -1.0F);
                        BetterGiant.this.entity.teleport(location);
                    } else {
                        BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
                        Location location = BetterGiant.this.entity.getLocation();
                        location.setYaw(player.getLocation().getYaw() * -1.0F);
                        BetterGiant.this.entity.teleport(location);

                        Location inFront = BetterGiant.this.inFront().add(0.0D, 7.0D, 0.0D);
                        FallingBlock block = BetterGiant.this.entity.getWorld().spawnFallingBlock(inFront, new MaterialData(Material.COBBLESTONE));
                        double factor = BetterGiant.this.distance2D(player.getLocation()) / 50.0D * -1.5D;
                        Vector vector = inFront.toVector().subtract(player.getLocation().toVector()).normalize();
                        block.setVelocity(vector.multiply(factor).setY(1));
                        BetterGiant.this.trackBoulder(block);
                        BetterGiant.this.throwArm();

                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    private double distance2D(Location location) {
        return Math.sqrt(Math.pow(this.entity.getLocation().getX() - location.getX(), 2.0D) + Math.pow(this.entity.getLocation().getZ() - location.getZ(), 2.0D));
    }

    private void trackBoulder(FallingBlock paramFallingBlock) {
        this.boulder = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), () -> {
            if (paramFallingBlock.isOnGround()) {
                paramFallingBlock.remove();
                double radius = MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.DamageRadius");
                for (Entity e : paramFallingBlock.getNearbyEntities(radius, radius, radius)) {
                    if (e.getType().equals(EntityType.PLAYER))
                        ((LivingEntity) e).damage(MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.Damage"));
                }

                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GRAY, 1);
                paramFallingBlock.getWorld().spawnParticle(Particle.REDSTONE, paramFallingBlock.getLocation(), 0, 0.0D, 0.0D, 40.0D, dustOptions);
                paramFallingBlock.getWorld().playSound(paramFallingBlock.getLocation(), Sound.BLOCK_STONE_BREAK, 4.0F, 4.0F);
                paramFallingBlock.getLocation().getBlock().setType(Material.AIR);
                Bukkit.getScheduler().cancelTask(boulder);
            }
        }, 0L, 5L);
    }

    private void throwMiniZombie() {
        final Player player = nearest();
        if (player == null)
            return;
        if (MobAI.settings.configuration.getBoolean("Giant.ThrowMiniZombie.Disable")) {
            return;
        }

        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            setBusy(true);
            this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
                boolean b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.ThrowMiniZombie.Speed"), 50.0D);

                public void run() {
                    if (BetterGiant.this.zombies.size() <= 0) {
                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);

                        return;
                    }
                    if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) ||
                            !player.isOnline() || BetterGiant.this.entity.isDead()) {
                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                        return;
                    }
                    if (!this.b) {
                        this.b = BetterGiant.this.track(player.getLocation(), (float) MobAI.settings.configuration.getDouble("Giant.ThrowMiniZombie.Speed"), 50.0D);
                        Location location = BetterGiant.this.entity.getLocation();
                        location.setYaw(player.getLocation().getYaw() * -1.0F);
                        BetterGiant.this.entity.teleport(location);
                    } else {
                        BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
                        Location location = BetterGiant.this.entity.getLocation();
                        location.setYaw(player.getLocation().getYaw() * -1.0F);
                        BetterGiant.this.entity.teleport(location);

                        BetterGiant.this.zombies.get(0).remove();
                        BetterGiant.this.zombies.remove(0);
                        Location inFront = BetterGiant.this.inFront().add(0.0D, 7.0D, 0.0D);
                        Zombie zombie = (Zombie) BetterGiant.this.entity.getWorld().spawnEntity(inFront, EntityType.ZOMBIE);
                        BetterGiant.this.throwArm();
                        double factor = BetterGiant.this.distance2D(player.getLocation()) / 50.0D * -1.5D;
                        Vector vector = inFront.toVector().subtract(player.getLocation().toVector()).normalize();
                        zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1));
                        zombie.setVelocity(vector.multiply(factor).setY(1));
                        zombie.setBaby(true);

                        BetterGiant.this.setBusy(false);
                        Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
                    }
                }
            }, 0L, 5L);
        }
    }

    private Location inFront() {
        Vector vec = this.entity.getLocation().toVector();
        Vector dir = this.entity.getLocation().getDirection();
        vec = vec.add(dir.multiply(3.0));
        return vec.toLocation(this.entity.getWorld());
    }

    private void throwArm() {
        PacketPlayOutAnimation packetPlayOutAnimation = new PacketPlayOutAnimation(this.entityGiantZombie, 0);
        sendPacket(packetPlayOutAnimation);
    }

    private List<Player> nearby() {
        List<Player> list = new ArrayList<>();
        for (Entity entity : this.entity.getNearbyEntities(100.0D, 100.0D, 100.0D)) {
            if (entity.getType().equals(EntityType.PLAYER)) {
                Player player = (Player) entity;
                if (player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) list.add(player);
            }
        }
        return list;
    }

    private Player nearest() {
        double distance = Double.MAX_VALUE;
        Player player = null;
        for (Player p : nearby()) {
            double dist = this.entity.getLocation().distance(p.getLocation());
            if (dist < distance) {
                distance = dist;
                player = p;
            }
        }
        return player;
    }

    private void defaultZombieGoals() {
        this.entityGiantZombie.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this.entityGiantZombie, 0.5D));
        this.entityGiantZombie.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this.entityGiantZombie, EntityHuman.class, 8.0F));
        this.entityGiantZombie.goalSelector.a(8, new PathfinderGoalRandomLookaround(this.entityGiantZombie));
    }

    private void clearGoals() {
        HashSet<?> goalD = (HashSet<?>) Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, this.entityGiantZombie.goalSelector);
        assert goalD != null;
        goalD.clear();
        HashSet<?> targetD = (HashSet<?>) Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, this.entityGiantZombie.targetSelector);
        assert targetD != null;
        targetD.clear();
    }

    private void clearGoals(Entity entity) {
        EntityMonster e = (EntityMonster) ((CraftEntity) entity).getHandle();

        HashSet<?> goalD = (HashSet<?>) Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, e.goalSelector);
        assert goalD != null;
        goalD.clear();
        HashSet<?> targetD = (HashSet<?>) Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, e.targetSelector);
        assert targetD != null;
        targetD.clear();
    }

    public void doProtection() {
        for (int i = 0; i < 15.0D; i++) {
            double x1 = this.entity.getLocation().getX() + 5.0D * Math.cos(Math.toRadians((36 * i)));
            double x3 = this.entity.getLocation().getZ() + 5.0D * Math.sin(Math.toRadians((36 * i)));
            Location location = new Location(this.entity.getWorld(), x1, this.entity.getLocation().getY() + 8.0D, x3);

            Zombie zombie = (Zombie) this.entity.getWorld().spawnEntity(location, EntityType.ZOMBIE);
            clearGoals(zombie);
            zombie.setVelocity(new Vector(0, 0, 0));
            zombie.setAI(false);
            zombie.setBaby(true);
            ((CraftLivingEntity) zombie).setMaxHealth(MobAI.settings.configuration.getDouble("Giant.MiniZombieHealth"));
            zombie.setHealth(MobAI.settings.configuration.getDouble("Giant.MiniZombieHealth"));

            this.zombies.add(zombie);
        }


        this.protect = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobAI.getInstance(), new Runnable() {
            double angle = 0.0D;

            public void run() {
                if (this.angle > 360.0D) this.angle -= 360.0D;

                if (!BetterGiant.this.entity.isDead() && BetterGiant.this.zombies.size() > 2) {
                    double pos = 0.0D;
                    double angleSize = (360.0 / BetterGiant.this.zombies.size());

                    for (Iterator<Zombie> iterator = BetterGiant.this.zombies.iterator(); iterator.hasNext(); ) {
                        Zombie zombie = iterator.next();
                        if (zombie.isDead()) {
                            iterator.remove();
                        } else {
                            double x1 = BetterGiant.this.entity.getLocation().getX() + 5.0D * Math.cos(Math.toRadians(angleSize * pos + this.angle));
                            double x3 = BetterGiant.this.entity.getLocation().getZ() + 5.0D * Math.sin(Math.toRadians(angleSize * pos + this.angle));
                            Location location = new Location(BetterGiant.this.entity.getWorld(), x1, BetterGiant.this.entity.getLocation().getY() + 8.0D, x3);
                            zombie.teleport(location);
                        }
                        pos++;
                    }
                } else {
                    for (Iterator<Zombie> iterator = BetterGiant.this.zombies.iterator(); iterator.hasNext(); ) {
                        iterator.next().remove();
                        iterator.remove();
                        Zombie zombie = (Zombie) BetterGiant.this.entity.getWorld().spawnEntity(BetterGiant.this.entity.getLocation(), EntityType.ZOMBIE);
                        zombie.setBaby(true);
                    }
                    Bukkit.getScheduler().cancelTask(BetterGiant.this.protect);
                }
                this.angle += 4.0D;
            }
        }, 0L, 2L);
    }
}





