package me.Doctor_do.multifunctionalgun.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final NamespacedKey nonClickable = new NamespacedKey(MultifunctionalGun.getInstance(), "nonclickable");

    private Utils() {
    }

    // 来自FluffyMachine，创建不可交互的物品栈
    public static ItemStack buildNonInteractable(Material material, @Nullable String name, @Nullable String... lore) {
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

        NCMeta.getPersistentDataContainer().set(nonClickable, PersistentDataType.BYTE, (byte) 1);
        nonClickableItem.setItemMeta(NCMeta);
        return nonClickableItem;
    }

    // 来自FluffyMachine，判断是否是不可交互的物品栈
    public static boolean checkNonInteractable(ItemStack item) {
        if (item == null || item.getItemMeta() == null) {
            return false;
        }

        return item.getItemMeta().getPersistentDataContainer().getOrDefault(nonClickable, PersistentDataType.BYTE, (byte) 0) == 1;
    }

    // 向玩家发送信息
    public static void sendMessage(Player player, String message) {
//        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        player.sendMessage(message);
    }

    // 来自FluffyMachine，物品给予背包或掉落
    public static void giveOrDropItem(Player p, ItemStack toGive) {
        for (ItemStack leftover : p.getInventory().addItem(toGive).values()) {
            p.getWorld().dropItemNaturally(p.getLocation(), leftover);
        }
    }
}
