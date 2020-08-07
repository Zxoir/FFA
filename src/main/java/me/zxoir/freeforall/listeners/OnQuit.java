package me.zxoir.freeforall.listeners;

import me.zxoir.freeforall.commands.CreateKit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */
public class OnQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CreateKit.CreationList.remove(player);
        CreateKit.CreationProcessHashMap.remove(player);
    }

}
