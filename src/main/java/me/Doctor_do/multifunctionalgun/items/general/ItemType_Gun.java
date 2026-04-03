package me.Doctor_do.multifunctionalgun.items.general;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ItemType_Gun extends SlimefunItem implements NotPlaceable, DamageableItem {
    public static Plugin plugin = MultifunctionalGun.getInstance();

    protected int damage;
    protected int maxRange;
    protected int minRange = 0;
    protected double cooldown;
    protected double multiplier;

    public static final NamespacedKey LAST_USE = Utils.createKey("last_use");

    public ItemType_Gun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int damage, int maxRange, double cooldown, double multiplier) {
        super(itemGroup, item, recipeType, recipe);

        this.damage = damage;
        this.maxRange = maxRange;
        this.cooldown = (int) (cooldown * 1000);
        this.multiplier = multiplier;

        addItemHandler(getItemHandler());
    }

    // 来自SlimefunWarfare，判定触发间隔(冷却时间)
    public ItemUseHandler getItemHandler() {
        return event -> {
            event.cancel();
            Player player = event.getPlayer();
            ItemStack gun = player.getInventory().getItemInMainHand();
            if (!(SlimefunItem.getByItem(gun) instanceof ItemType_Gun)) {
                return;
            }

            preprocessingCutInPoint(player, gun, null, 1.0F);
        };
    }

    // 预处理切入点，用于实现本武器或最终武器的自定义调用
    public void preprocessingCutInPoint(@Nonnull Player player, @Nonnull ItemStack gun, @Nullable List<Inventory> inventories, @Nullable Float customerMultiplier) {
        preprocessingAndShoot(player, gun, this, inventories, customerMultiplier, null);
    }

    /**
     * 关于具体实现类(子类)的使用说明，电力部分:
     * <p>
     * 如果需要用到获取当前物品的电容量
     * <p>
     * !!!请自行设置容量字段为CAPACITY
     * <p>
     * !!!并设置实现接口的getMaxItemCharge方法，使之return ChargeUtil.getMaxItemCharge(itemStack, this)
     * <p>
     * 工具类中使用了反射的方式，来获取目标物品的真正电容，故电容字段一定需要为CAPACITY
     */
    // 预处理，前置判断等，不同类型拥有不同判断和状态
    public void preprocessingAndShoot(@Nonnull Player player, @Nonnull ItemStack currentGun, @Nonnull SlimefunItem gunType, @Nullable List<Inventory> inventories, @Nullable Float customerMultiplier, @Nullable ItemType_Bullet target) {
        // 击发冷却判断
        ItemMeta meta = currentGun.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        long lastUse = pdc.getOrDefault(ItemType_Gun.LAST_USE, PersistentDataType.LONG, 0L);
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastUse) < cooldown) {
            // 玩家提示
            player.sendMessage(ChatColor.YELLOW + "换弹中!");
            return;
        }
        pdc.set(LAST_USE, PersistentDataType.LONG, currentTime);
        currentGun.setItemMeta(meta);

        // 弹药判断
        ItemType_Bullet bullet;
        if (target == null) {
            bullet = checkAndConsumeInv(player, inventories, gunType);
        } else {
            bullet = checkAndConsumeInv(player, inventories, gunType, target);
        }

        if (bullet == null) {
            // 玩家提示
            Utils.sendMessage(player, ChatColor.RED + "子弹耗尽!");
            // 播放音效
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.0f);
            return;
        }

        // 修正后的倍率，方便最终武器修改
        if (customerMultiplier == null) {
            customerMultiplier = 1.0F;
        }
        // 播放音效
        player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
        // 实际击发效果
        shoot(player, bullet, multiplier * customerMultiplier);
    }

    // 来自SlimefunWarfare，射击效果
    public void shoot(@Nonnull Player player, @Nonnull ItemType_Bullet bullet, double multiplier) {

        Vector vector = player.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(20);
        LlamaSpit spit = player.launchProjectile(LlamaSpit.class);
        spit.setMetadata("DMG_GunBullet", new FixedMetadataValue(plugin, true));
        spit.setMetadata("damage",
                new FixedMetadataValue(plugin, this.damage * bullet.getMultiplier() * multiplier)
        );
        spit.setMetadata("effect", new FixedMetadataValue(plugin, bullet.getEffect()));
        spit.setMetadata("keepTime", new FixedMetadataValue(plugin, bullet.getKeepTime()));
        spit.setMetadata("locInfo", new FixedMetadataValue(
                plugin,
                Utils.serializeLocation(player.getEyeLocation())
        ));
        spit.setMetadata("rangeInfo", new FixedMetadataValue(
                plugin,
                maxRange + ":" + minRange
        ));
        spit.setVelocity(vector);
    }

    // 来自SlimefunWarfare，判定弹药位置与类型
    @Nullable
    public static ItemType_Bullet checkAndConsumeInv(@Nullable Player player, @Nullable List<Inventory> inventories, @Nonnull SlimefunItem gun) {
        ItemType_Bullet bullet = null;

        if (inventories != null) {
            for (Inventory inv : inventories) {
                for (ItemStack itemStack : inv) {
                    if (itemStack != null) {
                        bullet = checkAndConsume(gun, itemStack);
                        if (bullet != null) {
                            return bullet;
                        }
                    }
                }
            }
        }

        if (player != null) {
            bullet = checkAndConsume(gun, player.getInventory().getItemInOffHand());
            if (bullet != null) {
                return bullet;
            }
            for (ItemStack itemStack : player.getInventory().getContents()) {
                if (itemStack != null) {
                    bullet = checkAndConsume(gun, itemStack);
                    if (bullet != null) {
                        return bullet;
                    }
                }
            }
        }

        return bullet;
    }

    @Nullable
    public static ItemType_Bullet checkAndConsumeInv(@Nullable Player player, @Nullable List<Inventory> inventories, @Nonnull SlimefunItem gun, @Nonnull ItemType_Bullet target) {
        ItemType_Bullet bullet = null;

        if (inventories != null) {
            for (Inventory inv : inventories) {
                for (ItemStack itemStack : inv) {
                    if (itemStack != null) {
                        bullet = checkAndConsume(gun, itemStack, target);
                        if (bullet != null) {
                            return bullet;
                        }
                    }
                }
            }
        }

        if (player != null) {
            bullet = checkAndConsume(gun, player.getInventory().getItemInOffHand(), target);
            if (bullet != null) {
                return bullet;
            }
            for (ItemStack itemStack : player.getInventory().getContents()) {
                if (itemStack != null) {
                    bullet = checkAndConsume(gun, itemStack, target);
                    if (bullet != null) {
                        return bullet;
                    }
                }
            }
        }

        return bullet;
    }

    // 来自SlimefunWarfare，判定弹药位置与类型
    @Nullable
    public static ItemType_Bullet checkAndConsume(@Nonnull SlimefunItem gun, @Nonnull ItemStack stack) {
        AtomicReference<ItemType_Bullet> bullet = new AtomicReference<>(null);

        SlimefunItem item = SlimefunItem.getByItem(stack);

        if (item instanceof ItemType_Bullet && Objects.equals(
                ((ItemType_Bullet) item).getGun(),
                gun
        )) {
            bullet.set((ItemType_Bullet) item);
            ItemUtils.consumeItem(stack, true);
        } else if (item instanceof SlimefunBackpack) {
            PlayerProfile.getBackpack(stack, backpack -> bullet.set(checkAndConsumeInv(null, List.of(backpack.getInventory()), gun)));
        }

        return bullet.get();
    }

    // 判定特定类型的弹药
    @Nullable
    public static ItemType_Bullet checkAndConsume(@Nonnull SlimefunItem gun, @Nonnull ItemStack stack, @Nonnull ItemType_Bullet target) {
        AtomicReference<ItemType_Bullet> bullet = new AtomicReference<>(null);

        SlimefunItem item = SlimefunItem.getByItem(stack);

        if (item instanceof ItemType_Bullet && Objects.equals(
                ((ItemType_Bullet) item).getGun(),
                gun
        ) && Objects.equals(item, target)) {
            bullet.set((ItemType_Bullet) item);
            ItemUtils.consumeItem(stack, true);
        } else if (item instanceof SlimefunBackpack) {
            PlayerProfile.getBackpack(stack, backpack -> bullet.set(checkAndConsumeInv(null, List.of(backpack.getInventory()), gun, target)));
        }

        return bullet.get();
    }

    public boolean isDamageable() {
        return true;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public double getCooldown() {
        return cooldown;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
