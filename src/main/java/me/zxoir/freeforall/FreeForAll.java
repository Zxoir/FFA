package me.zxoir.freeforall;

import me.zxoir.freeforall.commands.CreateKit;
import me.zxoir.freeforall.commands.DeleteKit;
import me.zxoir.freeforall.commands.Kits;
import me.zxoir.freeforall.listeners.OnChat;
import me.zxoir.freeforall.listeners.OnQuit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/6/2020
 */
public final class FreeForAll extends JavaPlugin {
    private static FreeForAll instance;

    @Override
    public void onEnable() {
        instance = this;

        CreateKit.KitCreation();
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getCommand("createkit").setExecutor(new CreateKit());
        getCommand("deletekit").setExecutor(new DeleteKit());
        getCommand("kits").setExecutor(new Kits());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new OnChat(), this);
        getServer().getPluginManager().registerEvents(new OnQuit(), this);
    }

    public static FreeForAll getInstance() {
        return instance;
    }
}
