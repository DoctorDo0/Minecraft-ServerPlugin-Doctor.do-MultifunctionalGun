package me.Doctor_do.multifunctionalgun.items.materials;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;

public final class Machine {

    public static SlimefunItemStack ENERGY_STORAGE_CAN_EMPTY = new SlimefunItemStack(
            "ENERGY_STORAGE_CAN_EMPTY",
            Material.GLASS_BOTTLE,
            "&f能量存储罐(空)(ENERGY_STORAGE_CAN_EMPTY_Name)",
            "&7ENERGY_STORAGE_CAN_EMPTY_Lore"
    );

    public static SlimefunItemStack ENERGY_STORAGE_CAN_FULL = new SlimefunItemStack(
            "ENERGY_STORAGE_CAN_FULL",
            Material.DRAGON_BREATH,
            "&f能量存储罐(满)(ENERGY_STORAGE_CAN_FULL_Name)",
            "&7ENERGY_STORAGE_CAN_FULL_Lore"
    );

    public static SlimefunItemStack MASS_ENERGY_ENGINE_GENERATOR = new SlimefunItemStack(
            "MASS_ENERGY_ENGINE_GENERATOR",
            Material.RESPAWN_ANCHOR,
            "&f质能引擎发电机(MASS_ENERGY_ENGINE_GENERATOR_Name)",
            "&7MASS_ENERGY_ENGINE_GENERATOR_Lore"
    );

    public static SlimefunItemStack ENERGY_COMPRESSION_PLANT = new SlimefunItemStack(
            "ENERGY_COMPRESSION_PLANT",
            Material.RESPAWN_ANCHOR,
            "&f能量压缩厂(ENERGY_COMPRESSION_PLANT_Name)",
            "&7ENERGY_COMPRESSION_PLANT_Lore"
    );

    public static SlimefunItemStack ENERGY_LOADING_PLANT = new SlimefunItemStack(
            "ENERGY_LOADING_PLANT",
            Material.RESPAWN_ANCHOR,
            "&f能量装填厂(ENERGY_LOADING_PLANT_Name)",
            "&7ENERGY_LOADING_PLANT_Lore"
    );
}
