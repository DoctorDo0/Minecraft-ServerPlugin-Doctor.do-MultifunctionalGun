package me.Doctor_do.multifunctionalgun.items.weapons.auxiliary;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Auxiliary;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class LaserSight extends ItemType_Auxiliary implements NotPlaceable {

    public LaserSight(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public void effect(Player player) {
        player.sendMessage(ChatColors.color("&7测试:&fGun:&fLaserSight"));
        getLogger().info("&7测试:&fGun:&fLaserSight");
    }
}
