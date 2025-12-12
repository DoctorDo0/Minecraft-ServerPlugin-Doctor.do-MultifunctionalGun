package me.Doctor_do.multifunctionalgun.items.materials;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.Doctor_do.multifunctionalgun.setup.DMG_SlimefunItemStack;
import org.bukkit.Material;

public final class Advanced_Materials {

    public static SlimefunItemStack PULSED_LASER_GENERATOR = new DMG_SlimefunItemStack(
            "PULSED_LASER_GENERATOR",
            Material.REDSTONE_TORCH,
            "&f脉冲激光发生器",
            "",
            "&7功率更高了，高温使得被照射的物体表面变成了等离子体",
            "&8PULSED_LASER_GENERATOR"
    );

    public static SlimefunItemStack COMPLETE_DEFLECTION_CRYSTAL = new DMG_SlimefunItemStack(
            "COMPLETE_DEFLECTION_CRYSTAL",
            Material.TINTED_GLASS,
            "&f完整的偏折晶体块",
            "",
            "&7一块更大的透明晶体，可以承受并折射更强的光束",
            "&8COMPLETE_DEFLECTION_CRYSTAL"
    );

    public static SlimefunItemStack FORCE_FIELD_CONTAINMENT_GENERATOR = new DMG_SlimefunItemStack(
            "FORCE_FIELD_CONTAINMENT_GENERATOR",
            Material.STRUCTURE_BLOCK,
            "&f力场束缚发生器",
            "",
            "&7&7&m也许真的能用来制作AT力场?",
            "&8FORCE_FIELD_CONTAINMENT_GENERATOR"
    );

    public static SlimefunItemStack STORAGE_CONTAINER = new DMG_SlimefunItemStack(
            "STORAGE_CONTAINER",
            Material.CHEST,
            "&f存储容器",
            "",
            "&7一个空间很大的便携式箱子",
            "&8STORAGE_CONTAINER"
    );

    public static SlimefunItemStack SENSOR_MONITORING_ARRAY = new DMG_SlimefunItemStack(
            "SENSOR_MONITORING_ARRAY",
            Material.OBSERVER,
            "&f传感器监测阵列",
            "",
            "&7内置了很多用来监测收集数据的装置",
            "&8SENSOR_MONITORING_ARRAY"
    );

    public static SlimefunItemStack REGULATOR_ARRAY = new DMG_SlimefunItemStack(
            "REGULATOR_ARRAY",
            Material.CLOCK,
            "&f调节器阵列",
            "",
            "&7&m???这是什么东西",
            "&8REGULATOR_ARRAY"
    );

    public static SlimefunItemStack INTERFACE_ARRAY = new DMG_SlimefunItemStack(
            "INTERFACE_ARRAY",
            Material.COMPASS,
            "&f接口阵列",
            "",
            "&7&m???这是什么东西",
            "&8INTERFACE_ARRAY"
    );

    public static SlimefunItemStack STRUCTURAL_REINFORCEMENT_MODULE = new DMG_SlimefunItemStack(
            "STRUCTURAL_REINFORCEMENT_MODULE",
            Material.IRON_BLOCK,
            "&f结构强化模块",
            "",
            "&7一个更坚固的外壳，可以保护里面的东西不会损坏。&7&m也更沉了",
            "&8STRUCTURAL_REINFORCEMENT_MODULE"
    );

    public static SlimefunItemStack CENTRAL_DATA_PROCESSING_HUB = new DMG_SlimefunItemStack(
            "CENTRAL_DATA_PROCESSING_HUB",
            Material.COMMAND_BLOCK,
            "&f中央数据处理核心中枢",
            "",
            "&7一块计算阵列，由数个中央数据处理单元，快速缓存存储单元构成",
            "&8CENTRAL_DATA_PROCESSING_HUB"
    );

    public static SlimefunItemStack TEMPERATURE_RAISE_COMPONENTS = new DMG_SlimefunItemStack(
            "TEMPERATURE_RAISE_COMPONENTS",
            Material.ORANGE_WOOL,
            "&f升温发热组",
            "",
            "&x&f&f&a&0&0&0&l&m&n太热了！",
            "&8TEMPERATURE_RAISE_COMPONENTS"
    );

    public static SlimefunItemStack TEMPERATURE_REDUCE_COMPONENTS = new DMG_SlimefunItemStack(
            "TEMPERATURE_REDUCE_COMPONENTS",
            Material.LIGHT_BLUE_WOOL,
            "&f降温冷却组",
            "",
            "&x&0&0&a&0&f&f&l&m&n太冷了！",
            "&8TEMPERATURE_REDUCE_COMPONENTS"
    );

    public static SlimefunItemStack HIGH_ENERGY_STORAGE_ARRAY = new DMG_SlimefunItemStack(
            "HIGH_ENERGY_STORAGE_ARRAY",
            Material.BEACON,
            "&f高容量能源存储阵列",
            "",
            "&7容量巨大的电池阵列，需要足够的电力供应。&7&m等等，我家怎么停电了?",
            "&8HIGH_ENERGY_STORAGE_ARRAY"
    );
}
