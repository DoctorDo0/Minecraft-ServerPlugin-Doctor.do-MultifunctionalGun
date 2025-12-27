package me.Doctor_do.multifunctionalgun.setup.items_register.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon;
import me.Doctor_do.multifunctionalgun.items.weapons.LightCone;
import me.Doctor_do.multifunctionalgun.items.weapons.TIKA_Rifle;
import me.Doctor_do.multifunctionalgun.setup.DMG_SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public final class Gun_And_Bullet {

    public final static SlimefunItemStack RIFLE_BULLETS = new DMG_SlimefunItemStack(
            "RIFLE_BULLETS",
            Material.IRON_NUGGET,
            "&f步枪子弹",
            "",
            "&8RIFLE_BULLETS"
    );

    public final static SlimefunItemStack GRENADE = new DMG_SlimefunItemStack(
            "GRENADE",
            Material.SNOWBALL,
            "&f榴弹",
            "",
            "&8GRENADE"
    );

    public final static SlimefunItemStack STEEL_BALLS = new DMG_SlimefunItemStack(
            "STEEL_BALLS",
            Material.IRON_NUGGET,
            "&f钢珠",
            "",
            "&8STEEL_BALLS"
    );

    public final static SlimefunItemStack BURNING_STEEL_BALLS = new DMG_SlimefunItemStack(
            "BURNING_STEEL_BALLS",
            Material.GOLD_NUGGET,
            "&f燃烧弹",
            "",
            "&8BURNING_STEEL_BALLS"
    );

    public final static SlimefunItemStack SPECIAL_BULLETS = new DMG_SlimefunItemStack(
            "SPECIAL_BULLETS",
            Material.IRON_INGOT,
            "&f特种子弹",
            "",
            "&8SPECIAL_BULLETS"
    );

    public final static SlimefunItemStack ASSAULT_RIFLE = new DMG_SlimefunItemStack(
            "ASSAULT_RIFLE",
            Material.CROSSBOW,
            "&f突击步枪",
            "",
            "&7基础枪械之一，非常经典的武器",
            "&7可以快速射击，伤害不俗，子弹消耗速度很快",
            "&7需要: " + Gun_And_Bullet.RIFLE_BULLETS.getDisplayName(),
            "&8ASSAULT_RIFLE"
    );

    public final static SlimefunItemStack GRENADE_LAUNCHER = new DMG_SlimefunItemStack(
            "GRENADE_LAUNCHER",
            Material.CROSSBOW,
            "&f榴弹发射器",
            "",
            "&7基础枪械之一，非常经典的武器",
            "&7将榴弹抛射出去，造成范围性的爆炸伤害，使用时请小心",
            "&7需要: " + Gun_And_Bullet.GRENADE.getDisplayName(),
            "&8GRENADE_LAUNCHER"
    );

    public final static SlimefunItemStack TIKA_RIFLE = new DMG_SlimefunItemStack(
            "TIKA_RIFLE",
            Material.CROSSBOW,
            "&f提卡气动步枪",
            "",
            "&7基础枪械之一，做工粗糙，但耐用的武器",
            LoreBuilder.powerCharged(0, (int) TIKA_Rifle.getMaxItemCharge_Temp()),
            "&7装载了气压瓶和电磁轨道炮模块，威力中等，消耗电力时威力更大",
            "&7需要: " + Gun_And_Bullet.STEEL_BALLS.getDisplayName() + "&7 或 " + Gun_And_Bullet.BURNING_STEEL_BALLS.getDisplayName(),
            "&8TIKA_RIFLE"
    );

    public final static SlimefunItemStack LIGHT_CONE = new DMG_SlimefunItemStack(
            "LIGHT_CONE",
            Material.REPEATER,
            "&f光锥",
            "",
            "&7基础枪械之一，像一把来自未来的武器",
            LoreBuilder.powerCharged(0, (int) LightCone.getMaxItemCharge_Temp()),
            "&7能量武器，伤害高，不消耗子弹",
            "&7需要: " + "&f消耗更多的电力",
            "&8LIGHT_CONE"
    );

    public final static SlimefunItemStack ANTI_MATERIEL_SNIPER_RIFLE = new DMG_SlimefunItemStack(
            "ANTI_MATERIEL_SNIPER_RIFLE",
            Material.CROSSBOW,
            "&f反器材狙击步枪",
            "",
            "&7基础枪械之一，不算常见的武器",
            "&7射击速度慢，但伤害高，且射击距离极远",
            "&7需要: " + Gun_And_Bullet.SPECIAL_BULLETS.getDisplayName(),
            "&8ANTI_MATERIEL_SNIPER_RIFLE"
    );

    public final static SlimefunItemStack SCOPE = new DMG_SlimefunItemStack(
            "SCOPE",
            Material.SPYGLASS,
            "&f瞄准镜",
            "",
            "&7另一种望远镜，可以看的较远",
            "&8SCOPE"
    );

    public final static SlimefunItemStack LASER_SIGHT = new DMG_SlimefunItemStack(
            "LASER_SIGHT",
            Material.END_ROD,
            "&f激光瞄准器",
            "",
            "&7照射到生物身上，并使其发光，用于辅助校准",
            "&8LASER_SIGHT"
    );

    public final static SlimefunItemStack FIREARM_EXPANSION_BACKPACK = new DMG_SlimefunItemStack(
            "FIREARM_EXPANSION_BACKPACK",
            Material.CHEST,
            "&f枪械扩容背包",
            "",
            "&7一个空间很大的便携式箱子，复合式枪械专用",
            "&8FIREARM_EXPANSION_BACKPACK"
    );

    public final static SlimefunItemStack REINFORCED_COMPUTER_ARRAY = new DMG_SlimefunItemStack(
            "REINFORCED_COMPUTER_ARRAY",
            Material.COMMAND_BLOCK,
            "&f强化计算机阵列",
            "",
            "&7&m???谁发明的这种难制作东西",
            "&8REINFORCED_COMPUTER_ARRAY"
    );

    public final static SlimefunItemStack ASSAULT_RIFLE_AND_GRENADE_LAUNCHER = new DMG_SlimefunItemStack(
            Gun_And_Bullet.ASSAULT_RIFLE.getItemId() + "+" + Gun_And_Bullet.GRENADE_LAUNCHER.getItemId(),
            Material.BARRIER,
            Gun_And_Bullet.ASSAULT_RIFLE.getDisplayName() + " + " + Gun_And_Bullet.GRENADE_LAUNCHER.getDisplayName(),
            "",
            "&7一个占位符，无实际作用，属性为不可见，不应该被显示",
            "&7需要: " + Gun_And_Bullet.RIFLE_BULLETS.getDisplayName() + " + " + Gun_And_Bullet.GRENADE.getDisplayName(),
            "&8ASSAULT_RIFLE"
    );

    public final static SlimefunItemStack ENDLESS_WEAPON = new DMG_SlimefunItemStack(
            "ENDLESS_WEAPON",
            Material.REPEATER,
            "&f最终成品",
            "",
            "&7仍在测试",
            LoreBuilder.material("基岩"),
            LoreBuilder.powerCharged(0, (int) EndlessWeapon.getMaxItemCharge_Temp()),
            "&7当前模式: %modes%",
            "",
//            LoreBuilder.RIGHT_CLICK_TO_USE,
            "&e左键&7使用",
            "&e右键&7使用",
            "&7按住 &eShift + 左键&7 以打开背包",
            "&7按住 &eShift + 右键&7 以更改模式",
            "&8ENDLESS_WEAPON"
    );

    // 修改物品属性，初始化时执行
    static {
        ItemMeta EndlessWeaponItemMeta = ENDLESS_WEAPON.getItemMeta();
        assert EndlessWeaponItemMeta != null;
        EndlessWeaponItemMeta.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(
                        UUID.randomUUID(),
                        Attribute.GENERIC_ATTACK_DAMAGE.getKey().getKey(),
                        5,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlot.HAND
                )
        );
        EndlessWeaponItemMeta.setUnbreakable(true);
        EndlessWeaponItemMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        ENDLESS_WEAPON.setItemMeta(EndlessWeaponItemMeta);

        ItemMeta item_meta;
        item_meta = ASSAULT_RIFLE.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        ASSAULT_RIFLE.setItemMeta(item_meta);
        item_meta = GRENADE_LAUNCHER.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        GRENADE_LAUNCHER.setItemMeta(item_meta);
        item_meta = TIKA_RIFLE.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        TIKA_RIFLE.setItemMeta(item_meta);
        item_meta = LIGHT_CONE.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        LIGHT_CONE.setItemMeta(item_meta);
        item_meta = ANTI_MATERIEL_SNIPER_RIFLE.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        ANTI_MATERIEL_SNIPER_RIFLE.setItemMeta(item_meta);
        item_meta = SCOPE.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        SCOPE.setItemMeta(item_meta);
        item_meta = LASER_SIGHT.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        LASER_SIGHT.setItemMeta(item_meta);
        item_meta = FIREARM_EXPANSION_BACKPACK.getItemMeta();
        assert item_meta != null;
        item_meta.setUnbreakable(true);
        FIREARM_EXPANSION_BACKPACK.setItemMeta(item_meta);
    }
}
