package me.zxoir.freeforall.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/6/2020
 */
@Getter
@Setter
public class CreationProcess {
    public String kitName = "";
    public boolean permissionNeeded = false;
    public boolean saveArmour = false;
    public ItemStack kitIcon;
    public Inventory inventory;
}
