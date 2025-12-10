package me.Doctor_do.multifunctionalgun.setup.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.materials.Basic_Materials;
import me.Doctor_do.multifunctionalgun.recipetypes.RecipeTypes;
import me.Doctor_do.multifunctionalgun.setup.ItemsRegister;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Basic_Materials_Register {
    public static void Basic_Materials_Items() {

        // 老式注册方法
//        SlimefunItemStack HIGH_EXPLOSIVE = new SlimefunItemStack(
//                "HIGH_EXPLOSIVE",
//                Material.GUNPOWDER,
//                "High_Explosive_Name",
//                "High_Explosive_Lore"
//        );
//
//        ItemStack[] recipe_HIGH_EXPLOSIVE = new ItemStack[]{
//                new ItemStack(Material.TNT), new ItemStack(Material.GUNPOWDER), new ItemStack(Material.SUGAR),
//                new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.COAL), SlimefunItems.MAGNESIUM_DUST,
//                null, null, null
//        };
//
//        SlimefunItem sf_item_HIGH_EXPLOSIVE = new SlimefunItem(basic_material_item_group, HIGH_EXPLOSIVE, RecipeType.GRIND_STONE, recipe_HIGH_EXPLOSIVE);
//        sf_item_HIGH_EXPLOSIVE.register(plugin);

        // 新式注册方法
//        new SlimefunItem(
//                basic_material_item_group,
//                new SlimefunItemStack(
//                        "??",
//                        Material.AIR,
//                        "??_Name",
//                        "??_Lore"
//                ),
//                RecipeType.ENHANCED_CRAFTING_TABLE,
//                new ItemStack[]{
//                        null, null, null,
//                        null, null, null,
//                        null, null, null
//                }
//        ).register(plugin);

        // 新式注册方法2.0
//        SlimefunItemStack ?? = new SlimefunItemStack(
//                "??",
//                Material.AIR,
//                "??_Name",
//                "??_Lore"
//        );
//        new SlimefunItem(
//                basic_material_item_group,
//                ??,
//                RecipeType.ENHANCED_CRAFTING_TABLE,
//                new ItemStack[]{
//                        null, null, null,
//                        null, null, null,
//                        null, null, null
//                }
//        ).register(plugin);

        // 注册模板
//        new SlimefunItem(
//                basic_material_item_group,
//                ??,
//                RecipeType.ENHANCED_CRAFTING_TABLE,
//                new ItemStack[]{
//                        null, null, null,
//                        null, null, null,
//                        null, null, null
//                }
//        ).register(plugin);

        // 新式注册模板
//        Basic_Materials_Item_Register(
//                Basic_Materials.REGULATOR,
//                new ItemStack[]{
//                        null, null, null,
//                        null, null, null,
//                        null, null, null
//                }
//        );

        // 正式注册流程--------------------------------
        Basic_Materials_Item_Register(
                Basic_Materials.SPECIAL_ALLOY,
                RecipeType.SMELTERY,
                new ItemStack[]{
                        SlimefunItems.STEEL_INGOT, SlimefunItems.ZINC_DUST, SlimefunItems.ZINC_INGOT,
                        SlimefunItems.COPPER_DUST, SlimefunItems.LEAD_INGOT, SlimefunItems.SILICON,
                        null, null, null
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.HIGH_EXPLOSIVE,
                RecipeType.GRIND_STONE,
                new ItemStack[]{
                        new ItemStack(Material.TNT), new ItemStack(Material.GUNPOWDER), new ItemStack(Material.SUGAR),
                        new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.COAL), SlimefunItems.MAGNESIUM_DUST,
                        null, null, null
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.DETONATOR,
                new ItemStack[]{
                        new ItemStack(Material.GLASS), Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.MAGNESIUM_DUST,
                        Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.TNT), SlimefunItems.COPPER_WIRE,
                        new ItemStack(Material.GLASS), Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.MAGNESIUM_DUST
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.FUSE,
                new ItemStack[]{
                        new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL),
                        Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.TNT), Basic_Materials.HIGH_EXPLOSIVE,
                        new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL)
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.STEEL_CASING,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, null, Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.FIRING_MECHANISM,
                new ItemStack[]{
                        new ItemStack(Material.TRIPWIRE_HOOK), new ItemStack(Material.OAK_TRAPDOOR), new ItemStack(Material.PISTON),
                        new ItemStack(Material.DISPENSER), Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.TRIPWIRE_HOOK),
                        new ItemStack(Material.TRIPWIRE_HOOK), new ItemStack(Material.PISTON), new ItemStack(Material.TRIPWIRE)
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.BARREL,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY,
                        null, null, null,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.MAGAZINE,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.CHEST), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.PISTON), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.GUNSTOCK,
                new ItemStack[]{
                        new ItemStack(Material.LEATHER), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LEATHER),
                        new ItemStack(Material.OAK_PLANKS), Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.OAK_PLANKS),
                        new ItemStack(Material.LEATHER), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LEATHER)
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.ELECTROMAGNETIC_PNEUMATIC_POWER_SYSTEM,
                new ItemStack[]{
                        SlimefunItems.MULTIMETER, SlimefunItems.POWER_CRYSTAL, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.ADVANCED_CIRCUIT_BOARD, new ItemStack(Material.PISTON), new ItemStack(Material.OAK_TRAPDOOR),
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.SMALL_CAPACITOR
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.ELECTROMAGNETIC_RAIL,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRO_MAGNET,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.PNEUMATIC_PROPULSION_SYSTEM,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.OAK_TRAPDOOR), Basic_Materials.SPECIAL_ALLOY,
                        null, Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.PISTON),
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.TRIPWIRE_HOOK), Basic_Materials.SPECIAL_ALLOY
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.PRESSURE_BOTTLE,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.PISTON), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.GLASS), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.REGULATOR,
                new ItemStack[]{
                        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.COPPER_WIRE, SlimefunItems.ENERGY_CONNECTOR,
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.ELECTRIC_MOTOR, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.CARGO_CONNECTOR_NODE, SlimefunItems.COPPER_WIRE, SlimefunItems.BASIC_CIRCUIT_BOARD
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.UNIVERSAL_INTERFACE,
                new ItemStack[]{
                        SlimefunItems.SMALL_CAPACITOR, SlimefunItems.COPPER_WIRE, SlimefunItems.ENERGY_CONNECTOR,
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.MULTIMETER, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.CARGO_CONNECTOR_NODE, SlimefunItems.COPPER_WIRE, SlimefunItems.SMALL_CAPACITOR
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL,
                new ItemStack[]{
                        new ItemStack(Material.GLASS), new ItemStack(Material.DIAMOND), new ItemStack(Material.GLASS),
                        new ItemStack(Material.DIAMOND), new ItemStack(Material.AMETHYST_BLOCK), new ItemStack(Material.DIAMOND),
                        new ItemStack(Material.GLASS), new ItemStack(Material.DIAMOND), new ItemStack(Material.GLASS)
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                new ItemStack[]{
                        new ItemStack(Material.GLASS), new ItemStack(Material.REDSTONE), new ItemStack(Material.GLASS),
                        SlimefunItems.COPPER_WIRE, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR,
                        SlimefunItems.SILICON, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.SILICON
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.RAY_GENERATOR,
                RecipeTypes.KILL_MOB_DROP,
                new ItemStack[]{
                        null, null, null, null, new CustomItemStack(Material.GUARDIAN_SPAWN_EGG, "&f击杀守卫者掉落"), null, null, null, null
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.LASER_DIODE,
                new ItemStack[]{
                        Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL, new ItemStack(Material.GLOWSTONE_DUST), null,
                        new ItemStack(Material.GLASS), Basic_Materials.RAY_GENERATOR, SlimefunItems.COPPER_WIRE,
                        Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL, new ItemStack(Material.GLOWSTONE_DUST), null
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                RecipeTypes.KILL_MOB_DROP,
                new ItemStack[]{
                        null, null, null, null, new CustomItemStack(Material.WITHER_SKELETON_SPAWN_EGG, "&f击杀凋零掉落"), null, null, null, null
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.FORCE_FIELD_GENERATOT_ENGINE,
                new ItemStack[]{
                        SlimefunItems.BLISTERING_INGOT_3, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.BLISTERING_INGOT_3,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.NETHER_STAR_REACTOR, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BLISTERING_INGOT_3
                }
        );

        Basic_Materials_Item_Register(
                Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.BLISTERING_INGOT_3, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.BLISTERING_INGOT_3,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );

    }

    public static void Basic_Materials_Item_Register(SlimefunItemStack item, ItemStack[] itemStack) {
        ItemsRegister.Item_Register_Method(
                Groups.basic_material_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack
        );
    }

    public static void Basic_Materials_Item_Register(SlimefunItemStack item, RecipeType recipeType, ItemStack[] itemStack) {
        ItemsRegister.Item_Register_Method(
                Groups.basic_material_item_group,
                item,
                recipeType,
                itemStack
        );
    }

}
