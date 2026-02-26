package me.Doctor_do.multifunctionalgun.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

    // 打开界面时禁止交互
    @SuppressWarnings("all")
    @EventHandler
    public void onOpenInventoryItemClick(InventoryClickEvent event) {
        SlimefunItem sfItem1 = SlimefunItem.getByItem(event.getCurrentItem());
        SlimefunItem sfItem2 = SlimefunItem.getByItem(event.getCursor());
        assert Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName() != null;
        if ((sfItem1 instanceof EndlessWeapon || sfItem2 instanceof EndlessWeapon)
                && event.getWhoClicked().getOpenInventory().getTitle().contains(Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName())
        ) {
            event.setCancelled(true);
            Utils.sendMessage(
                    (Player) event.getWhoClicked(),
                    Gun_And_Bullet.ENDLESS_WEAPON.getDisplayName()
                            + "在打开"
                            + Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName()
                            + "时禁止交互"
            );
        }
    }

    // 用于监听修改提卡模式
    @EventHandler(ignoreCancelled = true)
    public void onChangeModeClickInteract(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkChangeModeInteract(item)) {
            event.setCancelled(true);
            Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeMode(event);
        }
    }

    // 用于监听修改激光瞄准器设置
    @EventHandler(ignoreCancelled = true)
    public void onChangeLaserClickInteract(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkChangeLaserSettingInteract(item)) {
            event.setCancelled(true);

            // 关闭功能分支
            if (Utils.checkChangeLaserSettingCloseInteract(item)) {
                Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, "OFF");
            }
            // 开启功能分支
            if (Utils.checkChangeLaserSettingOpenInteract(item)) {
                Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, "ON");
            }
            // 增加Red功能分支
            if (Utils.checkChangeLaserSettingIncreaseRedInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 256);
                }
            }
            // 减少Red功能分支
            if (Utils.checkChangeLaserSettingDecreaseRedInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 256);
                }
            }
            // 增加Green功能分支
            if (Utils.checkChangeLaserSettingIncreaseGreenInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 256);
                }
            }
            // 减少Green功能分支
            if (Utils.checkChangeLaserSettingDecreaseGreenInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 256);
                }
            }
            // 增加Blue功能分支
            if (Utils.checkChangeLaserSettingIncreaseBlueInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 256);
                }
            }
            // 减少Blue功能分支
            if (Utils.checkChangeLaserSettingDecreaseBlueInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 256);
                }
            }
        }
    }
}
