package me.zxoir.freeforall.listeners;

import me.zxoir.freeforall.commands.CreateKit;
import me.zxoir.freeforall.events.CreationProcess;
import me.zxoir.freeforall.events.Kit;
import me.zxoir.freeforall.events.KitManager;
import me.zxoir.freeforall.utilities.Title;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;

import static me.zxoir.freeforall.commands.CreateKit.CreationList;
import static me.zxoir.freeforall.commands.CreateKit.CreationProcessHashMap;
import static me.zxoir.freeforall.utilities.Constants.colorize;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/6/2020
 */
public class OnChat implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (CreationList.containsKey(player)) {
            event.setCancelled(true);
            handle(player, event.getMessage());
        }
    }

    public void handle(Player player, String input) {
        if (input.equalsIgnoreCase("cancel")) {
            CreationList.remove(player);
            CreationProcessHashMap.remove(player);
            player.sendMessage(colorize("&cKit has not been created."));
            return;
        }
        CreationProcess creationProcess = CreationProcessHashMap.get(player);
        CreateKit.CreationStages stage = CreationList.get(player);

        switch (stage) {
            case NAME:
                if (KitManager.getKitByName(input) == null) {
                    creationProcess.setKitName(input);
                    player.sendMessage(colorize("&aKit name has been set!"));
                    CreationList.put(player, CreateKit.CreationStages.PERMISSION);
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                } else
                    player.sendMessage(colorize("&cThere is already a kit with that name, please choose another name."));
                break;
            case PERMISSION:
                if (input.equalsIgnoreCase("true")) {
                    player.sendMessage(colorize("&aKit permission has been set!"));
                    creationProcess.setPermissionNeeded(true);
                    CreationList.put(player, CreateKit.CreationStages.ARMOUR);
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                } else if (input.equalsIgnoreCase("false")) {
                    player.sendMessage(colorize("&aKit permission has not been set."));
                    creationProcess.setPermissionNeeded(false);
                    CreationList.put(player, CreateKit.CreationStages.ARMOUR);
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                } else {
                    player.sendMessage(colorize("&cAccepted values are true or false."));
                }
                break;
            case ARMOUR:
                if (input.equalsIgnoreCase("true")) {
                    player.sendMessage(colorize("&aKit armour has been set!"));
                    creationProcess.setSaveArmour(true);
                    CreationList.put(player, CreateKit.CreationStages.ICON);
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                } else if (input.equalsIgnoreCase("false")) {
                    player.sendMessage(colorize("&aKit armour has not been set."));
                    creationProcess.setSaveArmour(false);
                    CreationList.put(player, CreateKit.CreationStages.ICON);
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                } else {
                    player.sendMessage(colorize("&cAccepted values are true or false."));
                }
                break;
            case ICON:
                if (input.equalsIgnoreCase("done")) {

                    if (player.getItemInHand() != null) {

                        if (!player.getItemInHand().getType().equals(Material.AIR)) {
                            creationProcess.setKitIcon(player.getItemInHand());
                            CreationList.put(player, CreateKit.CreationStages.CONFIRM);
                            player.sendMessage(colorize("&aKit Icon has been set!\n" +
                                    "&7Kit name: &a" + creationProcess.kitName +
                                    "\n&7Permission: &a" + (creationProcess.permissionNeeded ? "freeforall." + creationProcess.kitName : "None." +
                                    "\n&7Save Armour: &a" + creationProcess.saveArmour +
                                    "\n&7Kit Icon: &a" + creationProcess.kitIcon.getType().name())));
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                        } else player.sendMessage(colorize("&cPlease hold an item in your hand."));

                    } else player.sendMessage(colorize("&cPlease hold an item in your hand."));

                } else {
                    player.sendMessage(colorize("&cAccepted value is done"));
                }
                break;
            case CONFIRM:
                if (input.equalsIgnoreCase("yes")) {
                    CreationList.put(player, CreateKit.CreationStages.CONFIRM);
                    Kit kit = new Kit(player.getUniqueId(), LocalDateTime.now(), creationProcess.kitIcon, creationProcess.kitName, creationProcess.getInventory(), (creationProcess.saveArmour ? player.getInventory().getArmorContents() : null), creationProcess.permissionNeeded);
                    KitManager.addKit(kit, this.getClass());
                    CreationList.remove(player);
                    CreationProcessHashMap.remove(player);
                    new Title().send(player, colorize("&aSuccess!"), colorize("&7You have created a new kit"), 30, 60, 30);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);

                    player.sendMessage(colorize("&aKit has been created!"));
                } else if (input.equalsIgnoreCase("no")) {
                    player.sendMessage(colorize("&cKit has not been created."));
                    CreationList.remove(player);
                } else {
                    player.sendMessage(colorize("&cAccepted values are true or false."));
                }
                break;
        }
    }
}
