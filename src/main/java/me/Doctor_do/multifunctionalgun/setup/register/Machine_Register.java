package me.Doctor_do.multifunctionalgun.setup.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.materials.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Basic_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Machine;
import me.Doctor_do.multifunctionalgun.recipetypes.RecipeTypes;
import me.Doctor_do.multifunctionalgun.setup.ItemsRegister;
import org.bukkit.inventory.ItemStack;

public class Machine_Register {
    public static void Machine_Items() {

        Machine_Item_Register(
                Machine.ENERGY_STORAGE_CAN_EMPTY,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HARDENED_GLASS, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, null, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HARDENED_GLASS, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );

        Machine_Item_Register(
                Machine.ENERGY_STORAGE_CAN_FULL,
                RecipeTypes.ENERGY_PLANT,
                new ItemStack[]{
                        null, null, null, null, Machine.ENERGY_STORAGE_CAN_EMPTY, null, null, null, null
                }
        );

        Machine_Item_Register(
                Machine.MASS_ENERGY_ENGINE_GENERATOR,
                new ItemStack[]{
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.ENERGY_CONNECTOR, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.NETHER_STAR_REACTOR, SlimefunItems.BLISTERING_INGOT_3,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.ENERGIZED_CAPACITOR, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE
                }
        );

        Machine_Item_Register(
                Machine.ENERGY_COMPRESSION_PLANT,
                new ItemStack[]{
                        SlimefunItems.POWER_CRYSTAL, Machine.MASS_ENERGY_ENGINE_GENERATOR, SlimefunItems.POWER_CRYSTAL,
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        SlimefunItems.ENERGIZED_CAPACITOR, Advanced_Materials.FORCE_FIELD_CONTAINMENT_GENERATOR, SlimefunItems.ENERGIZED_CAPACITOR
                }
        );

        Machine_Item_Register(
                Machine.ENERGY_LOADING_PLANT,
                new ItemStack[]{
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, Machine.MASS_ENERGY_ENGINE_GENERATOR, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, Machine.ENERGY_COMPRESSION_PLANT, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, Machine.ENERGY_STORAGE_CAN_EMPTY, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER
                }
        );
    }

    public static void Machine_Item_Register(SlimefunItemStack item, ItemStack[] itemStack) {
        ItemsRegister.Item_Register_Method(
                Groups.machine_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack
        );
    }

    public static void Machine_Item_Register(SlimefunItemStack item, RecipeType recipeType, ItemStack[] itemStack) {
        ItemsRegister.Item_Register_Method(
                Groups.machine_item_group,
                item,
                recipeType,
                itemStack
        );
    }

}
