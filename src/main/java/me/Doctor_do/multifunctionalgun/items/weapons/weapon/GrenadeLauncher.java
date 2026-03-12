package me.Doctor_do.multifunctionalgun.items.weapons.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import org.bukkit.inventory.ItemStack;

public class GrenadeLauncher extends ItemType_Gun implements NotPlaceable {

    public static final int damage = 120;
    public static final int range = 8;
    public static final double cooldown = 1.0;
    public static final double multiplier = 1.0;

    public GrenadeLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, damage, range, cooldown, multiplier);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public void preRegister() {
        addItemHandler(this.getItemHandler());
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getMaxRange() {
        return range;
    }

    @Override
    public double getCooldown() {
        return cooldown;
    }

    @Override
    public double getMultiplier() {
        return multiplier;
    }
}
