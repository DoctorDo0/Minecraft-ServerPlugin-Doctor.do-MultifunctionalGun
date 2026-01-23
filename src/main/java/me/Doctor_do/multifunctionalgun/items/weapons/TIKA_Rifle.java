package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class TIKA_Rifle extends SlimefunItem implements NotPlaceable, Rechargeable {
    public static final float CAPACITY = 200.0F;
    public static final float COST = 2.0F;
    public static final int DAMAGE = 6;

    public TIKA_Rifle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return CAPACITY;
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
        if (removeItemCharge(item, COST)) {
            TestCanTriggerEvent(event.getInteractEvent(), DAMAGE + 16);
        } else {
            TestCanTriggerEvent(event.getInteractEvent(), DAMAGE);
        }
    }

    public void TestCanTriggerEvent(PlayerInteractEvent event, Integer damage) {
        if (event.getPlayer().hasPermission("minecraft.admin")) {
            event.getPlayer().sendMessage(ChatColors.color("&7是管理员"));
            getLogger().info("&7是管理员");
            CauseDamageToEntity(event, damage);
        }
    }

    public void CauseDamageToEntity(PlayerInteractEvent event, Integer damage) {
        event.getPlayer().sendMessage(ChatColors.color("&7测试:&fGun:&fTIKA_Rifle:&fDamage" + damage));
        getLogger().info("&7测试:&fGun:&fTIKA_Rifle:&fDamage" + damage);
    }
}
