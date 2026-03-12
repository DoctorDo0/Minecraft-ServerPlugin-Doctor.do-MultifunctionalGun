package me.Doctor_do.multifunctionalgun.items.weapons.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import org.bukkit.inventory.ItemStack;

public class AssaultRifle extends ItemType_Gun implements NotPlaceable {
    public static final int damage = 24;
    public static final int range = 20;
    public static final double cooldown = 0.2;
    public static final double multiplier = 1.0;

    public AssaultRifle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, damage, range, cooldown, multiplier);
        super.damage = damage;
        super.maxRange = range;
        super.cooldown = cooldown;
        super.multiplier = multiplier;
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
