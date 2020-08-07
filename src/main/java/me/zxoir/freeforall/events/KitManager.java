package me.zxoir.freeforall.events;

import com.sun.istack.internal.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static me.zxoir.freeforall.utilities.Constants.debug;

/**
 * MIT License Copyright (c) 2020 Zxoir
 *
 * @author Zxoir
 * @since 8/7/2020
 */
@SuppressWarnings("rawtypes")
public class KitManager {
    private static List<Kit> kits = new ArrayList<>();

    public static void setKits(List<Kit> kits) {
        KitManager.kits = kits;
    }

    public static List<Kit> getKits() {
        return kits;
    }

    @Nullable
    public static Kit getKitByName(String kitName) {
        if (!kits.isEmpty()) {
            for (Kit kit : kits) {
                if (kit.kitName.equalsIgnoreCase(kitName))
                    return kit;
            }
        }

        return null;
    }

    public static void removeKit(Kit kit, Class executedClass) {
        debug("Kit removed." +
                "\nAuthor UUID: " + kit.creator +
                "\nCreation date: " + kit.dateCreated +
                "\nDeletion date: " + LocalDateTime.now() +
                "\nKit name: " + kit.kitName +
                "\nKit icon: " + kit.kitIcon.getType().name(), executedClass);
        kits.remove(kit);
    }

    public static void addKit(Kit kit, Class executedClass) {
        kits.add(kit);
        debug("New kit created." +
                "\nAuthor UUID: " + kit.creator +
                "\nCreation date: " + kit.dateCreated +
                "\nKit name: " + kit.kitName +
                "\nKit icon: " + kit.kitIcon.getType().name(), executedClass);
    }

    // Debug free
    public static void removeKit(Kit kit) {
        kits.remove(kit);
    }

    // Debug free
    public static void addKit(Kit kit) {
        kits.add(kit);
    }

}
