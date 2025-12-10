package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.register.Advanced_Materials_Register;
import me.Doctor_do.multifunctionalgun.setup.register.Basic_Materials_Register;
import me.Doctor_do.multifunctionalgun.setup.register.Gun_And_Bullet_Register;
import me.Doctor_do.multifunctionalgun.setup.register.Machine_Register;
import org.bukkit.inventory.ItemStack;

public final class ItemsRegister {

//    public static void basic_material_item_register(@Nonnull MultifunctionalGun plugin) {
//        Basic_Materials_Register.Basic_Materials_Register_Items();
//    }
//
//    public static void advanced_material_item_register(@Nonnull MultifunctionalGun plugin) {
//        Advanced_Materials_Register.Advanced_Materials_Register_Items();
//    }
//
//    public static void machine_item_register(@Nonnull MultifunctionalGun plugin) {
//        Machine_Register.Machine_Register_Items();
//    }
//
//    public static void gun_and_bullet_item_register(@Nonnull MultifunctionalGun plugin) {
//        Gun_And_Bullet_Register.Gun_And_Bullet_Register_Items();
//    }

    public ItemsRegister() {
        Basic_Materials_Register.Basic_Materials_Items();
        Advanced_Materials_Register.Advanced_Materials_Items();
        Machine_Register.Machine_Items();
        Gun_And_Bullet_Register.Gun_And_Bullet_Items();
    }

    public static void Item_Register_Method(SubItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        new SlimefunItem(
                group,
                item,
                recipeType,
                recipe
        ).register(MultifunctionalGun.getInstance());
    }
}
