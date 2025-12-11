package me.Doctor_do.multifunctionalgun.items.materials;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.Doctor_do.multifunctionalgun.items.DMG_SFItemStack;
import org.bukkit.Material;

public final class Machine {

    public static SlimefunItemStack ENERGY_STORAGE_CAN_EMPTY = new DMG_SFItemStack(
            "ENERGY_STORAGE_CAN_EMPTY",
            Material.GLASS_BOTTLE,
            "&f能量存储罐(空)",
            "",
            "&7一个科技感十足的罐子，金属外壳，玻璃护罩，里面空空荡荡的",
            "&8ENERGY_STORAGE_CAN_EMPTY"
    );

    public static SlimefunItemStack ENERGY_STORAGE_CAN_FULL = new DMG_SFItemStack(
            "ENERGY_STORAGE_CAN_FULL",
            Material.DRAGON_BREATH,
            "&f能量存储罐(满)",
            "",
            "&7一个科技感十足的罐子，金属外壳，玻璃护罩，里面能看到实体化的能量在闪闪发光",
            "&8ENERGY_STORAGE_CAN_FULL"
    );

    public static SlimefunItemStack MASS_ENERGY_ENGINE_GENERATOR = new DMG_SFItemStack(
            "MASS_ENERGY_ENGINE_GENERATOR",
            Material.RESPAWN_ANCHOR,
            "&f质能引擎发电机",
            "",
            "&7打破物理法则的发电机，能够将物质完全的转变为能量。&7&m应该不会爆炸",
            "&8MASS_ENERGY_ENGINE_GENERATOR"
    );

    public static SlimefunItemStack ENERGY_COMPRESSION_PLANT = new DMG_SFItemStack(
            "ENERGY_COMPRESSION_PLANT",
            Material.RESPAWN_ANCHOR,
            "&f能量压缩厂",
            "",
            "&7用于将电力能源压缩注入能量存储罐中",
            "&8ENERGY_COMPRESSION_PLANT"
    );

    public static SlimefunItemStack ENERGY_LOADING_PLANT = new DMG_SFItemStack(
            "ENERGY_LOADING_PLANT",
            Material.RESPAWN_ANCHOR,
            "&f能量装填厂",
            "",
            "&7更高级了，可以将反重力虚空粒子转变为能源并注入能量存储罐中",
            "&8ENERGY_LOADING_PLANT"
    );
}
