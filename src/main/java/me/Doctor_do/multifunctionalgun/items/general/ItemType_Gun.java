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
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    // 来自战争工艺，判定触发间隔(冷却时间)
    public ItemUseHandler getItemHandler() {
        return event -> {
            event.cancel();
            Player player = event.getPlayer();
            ItemStack gun = player.getInventory().getItemInMainHand();
            if (!(SlimefunItem.getByItem(gun) instanceof ItemType_Gun)) {
                return;
            }

            ItemMeta meta = gun.getItemMeta();
            assert meta != null;
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            long lastUse = pdc.getOrDefault(ItemType_Gun.LAST_USE, PersistentDataType.LONG, 0L);
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastUse) < cooldown) {
                player.sendMessage(ChatColor.YELLOW + "换弹中!");
                return;
            }
            pdc.set(LAST_USE, PersistentDataType.LONG, currentTime);
            gun.setItemMeta(meta);
            preprocessingAndShoot(player, gun);
        };
    }

    // 预处理，前置判断等，不同类型拥有不同判断和状态
    public void preprocessingAndShoot(@Nonnull Player player, @Nonnull ItemStack gun) {
        PlayerInventory inventory = player.getInventory();

        ItemType_Bullet bullet = checkAndConsumeInv(player, inventory, gun);

        if (bullet == null) {
            Utils.sendMessage(player, ChatColor.RED + "子弹耗尽!");
            return;
        }

        shoot(player, bullet, multiplier);
    }

    // 来自战争工艺，射击效果
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

    // 来自战争工艺，判定弹药位置与类型
    @Nullable
    protected static ItemType_Bullet checkAndConsumeInv(@Nullable Player player, @Nonnull Inventory inv, @Nonnull ItemStack gun) {
        ItemType_Bullet bullet = null;

        if (player != null) {
            bullet = checkAndConsume(gun, player.getInventory().getItemInOffHand());
        }

        if (bullet == null) {
            for (ItemStack itemStack : inv) {
                bullet = checkAndConsume(gun, itemStack);
                if (bullet != null) {
                    break;
                }
            }
        }

        return bullet;
    }

    // 来自战争工艺，判定弹药位置与类型
    @SuppressWarnings("all")
    @Nullable
    protected static ItemType_Bullet checkAndConsume(@Nonnull ItemStack gun, @Nonnull ItemStack stack) {
        AtomicReference<ItemType_Bullet> bullet = new AtomicReference<>(null);

        SlimefunItem item = SlimefunItem.getByItem(stack);

        if (item instanceof ItemType_Bullet && Objects.equals(
                ((ItemType_Bullet) item).getGun(),
                SlimefunItem.getByItem(gun)
        )) {
            bullet.set((ItemType_Bullet) item);
            ItemUtils.consumeItem(stack, true);
        } else if (item instanceof SlimefunBackpack) {
            PlayerProfile.getBackpack(stack, backpack -> bullet.set(checkAndConsumeInv(null, backpack.getInventory(), gun)));
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
