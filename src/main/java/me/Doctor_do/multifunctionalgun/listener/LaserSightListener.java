package me.Doctor_do.multifunctionalgun.listener;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import me.Doctor_do.multifunctionalgun.utils.GlowEffectManager;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class LaserSightListener implements Listener {
    private final GlowEffectManager glowEffectManager;

    public LaserSightListener(Plugin plugin) {
        this.glowEffectManager = GlowEffectManager.getInstance();
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLaserSightHitEntity(EntityDamageByEntityEvent e) {
        // 检查是否是激光弹射物
        if (!(e.getDamager() instanceof Projectile bullet) || !bullet.hasMetadata("DMG_LaserSight")) {
            return;
        }
        e.setCancelled(true);

        Entity shot = e.getEntity();

        // 获取发射者位置
        Location shooterLoc = Utils.deserializeLocation(bullet.getMetadata("locInfo").get(0).asString());

        // 获取距离范围信息
        String[] split = CommonPatterns.COLON.split(bullet.getMetadata("rangeInfo").get(0).asString());
        double distance = shooterLoc.distance(e.getEntity().getLocation());

        // 获取颜色信息（org.bukkit.Color 类型）
        Color color = Utils.deserializeColor(bullet.getMetadata("color").get(0).asString());

        // 检查距离是否在有效范围内
        if (distance <= Integer.parseInt(split[0]) && distance >= Integer.parseInt(split[1])) {
            if (shot instanceof LivingEntity) {
                int keepTime = bullet.getMetadata("keepTime").get(0).asInt();

                // 应用彩色发光效果和粒子效果
                glowEffectManager.applyGlowEffect((LivingEntity) shot, keepTime, color);
            }
        }
        // 超出范围则什么都不做（已经取消了伤害）
    }
}
