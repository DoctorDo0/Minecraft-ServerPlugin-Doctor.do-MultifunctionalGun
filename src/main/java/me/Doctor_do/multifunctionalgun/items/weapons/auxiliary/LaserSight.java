package me.Doctor_do.multifunctionalgun.items.weapons.auxiliary;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Auxiliary;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class LaserSight extends ItemType_Auxiliary implements NotPlaceable, Rechargeable {
    public static final float CAPACITY = 200.0F;
    public static final float COST = 1.0F;

    public LaserSight(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return CAPACITY;
    }

    @Override
    public void effect(Player player) {
        if (removeItemCharge(player.getInventory().getItemInMainHand(), COST)) {
            Vector vector = player.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(50);
            LlamaSpit spit = player.launchProjectile(LlamaSpit.class);
            spit.setMetadata("DMG_LaserSight", new FixedMetadataValue(plugin, true));
            spit.setMetadata("keepTime", new FixedMetadataValue(plugin, 20));
            spit.setMetadata("locInfo", new FixedMetadataValue(
                    plugin,
                    Utils.serializeLocation(player.getEyeLocation())
            ));
            spit.setMetadata("rangeInfo", new FixedMetadataValue(plugin, 50 + ":" + 0));
            spit.setVelocity(vector);
        }
    }
}
