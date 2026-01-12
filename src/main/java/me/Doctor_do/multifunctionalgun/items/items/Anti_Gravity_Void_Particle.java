package me.Doctor_do.multifunctionalgun.items.items;

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

    // private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, "drop-from-wither", true);
    // private final ItemSetting<Integer> chance = new IntRangeSetting(this, "wither-drop-chance", 0, 75, 100);
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

//        this.addItemSetting(new ItemSetting[]{this.dropSetting});
//        this.addItemSetting(new ItemSetting[]{this.chance});
    }

//    @Override
//    public int getMobDropChance() {
//        return chance.getValue();
//    }

    public int getMobDropChance() {
        return Integer.parseInt(String.valueOf(item_chance_list.get(0)));
    }

    public static EntityType getEntityTypeByMobDrop() {
        return EntityType.WITHER;
    }

//    public boolean isDroppedFromWither() {
//        return dropSetting.getValue();
//    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }

}
