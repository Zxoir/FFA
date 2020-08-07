package me.zxoir.freeforall.utilities;

import me.zxoir.freeforall.FreeForAll;
import org.bukkit.ChatColor;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */

public interface Constants {
    boolean debug = FreeForAll.getInstance().getConfig().getBoolean("Debug");

    String ADMIN_PERMISSION = "freeforall.admin";
    String INVALID_PERMISSION = colorize(FreeForAll.getInstance().getConfig().getString("NoPermission"));

    static String colorize(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }

    static void debug(String arg, @SuppressWarnings("rawtypes") Class executedClass) {
        if (debug)
            FreeForAll.getInstance().getServer().getConsoleSender().sendMessage(
                    "[" + FreeForAll.getInstance().getName() + "] " + executedClass.getName() +
                            " - " + arg
            );
    }
}
