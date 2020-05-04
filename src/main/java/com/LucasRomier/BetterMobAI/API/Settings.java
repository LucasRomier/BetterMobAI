package com.LucasRomier.BetterMobAI.API;

import com.LucasRomier.BetterMobAI.MobAI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class Settings {
    public ConfigManager.SimpleConfig configuration = (new ConfigManager(MobAI.getInstance())).getNewConfig("Settings.yml");

    public Settings() {
        if (!this.configuration.contains("VersionTracking")) {
            this.configuration.set("VersionTracking", Boolean.TRUE, "Set this to true/false to enable/disable version tracking");
        }

        if (!this.configuration.contains("Worlds")) {
            ArrayList<String> list = new ArrayList<>();
            for (World world : Bukkit.getWorlds()) {
                list.add(world.getName());
            }
            this.configuration.set("Worlds", list, new String[]{"Add all the worlds affected by Better MobAI here", "Remove worlds from here to disable the Better MobAI on these"});
        }


        if (!this.configuration.contains("BetterMobs")) {
            ArrayList<String> list = new ArrayList<>();
            list.add(EntityType.BLAZE.toString());
            list.add(EntityType.CAVE_SPIDER.toString());
            list.add(EntityType.CREEPER.toString());
            list.add(EntityType.ENDERMAN.toString());
            list.add(EntityType.GHAST.toString());
            list.add(EntityType.GUARDIAN.toString());
            list.add(EntityType.PIG_ZOMBIE.toString());
            list.add(EntityType.SKELETON.toString());
            list.add(EntityType.SPIDER.toString());
            list.add(EntityType.WITCH.toString());
            list.add(EntityType.ZOMBIE.toString());
            list.add(EntityType.GIANT.toString());
            this.configuration.set("BetterMobs", list, new String[]{"Add all the mobs affected by Better MobAI here", "Remove mobs from here to disable the Better MobAI on these"});
        }


        if (!this.configuration.contains("CaveSpider")) {
            this.configuration.set("CaveSpider.Health", 12, "Edit the cave spider parameters here");
            this.configuration.set("CaveSpider.TrackingSpeed", 1);

            this.configuration.set("CaveSpider.NormalAttack.Speed", 1.3D);
            this.configuration.set("CaveSpider.NormalAttack.Damage", 10);
            this.configuration.set("CaveSpider.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("CaveSpider.PoisonAttack.Disable", Boolean.FALSE);
            this.configuration.set("CaveSpider.PoisonAttack.Speed", 1.3D);
            this.configuration.set("CaveSpider.PoisonAttack.Damage", 8);
            this.configuration.set("CaveSpider.PoisonAttack.PoisoningTime", 7);
            this.configuration.set("CaveSpider.PoisonAttack.NextAttackDelay", 5);

            this.configuration.set("CaveSpider.SkyAttack.Disable", Boolean.FALSE);
            this.configuration.set("CaveSpider.SkyAttack.Speed", 1.3D);
            this.configuration.set("CaveSpider.SkyAttack.Damage", 13);
            this.configuration.set("CaveSpider.SkyAttack.NextAttackDelay", 5);

            this.configuration.set("CaveSpider.WebAttack.Disable", Boolean.FALSE);
            this.configuration.set("CaveSpider.WebAttack.Speed", 1.3D);
            this.configuration.set("CaveSpider.WebAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Spider")) {
            this.configuration.set("Spider.Health", 16, "Edit the spider parameters here");
            this.configuration.set("Spider.TrackingSpeed", 1);

            this.configuration.set("Spider.NormalAttack.Speed", 1.3D);
            this.configuration.set("Spider.NormalAttack.Damage", 10);
            this.configuration.set("Spider.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("Spider.PoisonAttack.Disable", Boolean.FALSE);
            this.configuration.set("Spider.PoisonAttack.Speed", 1.3D);
            this.configuration.set("Spider.PoisonAttack.Damage", 8);
            this.configuration.set("Spider.PoisonAttack.PoisoningTime", 7);
            this.configuration.set("Spider.PoisonAttack.NextAttackDelay", 5);

            this.configuration.set("Spider.SkyAttack.Disable", Boolean.FALSE);
            this.configuration.set("Spider.SkyAttack.Speed", 1.3D);
            this.configuration.set("Spider.SkyAttack.Damage", 13);
            this.configuration.set("Spider.SkyAttack.NextAttackDelay", 5);

            this.configuration.set("Spider.WebAttack.Disable", Boolean.FALSE);
            this.configuration.set("Spider.WebAttack.Speed", 1.3D);
            this.configuration.set("Spider.WebAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Creeper")) {
            this.configuration.set("Creeper.Health", 20, "Edit the creeper parameters here");
            this.configuration.set("Creeper.TrackingSpeed", 1);

            this.configuration.set("Creeper.NormalAttack.Speed", 1.3D);

            this.configuration.set("Creeper.ImplosionExplosionAttack.Disable", Boolean.FALSE);
            this.configuration.set("Creeper.ImplosionExplosionAttack.Speed", 1.3D);
            this.configuration.set("Creeper.ImplosionExplosionAttack.ImplosionRadius", 10);

            this.configuration.set("Creeper.ChargedCreeperAttack.Disable", Boolean.FALSE);
            this.configuration.set("Creeper.ChargedCreeperAttack.Speed", 1.3D);

            this.configuration.set("Creeper.ChargedSuperCreeperAttack.Disable", Boolean.FALSE);
            this.configuration.set("Creeper.ChargedSuperCreeperAttack.Speed", 1.3D);
        }
        if (!this.configuration.contains("Enderman")) {
            this.configuration.set("Enderman.Health", 40, "Edit the enderman parameters here");
            this.configuration.set("Enderman.TrackingSpeed", 1);

            this.configuration.set("Enderman.NormalAttack.Speed", 1);
            this.configuration.set("Enderman.NormalAttack.Damage", 7);
            this.configuration.set("Enderman.NormalAttack.ConfusionTime", 4);
            this.configuration.set("Enderman.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("Enderman.JumperAttack.Disable", Boolean.FALSE);
            this.configuration.set("Enderman.JumperAttack.Speed", 1);
            this.configuration.set("Enderman.JumperAttack.Damage", 10);
            this.configuration.set("Enderman.JumperAttack.ConfusionTime", 6);
            this.configuration.set("Enderman.JumperAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Skeleton")) {
            this.configuration.set("Skeleton.Health", 20, "Edit the skeleton parameters here");
            this.configuration.set("Skeleton.TrackingSpeed", 1);

            this.configuration.set("Skeleton.NormalAttack.Speed", 1.3D);
            this.configuration.set("Skeleton.NormalAttack.NextAttackDelay", 3);

            this.configuration.set("Skeleton.BurningArrowAttack.Disable", Boolean.FALSE);
            this.configuration.set("Skeleton.BurningArrowAttack.Speed", 1.3D);
            this.configuration.set("Skeleton.BurningArrowAttack.NextAttackDelay", 3);

            this.configuration.set("Skeleton.PoisonArrowAttack.Disable", Boolean.FALSE);
            this.configuration.set("Skeleton.PoisonArrowAttack.Speed", 1.3D);
            this.configuration.set("Skeleton.PoisonArrowAttack.NextAttackDelay", 3);

            this.configuration.set("Skeleton.NailingArrowAttack.Disable", Boolean.FALSE);
            this.configuration.set("Skeleton.NailingArrowAttack.Speed", 1.3D);
            this.configuration.set("Skeleton.NailingArrowAttack.NextAttackDelay", 3);

            this.configuration.set("Skeleton.GetARide.Disable", Boolean.FALSE);
            this.configuration.set("Skeleton.GetARide.Radius", 10);

            this.configuration.set("Skeleton.ArrowRain.Disable", Boolean.FALSE);
            this.configuration.set("Skeleton.ArrowRain.Speed", 1.3D);
            this.configuration.set("Skeleton.ArrowRain.Arrows", 6);
            this.configuration.set("Skeleton.ArrowRain.NextAttackDelay", 3);
        }
        if (!this.configuration.contains("Zombie")) {
            this.configuration.set("Zombie.Health", 20, "Edit the zombie parameters here");
            this.configuration.set("Zombie.TrackingSpeed", 1);

            this.configuration.set("Zombie.NormalAttack.Speed", 1);
            this.configuration.set("Zombie.NormalAttack.Damage", 6);
            this.configuration.set("Zombie.NormalAttack.NextAttackDelay", 3);

            this.configuration.set("Zombie.BloodRushAttack.Disable", Boolean.FALSE);
            this.configuration.set("Zombie.BloodRushAttack.Speed", 1.5D);
            this.configuration.set("Zombie.BloodRushAttack.Damage", 8);
            this.configuration.set("Zombie.BloodRushAttack.NextAttackDelay", 3);

            this.configuration.set("Zombie.MinionsAttack.Disable", Boolean.FALSE);
            this.configuration.set("Zombie.MinionsAttack.Speed", 1);

            this.configuration.set("Zombie.VampireAttack.Disable", Boolean.FALSE);
            this.configuration.set("Zombie.VampireAttack.Speed", 1);
            this.configuration.set("Zombie.VampireAttack.Damage", 8);
            this.configuration.set("Zombie.VampireAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Witch")) {
            this.configuration.set("Witch.Health", 26, "Edit the witch parameters here");
            this.configuration.set("Witch.TrackingSpeed", 1);

            this.configuration.set("Witch.NormalAttack.Speed", 1.3D);
            this.configuration.set("Witch.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.BlackMagicAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.BlackMagicAttack.Speed", 1.3D);
            this.configuration.set("Witch.BlackMagicAttack.EffectLength", 7);
            this.configuration.set("Witch.BlackMagicAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.FireAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.FireAttack.Speed", 1.3D);
            this.configuration.set("Witch.FireAttack.EffectLength", 7);
            this.configuration.set("Witch.FireAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.FireCircleAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.FireCircleAttack.Speed", 1.3D);
            this.configuration.set("Witch.FireCircleAttack.Radius", 2);
            this.configuration.set("Witch.FireCircleAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.LavaAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.LavaAttack.Speed", 1.3D);
            this.configuration.set("Witch.LavaAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.PoisonAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.PoisonAttack.Speed", 1.3D);
            this.configuration.set("Witch.PoisonAttack.EffectLength", 7);
            this.configuration.set("Witch.PoisonAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.SlownessAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.SlownessAttack.Speed", 1.3D);
            this.configuration.set("Witch.SlownessAttack.EffectLength", 5);
            this.configuration.set("Witch.SlownessAttack.NextAttackDelay", 5);

            this.configuration.set("Witch.EndermitesAttack.Disable", Boolean.FALSE);
            this.configuration.set("Witch.EndermitesAttack.Speed", 1.3D);
            this.configuration.set("Witch.EndermitesAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Guardian")) {
            this.configuration.set("Guardian.Normal.Health", 30, "Edit the guardian parameters here");
            this.configuration.set("Guardian.Elder.Health", 80);
            this.configuration.set("Guardian.TrackingSpeed", 0.5D);

            this.configuration.set("Guardian.NormalAttack.Speed", 0.5D);
            this.configuration.set("Guardian.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("Guardian.InstantAttack.Disable", Boolean.FALSE);
            this.configuration.set("Guardian.InstantAttack.Speed", 0.5D);
            this.configuration.set("Guardian.InstantAttack.NextAttackDelay", 5);

            this.configuration.set("Guardian.NailingVibesAttack.Disable", Boolean.FALSE);
            this.configuration.set("Guardian.NailingVibesAttack.Speed", 0.5D);
            this.configuration.set("Guardian.NailingVibesAttack.EffectLength", 4);
            this.configuration.set("Guardian.NailingVibesAttack.NextAttackDelay", 5);

            this.configuration.set("Guardian.SuffocationVibesAttack.Disable", Boolean.FALSE);
            this.configuration.set("Guardian.SuffocationVibesAttack.Speed", 0.5D);
            this.configuration.set("Guardian.SuffocationVibesAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("PigmanZombie")) {
            this.configuration.set("PigmanZombie.Health", 20, "Edit the pigman zombie parameters here");
            this.configuration.set("PigmanZombie.TrackingSpeed", 1);

            this.configuration.set("PigmanZombie.NormalAttack.Speed", 1);
            this.configuration.set("PigmanZombie.NormalAttack.Damage", 6);
            this.configuration.set("PigmanZombie.NormalAttack.NextAttackDelay", 3);

            this.configuration.set("PigmanZombie.LightningAttack.Disable", Boolean.FALSE);
            this.configuration.set("PigmanZombie.LightningAttack.Speed", 1);
            this.configuration.set("PigmanZombie.LightningAttack.Damage", 6);
            this.configuration.set("PigmanZombie.LightningAttack.NextAttackDelay", 3);

            this.configuration.set("PigmanZombie.SwordAttack.Disable", Boolean.FALSE);
            this.configuration.set("PigmanZombie.SwordAttack.Speed", 1);
            this.configuration.set("PigmanZombie.SwordAttack.Damage", 8);
            this.configuration.set("PigmanZombie.SwordAttack.NextAttackDelay", 3);

            this.configuration.set("PigmanZombie.ThrowSwordAttack.Disable", Boolean.FALSE);
            this.configuration.set("PigmanZombie.ThrowSwordAttack.Damage", 8);
            this.configuration.set("PigmanZombie.ThrowSwordAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("WitherSkeleton")) {
            this.configuration.set("WitherSkeleton.Health", 20, "Edit the wither skeleton parameters here");
            this.configuration.set("WitherSkeleton.TrackingSpeed", 1);

            this.configuration.set("WitherSkeleton.NormalAttack.Speed", 1);
            this.configuration.set("WitherSkeleton.NormalAttack.Damage", 8);
            this.configuration.set("WitherSkeleton.NormalAttack.NextAttackDelay", 3);

            this.configuration.set("WitherSkeleton.WitherSkullAttack.Disable", Boolean.FALSE);
            this.configuration.set("WitherSkeleton.WitherSkullAttack.Speed", 1);
            this.configuration.set("WitherSkeleton.WitherSkullAttack.NextAttackDelay", 3);

            this.configuration.set("WitherSkeleton.SwordAttack.Disable", Boolean.FALSE);
            this.configuration.set("WitherSkeleton.SwordAttack.Speed", 1);
            this.configuration.set("WitherSkeleton.SwordAttack.Damage", 10);
            this.configuration.set("WitherSkeleton.SwordAttack.NextAttackDelay", 3);

            this.configuration.set("WitherSkeleton.ThrowSwordAttack.Disable", Boolean.FALSE);
            this.configuration.set("WitherSkeleton.ThrowSwordAttack.Damage", 10);
            this.configuration.set("WitherSkeleton.ThrowSwordAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Ghast")) {
            this.configuration.set("Ghast.Health", 10, "Edit the ghast parameters here");
            this.configuration.set("Ghast.TrackingSpeed", 0.5D);

            this.configuration.set("Ghast.NormalAttack.Speed", 0.5D);
            this.configuration.set("Ghast.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("Ghast.HighSpeedAttack.Disable", Boolean.FALSE);
            this.configuration.set("Ghast.HighSpeedAttack.Speed", 0.5D);
            this.configuration.set("Ghast.HighSpeedAttack.NextAttackDelay", 5);

            this.configuration.set("Ghast.MultiAttack.Disable", Boolean.FALSE);
            this.configuration.set("Ghast.MultiAttack.Speed", 0.5D);
            this.configuration.set("Ghast.MultiAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Blaze")) {
            this.configuration.set("Blaze.Health", 10, "Edit the blaze parameters here");
            this.configuration.set("Blaze.TrackingSpeed", 1);

            this.configuration.set("Blaze.NormalAttack.Speed", 1);
            this.configuration.set("Blaze.NormalAttack.NextAttackDelay", 5);

            this.configuration.set("Blaze.HeatAttack.Disable", Boolean.FALSE);
            this.configuration.set("Blaze.HeatAttack.Speed", 1);
            this.configuration.set("Blaze.HeatAttack.EffectLength", 5);
            this.configuration.set("Blaze.HeatAttack.NextAttackDelay", 5);

            this.configuration.set("Blaze.SmokeAttack.Disable", Boolean.FALSE);
            this.configuration.set("Blaze.SmokeAttack.Speed", 1);
            this.configuration.set("Blaze.SmokeAttack.EffectLength", 5);
            this.configuration.set("Blaze.SmokeAttack.NextAttackDelay", 5);
        }
        if (!this.configuration.contains("Giant")) {
            this.configuration.set("Giant.Health", 200, "Edit the giant parameters here");
            this.configuration.set("Giant.TrackingSpeed", 0.5D);
            this.configuration.set("Giant.MiniZombieHealth", 20);

            this.configuration.set("Giant.ThrowMiniZombie.Disable", Boolean.FALSE);
            this.configuration.set("Giant.ThrowMiniZombie.Speed", 0.5D);
            this.configuration.set("Giant.ThrowMiniZombie.NextAttackDelay", 5);

            this.configuration.set("Giant.ThrowBoulder.Disable", Boolean.FALSE);
            this.configuration.set("Giant.ThrowBoulder.Speed", 0.5D);
            this.configuration.set("Giant.ThrowBoulder.Damage", 14);
            this.configuration.set("Giant.ThrowBoulder.DamageRadius", 6);
            this.configuration.set("Giant.ThrowBoulder.NextAttackDelay", 5);

            this.configuration.set("Giant.Earthquake.Disable", Boolean.FALSE);
            this.configuration.set("Giant.Earthquake.Speed", 0.5D);
            this.configuration.set("Giant.Earthquake.Damage", 8);
            this.configuration.set("Giant.Earthquake.Radius", 10);
            this.configuration.set("Giant.Earthquake.NextAttackDelay", 8);

            this.configuration.set("Giant.LightningStriker.Disable", Boolean.FALSE);
            this.configuration.set("Giant.LightningStriker.Speed", 0.5D);
            this.configuration.set("Giant.LightningStriker.DamageRadius", 10);
            this.configuration.set("Giant.LightningStriker.NextAttackDelay", 8);
        }
        this.configuration.saveConfig();
    }
}





