package me.Doctor_do.multifunctionalgun.utils;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
    // 来自FluffyMachine，监听器，用于取消有不可交互标记的对象的交互事件
    // This is used to make the non clickable GUI items non clickable
    @EventHandler(ignoreCancelled = true)
    public void onNonClickableClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkNonInteractable(item)) {
            e.setCancelled(true);
        }
    }
}
