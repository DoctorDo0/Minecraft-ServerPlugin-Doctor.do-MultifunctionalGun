package me.Doctor_do.multifunctionalgun.items.weapons.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Bullet;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;

public class AntiMaterielSniperRifle extends ItemType_Gun implements NotPlaceable {
    public static final int damage = 80;
    public static final int range = 50;
    public static final double cooldown = 1.0;
    public static final double multiplier = 1.0;

    public AntiMaterielSniperRifle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, damage, range, cooldown, multiplier);
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public void shoot(@Nonnull Player player, @Nonnull ItemType_Bullet bullet, double multiplier) {

        Vector vector = player.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(10);
        ShulkerBullet shulkerBullet = player.launchProjectile(ShulkerBullet.class);
        shulkerBullet.setTarget(null);

        shulkerBullet.setMetadata("DMG_GunBullet", new FixedMetadataValue(plugin, true));
        shulkerBullet.setMetadata("damage",
                new FixedMetadataValue(plugin, damage * bullet.getMultiplier() * multiplier)
        );
        shulkerBullet.setMetadata("effect", new FixedMetadataValue(plugin, bullet.getEffect()));
        shulkerBullet.setMetadata("keepTime", new FixedMetadataValue(plugin, bullet.getKeepTime()));
        shulkerBullet.setMetadata("locInfo", new FixedMetadataValue(
                plugin,
                Utils.serializeLocation(player.getEyeLocation())
        ));
        shulkerBullet.setMetadata("rangeInfo", new FixedMetadataValue(
                plugin,
                range + ":" + 0
        ));
        shulkerBullet.setVelocity(vector);
    }

    @Override
    public void preRegister() {
        addItemHandler(this.getItemHandler());
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
