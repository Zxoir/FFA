package me.zxoir.freeforall.commands;

import me.zxoir.freeforall.events.Kit;
import me.zxoir.freeforall.events.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.zxoir.freeforall.events.KitManager.getKitByName;
import static me.zxoir.freeforall.events.KitManager.removeKit;
import static me.zxoir.freeforall.utilities.Constants.*;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */
public class DeleteKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission(ADMIN_PERMISSION)) {

                if (args.length == 1) {
                    Kit kit = getKitByName(args[0]);

                    if (kit != null) {
                        removeKit(kit, this.getClass());
                        player.sendMessage(colorize("&aKit successfully deleted."));
                    } else player.sendMessage(colorize("&cKit not found."));

                } else player.sendMessage(colorize("&cInvalid arguments. Correct usage: /deletekit <kitName>"));

            } else player.sendMessage(INVALID_PERMISSION);

        }

        return true;
    }

}