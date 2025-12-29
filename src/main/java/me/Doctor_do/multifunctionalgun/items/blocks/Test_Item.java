package me.Doctor_do.multifunctionalgun.items.blocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.items_register.items_setup.Gun_And_Bullet_Item_Register;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Test_Item extends SlimefunItem implements NotPlaceable {

    public Test_Item(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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
        var itemMeta = item.getItemMeta();
        assert itemMeta != null;
        List<String> lore = itemMeta.getLore();
        assert lore != null;
        lore.add("§7Plugin:" + MultifunctionalGun.getInstance().toString());
        lore.add("§7Plugin:Version:" + MultifunctionalGun.getInstance().getDescription().getVersion());
        lore.add("§7Hash Code:" + MultifunctionalGun.getInstance().hashCode());
        lore.add("§7Item: " + Gun_And_Bullet_Item_Register.getEndlessWeaponInstance().toString());
        lore.add("§8TEST_ITEM");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        Slimefun.getLocalization().sendMessage(player, "Test_Item_RightClick");
    }
}
