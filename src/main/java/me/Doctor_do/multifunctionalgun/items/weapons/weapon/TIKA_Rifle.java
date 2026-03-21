package me.Doctor_do.multifunctionalgun.items.weapons.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Bullet;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nonnull;

public class TIKA_Rifle extends ItemType_Gun implements NotPlaceable, Rechargeable {
    public static final float CAPACITY = 200.0F;
    public static final float COST = 2.0F;
    public static final int damage = 6;
    public static final int range = 20;
    public static final double cooldown = 0.25;
    public static double multiplier = 1.0;

    public TIKA_Rifle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, damage, range, cooldown, multiplier);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return CAPACITY;
    }

    @Override
    public void preRegister() {
        addItemHandler(this.getItemHandler());
    }

    // 方法重写，根据能源调整倍率
    @Override
    public void preprocessingAndShoot(@Nonnull Player player, @Nonnull ItemStack gun) {
        PlayerInventory inventory = player.getInventory();

        ItemType_Bullet bullet = checkAndConsumeInv(inventory, gun);

        if (bullet == null) {
            Utils.sendMessage(player, ChatColor.RED + "子弹耗尽!");
            return;
        }

        double current_multiplier;
        if (removeItemCharge(gun, COST)) {
            current_multiplier = multiplier * 3;
        } else {
            current_multiplier = multiplier * 1;
        }

        shoot(player, bullet, current_multiplier);
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
