package me.Doctor_do.multifunctionalgun.items.weapons.auxiliary;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Auxiliary;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class LaserSight extends ItemType_Auxiliary implements NotPlaceable, Rechargeable {
    public static final float CAPACITY = 200.0F;
    public static final float COST = 1.0F;
    public static final Color COLOR = Color.WHITE;

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
    public void effect(Player player, ItemStack itemStack) {
        if (removeItemCharge(itemStack, COST)) {
            // 保存起始位置和方向（必须在发射前保存，因为玩家可能会移动）
            Location startLoc = player.getEyeLocation().clone();
            Vector direction = startLoc.getDirection().normalize();

            // 使用调度器生成延迟粒子
            generateLaserParticlesDelayed(player, startLoc, direction);

            // 发射弹射物（原有逻辑）
            Vector vector = player.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(50);
            LlamaSpit spit = player.launchProjectile(LlamaSpit.class);
            spit.setMetadata("DMG_LaserSight", new FixedMetadataValue(plugin, true));
            spit.setMetadata("keepTime", new FixedMetadataValue(plugin, 20));
            spit.setMetadata("locInfo", new FixedMetadataValue(plugin, Utils.serializeLocation(player.getEyeLocation())));
            spit.setMetadata("rangeInfo", new FixedMetadataValue(plugin, 50 + ":" + 0));
            spit.setMetadata("color", new FixedMetadataValue(plugin, Utils.serializeColor(COLOR)));
            spit.setVelocity(vector);
        }
    }

    private void generateLaserParticlesDelayed(Player player, Location startLoc, Vector direction) {
        int maxDistance = 50;
        double stepDistance = 0.5;
        int totalParticles = (int) (maxDistance / stepDistance);

        for (int i = 0; i <= totalParticles; i++) {
            final double distance = i * stepDistance;

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Location particleLoc = startLoc.clone().add(direction.clone().multiply(distance));

                if (!particleLoc.getWorld().isChunkLoaded(particleLoc.getBlock().getChunk())) {
                    return;
                }

                double attenuation = 1.0 - (distance / maxDistance) * 0.5;
                int r = (int) (COLOR.getRed() * attenuation);
                int g = (int) (COLOR.getGreen() * attenuation);
                int b = (int) (COLOR.getBlue() * attenuation);

                player.getWorld().spawnParticle(
                        Particle.REDSTONE,
                        particleLoc,
                        1,
                        0, 0, 0,
                        0,
                        new Particle.DustOptions(Color.fromRGB(r, g, b), (float) (0.8f * attenuation))
                );
            }, i / 2); // 延迟时间等于索引值，形成依次生成的效果
        }
    }
}