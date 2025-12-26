package me.Doctor_do.multifunctionalgun.categories;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class Groups {

    public static final NestedItemGroup main_contents_item_group = new NestedItemGroup(
            new NamespacedKey(MultifunctionalGun.getInstance(), "main_contents_item_group"),
            new CustomItemStack(Material.REPEATER, "&4Doctor.do的多功能枪械")
    );
    public static final SubItemGroup gun_and_bullet_item_group = new SubItemGroup(
            new NamespacedKey(MultifunctionalGun.getInstance(), "gun_and_bullet_item_group"),
            main_contents_item_group,
            new CustomItemStack(Material.REPEATER, "&4枪械 - 枪械及子弹")
    );
    public static final SubItemGroup machine_item_group = new SubItemGroup(
            new NamespacedKey(MultifunctionalGun.getInstance(), "machine_item_group"),
            main_contents_item_group,
            new CustomItemStack(Material.IRON_BLOCK, "&7枪械 - 机器")
    );
    public static final SubItemGroup advanced_material_item_group = new SubItemGroup(
            new NamespacedKey(MultifunctionalGun.getInstance(), "advanced_material_item_group"),
            main_contents_item_group,
            new CustomItemStack(Material.IRON_INGOT, "&6枪械 - 高级材料")
    );
    public static final SubItemGroup basic_material_item_group = new SubItemGroup(
            new NamespacedKey(MultifunctionalGun.getInstance(), "basic_material_item_group"),
            main_contents_item_group,
            new CustomItemStack(Material.GUNPOWDER, "&f枪械 - 基础材料")
    );
}
