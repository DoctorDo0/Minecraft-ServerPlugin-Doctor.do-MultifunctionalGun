package me.Doctor_do.multifunctionalgun.listener;

import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.items.items.Anti_Gravity_Void_Particle;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Basic_Materials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.concurrent.ThreadLocalRandom;

public class MobDropListener implements Listener {

    private final static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    public MobDropListener() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onMobKilled(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null && event.getEntity().getType() == Anti_Gravity_Void_Particle.getEntityTypeByMobDrop()) {
//            Player player = event.getEntity().getKiller();
//            var entityType = event.getEntity().getType();
//            double chance = getChance(entityType, event.getEntity().getKiller());
//            EntityType entityType = event.getEntityType();

            for (int i = 0; i < Anti_Gravity_Void_Particle.item_chance_list.size(); i++) {
                Double chance = Anti_Gravity_Void_Particle.item_chance_list.get(i);
                if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE.clone());
                }
            }

//            if (ThreadLocalRandom.current().nextInt(100) < chance) {
//                // 检索下列代码含义
//                event.setDroppedExp(0);
//                event.getDrops().clear();
//                event.getEntity().remove();
//                event.getEntity().getKiller().remove();
//                plugin.getServer().getScheduler().cancelTask(event.getEntity().getKiller().getEntityId());
//                event.getEntity().getKiller().spigot().respawn();
//            }
        }
    }

//    private double getChance(@Nonnull EntityType type, @Nullable Player killer) {
//        return plugin.getConfig().getDouble("chance." + type.name().toLowerCase());
//    }
}
