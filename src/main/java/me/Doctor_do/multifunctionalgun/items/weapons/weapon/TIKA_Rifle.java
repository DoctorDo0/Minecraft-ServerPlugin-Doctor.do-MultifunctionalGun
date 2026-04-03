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
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TIKA_Rifle extends ItemType_Gun implements NotPlaceable, Rechargeable {
    public static final float CAPACITY = 200.0F;
    public static final float COST = 2.0F;
    public static final int damage = 6;
    public static final int range = 20;
    public static final double cooldown = 0.25;
    public static final double multiplier = 1.0;

    public TIKA_Rifle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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

    // 方法重写，根据能源调整倍率
    @Override
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

        double current_multiplier;
        if (removeItemCharge(currentGun, COST)) {
            current_multiplier = multiplier * 3;
        } else {
            current_multiplier = multiplier * 1;
        }
        // 修正后的倍率，方便最终武器修改
        if (customerMultiplier == null) {
            customerMultiplier = 1.0F;
        }
        // 播放音效
        player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
        // 实际击发效果
        shoot(player, bullet, current_multiplier * customerMultiplier);
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
