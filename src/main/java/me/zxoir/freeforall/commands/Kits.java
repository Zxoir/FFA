package me.zxoir.freeforall.commands;

import me.zxoir.freeforall.events.Kit;
import me.zxoir.freeforall.events.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */
public class Kits implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!KitManager.getKits().isEmpty()) {
                for (Kit kit : KitManager.getKits()) {
                    player.sendMessage(kit.getKitName());
                }
            } else player.sendMessage("Empty");
        }

        return true;
    }

}