package me.Doctor_do.multifunctionalgun.items.items.mobdrops;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Anti_Gravity_Void_Particle extends SlimefunItem implements NotPlaceable, RandomMobDrop {

    public static final List<Double> item_chance_list = new ArrayList<>(5);

    static {
        item_chance_list.add(100.0);
        item_chance_list.add(100.0);
        item_chance_list.add(80.0);
        item_chance_list.add(60.0);
        item_chance_list.add(40.0);
    }

    public Anti_Gravity_Void_Particle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public int getMobDropChance() {
        return Integer.parseInt(String.valueOf(item_chance_list.get(0)));
    }

    public static EntityType getEntityTypeByMobDrop() {
        return EntityType.WITHER;
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }

}
