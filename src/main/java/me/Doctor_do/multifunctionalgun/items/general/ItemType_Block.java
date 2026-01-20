package me.Doctor_do.multifunctionalgun.items.general;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemType_Block extends SimpleSlimefunItem implements NotPlaceable {

    public ItemType_Block(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public ItemType_Block(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }
}
