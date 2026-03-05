package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EndlessWeapon_Mode {
    A(0, Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER, true),
    B(1, Gun_And_Bullet.TIKA_RIFLE, true),
    C(2, Gun_And_Bullet.LIGHT_CONE, true),
    D(3, Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE, true);

    private final int id;
    private final SlimefunItemStack item;
    private final boolean enabled;

    EndlessWeapon_Mode(int id, @Nonnull SlimefunItemStack slimefunItemStack, boolean enabled) {
        this.id = id;
        this.item = slimefunItemStack;
        this.enabled = enabled;
    }

    // 获取枚举常量数量
    public static int getCount() {
        return values().length;
    }

    // 返回枚举常量本身
    @Nonnull
    @SuppressWarnings("all")
    static EndlessWeapon_Mode get(int id) {
        for (EndlessWeapon_Mode weapon : values()) {
            if (weapon.id == id) {
                return weapon;
            }
        }
        return null;
    }

    // 返回slimefunItem，而不是slimefunItemStack
    @Nullable
    SlimefunItem getItem() {
        return item.getItem();
    }

    boolean isEnabled() {
        return enabled;
    }
}
