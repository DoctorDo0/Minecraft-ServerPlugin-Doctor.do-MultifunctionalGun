package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class AssaultRifle extends SlimefunItem implements NotPlaceable {
    public static final Integer damage_temp = 20;
    public static Integer damage = damage_temp;

    public AssaultRifle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler_Right = this::onItemUseRightClick;
        addItemHandler(itemUseHandler_Right);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        event.cancel();
        TestCanTriggerEvent(event.getInteractEvent(), damage);
    }

    public void TestCanTriggerEvent(PlayerInteractEvent event, Integer damage) {
        if (event.getPlayer().hasPermission("minecraft.admin")) {
            event.getPlayer().sendMessage(ChatColors.color("&7是管理员"));
            getLogger().info("&7是管理员");
            CauseDamageToEntity(event, damage);
        }
    }

    public void CauseDamageToEntity(PlayerInteractEvent event, Integer damage) {
        event.getPlayer().sendMessage(ChatColors.color("&7测试:&fGun:&fAssaultRifle:&fDamage" + damage));
        getLogger().info("&7测试:&fGun:&fAssaultRifle:&fDamage" + damage);
    }
}
