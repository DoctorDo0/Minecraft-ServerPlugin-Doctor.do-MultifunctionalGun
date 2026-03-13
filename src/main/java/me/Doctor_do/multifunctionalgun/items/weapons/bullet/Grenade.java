package me.Doctor_do.multifunctionalgun.items.weapons.bullet;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Bullet;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import org.bukkit.inventory.ItemStack;

import static me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup.GrenadeLauncher;

public class Grenade extends ItemType_Bullet {
    public static final ItemType_Gun gun = (ItemType_Gun) GrenadeLauncher;
    public static final double multiplier = 1.0;
    public static final String effect = "normal";
    public static final int keepTime = 0;
    public static final double radius = 5;

    public Grenade(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput, gun, multiplier, effect, keepTime, radius);
        super.gun = gun;
        super.multiplier = multiplier;
        super.effect = effect;
        super.keepTime = keepTime;
        super.radius = radius;
    }

    @Override
    public ItemType_Gun getGun() {
        return gun;
    }

    @Override
    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public String getEffect() {
        return effect;
    }

    @Override
    public int getKeepTime() {
        return keepTime;
    }
}
