package me.Doctor_do.multifunctionalgun.listener;

import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobDropListener implements Listener {

    private final static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    public MobDropListener(){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void onMobKilled(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            // 检索下列代码含义
//            event.getEntity().getKiller().remove();
//            plugin.getServer().getScheduler().cancelTask(event.getEntity().getKiller().getEntityId());
            Player player = event.getEntity().getKiller();

        }
    }
}
