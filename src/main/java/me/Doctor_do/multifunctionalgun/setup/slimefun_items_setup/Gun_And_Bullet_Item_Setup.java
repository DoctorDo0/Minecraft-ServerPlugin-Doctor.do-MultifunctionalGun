package me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.categories.Groups;
import me.Doctor_do.multifunctionalgun.items.items.Test_Item;
import me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon;
import me.Doctor_do.multifunctionalgun.items.weapons.auxiliary.ExpansionBackpack;
import me.Doctor_do.multifunctionalgun.items.weapons.auxiliary.LaserSight;
import me.Doctor_do.multifunctionalgun.items.weapons.auxiliary.Scope;
import me.Doctor_do.multifunctionalgun.items.weapons.bullet.*;
import me.Doctor_do.multifunctionalgun.items.weapons.weapon.*;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Advanced_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Basic_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Gun_And_Bullet_Item_Setup {

    private final static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    private Gun_And_Bullet_Item_Setup() {
        // 构造器访问权限控制，禁止新建对象
    }

    public static SlimefunItem EndlessWeapon = null;
    public static SlimefunItem AssaultRifle;
    public static SlimefunItem GrenadeLauncher;
    public static SlimefunItem TIKA_Rifle;
    public static SlimefunItem LightCone;
    public static SlimefunItem AntiMaterielSniperRifle;
    public static SlimefunItem Scope;
    public static SlimefunItem LaserSight;
    public static SlimefunItem ExpansionBackpack;

    public static SlimefunItem RifleBullet;
    public static SlimefunItem Grenade;
    public static SlimefunItem SteelBall;
    public static SlimefunItem BurningSteelBall;
    public static SlimefunItem SpecialBullet;

    public static void Gun_And_Bullet_Item_Register_setup() {

        // 一个测试物品，修改了部分属性，右键修改物品的lore，附上一些插件内部数据，装饰用
        new Test_Item(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.TEST_ITEM,
                RecipeType.NULL,
                new ItemStack[9]
        ).register(plugin);

        // 最终成品，物品注册，包含关联数据，如最大电容，携带功能
        EndlessWeapon = new EndlessWeapon(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ENDLESS_WEAPON,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Gun_And_Bullet.LASER_SIGHT, Gun_And_Bullet.SCOPE, Gun_And_Bullet.EXPANSION_BACKPACK,
                        Gun_And_Bullet.ASSAULT_RIFLE, Gun_And_Bullet.TIKA_RIFLE, Gun_And_Bullet.LIGHT_CONE,
                        Gun_And_Bullet.GRENADE_LAUNCHER, Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE, Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY
                }
        );
        EndlessWeapon.register(plugin);

        new SlimefunItem(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.REINFORCED_COMPUTER_ARRAY,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Advanced_Materials.REGULATOR_ARRAY, Advanced_Materials.SENSOR_MONITORING_ARRAY, Advanced_Materials.INTERFACE_ARRAY,
                        Advanced_Materials.STRUCTURAL_REINFORCEMENT_MODULE, Advanced_Materials.CENTRAL_DATA_PROCESSING_HUB, Machine.MASS_ENERGY_ENGINE_GENERATOR,
                        Advanced_Materials.TEMPERATURE_RAISE_COMPONENTS, Advanced_Materials.HIGH_ENERGY_STORAGE_ARRAY, Advanced_Materials.TEMPERATURE_REDUCE_COMPONENTS
                }
        ).register(plugin);

        AssaultRifle = new AssaultRifle(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.ASSAULT_RIFLE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.RIFLE_BULLET, Basic_Materials.STEEL_CASING,
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
                        Basic_Materials.STEEL_CASING, Basic_Materials.STEEL_CASING, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
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
                        Basic_Materials.STEEL_CASING, Gun_And_Bullet.SPECIAL_BULLET, Basic_Materials.STEEL_CASING,
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

        ExpansionBackpack = new ExpansionBackpack(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.EXPANSION_BACKPACK,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        Basic_Materials.REGULATOR, SlimefunItems.HARDENED_GLASS, Basic_Materials.GENERIC_INTERFACE,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.STORAGE_CONTAINER, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Advanced_Materials.FORCE_FIELD_CONTAINMENT_GENERATOR, SlimefunItems.REINFORCED_ALLOY_INGOT
                }
        );
        ExpansionBackpack.register(plugin);

        RifleBullet = new RifleBullet(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.RIFLE_BULLET,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.HARDENED_METAL_INGOT, null,
                        SlimefunItems.STEEL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.STEEL_INGOT,
                        SlimefunItems.STEEL_INGOT, Basic_Materials.FUSE, SlimefunItems.STEEL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.RIFLE_BULLET, 8)
        );
        RifleBullet.register(plugin);

        Grenade = new Grenade(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.GRENADE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.REINFORCED_ALLOY_INGOT, Basic_Materials.FUSE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                        Basic_Materials.HIGH_EXPLOSIVE, Basic_Materials.DETONATOR, Basic_Materials.HIGH_EXPLOSIVE,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.HARDENED_METAL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.GRENADE, 1)
        );
        Grenade.register(plugin);

        SteelBall = new SteelBall(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.STEEL_BALL,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, new ItemStack(Material.IRON_NUGGET), null,
                        new ItemStack(Material.IRON_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(Material.IRON_NUGGET),
                        null, new ItemStack(Material.IRON_NUGGET), null
                },
                new SlimefunItemStack(Gun_And_Bullet.STEEL_BALL, 16)
        );
        SteelBall.register(plugin);

        BurningSteelBall = new BurningSteelBall(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.BURNING_STEEL_BALL,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.STEEL_INGOT, null,
                        new ItemStack(Material.GLASS_PANE), Basic_Materials.HIGH_EXPLOSIVE, new ItemStack(Material.GLASS_PANE),
                        null, SlimefunItems.STEEL_INGOT, null
                },
                new SlimefunItemStack(Gun_And_Bullet.BURNING_STEEL_BALL, 4)
        );
        BurningSteelBall.register(plugin);

        SpecialBullet = new SpecialBullet(
                Groups.gun_and_bullet_item_group,
                Gun_And_Bullet.SPECIAL_BULLET,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.HIGH_EXPLOSIVE, SlimefunItems.HARDENED_METAL_INGOT,
                        SlimefunItems.HARDENED_METAL_INGOT, Basic_Materials.FUSE, SlimefunItems.HARDENED_METAL_INGOT
                },
                new SlimefunItemStack(Gun_And_Bullet.SPECIAL_BULLET, 2)
        );
        SpecialBullet.register(plugin);
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

}
