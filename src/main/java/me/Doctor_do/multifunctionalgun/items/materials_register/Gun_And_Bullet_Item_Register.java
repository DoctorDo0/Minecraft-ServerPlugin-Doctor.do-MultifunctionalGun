package me.Doctor_do.multifunctionalgun.items.materials_register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.materials.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Basic_Materials;
import me.Doctor_do.multifunctionalgun.items.materials.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.items.materials.Machine;
import me.Doctor_do.multifunctionalgun.setup.slimefun_item_setup.SimpleSlimefunItem_NotPlaceable;
import me.Doctor_do.multifunctionalgun.setup.slimefun_item_setup.SpecialSlimefunItem_Gun;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Gun_And_Bullet_Item_Register {
    public Gun_And_Bullet_Item_Register() {

        new SlimefunItem(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ENDLESS_WEAPON,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Gun_And_Bullet.LASER_SIGHT, Gun_And_Bullet.SCOPE, Gun_And_Bullet.FIREARM_EXPANSION_BACKPACK,
                        Gun_And_Bullet.ASSAULT_RIFLE, Gun_And_Bullet.TICA_RIFLE, Gun_And_Bullet.LIGHT_CONE,
                        Gun_And_Bullet.GRENADE_LAUNCHER, Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE, Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY
                }
        ).register(MultifunctionalGun.getInstance());

        new SimpleSlimefunItem_NotPlaceable(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Advanced_Materials.REGULATOR_ARRAY, Advanced_Materials.SENSOR_MONITORING_ARRAY, Advanced_Materials.INTERFACE_ARRAY,
                        Advanced_Materials.STRUCTURAL_REINFORCEMENT_MODULE, Advanced_Materials.CENTRAL_DATA_PROCESSING_HUB, Machine.MASS_ENERGY_ENGINE_GENERATOR,
                        Advanced_Materials.TEMPERATURE_RAISE_COMPONENTS, Advanced_Materials.HIGH_ENERGY_STORAGE_ARRAY, Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS
                }
        ).register(MultifunctionalGun.getInstance());

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.ASSAULT_RIFLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.RIFLE_BULLETS, Basic_Materials.STEEL_CASING,
                        Basic_Materials.BARREL, Basic_Materials.FIRING_MECHANISM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, Basic_Materials.STEEL_CASING
                }
        );

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.GRENADE_LAUNCHER,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.GRENADE, Basic_Materials.STEEL_CASING,
                        Basic_Materials.BARREL, Basic_Materials.FIRING_MECHANISM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, Basic_Materials.STEEL_CASING
                }
        );

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.TICA_RIFLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Basic_Materials.ELECTROMAGNETIC_PNEUMATIC_POWER_SYSTEM, Basic_Materials.PRESSURE_BOTTLE,
                        Basic_Materials.ELECTROMAGNETIC_RAIL, Basic_Materials.PNEUMATIC_PROPULSION_SYSTEM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, SlimefunItems.ENERGIZED_CAPACITOR
                }
        );

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.LIGHT_CONE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                        Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, Advanced_Materials.PULSED_LASER_GENERATOR,
                        Basic_Materials.STEEL_CASING, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ENERGIZED_CAPACITOR
                }
        );

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.SPECIAL_BULLETS, Basic_Materials.STEEL_CASING,
                        Basic_Materials.BARREL, Basic_Materials.FIRING_MECHANISM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, Basic_Materials.STEEL_CASING
                }
        );

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.SCOPE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, Basic_Materials.REGULATOR, Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.UNIVERSAL_INTERFACE, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );

        GunAndBullet_SpecialSlimefunItem_Gun(
                Gun_And_Bullet.LASER_SIGHT,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT, Basic_Materials.LASER_DIODE, Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                        Basic_Materials.REGULATOR, SlimefunItems.BIG_CAPACITOR, Basic_Materials.UNIVERSAL_INTERFACE
                }
        );

        new SimpleSlimefunItem_NotPlaceable(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.FIREARM_EXPANSION_BACKPACK,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.REGULATOR, SlimefunItems.HARDENED_GLASS, Basic_Materials.UNIVERSAL_INTERFACE,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.STORAGE_CONTAINER, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.FORCE_FIELD_CONTAINMENT_GENERATOR, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        ).register(MultifunctionalGun.getInstance());

        GunAndBullet_SimpleSlimefunItem_NotPlaceable(
                Gun_And_Bullet.RIFLE_BULLETS,
                new ItemStack[]{
                        null, SlimefunItems.HARDENED_METAL_INGOT, null,
                        SlimefunItems.STEEL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.STEEL_INGOT,
                        SlimefunItems.STEEL_INGOT, Basic_Materials.FUSE, SlimefunItems.STEEL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.RIFLE_BULLETS, 8)
        );

        GunAndBullet_SimpleSlimefunItem_NotPlaceable(
                Gun_And_Bullet.GRENADE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.FUSE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.HIGH_EXPLOSIVE, Basic_Materials.DETONATOR, Basic_Materials.HIGH_EXPLOSIVE,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.HARDENED_METAL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.GRENADE, 1)
        );

        GunAndBullet_SimpleSlimefunItem_NotPlaceable(
                Gun_And_Bullet.STEEL_BALLS,
                new ItemStack[]{
                        null, new ItemStack(Material.IRON_NUGGET), null,
                        new ItemStack(Material.IRON_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(Material.IRON_NUGGET),
                        null, new ItemStack(Material.IRON_NUGGET), null
                },
                new SlimefunItemStack(Gun_And_Bullet.STEEL_BALLS, 16)
        );

        GunAndBullet_SimpleSlimefunItem_NotPlaceable(
                Gun_And_Bullet.BURNING_STEEL_BALLS,
                new ItemStack[]{
                        null, SlimefunItems.STEEL_INGOT, null,
                        new ItemStack(Material.GLASS_PANE), Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.GLASS_PANE),
                        null, SlimefunItems.STEEL_INGOT, null
                },
                new SlimefunItemStack(Gun_And_Bullet.BURNING_STEEL_BALLS, 4)
        );

        GunAndBullet_SimpleSlimefunItem_NotPlaceable(
                Gun_And_Bullet.SPECIAL_BULLETS,
                new ItemStack[]{
                        null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.HARDENED_METAL_INGOT,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.FUSE, SlimefunItems.HARDENED_METAL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.SPECIAL_BULLETS, 2)
        );
    }

    public void GunAndBullet_SpecialSlimefunItem_Gun(SlimefunItemStack item, ItemStack[] itemStack) {
        new SpecialSlimefunItem_Gun(
                Groups.gun_and_bullet_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack
        ).register(MultifunctionalGun.getInstance());
    }

    public void GunAndBullet_SimpleSlimefunItem_NotPlaceable(SlimefunItemStack item, ItemStack[] itemStack, ItemStack recipeOutput) {
        new SimpleSlimefunItem_NotPlaceable(
                Groups.gun_and_bullet_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack,
                recipeOutput
        ).register(MultifunctionalGun.getInstance());
    }

}
