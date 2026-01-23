package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Basic_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import org.bukkit.NamespacedKey;

public class ResearchSetup {

    private ResearchSetup() {
        // 禁止创建实例对象
    }

    public static void Research_Register_Setup() {
        // 原始教程
//        NamespacedKey researchKey = new NamespacedKey(this, "fire_cake");
//        Research research = new Research(researchKey, 123, "你不会想吃这些东西的", 10);
//        research.addItems(cake);
//
//        research.register();


        // 研究注册模板
//        new Research(
//                new NamespacedKey(MultifunctionalGun.getInstance(), "??"),
//                MultifunctionalGun.getInstance().hashCode() + 1,
//                "??_Name",
//                0
//        ).addItems(
//                Basic_Materials.SPECIAL_ALLOY,
//                Basic_Materials.STEEL_CASING
//        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "metal_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 1,
                "DMG:合金",
                5
        ).addItems(
                Basic_Materials.SPECIAL_ALLOY,
                Basic_Materials.STEEL_CASING
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "bullet_materials_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 2,
                "DMG:弹药合成材料",
                15
        ).addItems(
                Basic_Materials.HIGH_EXPLOSIVE,
                Basic_Materials.DETONATOR,
                Basic_Materials.FUSE
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "gun_materials_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 3,
                "DMG:枪械合成材料",
                15
        ).addItems(
                Basic_Materials.FIRING_MECHANISM,
                Basic_Materials.BARREL,
                Basic_Materials.MAGAZINE,
                Basic_Materials.GUNSTOCK,
                Basic_Materials.ELECTROMAGNETIC_PNEUMATIC_POWER_SYSTEM,
                Basic_Materials.ELECTROMAGNETIC_RAIL,
                Basic_Materials.PNEUMATIC_PROPULSION_SYSTEM,
                Basic_Materials.PRESSURE_BOTTLE
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "basic_materials_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 4,
                "DMG:基础材料",
                10
        ).addItems(
                Basic_Materials.REGULATOR,
                Basic_Materials.GENERIC_INTERFACE,
                Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL,
                Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                Basic_Materials.LASER_DIODE,
                Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                Basic_Materials.FORCE_FIELD_GENERATOT_ENGINE,
                Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "advanced_materials_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 5,
                "DMG:高级材料",
                20
        ).addItems(
                Advanced_Materials.PULSED_LASER_GENERATOR,
                Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL,
                Advanced_Materials.FORCE_FIELD_CONTAINMENT_GENERATOR,
                Advanced_Materials.STORAGE_CONTAINER
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "computer_components_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 6,
                "DMG:计算组件",
                25
        ).addItems(
                Advanced_Materials.SENSOR_MONITORING_ARRAY,
                Advanced_Materials.REGULATOR_ARRAY,
                Advanced_Materials.INTERFACE_ARRAY,
                Advanced_Materials.STRUCTURAL_REINFORCEMENT_MODULE,
                Advanced_Materials.CENTRAL_DATA_PROCESSING_HUB,
                Advanced_Materials.TEMPERATURE_RAISE_COMPONENTS,
                Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS,
                Advanced_Materials.HIGH_ENERGY_STORAGE_ARRAY
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "machine_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 7,
                "DMG:机器",
                30
        ).addItems(
                Machine.ENERGY_STORAGE_CAN_EMPTY,
                Machine.ENERGY_STORAGE_CAN_FULL,
                Machine.MASS_ENERGY_ENGINE_GENERATOR,
                Machine.ENERGY_COMPRESSOR,
                Machine.ENERGY_CONVERTER
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "bullet_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 8,
                "DMG:弹药",
                25
        ).addItems(
                Gun_And_Bullet.RIFLE_BULLETS,
                Gun_And_Bullet.GRENADE,
                Gun_And_Bullet.STEEL_BALLS,
                Gun_And_Bullet.BURNING_STEEL_BALLS,
                Gun_And_Bullet.SPECIAL_BULLETS
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "gun_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 9,
                "DMG:枪械",
                40
        ).addItems(
                Gun_And_Bullet.ASSAULT_RIFLE,
                Gun_And_Bullet.GRENADE_LAUNCHER,
                Gun_And_Bullet.TIKA_RIFLE,
                Gun_And_Bullet.LIGHT_CONE,
                Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE,
                Gun_And_Bullet.SCOPE,
                Gun_And_Bullet.LASER_SIGHT,
                Gun_And_Bullet.FIREARM_EXPANSION_BACKPACK
        ).register();

        new Research(
                new NamespacedKey(MultifunctionalGun.getInstance(), "endless_research_group"),
                MultifunctionalGun.getInstance().hashCode() + 10,
                "DMG:最终成品",
                50
        ).addItems(
                Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY,
                Gun_And_Bullet.ENDLESS_WEAPON
        ).register();
    }
}
