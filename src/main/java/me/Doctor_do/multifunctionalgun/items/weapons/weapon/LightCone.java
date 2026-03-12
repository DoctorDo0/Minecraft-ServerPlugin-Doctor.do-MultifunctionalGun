package me.Doctor_do.multifunctionalgun.items.weapons.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;

public class LightCone extends ItemType_Gun implements NotPlaceable, Rechargeable {
    Plugin plugin = MultifunctionalGun.getInstance();

    public static final float CAPACITY = 5000.0F;
    public static final float COST = 40.0F;
    public static final int damage = 40;
    public static final int range = 50;
    public static final double cooldown = 0.0;
    public static final double multiplier = 1.0;

    public LightCone(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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

    // 方法重写，根据能源判定触发，无弹药部分
    @Override
    public void preprocessingAndShoot(@Nonnull Player player, @Nonnull ItemStack gun) {

        if (getItemCharge(gun) < COST) {
            return;
        } else {
            removeItemCharge(gun, COST);
        }

        shoot(player);
    }

    // 来自战争工艺，射击效果
    public void shoot(@Nonnull Player player) {

        Vector vector = player.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(10);
        ShulkerBullet bullet = player.launchProjectile(ShulkerBullet.class);
        bullet.setTarget(null);

        bullet.setMetadata("DMG_GunBullet", new FixedMetadataValue(plugin, true));
        bullet.setMetadata("damage",
                new FixedMetadataValue(plugin, damage)
        );
        bullet.setMetadata("options", new FixedMetadataValue(plugin, "fire"));
        bullet.setMetadata("keepTime", new FixedMetadataValue(plugin, 60));
        bullet.setMetadata("locInfo", new FixedMetadataValue(
                plugin,
                Utils.serializeLocation(player.getEyeLocation())
        ));
        bullet.setMetadata("rangeInfo", new FixedMetadataValue(
                plugin,
                range + ":" + 0
        ));
        bullet.setVelocity(vector);
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
