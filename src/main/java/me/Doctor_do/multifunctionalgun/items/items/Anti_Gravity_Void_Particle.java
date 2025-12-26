package me.Doctor_do.multifunctionalgun.items.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Anti_Gravity_Void_Particle extends SimpleSlimefunItem implements NotPlaceable, RandomMobDrop {

    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, "drop-from-wither", true);
    private final ItemSetting<Integer> chance = new IntRangeSetting(this, "wither-drop-chance", 0, 75, 100);

    public Anti_Gravity_Void_Particle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.addItemSetting(new ItemSetting[]{this.dropSetting});
        this.addItemSetting(new ItemSetting[]{this.chance});
    }

    @Override
    public int getMobDropChance() {
        return chance.getValue();
    }

    public boolean isDroppedFromWither() {
        return dropSetting.getValue();
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }

}
