package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.listener.MobDropListener;
import me.Doctor_do.multifunctionalgun.listener.PlayerUseWeaponListener;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Advanced_Materials_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Basic_Materials_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Machine_Item_Setup;
import me.Doctor_do.multifunctionalgun.utils.Events;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class Setup {
    private Setup() {
        // 禁止实例化对象
    }

    public static void SetupAll(Config cfg) {
        // 注册物品流程方法
        Basic_Materials_Item_Setup.Basic_Materials_Item_Register_Setup();
        Advanced_Materials_Item_Setup.Advanced_Materials_Item_Register_Setup();
        Machine_Item_Setup.Machine_Item_Register_Setup();
        Gun_And_Bullet_Item_Setup.Gun_And_Bullet_Item_Register_setup();
        //15,5,12,22

        // 注册研究流程方法
        if (cfg.getBoolean("options.enable-researches")) {
            ResearchSetup.Research_Register_Setup();
        }
        getLogger().info("Hash Code: " + MultifunctionalGun.getInstance().hashCode());
        //1748319447

        // 测试监听器与事件
        new MobDropListener();
        new PlayerUseWeaponListener();
        getServer().getPluginManager().registerEvents(new Events(), MultifunctionalGun.getInstance());
    }
}
