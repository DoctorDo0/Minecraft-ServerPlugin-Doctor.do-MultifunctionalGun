package me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.blocks.ItemType_Item;
import me.Doctor_do.multifunctionalgun.items.items.Anti_Gravity_Void_Particle;
import me.Doctor_do.multifunctionalgun.items.items.Ray_Generator;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Basic_Materials;
import me.Doctor_do.multifunctionalgun.recipetypes.RecipeTypes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Basic_Materials_Item_Setup {

    private final static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    private Basic_Materials_Item_Setup() {
        // 构造器访问权限控制，禁止新建对象
    }

    public static void Basic_Materials_Item_Register_Setup() {

        // 原始教程
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

        // 注册模板
//        BasicMaterials_SimpleSlimefunItem_NotPlaceable(
//                Basic_Materials.SPECIAL_ALLOY,
//                new ItemStack[]{
//                        null, null, null,
//                        null, null, null,
//                        null, null, null
//                }
//        );

        // 正式注册流程--------------------------------
        BasicMaterials_ItemType_Item(
                Basic_Materials.SPECIAL_ALLOY,
                RecipeType.SMELTERY,
                new ItemStack[]{
                        SlimefunItems.STEEL_INGOT, SlimefunItems.ZINC_DUST, SlimefunItems.ZINC_INGOT,
                        SlimefunItems.COPPER_DUST, SlimefunItems.LEAD_INGOT, SlimefunItems.SILICON,
                        null, null, null
                },
                new SlimefunItemStack(Basic_Materials.SPECIAL_ALLOY, 2)
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.STEEL_CASING,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, null, Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.HIGH_EXPLOSIVE,
                new ItemStack[]{
                        SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), new ItemStack(Material.SUGAR),
                        new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.COAL), null,
                        null, null, null
                },
                new SlimefunItemStack(Basic_Materials.HIGH_EXPLOSIVE, 8)
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.DETONATOR,
                new ItemStack[]{
                        new ItemStack(Material.GLASS), Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.MAGNESIUM_DUST,
                        Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.TNT), SlimefunItems.COPPER_WIRE,
                        new ItemStack(Material.GLASS), Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.MAGNESIUM_DUST
                },
                new SlimefunItemStack(Basic_Materials.DETONATOR, 2)
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.FUSE,
                new ItemStack[]{
                        new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.STRING), new ItemStack(Material.WHITE_WOOL),
                        Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.TNT), Basic_Materials.HIGH_EXPLOSIVE,
                        new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.STRING), new ItemStack(Material.WHITE_WOOL)
                },
                new SlimefunItemStack(Basic_Materials.FUSE, 4)
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.FIRING_MECHANISM,
                new ItemStack[]{
                        new ItemStack(Material.TRIPWIRE_HOOK), new ItemStack(Material.OAK_TRAPDOOR), new ItemStack(Material.PISTON),
                        new ItemStack(Material.DISPENSER), Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.TRIPWIRE_HOOK),
                        new ItemStack(Material.STRING), new ItemStack(Material.PISTON), new ItemStack(Material.REPEATER)
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.BARREL,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY,
                        null, null, null,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.MAGAZINE,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.CHEST), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.PISTON), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.GUNSTOCK,
                new ItemStack[]{
                        new ItemStack(Material.LEATHER), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LEATHER),
                        new ItemStack(Material.OAK_PLANKS), Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.OAK_PLANKS),
                        new ItemStack(Material.LEATHER), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LEATHER)
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.ELECTROMAGNETIC_PNEUMATIC_POWER_SYSTEM,
                new ItemStack[]{
                        SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.ADVANCED_CIRCUIT_BOARD, new ItemStack(Material.PISTON), new ItemStack(Material.OAK_TRAPDOOR),
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.SMALL_CAPACITOR
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.ELECTROMAGNETIC_RAIL,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRO_MAGNET,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.PNEUMATIC_PROPULSION_SYSTEM,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.OAK_TRAPDOOR), Basic_Materials.SPECIAL_ALLOY,
                        null, Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.PISTON),
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.TRIPWIRE_HOOK), Basic_Materials.SPECIAL_ALLOY
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.PRESSURE_BOTTLE,
                new ItemStack[]{
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.PISTON), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, new ItemStack(Material.GLASS), Basic_Materials.SPECIAL_ALLOY,
                        Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY, Basic_Materials.SPECIAL_ALLOY
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.REGULATOR,
                new ItemStack[]{
                        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.COPPER_WIRE, SlimefunItems.ENERGY_CONNECTOR,
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.ELECTRIC_MOTOR, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.CARGO_CONNECTOR_NODE, SlimefunItems.COPPER_WIRE, SlimefunItems.BASIC_CIRCUIT_BOARD
                },
                new SlimefunItemStack(Basic_Materials.REGULATOR, 4)
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.GENERIC_INTERFACE,
                new ItemStack[]{
                        SlimefunItems.SMALL_CAPACITOR, SlimefunItems.COPPER_WIRE, SlimefunItems.ENERGY_CONNECTOR,
                        Basic_Materials.SPECIAL_ALLOY, SlimefunItems.MULTIMETER, Basic_Materials.SPECIAL_ALLOY,
                        SlimefunItems.CARGO_CONNECTOR_NODE, SlimefunItems.COPPER_WIRE, SlimefunItems.SMALL_CAPACITOR
                },
                new SlimefunItemStack(Basic_Materials.GENERIC_INTERFACE, 4)
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL,
                new ItemStack[]{
                        new ItemStack(Material.GLASS), new ItemStack(Material.DIAMOND), new ItemStack(Material.GLASS),
                        new ItemStack(Material.DIAMOND), new ItemStack(Material.AMETHYST_BLOCK), new ItemStack(Material.DIAMOND),
                        new ItemStack(Material.GLASS), new ItemStack(Material.DIAMOND), new ItemStack(Material.GLASS)
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                new ItemStack[]{
                        new ItemStack(Material.GLASS), new ItemStack(Material.REDSTONE), new ItemStack(Material.GLASS),
                        SlimefunItems.COPPER_WIRE, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR,
                        SlimefunItems.SILICON, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.SILICON
                }
        );

        new Ray_Generator(
                Groups.basic_material_item_group,
                Basic_Materials.RAY_GENERATOR,
                RecipeTypes.KILL_MOB_DROP,
                new ItemStack[]{
                        null, null, null, null, new CustomItemStack(Material.GUARDIAN_SPAWN_EGG, "&f击杀守卫者掉落"), null, null, null, null
                }
        ).register(plugin);

        BasicMaterials_ItemType_Item(
                Basic_Materials.LASER_DIODE,
                new ItemStack[]{
                        Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL, new ItemStack(Material.GLOWSTONE_DUST), null,
                        new ItemStack(Material.GLASS), Basic_Materials.RAY_GENERATOR, SlimefunItems.COPPER_WIRE,
                        Basic_Materials.UNFORMED_DEFLECTION_CRYSTAL, new ItemStack(Material.GLOWSTONE_DUST), null
                }
        );

        new Anti_Gravity_Void_Particle(
                Groups.basic_material_item_group,
                Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                RecipeTypes.KILL_MOB_DROP,
                new ItemStack[]{
                        null, null, null, null, new CustomItemStack(Material.WITHER_SKELETON_SPAWN_EGG, "&f击杀凋零掉落"), null, null, null, null
                }
        ).register(plugin);

        BasicMaterials_ItemType_Item(
                Basic_Materials.FORCE_FIELD_GENERATOT_ENGINE,
                new ItemStack[]{
                        SlimefunItems.BLISTERING_INGOT_3, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.BLISTERING_INGOT_3,
                        Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.NETHER_STAR_REACTOR, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE,
                        SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BLISTERING_INGOT_3
                }
        );

        BasicMaterials_ItemType_Item(
                Basic_Materials.FORCE_FIELD_REFLECT_STABILIZER,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.BLISTERING_INGOT_3, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, SlimefunItems.BLISTERING_INGOT_3,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );

    }

    public static void BasicMaterials_ItemType_Item(SlimefunItemStack item, ItemStack[] itemStack) {
        new ItemType_Item(
                Groups.basic_material_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack
        ).register(plugin);
    }

    public static void BasicMaterials_ItemType_Item(SlimefunItemStack item, ItemStack[] itemStack, ItemStack recipeOutput) {
        new ItemType_Item(
                Groups.basic_material_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack,
                recipeOutput
        ).register(plugin);
    }

    public static void BasicMaterials_ItemType_Item(SlimefunItemStack item, RecipeType recipeType, ItemStack[] itemStack, ItemStack recipeOutput) {
        new ItemType_Item(
                Groups.basic_material_item_group,
                item,
                recipeType,
                itemStack,
                recipeOutput
        ).register(plugin);
    }

}
