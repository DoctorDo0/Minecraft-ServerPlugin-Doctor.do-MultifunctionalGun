package me.Doctor_do.multifunctionalgun.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ChargeUtil {

    /**
     * 获取物品的最大电量
     *
     * @param itemStack    物品
     * @param selfInstance 调用此方法的 Rechargeable 实例（用于防止递归和获取默认值）
     * @return 物品的最大电量
     */
    public static float getMaxItemCharge(ItemStack itemStack, Rechargeable selfInstance) {
        // 空值检查：返回调用者自身的容量
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return getCapacityFromInstance(selfInstance);
        }

        // 获取物品对应的 SlimefunItem
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        // 如果找不到对应的 SlimefunItem
        if (slimefunItem == null) {
            return getCapacityFromInstance(selfInstance);
        }

        // 如果是自身，返回自身的容量（避免递归）
        if (slimefunItem == selfInstance) {
            return getCapacityFromInstance(selfInstance);
        }

        // 如果是其他实现了 Rechargeable 的物品，通过反射获取其 CAPACITY 常量
        if (slimefunItem instanceof Rechargeable) {
            float capacity = getCapacityFromInstance((Rechargeable) slimefunItem);
            if (capacity > 0) {
                return capacity;
            }
        }

        // 默认返回调用者自身的容量
        return getCapacityFromInstance(selfInstance);
    }

    /**
     * 通过反射获取 Rechargeable 实例的 CAPACITY 常量
     */
    private static float getCapacityFromInstance(Rechargeable instance) {
        try {
            // 获取实例的类
            Class<?> clazz = instance.getClass();

            // 尝试获取 CAPACITY 字段（静态常量）
            java.lang.reflect.Field field = clazz.getDeclaredField("CAPACITY");
            field.setAccessible(true);
            return field.getFloat(null); // 静态字段传 null

        } catch (NoSuchFieldException e) {
            // 没有 CAPACITY 字段，尝试其他方式
            return getCapacityFromInstanceFallback(instance);
        } catch (IllegalAccessException e) {
            return 0F;
        }
    }

    /**
     * 降级方案：尝试从实例中获取容量
     */
    private static float getCapacityFromInstanceFallback(Rechargeable instance) {
        try {
            // 尝试调用 getMaxItemCharge 方法，传入 null 获取自身容量
            // 注意：这里假设实现类对 null 参数返回自身容量
            return instance.getMaxItemCharge(null);
        } catch (Exception e) {
            return 0F;
        }
    }
}
