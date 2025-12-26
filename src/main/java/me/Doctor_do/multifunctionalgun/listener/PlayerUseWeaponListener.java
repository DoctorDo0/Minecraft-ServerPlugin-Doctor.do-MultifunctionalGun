package me.Doctor_do.multifunctionalgun.listener;

import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Gun_And_Bullet_Item_Register;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerUseWeaponListener implements Listener {

    private final static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    public PlayerUseWeaponListener() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        // 同一个物品栈
//        ItemStack item = player.getInventory().getItemInMainHand();
//        ItemStack item2 = event.getItem();
//        ItemStack item3 = event.getPlayer().getInventory().getItemInMainHand();

        if (item != null && item.hasItemMeta() &&
                Objects.requireNonNull(item.getItemMeta()).hasDisplayName() &&
                item.getItemMeta().getDisplayName().equals(Gun_And_Bullet.ENDLESS_WEAPON.getDisplayName())) {
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                Gun_And_Bullet_Item_Register.getEndlessWeaponInstance().LeftClickEvent(player, event, item);
            }
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                Gun_And_Bullet_Item_Register.getEndlessWeaponInstance().RightClickEvent(player, event, item);
            }
        }

    }
}
