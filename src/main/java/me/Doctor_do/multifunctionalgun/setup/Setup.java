package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon;
import me.Doctor_do.multifunctionalgun.listener.*;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Advanced_Materials_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Basic_Materials_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Machine_Item_Setup;
import me.Doctor_do.multifunctionalgun.utils.GlowEffectManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Setup {
    public static final JavaPlugin plugin = MultifunctionalGun.getInstance();

    private Setup() {
        // 禁止实例化对象
    }

    public static void SetupAll(Config cfg) {
        // 注册物品流程方法
        Basic_Materials_Item_Setup.Basic_Materials_Item_Register_Setup();
        Advanced_Materials_Item_Setup.Advanced_Materials_Item_Register_Setup();
        Machine_Item_Setup.Machine_Item_Register_Setup();
        Gun_And_Bullet_Item_Setup.Gun_And_Bullet_Item_Register_setup();
        //21,12,5,16

        // 注册研究流程方法
        if (cfg.getBoolean("options.enable-researches")) {
            ResearchSetup.Research_Register_Setup();
        }

        // 最终物品配置
        if (cfg.contains("options.endless-weapon.capacity")) {
            EndlessWeapon.setCAPACITY(cfg.getFloat("options.endless-weapon.capacity"));
        }
        if (cfg.contains("options.endless-weapon.generate")) {
            EndlessWeapon.setGENERATE(cfg.getFloat("options.endless-weapon.generate"));
        }
        if (cfg.contains("options.endless-weapon.cost")) {
            EndlessWeapon.setCOST(cfg.getFloat("options.endless-weapon.cost"));
        }
        if (cfg.contains("options.endless-weapon.multiplier")) {
            EndlessWeapon.setMultiplier(cfg.getFloat("options.endless-weapon.multiplier"));
        }

        // 注册监听器与事件
        // 生物掉落监听器(凋零掉落AGVP)
        new MobDropListener();
        // 玩家动作监听器(玩家使用EndlessWeapon的动作监听)
        new PlayerUseWeaponListener();
        // 使用武器时的监听器和事件处理器(包含子弹和榴弹的效果，以及爆炸效果)
        new WeaponUseHandler();
        // 背包容器处理器(主要针对EndlessWeapon的背包容器)
        new InventoryHandler();
        // 初始化发光效果管理器（使用单例）
        GlowEffectManager.getInstance(plugin);
        // 注册事件监听器
        new LaserSightListener(plugin);
    }
}
