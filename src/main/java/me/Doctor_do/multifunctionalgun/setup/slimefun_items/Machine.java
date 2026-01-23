package me.Doctor_do.multifunctionalgun.setup.slimefun_items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.Doctor_do.multifunctionalgun.items.electric.machine.Energy_Compressor;
import me.Doctor_do.multifunctionalgun.items.electric.machine.Energy_Converter;
import me.Doctor_do.multifunctionalgun.items.electric.generator.Mass_Energy_Engine_Generator;
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
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR),
            LoreBuilder.speed(Mass_Energy_Engine_Generator.SPEED),//2x
            LoreBuilder.powerBuffer(Mass_Energy_Engine_Generator.CAPACITY),//32768
            LoreBuilder.powerPerSecond(Mass_Energy_Engine_Generator.ENERGY_PRODUCTION * 2),//4096
            "&8MASS_ENERGY_ENGINE_GENERATOR"
    );

    public final static SlimefunItemStack ENERGY_COMPRESSOR = new DMG_SlimefunItemStack(
            "ENERGY_COMPRESSOR",
            Material.RESPAWN_ANCHOR,
            "&f能量压缩器",
            "",
            "&7用于将电力能源压缩注入能量存储罐中",
            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
            LoreBuilder.speed(Energy_Compressor.SPEED),//1x
            LoreBuilder.powerBuffer(Energy_Compressor.CAPACITY),//4096
            LoreBuilder.powerPerSecond(Energy_Compressor.ENERGY_CONSUMPTION * 2),//2048
            "&8ENERGY_COMPRESSOR_PLANT"
    );

    public final static SlimefunItemStack ENERGY_CONVERTER = new DMG_SlimefunItemStack(
            "ENERGY_CONVERTER",
            Material.RESPAWN_ANCHOR,
            "&f能量转换器",
            "",
            "&7可以将反重力虚空粒子转变为能源并注入能量存储罐中",
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
            LoreBuilder.speed(Energy_Converter.SPEED),//4x
            LoreBuilder.powerBuffer(Energy_Converter.CAPACITY),//16384
            LoreBuilder.powerPerSecond(Energy_Converter.ENERGY_CONSUMPTION * 2),//8192
            "&8ENERGY_CONVERTER"
    );
}
