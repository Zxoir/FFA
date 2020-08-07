package me.zxoir.freeforall.commands;

import me.zxoir.freeforall.FreeForAll;
import me.zxoir.freeforall.events.CreationProcess;
import me.zxoir.freeforall.utilities.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

import static me.zxoir.freeforall.utilities.Constants.*;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */
public class CreateKit implements CommandExecutor {
    public static final HashMap<Player, CreationProcess> CreationProcessHashMap = new HashMap<>();
    public static final HashMap<Player, CreationStages> CreationList = new HashMap<>();

    public static void KitCreation() {
        Title title = new Title();

        Bukkit.getScheduler().runTaskTimerAsynchronously(FreeForAll.getInstance(), () -> {

            if (!CreationList.isEmpty()) {

                for (Player player : CreationList.keySet()) {
                    CreationStages stage = CreationList.get(player);

                    switch (stage) {
                        case NAME:
                            title.send(player, colorize("&bKit name"), colorize("&7Send the name of the kit in Chat"), 0, 20, 40);
                            break;
                        case PERMISSION:
                            title.send(player, colorize("&bRequires Permission"), colorize("&7Send true or false in Chat"), 0, 20, 40);
                            break;
                        case ARMOUR:
                            title.send(player, colorize("&bInclude Armour"), colorize("&7Send true or false in Chat"), 0, 20, 40);
                            break;
                        case ICON:
                            title.send(player, colorize("&bKit Icon"), colorize("&7Hold a item then send done in Chat"), 0, 20, 40);
                            break;
                        case CONFIRM:
                            title.send(player, colorize("&bConfirmation"), colorize("&7Send yes or no in Chat"), 0, 20, 40);
                            break;
                    }

                }

            }

        }, 0L, 5L);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission(ADMIN_PERMISSION)) {

                if (!CreationList.containsKey(player)) {
                    CreationList.put(player, CreationStages.NAME);
                    CreationProcess creationProcess = new CreationProcess();
                    creationProcess.setInventory(duplicateInventory(player.getInventory()));

                    CreationProcessHashMap.put(player, new CreationProcess());
                    player.sendMessage(colorize("&7To cancel this kit, simply send &8cancel &7in chat."));
                } else player.sendMessage(colorize("&7You're already creating a kit."));

            } else player.sendMessage(INVALID_PERMISSION);

        }

        return true;
    }

    public enum CreationStages {
        NAME, PERMISSION, ARMOUR, ICON, CONFIRM
    }

    private Inventory duplicateInventory(Inventory inventory) {
        Inventory duplicate = Bukkit.createInventory(null, inventory.getSize());
        duplicate.setContents(inventory.getContents());
        return duplicate;
    }

}
