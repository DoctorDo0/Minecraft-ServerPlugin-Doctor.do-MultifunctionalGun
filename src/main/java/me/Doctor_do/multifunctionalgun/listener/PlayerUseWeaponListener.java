package me.Doctor_do.multifunctionalgun.listener;

import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
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

        if (item != null && item.hasItemMeta() &&
                Objects.requireNonNull(item.getItemMeta()).hasDisplayName() &&
                item.getItemMeta().getDisplayName().equals(Gun_And_Bullet.ENDLESS_WEAPON.getDisplayName())) {
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().LeftClickEvent(player, event, item);
            }
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().RightClickEvent(player, event, item);
            }
        }

    }
}
