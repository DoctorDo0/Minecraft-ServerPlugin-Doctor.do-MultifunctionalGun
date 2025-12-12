package me.Doctor_do.multifunctionalgun.items.materials_register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.materials.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Basic_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Machine;
import me.Doctor_do.multifunctionalgun.recipetypes.RecipeTypes;
import me.Doctor_do.multifunctionalgun.setup.slimefun_item_setup.SimpleSlimefunItem_NotPlaceable;
import me.Doctor_do.multifunctionalgun.setup.slimefun_item_setup.SpecialSlimefunItem_Machine;
import org.bukkit.inventory.ItemStack;

public class Machine_Item_Register {
    public Machine_Item_Register() {

        new SimpleSlimefunItem_NotPlaceable(
                Groups.machine_item_group,
                Machine.ENERGY_STORAGE_CAN_EMPTY,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HARDENED_GLASS, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, null, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.HARDENED_GLASS, SlimefunItems.REINFORCED_ALLOY_INGOT
                },
                new SlimefunItemStack(Machine.ENERGY_STORAGE_CAN_EMPTY, 4)
        ).register(MultifunctionalGun.getInstance());

        new SimpleSlimefunItem_NotPlaceable(
                Groups.machine_item_group,
                Machine.ENERGY_STORAGE_CAN_FULL,
                RecipeTypes.ENERGY_PLANT,
                new ItemStack[]{
                        null, null, null, null, Machine.ENERGY_STORAGE_CAN_EMPTY, null, null, null, null
                }
        ).register(MultifunctionalGun.getInstance());

        new SpecialSlimefunItem_Machine(
                Groups.machine_item_group,
                Machine.MASS_ENERGY_ENGINE_GENERATOR,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, Advanced_Materials.STORAGE_CONTAINER, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.NETHER_STAR_REACTOR, SlimefunItems.BLISTERING_INGOT_3,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.ENERGIZED_CAPACITOR, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE
                }
        ).register(MultifunctionalGun.getInstance());

        new SpecialSlimefunItem_Machine(
                Groups.machine_item_group,
                Machine.ENERGY_COMPRESSION_PLANT,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.POWER_CRYSTAL, Advanced_Materials.STORAGE_CONTAINER, SlimefunItems.POWER_CRYSTAL,
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        SlimefunItems.ENERGIZED_CAPACITOR, Machine.MASS_ENERGY_ENGINE_GENERATOR, SlimefunItems.ENERGIZED_CAPACITOR
                }
        ).register(MultifunctionalGun.getInstance());

        new SpecialSlimefunItem_Machine(
                Groups.machine_item_group,
                Machine.ENERGY_LOADING_PLANT,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, Machine.ENERGY_COMPRESSION_PLANT, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, Machine.ENERGY_STORAGE_CAN_EMPTY, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER, Machine.MASS_ENERGY_ENGINE_GENERATOR, Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER
                }
        ).register(MultifunctionalGun.getInstance());
    }

}
