package me.Doctor_do.multifunctionalgun.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    private static final NamespacedKey nonClickable = new NamespacedKey(MultifunctionalGun.getInstance(), "nonclickable");
    private static final NamespacedKey changeMode = new NamespacedKey(MultifunctionalGun.getInstance(), "changeMode");
    private static final NamespacedKey changeLaser = new NamespacedKey(MultifunctionalGun.getInstance(), "changeLaser");
    private static final NamespacedKey laserSetting = new NamespacedKey(MultifunctionalGun.getInstance(), "laserSetting");

    final static Plugin plugin = MultifunctionalGun.getInstance();

    private Utils() {
    }

    // 创建并返回一个NamespacedKey
    public static NamespacedKey createKey(String name) {
        return new NamespacedKey(plugin, name);
    }

    // 向玩家发送信息
    public static void sendMessage(Player player, String message) {
//        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        player.sendMessage(message);
    }

    // 来自FluffyMachines，物品给予背包或掉落
    public static void giveOrDropItem(Player p, ItemStack toGive) {
        for (ItemStack leftover : p.getInventory().addItem(toGive).values()) {
            p.getWorld().dropItemNaturally(p.getLocation(), leftover);
        }
    }

    // 已修改，来自FluffyMachines，用于创建初始物品
    public static ItemStack buildInitialItem(Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack nonClickableItem = new ItemStack(material);
        ItemMeta NCMeta = nonClickableItem.getItemMeta();
        assert NCMeta != null;
        if (name != null) {
            NCMeta.setDisplayName(ChatColors.color(name));
        } else {
            NCMeta.setDisplayName(" ");
        }

        if (lore != null && lore.length > 0) {
            List<String> lines = new ArrayList<>();

            for (String line : lore) {
                lines.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            NCMeta.setLore(lines);
        }

        nonClickableItem.setItemMeta(NCMeta);
        return nonClickableItem;
    }

    // 已修改，来自FluffyMachines，创建不可交互的物品栈
    public static ItemStack buildNonInteractable(Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack nonClickableItem = buildInitialItem(material, name, lore);
        ItemMeta NCMeta = nonClickableItem.getItemMeta();
        assert NCMeta != null;
        NCMeta.getPersistentDataContainer().set(nonClickable, PersistentDataType.BYTE, (byte) 1);
        nonClickableItem.setItemMeta(NCMeta);
        return nonClickableItem;
    }

    // 创建附魔光泽的物品栈
    public static ItemStack buildEnchantGlow(Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack enchantGlowItem = buildInitialItem(material, name, lore);
        ItemMeta EGMeta = enchantGlowItem.getItemMeta();
        assert EGMeta != null;
        EGMeta.getPersistentDataContainer().set(nonClickable, PersistentDataType.BYTE, (byte) 1);
        EGMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        EGMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        enchantGlowItem.setItemMeta(EGMeta);
        return enchantGlowItem;
    }

    // 创建可修改提卡模式的物品栈
    public static ItemStack buildChangeMode(Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack changeModeItem = buildInitialItem(material, name, lore);
        ItemMeta CMMeta = changeModeItem.getItemMeta();
        assert CMMeta != null;
        CMMeta.getPersistentDataContainer().set(changeMode, PersistentDataType.STRING, "can change mode");
        changeModeItem.setItemMeta(CMMeta);
        return changeModeItem;
    }

    // 创建可修改激光瞄准器设置的物品栈
    public static ItemStack buildChangeLaser(String attribute, Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack changeLaserItem = buildInitialItem(material, name, lore);
        ItemMeta CLMeta = changeLaserItem.getItemMeta();
        assert CLMeta != null;
        CLMeta.getPersistentDataContainer().set(changeLaser, PersistentDataType.STRING, "can change laser settings");
        CLMeta.getPersistentDataContainer().set(laserSetting, PersistentDataType.STRING, attribute);
        changeLaserItem.setItemMeta(CLMeta);
        return changeLaserItem;
    }

    // 来自FluffyMachines，判断是否是不可交互的物品栈
    public static boolean checkNonInteractable(ItemStack item) {
        if (item == null || item.getItemMeta() == null) {
            return false;
        }

        return item.getItemMeta().getPersistentDataContainer().getOrDefault(nonClickable, PersistentDataType.BYTE, (byte) 0) == 1;
    }

    // 通用判断逻辑
    public static boolean checkAttributeIsSame(String attribute, NamespacedKey nsk, ItemStack item) {
        if (item == null || item.getItemMeta() == null) {
            return false;
        }
        return Objects.equals(item.getItemMeta().getPersistentDataContainer().getOrDefault(nsk, PersistentDataType.STRING, "null"), attribute);
    }

    // 判断是否是用于改变mode的物品栈
    public static boolean checkChangeModeInteract(ItemStack item) {
        return checkAttributeIsSame("can change mode", changeMode, item);
    }

    // 判断是否用于改变laserSetting(激光瞄准器设置)的物品栈
    public static boolean checkChangeLaserSettingInteract(ItemStack item) {
        return checkAttributeIsSame("can change laser settings", changeLaser, item);
    }

    // 判断是否为用于off(关闭)设置的物品栈
    public static boolean checkChangeLaserSettingCloseInteract(ItemStack item) {
        return checkAttributeIsSame("off", laserSetting, item);
    }

    // 判断是否为用于on(开启)设置的物品栈
    public static boolean checkChangeLaserSettingOpenInteract(ItemStack item) {
        return checkAttributeIsSame("on", laserSetting, item);
    }

    // 判断是否为用于增加R属性设置的物品栈
    public static boolean checkChangeLaserSettingIncreaseRedInteract(ItemStack item) {
        return checkAttributeIsSame("increase-red", laserSetting, item);
    }

    // 判断是否为用于减少R属性设置的物品栈
    public static boolean checkChangeLaserSettingDecreaseRedInteract(ItemStack item) {
        return checkAttributeIsSame("decrease-red", laserSetting, item);
    }

    // 判断是否为用于增加G属性设置的物品栈
    public static boolean checkChangeLaserSettingIncreaseGreenInteract(ItemStack item) {
        return checkAttributeIsSame("increase-green", laserSetting, item);
    }

    // 判断是否为用于减少G属性设置的物品栈
    public static boolean checkChangeLaserSettingDecreaseGreenInteract(ItemStack item) {
        return checkAttributeIsSame("decrease-green", laserSetting, item);
    }

    // 判断是否为用于增加B属性设置的物品栈
    public static boolean checkChangeLaserSettingIncreaseBlueInteract(ItemStack item) {
        return checkAttributeIsSame("increase-blue", laserSetting, item);
    }

    // 判断是否为用于减少属性B设置的物品栈
    public static boolean checkChangeLaserSettingDecreaseBlueInteract(ItemStack item) {
        return checkAttributeIsSame("decrease-blue", laserSetting, item);
    }

    // 用于使物品在界面内变为不可交互状态
    public static void setItemNonClickable(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(nonClickable, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
    }

    // 用于使物品在界面内恢复为可交互状态
    public static void setItemCanClickable(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(nonClickable);
        item.setItemMeta(meta);
    }

    // 来自SlimefunWarfare，未知(反序列化位置)
    @Nonnull
    public static Location deserializeLocation(@Nonnull String s) {
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid location deserialization parameter, got " + s);
        }

        String[] parts = s.split(":");

        if (parts.length == 4) {
            World w = Bukkit.getServer().getWorld(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            return new Location(w, x, y, z);
        }

        throw new IllegalArgumentException("Invalid location deserialization parameter, got " + s);
    }

    // 来自SlimefunWarfare，用于创建坐标信息
    @Nonnull
    public static String serializeLocation(@Nonnull Location loc) {
        return loc.getWorld().getName() +
                ":" + loc.getX() +
                ":" + loc.getY() +
                ":" + loc.getZ();
    }
}
