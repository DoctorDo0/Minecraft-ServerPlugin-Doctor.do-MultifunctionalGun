package me.Doctor_do.multifunctionalgun.setup.item_register.materials_register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.blocks.ItemType_Item;
import me.Doctor_do.multifunctionalgun.items.weapons.*;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials.Basic_Materials;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials.Machine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Gun_And_Bullet_Item_Register {

    private final static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    private Gun_And_Bullet_Item_Register() {
        // 构造器访问权限控制，禁止新建对象
    }

    public static SlimefunItem EndlessWeapon = null;
    public static SlimefunItem AssaultRifle_And_GrenadeLauncher;
    public static SlimefunItem AssaultRifle;
    public static SlimefunItem GrenadeLauncher;
    public static SlimefunItem TIKA_Rifle;
    public static SlimefunItem LightCone;
    public static SlimefunItem AntiMaterielSniperRifle;
    public static SlimefunItem Scope;
    public static SlimefunItem LaserSight;
    public static SlimefunItem FirearmExpansionBackpack;

    // 通过物品名称来反向查找指定物品，返回值为物品对象，类型为SlimefunItemStack
    @Nullable
    public static SlimefunItem getItemByDisplayName(@Nonnull String displayName) {
        if (displayName.equals(Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
            return Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getItem();
        } else if (displayName.equals(Gun_And_Bullet.TIKA_RIFLE.getDisplayName())) {
            return Gun_And_Bullet.TIKA_RIFLE.getItem();
        } else if (displayName.equals(Gun_And_Bullet.LIGHT_CONE.getDisplayName())) {
            return Gun_And_Bullet.LIGHT_CONE.getItem();
        } else if (displayName.equals(Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName())) {
            return Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getItem();
        } else return null;
    }

    public static void Gun_And_Bullet_Item_Register_setup() {

        // 最终成品，物品注册，包含关联数据，如最大电容，携带功能
        EndlessWeapon = new EndlessWeapon(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ENDLESS_WEAPON,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Gun_And_Bullet.LASER_SIGHT, Gun_And_Bullet.SCOPE, Gun_And_Bullet.FIREARM_EXPANSION_BACKPACK,
                        Gun_And_Bullet.ASSAULT_RIFLE, Gun_And_Bullet.TIKA_RIFLE, Gun_And_Bullet.LIGHT_CONE,
                        Gun_And_Bullet.GRENADE_LAUNCHER, Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE, Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY
                },
                // 设置最大电容，数据不符合要求会变更为默认(20000)
                me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon.getMaxItemCharge_Temp(),
                // 最终成品的功能模式类型
                new String[]{
                        Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName(),
                        Gun_And_Bullet.TIKA_RIFLE.getDisplayName(),
                        Gun_And_Bullet.LIGHT_CONE.getDisplayName(),
                        Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName()
                }
        );
        EndlessWeapon.register(plugin);

        // 一个占位符，物品可被注册但不可见，用于支持被模式选择
        AssaultRifle_And_GrenadeLauncher = new SlimefunItem(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER,
                RecipeType.NULL,
                new ItemStack[9]
        );
        AssaultRifle_And_GrenadeLauncher.register(plugin);

        // 一个测试物品，右键修改物品的lore，附上一些插件内部数据，装饰用
//        new Test_Item(
//                Groups.gun_and_bullet_item_group,
//                new DMG_SlimefunItemStack(
//                        "DMG_TEST_ITEM",
//                        Material.COMMAND_BLOCK,
//                        "&f测试物品",
//                        ""
//                ),
//                RecipeType.NULL,
//                new ItemStack[9]
//        ).register(plugin);

        GunAndBullet_ItemType_Item(
                Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY,
                new ItemStack[]{
                        Advanced_Materials.REGULATOR_ARRAY, Advanced_Materials.SENSOR_MONITORING_ARRAY, Advanced_Materials.INTERFACE_ARRAY,
                        Advanced_Materials.STRUCTURAL_REINFORCEMENT_MODULE, Advanced_Materials.CENTRAL_DATA_PROCESSING_HUB, Machine.MASS_ENERGY_ENGINE_GENERATOR,
                        Advanced_Materials.TEMPERATURE_RAISE_COMPONENTS, Advanced_Materials.HIGH_ENERGY_STORAGE_ARRAY, Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS
                }
        );

        AssaultRifle = new AssaultRifle(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ASSAULT_RIFLE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.RIFLE_BULLETS, Basic_Materials.STEEL_CASING,
                        Basic_Materials.BARREL, Basic_Materials.FIRING_MECHANISM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, Basic_Materials.STEEL_CASING
                }
        );
        AssaultRifle.register(plugin);

        GrenadeLauncher = new GrenadeLauncher(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.GRENADE_LAUNCHER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.GRENADE, Basic_Materials.STEEL_CASING,
                        Basic_Materials.BARREL, Basic_Materials.FIRING_MECHANISM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, Basic_Materials.STEEL_CASING
                }
        );
        GrenadeLauncher.register(plugin);

        TIKA_Rifle = new TIKA_Rifle(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.TIKA_RIFLE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Basic_Materials.ELECTROMAGNETIC_PNEUMATIC_POWER_SYSTEM, Basic_Materials.PRESSURE_BOTTLE,
                        Basic_Materials.ELECTROMAGNETIC_RAIL, Basic_Materials.PNEUMATIC_PROPULSION_SYSTEM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, SlimefunItems.ENERGIZED_CAPACITOR
                }
        );
        TIKA_Rifle.register(plugin);

        LightCone = new LightCone(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.LIGHT_CONE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                        Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, Advanced_Materials.PULSED_LASER_GENERATOR,
                        Basic_Materials.STEEL_CASING, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ENERGIZED_CAPACITOR
                }
        );
        LightCone.register(plugin);

        AntiMaterielSniperRifle = new AntiMaterielSniperRifle(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.SPECIAL_BULLETS, Basic_Materials.STEEL_CASING,
                        Basic_Materials.BARREL, Basic_Materials.FIRING_MECHANISM, Basic_Materials.GUNSTOCK,
                        Basic_Materials.STEEL_CASING, Basic_Materials.MAGAZINE, Basic_Materials.STEEL_CASING
                }
        );
        AntiMaterielSniperRifle.register(plugin);

        Scope = new Scope(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.SCOPE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, Basic_Materials.REGULATOR, Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.GENERIC_INTERFACE, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );
        Scope.register(plugin);

        LaserSight = new LaserSight(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.LASER_SIGHT,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.COMPLETE_DEFLECTION_CRYSTAL, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT, Basic_Materials.LASER_DIODE, Basic_Materials.VOLTAGE_REGULATOR_DIODE_CIRCUIT,
                        Basic_Materials.REGULATOR, SlimefunItems.BIG_CAPACITOR, Basic_Materials.GENERIC_INTERFACE
                }
        );
        LaserSight.register(plugin);

        FirearmExpansionBackpack = new FirearmExpansionBackpack(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.FIREARM_EXPANSION_BACKPACK,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.REGULATOR, SlimefunItems.HARDENED_GLASS, Basic_Materials.GENERIC_INTERFACE,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.STORAGE_CONTAINER, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.FORCE_FIELD_CONTAINMENT_GENERATOR, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );
        FirearmExpansionBackpack.register(plugin);

        GunAndBullet_ItemType_Item(
                Gun_And_Bullet.RIFLE_BULLETS,
                new ItemStack[]{
                        null, SlimefunItems.HARDENED_METAL_INGOT, null,
                        SlimefunItems.STEEL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.STEEL_INGOT,
                        SlimefunItems.STEEL_INGOT, Basic_Materials.FUSE, SlimefunItems.STEEL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.RIFLE_BULLETS, 8)
        );

        GunAndBullet_ItemType_Item(
                Gun_And_Bullet.GRENADE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.FUSE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.HIGH_EXPLOSIVE, Basic_Materials.DETONATOR, Basic_Materials.HIGH_EXPLOSIVE,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.HARDENED_METAL_INGOT
                }
        );

        GunAndBullet_ItemType_Item(
                Gun_And_Bullet.STEEL_BALLS,
                new ItemStack[]{
                        null, new ItemStack(Material.IRON_NUGGET), null,
                        new ItemStack(Material.IRON_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(Material.IRON_NUGGET),
                        null, new ItemStack(Material.IRON_NUGGET), null
                },
                new SlimefunItemStack(Gun_And_Bullet.STEEL_BALLS, 16)
        );

        GunAndBullet_ItemType_Item(
                Gun_And_Bullet.BURNING_STEEL_BALLS,
                new ItemStack[]{
                        null, SlimefunItems.STEEL_INGOT, null,
                        new ItemStack(Material.GLASS_PANE), Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.GLASS_PANE),
                        null, SlimefunItems.STEEL_INGOT, null
                },
                new SlimefunItemStack(Gun_And_Bullet.BURNING_STEEL_BALLS, 4)
        );

        GunAndBullet_ItemType_Item(
                Gun_And_Bullet.SPECIAL_BULLETS,
                new ItemStack[]{
                        null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.HARDENED_METAL_INGOT,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.FUSE, SlimefunItems.HARDENED_METAL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.SPECIAL_BULLETS, 2)
        );
    }

    public static void GunAndBullet_ItemType_Item(SlimefunItemStack item, ItemStack[] itemStack) {
        new ItemType_Item(
                Groups.gun_and_bullet_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack
        ).register(plugin);
    }

    public static void GunAndBullet_ItemType_Item(SlimefunItemStack item, ItemStack[] itemStack, ItemStack recipeOutput) {
        new ItemType_Item(
                Groups.gun_and_bullet_item_group,
                item,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                itemStack,
                recipeOutput
        ).register(plugin);
    }

    public static EndlessWeapon getEndlessWeaponInstance() {
        return (EndlessWeapon) EndlessWeapon;
    }

    public static AssaultRifle getAssaultRifleInstance() {
        return (AssaultRifle) AssaultRifle;
    }

    public static GrenadeLauncher getGrenadeLauncherInstance() {
        return (GrenadeLauncher) GrenadeLauncher;
    }

    public static TIKA_Rifle getTIKA_RifleInstance() {
        return (TIKA_Rifle) TIKA_Rifle;
    }

    public static LightCone getLightConeInstance() {
        return (LightCone) LightCone;
    }

    public static AntiMaterielSniperRifle getAntiMaterielSniperRifleInstance() {
        return (AntiMaterielSniperRifle) AntiMaterielSniperRifle;
    }

    public static Scope getScopeInstance() {
        return (Scope) Scope;
    }

    public static LaserSight getLaserSightInstance() {
        return (LaserSight) LaserSight;
    }

    public static FirearmExpansionBackpack getFirearmExpansionBackpackInstance() {
        return (FirearmExpansionBackpack) FirearmExpansionBackpack;
    }

}
