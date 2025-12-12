package me.Doctor_do.multifunctionalgun.items.materials_register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.materials.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Basic_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_item_setup.SimpleSlimefunItem_NotPlaceable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Advanced_Materials_Item_Register {
    public Advanced_Materials_Item_Register() {

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.PULSED_LASER_GENERATOR,
                new ItemStack[]{
                        Basic_Materials.LASER_DIODE, SlimefunItems.POWER_CRYSTAL, Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                        Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL, Basic_Materials.LASER_DIODE, SlimefunItems.SMALL_CAPACITOR,
                        Basic_Materials.LASER_DIODE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.HARDENED_GLASS, Basic_Materials.SPECIAL_ALLOY,
                        new ItemStack(Material.AMETHYST_SHARD), Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL, new ItemStack(Material.AMETHYST_SHARD),
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.HARDENED_GLASS, Basic_Materials.SPECIAL_ALLOY
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.FORCE_FIELD_CONTAINMENT_GENERATOR,
                new ItemStack[]{
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, null, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, null, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.FORCE_FIELD_GENERATOT_ENGINE, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.STORAGE_CONTAINER,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, new ItemStack(Material.CHEST), Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.FORCE_FIELD_GENERATOT_ENGINE, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.SENSOR_MONITORING_ARRAY,
                new ItemStack[]{
                        SlimefunItems.SOLAR_PANEL, SlimefunItems.ELECTRO_MAGNET, new ItemStack(Material.COMPARATOR),
                        SlimefunItems.COOLING_UNIT, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.HEATING_COIL,
                        Basic_Materials.REGULATOR, SlimefunItems.MULTIMETER, Basic_Materials.UNIVERSAL_INTERFACE
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.REGULATOR_ARRAY,
                new ItemStack[]{
                        Basic_Materials.REGULATOR, new ItemStack(Material.OBSERVER), Basic_Materials.REGULATOR,
                        Basic_Materials.REGULATOR, SlimefunItems.ANDROID_MEMORY_CORE, Basic_Materials.UNIVERSAL_INTERFACE,
                        Basic_Materials.REGULATOR, Basic_Materials.REGULATOR, Basic_Materials.REGULATOR
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.INTERFACE_ARRAY,
                new ItemStack[]{
                        Basic_Materials.UNIVERSAL_INTERFACE, new ItemStack(Material.OBSERVER), Basic_Materials.UNIVERSAL_INTERFACE,
                        Basic_Materials.UNIVERSAL_INTERFACE, SlimefunItems.ANDROID_MEMORY_CORE, Basic_Materials.REGULATOR,
                        Basic_Materials.UNIVERSAL_INTERFACE, Basic_Materials.UNIVERSAL_INTERFACE, Basic_Materials.UNIVERSAL_INTERFACE
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.STRUCTURAL_REINFORCEMENT_MODULE,
                new ItemStack[]{
                        SlimefunItems.LEAD_INGOT, Basic_Materials.STEEL_CASING, SlimefunItems.LEAD_INGOT,
                        Basic_Materials.STEEL_CASING, SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.STEEL_CASING,
                        SlimefunItems.LEAD_INGOT, Basic_Materials.STEEL_CASING, SlimefunItems.LEAD_INGOT
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.CENTRAL_DATA_PROCESSING_HUB,
                new ItemStack[]{
                        Basic_Materials.REGULATOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                        SlimefunItems.BIG_CAPACITOR, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.POWER_CRYSTAL,
                        Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, Basic_Materials.UNIVERSAL_INTERFACE
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.TEMPERATURE_RAISE_COMPONENTS,
                new ItemStack[]{
                        SlimefunItems.HEATING_COIL, SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.HEATING_COIL,
                        Basic_Materials.STEEL_CASING, new ItemStack(Material.MAGMA_BLOCK), Basic_Materials.STEEL_CASING,
                        SlimefunItems.HEATING_COIL, SlimefunItems.BIG_CAPACITOR, SlimefunItems.HEATING_COIL
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS,
                new ItemStack[]{
                        SlimefunItems.COOLING_UNIT, SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.COOLING_UNIT,
                        Basic_Materials.STEEL_CASING, new ItemStack(Material.BLUE_ICE), Basic_Materials.STEEL_CASING,
                        SlimefunItems.COOLING_UNIT, SlimefunItems.BIG_CAPACITOR, SlimefunItems.COOLING_UNIT
                }
        );

        AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(
                Advanced_Materials.HIGH_ENERGY_STORAGE_ARRAY,
                new ItemStack[]{
                        SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.ENERGIZED_CAPACITOR,
                        Basic_Materials.STEEL_CASING, SlimefunItems.ENERGIZED_CAPACITOR, Basic_Materials.STEEL_CASING,
                        SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.MULTIMETER, SlimefunItems.ENERGIZED_CAPACITOR
                }
        );
    }

    public void AdvancedMaterials_SimpleSlimefunItem_NotPlaceable(SlimefunItemStack item, ItemStack[] itemStack) {
        new SimpleSlimefunItem_NotPlaceable(
                Groups.advanced_material_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack
        ).register(MultifunctionalGun.getInstance());
    }

}
