package me.Doctor_do.multifunctionalgun.items.weapons.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Bullet;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import me.Doctor_do.multifunctionalgun.utils.ChargeUtil;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LightCone extends ItemType_Gun implements NotPlaceable, Rechargeable {
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
        return ChargeUtil.getMaxItemCharge(itemStack, this);
    }

    @Override
    public void preRegister() {
        addItemHandler(this.getItemHandler());
    }

    // 预处理切入点，用于实现本武器或最终武器的自定义调用
    @Override
    public void preprocessingCutInPoint(@Nonnull Player player, @Nonnull ItemStack gun, @Nullable List<Inventory> inventories, @Nullable Float customerMultiplier) {
        preprocessingAndShoot(player, gun, this, null, customerMultiplier, null);
    }

    // 方法重写，根据能源判定触发，无弹药部分
    @Override
    public void preprocessingAndShoot(@Nonnull Player player, @Nonnull ItemStack currentGun, @Nonnull SlimefunItem gunType, @Nullable List<Inventory> inventories, @Nullable Float customerMultiplier, @Nullable ItemType_Bullet target) {

        if (getItemCharge(currentGun) < COST) {
            // 播放无充能音效
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
            return;
        } else {
            removeItemCharge(currentGun, COST);
        }

        // 修正后的倍率，方便最终武器修改
        if (customerMultiplier == null) {
            customerMultiplier = 1.0F;
        }
        Location startLoc = player.getEyeLocation();
        Vector direction = startLoc.getDirection().normalize();
        // 播放有充能音效
        player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.0f);
        // 生成粒子效果
        generateTrailEffect(player, startLoc, direction);
        // 实际击发效果
        shoot(player, multiplier * customerMultiplier);
    }

    public void shoot(@Nonnull Player player, double multiplier) {

        Vector vector = player.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(10);
        ShulkerBullet bullet = player.launchProjectile(ShulkerBullet.class);
        bullet.setTarget(null);

        bullet.setMetadata("DMG_GunBullet", new FixedMetadataValue(plugin, true));
        bullet.setMetadata("damage",
                new FixedMetadataValue(plugin, damage * multiplier)
        );
        bullet.setMetadata("effect", new FixedMetadataValue(plugin, "fire"));
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

    private void generateTrailEffect(Player player, Location startLoc, Vector direction) {
        double stepDistance = 0.5;
        // 存储粒子位置，用于后续可能的清理或使用
        List<Location> particleLocations = new ArrayList<>();

        // 使用 Bukkit 调度器在主线程生成粒子，此处为添加位置
        for (double distance = 0; distance <= LightCone.range; distance += stepDistance) {
            Location particleLoc = startLoc.clone().add(
                    direction.clone().multiply(distance)
            );
            particleLocations.add(particleLoc.clone());
        }
        // 在指定位置上生成粒子效果
        for (Location loc : particleLocations) {
            if (loc.getWorld().isChunkLoaded(loc.getBlock().getChunk())) {
                player.getWorld().spawnParticle(
                        Particle.REDSTONE,
                        loc,
                        1,
                        0, 0, 0,
                        0,
                        new Particle.DustOptions(Color.WHITE, 1.3f)
                );
            }
        }
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
