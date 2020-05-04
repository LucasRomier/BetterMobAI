package com.LucasRomier.BetterMobAI.API;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_15_R1.Packet;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class PacketReader {
    private Player player;
    private Channel channel;

    public PacketReader(Player player) {
        this.player = player;
    }

    public void inject() {
        CraftPlayer cp = (CraftPlayer) this.player.getPlayer();
        assert cp != null;
        this.channel = (cp.getHandle()).playerConnection.networkManager.channel;
        this.channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {
            protected void decode(ChannelHandlerContext context, Packet<?> packet, List<Object> list) {
                list.add(packet);
            }
        });
    }

    public void unInject() {
        if (this.channel.pipeline().get("PacketInjector") != null) {
            this.channel.pipeline().remove("PacketInjector");
        }
    }


    public void setValue(Object object, String name, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getValue(Object object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}





