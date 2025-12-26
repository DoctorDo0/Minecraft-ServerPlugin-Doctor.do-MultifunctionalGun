package me.Doctor_do.multifunctionalgun.items.blocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon_Mode;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Gun_And_Bullet_Item_Register;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Test_Item extends SlimefunItem implements NotPlaceable {

    private final List<EndlessWeapon_Mode> modes = new ArrayList<>();

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
        List<String> lore = itemMeta.getLore();
        lore.add("§7plugin:" + MultifunctionalGun.getInstance().toString());
        lore.add("§7plugin:Version:" + MultifunctionalGun.getInstance().getDescription().getVersion());
        lore.add("§7Hash Code:" + MultifunctionalGun.getInstance().hashCode());
        lore.add("§7Item: " + Gun_And_Bullet_Item_Register.getEndlessWeaponInstance().toString());
        lore.add("§8TEST_ITEM");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }
}
