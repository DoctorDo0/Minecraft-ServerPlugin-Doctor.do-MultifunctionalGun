package me.Doctor_do.multifunctionalgun.items.weapons.bullet;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Bullet;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import org.bukkit.inventory.ItemStack;

import static me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup.AntiMaterielSniperRifle;

public class SpecialBullet extends ItemType_Bullet {
    public static final ItemType_Gun gun = (ItemType_Gun) AntiMaterielSniperRifle;
    public static final double multiplier = 1.0;
    public static final String effect = "none";
    public static final int keepTime = 0;

    public SpecialBullet(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput, gun, multiplier, effect, keepTime);
        super.gun = gun;
        super.multiplier = multiplier;
        super.effect = effect;
        super.keepTime = keepTime;
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
