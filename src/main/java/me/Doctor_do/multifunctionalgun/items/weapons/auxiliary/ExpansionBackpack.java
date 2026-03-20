package me.Doctor_do.multifunctionalgun.items.weapons.auxiliary;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Auxiliary;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExpansionBackpack extends ItemType_Auxiliary implements NotPlaceable {

    public ExpansionBackpack(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public void effect(Player player) {
        final int INVENTORY_SIZE = 54;
        Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, ChatColor.GOLD + Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName());
        ItemStack backgroundItem = Utils.buildNonInteractable(Material.BLACK_STAINED_GLASS_PANE, "&f无连接");
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            inventory.setItem(i, backgroundItem);
        }
        player.openInventory(inventory);
    }
}
