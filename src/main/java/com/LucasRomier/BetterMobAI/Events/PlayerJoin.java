package com.LucasRomier.BetterMobAI.Events;

import com.LucasRomier.BetterMobAI.API.PacketReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PacketReader reader = new PacketReader(event.getPlayer());
        reader.inject();
    }
}





