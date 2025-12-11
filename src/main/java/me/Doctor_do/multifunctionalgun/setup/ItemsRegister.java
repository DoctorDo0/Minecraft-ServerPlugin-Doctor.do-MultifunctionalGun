package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.item.Advanced_Materials_Item_Register;
import me.Doctor_do.multifunctionalgun.setup.item.Basic_Materials_Item_Register;
import me.Doctor_do.multifunctionalgun.setup.item.Gun_And_Bullet_Item_Register;
import me.Doctor_do.multifunctionalgun.setup.item.Machine_Item_Register;
import org.bukkit.inventory.ItemStack;

public class ItemsRegister {

    public ItemsRegister() {
        new Basic_Materials_Item_Register();
        new Advanced_Materials_Item_Register();
        new Machine_Item_Register();
        new Gun_And_Bullet_Item_Register();
    }

    public static void Item_Register_Interface(SubItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        new SlimefunItem(
                group,
                item,
                recipeType,
                recipe
        ).register(MultifunctionalGun.getInstance());
    }

    public static void Item_Register_Interface(SubItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,ItemStack recipeoutput) {
        new SlimefunItem(
                group,
                item,
                recipeType,
                recipe,
                recipeoutput
        ).register(MultifunctionalGun.getInstance());
    }
}
