package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.listener.MobDropListener;
import me.Doctor_do.multifunctionalgun.listener.PlayerUseWeaponListener;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;

public class Setup {
    public Setup(Config cfg) {

        Plugin plugin = MultifunctionalGun.getInstance();
        // 注册物品流程方法
        new ItemsRegister();
        //15,5,12,22

        // 注册研究流程方法
        if (cfg.getBoolean("options.enable-researches")) {
            new ResearchRegister();
        }
        getLogger().info("Hash Code: " + MultifunctionalGun.getInstance().hashCode());
        //1748319447

        // 测试监听器与事件
        new MobDropListener();
        new PlayerUseWeaponListener();
    }
}
