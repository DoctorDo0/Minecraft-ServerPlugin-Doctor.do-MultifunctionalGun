package me.Doctor_do.multifunctionalgun.items.general;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemType_Auxiliary extends SlimefunItem implements NotPlaceable {
    public ItemType_Auxiliary(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(getItemHandler());
    }

    public ItemUseHandler getItemHandler() {
        return event -> {
            event.cancel();
            effect(event.getPlayer());
        };
    }

    public void effect(Player player) {
        //
    }
}
