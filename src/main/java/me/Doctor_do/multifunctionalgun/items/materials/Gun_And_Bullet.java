package me.Doctor_do.multifunctionalgun.items.materials;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.Doctor_do.multifunctionalgun.items.DMG_SFItemStack;
import org.bukkit.Material;

public final class Gun_And_Bullet {

    public static SlimefunItemStack RIFLE_BULLETS = new DMG_SFItemStack(
            "RIFLE_BULLETS",
            Material.IRON_NUGGET,
            "&f步枪子弹",
            "",
            "&8RIFLE_BULLETS"
    );

    public static SlimefunItemStack GRENADE = new DMG_SFItemStack(
            "GRENADE",
            Material.SNOWBALL,
            "&f榴弹",
            "",
            "&8GRENADE"
    );

    public static SlimefunItemStack STEEL_BALLS = new DMG_SFItemStack(
            "STEEL_BALLS",
            Material.IRON_NUGGET,
            "&f钢珠",
            "",
            "&8STEEL_BALLS"
    );

    public static SlimefunItemStack BURNING_STEEL_BALLS = new DMG_SFItemStack(
            "BURNING_STEEL_BALLS",
            Material.GOLD_NUGGET,
            "&f燃烧弹",
            "",
            "&8BURNING_STEEL_BALLS"
    );

    public static SlimefunItemStack SPECIAL_BULLETS = new DMG_SFItemStack(
            "SPECIAL_BULLETS",
            Material.IRON_INGOT,
            "&f特种子弹",
            "",
            "&8SPECIAL_BULLETS"
    );

    public static SlimefunItemStack ASSAULT_RIFLE = new DMG_SFItemStack(
            "ASSAULT_RIFLE",
            Material.CROSSBOW,
            "&f突击步枪",
            "",
            "&7基础枪械之一，非常经典的武器",
            "&7可以快速射击，伤害不俗，子弹消耗速度很快",
            "&7需要: " + Gun_And_Bullet.RIFLE_BULLETS.getDisplayName(),
            "&8ASSAULT_RIFLE"
    );

    public static SlimefunItemStack GRENADE_LAUNCHER = new DMG_SFItemStack(
            "GRENADE_LAUNCHER",
            Material.CROSSBOW,
            "&f榴弹发射器",
            "",
            "&7基础枪械之一，非常经典的武器",
            "&7将榴弹抛射出去，造成范围性的爆炸伤害，使用时请小心",
            "&7需要: " + Gun_And_Bullet.GRENADE.getDisplayName(),
            "&8GRENADE_LAUNCHER"
    );

    public static SlimefunItemStack TICA_RIFLE = new DMG_SFItemStack(
            "TICA_RIFLE",
            Material.CROSSBOW,
            "&f提卡气动步枪",
            "",
            "&7基础枪械之一，做工粗糙，但耐用的武器",
            "&7装载了气压瓶和电磁轨道炮模块，威力中等，消耗电力时威力更大",
            "&7需要: " + Gun_And_Bullet.STEEL_BALLS.getDisplayName() + "&7 或 " + Gun_And_Bullet.BURNING_STEEL_BALLS.getDisplayName(),
            "&8TICA_RIFLE"
    );

    public static SlimefunItemStack LIGHT_CONE = new DMG_SFItemStack(
            "LIGHT_CONE",
            Material.REPEATER,
            "&f光锥",
            "",
            "&7基础枪械之一，像一把来自未来的武器",
            "&7能量武器，伤害高，不消耗子弹",
            "&7需要: " + "&f消耗更多的电力",
            "&8LIGHT_CONE"
    );

    public static SlimefunItemStack ANTI_MATERIEL_SNIPER_RIFLE = new DMG_SFItemStack(
            "ANTI_MATERIEL_SNIPER_RIFLE",
            Material.CROSSBOW,
            "&f反器材狙击步枪",
            "",
            "&7基础枪械之一，不算常见的武器",
            "&7射击速度慢，但伤害高，且射击距离极远",
            "&7需要: " + Gun_And_Bullet.SPECIAL_BULLETS.getDisplayName(),
            "&8ANTI_MATERIEL_SNIPER_RIFLE"
    );

    public static SlimefunItemStack SCOPE = new DMG_SFItemStack(
            "SCOPE",
            Material.SPYGLASS,
            "&f瞄准镜",
            "",
            "&7另一种望远镜，可以看的较远",
            "&8SCOPE"
    );

    public static SlimefunItemStack LASER_SIGHT = new DMG_SFItemStack(
            "LASER_SIGHT",
            Material.END_ROD,
            "&f激光瞄准器",
            "",
            "&7照射到生物身上，并使其发光，用于辅助校准",
            "&8LASER_SIGHT"
    );

    public static SlimefunItemStack FIREARM_EXPANSION_BACKPACK = new DMG_SFItemStack(
            "FIREARM_EXPANSION_BACKPACK",
            Material.CHEST,
            "&f枪械扩容背包",
            "",
            "&7一个空间很大的便携式箱子，复合式枪械专用",
            "&8FIREARM_EXPANSION_BACKPACK"
    );

    public static SlimefunItemStack REINFORCED_COMPUTER_ARRAY = new DMG_SFItemStack(
            "REINFORCED_COMPUTER_ARRAY",
            Material.COMMAND_BLOCK,
            "&f强化计算机阵列",
            "",
            "&7&m???谁发明的这种难制作东西",
            "&8REINFORCED_COMPUTER_ARRAY"
    );

    public static SlimefunItemStack ENDLESS_WEAPON = new DMG_SFItemStack(
            "ENDLESS_WEAPON",
            Material.REPEATER,
            "&f最终成品",
            "",
            "&7仍在测试",
            "&8ENDLESS_WEAPON"
    );
}
