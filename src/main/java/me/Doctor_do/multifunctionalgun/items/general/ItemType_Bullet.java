package me.Doctor_do.multifunctionalgun.items.general;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import org.bukkit.inventory.ItemStack;

public class ItemType_Bullet extends SlimefunItem implements NotPlaceable {
    protected ItemType_Gun gun;
    protected double multiplier;
    protected String options;
    protected int keepTime;

    public ItemType_Bullet(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput, ItemType_Gun gun, double multiplier, String options, int keepTime) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        this.gun = gun;
        this.multiplier = multiplier;
        this.options = options;
        this.keepTime = keepTime;
    }

    public ItemType_Gun getGun(){
        return gun;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public String getOptions() {
        return options;
    }

    public int getKeepTime() {
        return keepTime;
    }
}
