package me.Doctor_do.multifunctionalgun.items.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 这里使用到了异步线程调用。
 * 该调用会使玩家触发的所有效果进入一个队列中进行等待。
 * 当本次任务(效果)执行结束后，才会使队列中的下个任务(效果)进入执行状态。
 * <p>
 * 并且该效果中含有一个二次显示。
 * 效果大部分是ai生成的，刚开始看描述的时候感觉挺花哨的，实际测试的时候感觉比较一般，效果没那么强烈。
 * 说实话我个人只想保留那个逐渐显示的粒子效果，一次性显示一条直线的粒子效果给光锥可能更合适(?)
 * 后续:
 * 调整了一下粒子显示位置，现在有加速的效果了
 * <p>
 * 如果整个扔掉的话，个人觉得有点可惜，遂移植到测试物品中，也算给测试物品加点新花样了，不至于像原来那么单调。
 */
public class Test_Item extends SlimefunItem implements NotPlaceable {
    Plugin plugin = MultifunctionalGun.getInstance();
    // 异步线程使用
    private final java.util.concurrent.ExecutorService particleExecutor = java.util.concurrent.Executors.newSingleThreadExecutor();

    public Test_Item(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        hidden = true;
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler_Right = this::onItemUseRightClick;
        addItemHandler(itemUseHandler_Right);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        event.cancel();

        // 在玩家周围生成彩色粒子
        spawnParticle(player);

        // 异步生成粒子效果，避免阻塞主线程
        Location startLoc = player.getEyeLocation().clone();
        particleExecutor.submit(() -> generateLaserParticlesAsync(startLoc, player));

        // 覆盖lore标签
        changeLore(item);

        Utils.sendMessage(player, "Test_Item_RightClick");
    }

    // 在玩家周围生成彩色粒子
    private void spawnParticle(Player player) {
        Location playerLocation = player.getLocation();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        Color color = Color.fromRGB(red, green, blue);

        for (int i = 0; i < 25; i++) {
            float xRand = (random.nextFloat() - 0.5F) * 3.2F;
            float yRand = (random.nextFloat() - 0.5F) * 3.2F;
            float zRand = (random.nextFloat() - 0.5F) * 3.2F;

            player.getWorld().spawnParticle(Particle.REDSTONE,
                    playerLocation.getX() + xRand,
                    playerLocation.getY() + 2.0D + yRand,
                    playerLocation.getZ() + zRand,
                    1,
                    new Particle.DustOptions(color, 1));
        }
        Utils.sendMessage(player, "RGB: " + red + " " + green + " " + blue);
        Utils.sendMessage(player, Utils.serializeColor(Color.fromRGB(red, green, blue)));
        /*String redFormattedHexString = String.format("%02X", red);
        String greenFormattedHexString = String.format("%02X", green);
        String blueFormattedHexString = String.format("%02X", blue);
        Utils.sendMessage(player,
                "§x" +
                        "§" + redFormattedHexString.toLowerCase().charAt(0) +
                        "§" + redFormattedHexString.toLowerCase().charAt(1) +
                        "§" + greenFormattedHexString.toLowerCase().charAt(0) +
                        "§" + greenFormattedHexString.toLowerCase().charAt(1) +
                        "§" + blueFormattedHexString.toLowerCase().charAt(0) +
                        "§" + blueFormattedHexString.toLowerCase().charAt(1) +
                        "#" +
                        redFormattedHexString.toUpperCase().charAt(0) +
                        redFormattedHexString.toUpperCase().charAt(1) +
                        "_" +
                        greenFormattedHexString.toUpperCase().charAt(0) +
                        greenFormattedHexString.toUpperCase().charAt(1) +
                        "_" +
                        blueFormattedHexString.toUpperCase().charAt(0) +
                        blueFormattedHexString.toUpperCase().charAt(1)
        );*/
    }

    /**
     * 异步生成粒子线（修复版）
     */
    private void generateLaserParticlesAsync(Location startLoc, Player player) {
        Vector direction = startLoc.getDirection().normalize();
        int maxDistance = 25;

        List<ParticleInfo> particles = new ArrayList<>();

        double currentDistance = 0;
        int maxIterations = 100;
        int iteration = 0;

        while (currentDistance < maxDistance && iteration < maxIterations) {
            iteration++;

            double progress = currentDistance / maxDistance;
            double step = 0.3 + (progress * 0.9);

            if (currentDistance + step > maxDistance) {
                step = maxDistance - currentDistance;
            }

            currentDistance += step;

            if (currentDistance > maxDistance) {
                currentDistance = maxDistance;
            }

            Location particleLoc = startLoc.clone().add(direction.clone().multiply(currentDistance));
            particles.add(new ParticleInfo(particleLoc.clone(), currentDistance));

            if (currentDistance >= maxDistance) {
                break;
            }
        }

        // 确保有终点粒子
        if (particles.isEmpty() || particles.get(particles.size() - 1).distance < maxDistance - 0.1) {
            Location endLoc = startLoc.clone().add(direction.clone().multiply(maxDistance));
            particles.add(new ParticleInfo(endLoc, maxDistance));
        }

        // 调度粒子生成
        for (int i = 0; i < particles.size(); i++) {
            ParticleInfo info = particles.get(i);
            long delay = i * 2L;
            final int index = i; // 关键：创建 final 变量

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if (!info.location.getWorld().isChunkLoaded(info.location.getBlock().getChunk())) {
                    return;
                }

                float particleSize = 3.0f - (float) (info.distance / maxDistance) * 1.5f;

                // 初始粒子
                if (info.distance < 0.5) {
                    player.getWorld().spawnParticle(
                            Particle.FIREWORKS_SPARK,
                            info.location,
                            3,
                            0.2, 0.2, 0.2,
                            0
                    );
                    player.playSound(info.location, Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);
                }
                // 末尾粒子
                // 使用 index 判断是否是最后一个粒子
                else if (index == particles.size() - 1) {
                    player.getWorld().spawnParticle(
                            Particle.REDSTONE,
                            info.location,
                            5,
                            0.1, 0.1, 0.1,
                            0,
                            new Particle.DustOptions(Color.RED, 1.0f)
                    );
                } else {
                    double brightness = 1.0 - ((info.distance / maxDistance) / 2);
                    int r = 255;
                    int g = (int) (150 * brightness);
                    int b = 0;

                    player.getWorld().spawnParticle(
                            Particle.REDSTONE,
                            info.location,
                            1,
                            0, 0, 0,
                            0,
                            new Particle.DustOptions(Color.fromRGB(r, g, b), particleSize)
                    );
                }
            }, delay);
        }
    }

    /**
     * 粒子信息内部类
     */
    private static class ParticleInfo {
        final Location location;
        final double distance;

        ParticleInfo(Location location, double distance) {
            this.location = location;
            this.distance = distance;
        }
    }

    // 覆盖lore标签
    private void changeLore(ItemStack item) {
        var itemMeta = item.getItemMeta();
        if (itemMeta == null) return;
        List<String> lore = itemMeta.getLore();
        if (lore == null) return;

//        lore.removeIf(loreLine -> loreLine.contains("cover this:"));
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains("cover this:")) {
                lore.remove(i);
                lore.add("§7Plugin:" + MultifunctionalGun.getInstance().toString());
                lore.add("§7Plugin:Version:" + MultifunctionalGun.getInstance().getDescription().getVersion());
                lore.add("§7Hash Code:" + MultifunctionalGun.getInstance().hashCode());
                lore.add("§7Item: " + Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().toString());
                lore.add("§8TEST_ITEM");
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                break;
            }
        }
    }
}
