package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Gun_And_Bullet_Item_Register;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EndlessWeapon_Mode {
    private final ItemSetting<String> item;
    private final ItemSetting<Boolean> enabled;

    EndlessWeapon_Mode(@Nonnull EndlessWeapon endlessWeapon, int id, @Nonnull String functionId) {
        this.item = new ItemSetting<>(endlessWeapon, "mode." + id + ".function", functionId);
        this.enabled = new ItemSetting<>(endlessWeapon, "mode." + id + ".enabled", true);

        endlessWeapon.addItemSetting(item, enabled);
    }

//    @Nullable
//    SlimefunItem getItem() {
//        return SlimefunItem.getById(item.getValue());
//    }

    // 此处，以及后面的item.getValue()，调用ItemSetting<>内存放的数据，为之前录入的%SlimefunItem%.getDisplayName()
    @Nullable
    SlimefunItem getItem() {
        return Gun_And_Bullet_Item_Register.getItemByDisplayName(item.getValue());
    }

    boolean isEnabled() {
        return enabled.getValue();
    }
}
