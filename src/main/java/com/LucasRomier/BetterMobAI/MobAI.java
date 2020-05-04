package com.LucasRomier.BetterMobAI;

import com.name.util.Metrics;
import com.LucasRomier.BetterMobAI.API.Settings;
import com.LucasRomier.BetterMobAI.API.VersionTracker;
import com.LucasRomier.BetterMobAI.Events.*;
import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Bosses.BetterGiant;
import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterSkeleton;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class MobAI extends JavaPlugin {
    public static Settings settings;
    private static MobAI instance;

    public static MobAI getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        settings = new Settings();

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new EntityTarget(), this);
        manager.registerEvents(new EntityDamageByEntity(), this);
        manager.registerEvents(new ExplosionEvents(), this);
        manager.registerEvents(new ProjectileEvents(), this);
        manager.registerEvents(new PotionSplash(), this);
        manager.registerEvents(new TeleportEvents(), this);
        manager.registerEvents(new SpawnEvents(), this);
        manager.registerEvents(new PlayerMove(), this);
        manager.registerEvents(new PlayerJoin(), this);

        BetterSkeleton.lastArrow = new HashMap<>();
        BetterMob.busyEntities = new HashMap<>();
        BetterMob.protectedEntities = new HashMap<>();

        System.out.println("[" + getDescription().getName() + "] Current worlds: " + settings.configuration.getStringList("Worlds"));
        System.out.println("[" + getDescription().getName() + "] Current mobs: " + settings.configuration.getStringList("BetterMobs"));

        initMetrics();
        new VersionTracker();

        BetterGiant.init();
    }


    public void onDisable() {
        BetterGiant.stop();
    }

    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if (command.getName().equalsIgnoreCase("BetterMobAI")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!(sender instanceof org.bukkit.entity.Player) || sender.hasPermission("BetterMobAI.reload")) {

                        sender.sendMessage(ChatColor.DARK_GREEN + "Better MobAI reloaded!");
                        settings = new Settings();
                    } else if (!sender.hasPermission("BetterMobAI.reload")) {
                        sender.sendMessage(ChatColor.DARK_RED + "You do not have the permission!");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + ">>Better MobAI<<");
                sender.sendMessage(ChatColor.DARK_GREEN + "Version: " + getDescription().getVersion());
                sender.sendMessage(ChatColor.DARK_GREEN + "Authors: " + getDescription().getAuthors());
                sender.sendMessage(ChatColor.DARK_GREEN + "Website: " + getDescription().getWebsite());
            }
        }
        return false;
    }

    private void initMetrics() {
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.AdvancedPie("used_plugins") {
            public HashMap<String, Integer> getValues(HashMap<String, Integer> hashMap) {
                for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                    hashMap.put(plugin.getName(), 1);
                }
                return hashMap;
            }
        });
    }
}