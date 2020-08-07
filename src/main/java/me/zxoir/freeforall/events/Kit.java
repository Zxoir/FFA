package me.zxoir.freeforall.events;

import lombok.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */
@AllArgsConstructor
@Getter
@Setter
public class Kit {
    @Nonnull
    UUID creator;
    @Nonnull
    LocalDateTime dateCreated;
    @Nonnull
    ItemStack kitIcon;
    @Nonnull
    String kitName;
    @Nonnull
    Inventory inventory;
    @Nullable
    ItemStack[] armour;
    boolean permissionNeeded;
}
