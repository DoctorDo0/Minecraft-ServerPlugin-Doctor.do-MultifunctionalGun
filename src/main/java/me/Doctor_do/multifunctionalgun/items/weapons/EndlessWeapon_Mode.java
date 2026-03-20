package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EndlessWeapon_Mode {
    MODE_0(0,
            Gun_And_Bullet.ASSAULT_RIFLE.getDisplayName() + " + " + Gun_And_Bullet.GRENADE_LAUNCHER.getDisplayName(),
            Gun_And_Bullet_Item_Setup.AssaultRifle,
            Gun_And_Bullet_Item_Setup.GrenadeLauncher,
            true),
    MODE_1(1,
            Gun_And_Bullet.TIKA_RIFLE.getDisplayName() + " + " + Gun_And_Bullet.SCOPE.getDisplayName(),
            Gun_And_Bullet_Item_Setup.TIKA_Rifle,
            Gun_And_Bullet_Item_Setup.Scope,
            true),
    MODE_2(2,
            Gun_And_Bullet.LIGHT_CONE.getDisplayName() + " + " + Gun_And_Bullet.SCOPE.getDisplayName(),
            Gun_And_Bullet_Item_Setup.LightCone,
            Gun_And_Bullet_Item_Setup.Scope,
            true),
    MODE_3(3,
            Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName() + " + " + Gun_And_Bullet.SCOPE.getDisplayName(),
            Gun_And_Bullet_Item_Setup.AntiMaterielSniperRifle,
            Gun_And_Bullet_Item_Setup.Scope,
            true);

    private final int index;
    private final String displayName;
    private final SlimefunItem primaryItem;
    private final SlimefunItem secondaryItem;
    private final boolean enabled;

    EndlessWeapon_Mode(int index, String name, @Nonnull SlimefunItem primary, @Nonnull SlimefunItem secondary, boolean enabled) {
        this.index = index;
        this.displayName = name;
        this.primaryItem = primary;
        this.secondaryItem = secondary;
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
            if (weapon.index == id) {
                return weapon;
            }
        }
        return null;
    }

    @Nullable
    String getModeName() {
        return displayName;
    }

    // 返回slimefunItem，而不是slimefunItemStack
    @Nonnull
    SlimefunItem getPrimaryItem() {
        return primaryItem;
    }

    @Nonnull
    SlimefunItem getSecondaryItem() {
        return secondaryItem;
    }

    boolean isEnabled() {
        return enabled;
    }
}
