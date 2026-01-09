package me.Doctor_do.multifunctionalgun.setup.slimefun_items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.Doctor_do.multifunctionalgun.setup.DMG_SlimefunItemStack;
import org.bukkit.Material;

public final class Machine {

    public final static SlimefunItemStack ENERGY_STORAGE_CAN_EMPTY = new DMG_SlimefunItemStack(
            "ENERGY_STORAGE_CAN_EMPTY",
            Material.GLASS_BOTTLE,
            "&f能量存储罐(空)",
            "",
            "&7金属外壳，玻璃护罩，科技感十足的罐子",
            "&8ENERGY_STORAGE_CAN_EMPTY"
    );

    public final static SlimefunItemStack ENERGY_STORAGE_CAN_FULL = new DMG_SlimefunItemStack(
            "ENERGY_STORAGE_CAN_FULL",
            Material.DRAGON_BREATH,
            "&f能量存储罐(满)",
            "",
            "&7存储罐里实体化的能量在闪闪发光",
            "&8ENERGY_STORAGE_CAN_FULL"
    );

    public final static SlimefunItemStack MASS_ENERGY_ENGINE_GENERATOR = new DMG_SlimefunItemStack(
            "MASS_ENERGY_ENGINE_GENERATOR",
            Material.RESPAWN_ANCHOR,
            "&f质能引擎发电机",
            "",
            "&7神奇的发电机，能够将物质完全的转变为能量。&7&m应该不会爆炸",
            "&8MASS_ENERGY_ENGINE_GENERATOR"
    );

    public final static SlimefunItemStack ENERGY_COMPRESSION_PLANT = new DMG_SlimefunItemStack(
            "ENERGY_COMPRESSION_PLANT",
            Material.RESPAWN_ANCHOR,
            "&f能量压缩厂",
            "",
            "&7用于将电力能源压缩注入能量存储罐中",
            "&8ENERGY_COMPRESSION_PLANT"
    );

    public final static SlimefunItemStack ENERGY_LOADING_PLANT = new DMG_SlimefunItemStack(
            "ENERGY_LOADING_PLANT",
            Material.RESPAWN_ANCHOR,
            "&f能量装填厂",
            "",
            "&7可以将反重力虚空粒子转变为能源并注入能量存储罐中",
            "&8ENERGY_LOADING_PLANT"
    );
}
