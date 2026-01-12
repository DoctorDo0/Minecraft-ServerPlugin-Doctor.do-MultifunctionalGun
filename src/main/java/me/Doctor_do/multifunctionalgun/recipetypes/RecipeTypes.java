package me.Doctor_do.multifunctionalgun.recipetypes;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class RecipeTypes {

    public static final RecipeType KILL_MOB_DROP = new RecipeType(
            new NamespacedKey(
                    MultifunctionalGun.getInstance(),
                    "kill_mob_drop"
            ),
            new CustomItemStack(
                    Material.IRON_SWORD,
                    "&f击杀指定生物掉落"
            )
    );

    public static final RecipeType ENERGY_PLANT = new RecipeType(
            new NamespacedKey(
                    MultifunctionalGun.getInstance(),
                    "energy_plant"
            ),
            new CustomItemStack(
                    Material.RESPAWN_ANCHOR,
                    "&f使用 " + Machine.ENERGY_COMPRESSION_PLANT.getDisplayName() + " &f或 " + Machine.ENERGY_LOADING_PLANT.getDisplayName() + " 制造"
            )
    );
}
