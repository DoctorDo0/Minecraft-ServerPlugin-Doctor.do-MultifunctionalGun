package me.Doctor_do.multifunctionalgun.listener;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Dropper;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;

public class WeaponUseHandler implements Listener {
    Plugin plugin = MultifunctionalGun.getInstance();

    public WeaponUseHandler() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // 来自SlimefunWarfare
    // 子弹击中实体产生的效果
    @SuppressWarnings("all")
    @EventHandler
    public void onEntityBulletHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Projectile bullet)) return;

        Entity shot = e.getEntity();
        if (bullet.hasMetadata("DMG_GunBullet")) {
            if (bullet.getShooter() instanceof Player shooter) {
                if (!Slimefun.getProtectionManager().hasPermission(shooter, shot.getLocation(), Interaction.ATTACK_PLAYER)) {
                    return;
                }
            }
            Location shooterLoc = Utils.deserializeLocation(bullet.getMetadata("locInfo").get(0).asString());
            String[] split = CommonPatterns.COLON.split(bullet.getMetadata("rangeInfo").get(0).asString());
            double distance = shooterLoc.distance(e.getEntity().getLocation());
            if (distance <= Integer.parseInt(split[0]) && distance >= Integer.parseInt(split[1])) {
                e.setDamage(bullet.getMetadata("damage").get(0).asInt());
                switch (bullet.getMetadata("effect").get(0).asString()) {
                    case "fire" ->
                            shot.setFireTicks(e.getEntity().getFireTicks() + bullet.getMetadata("keepTime").get(0).asInt());
                    //TODO:
                    //可扩展其他功能
                }

                if (bullet instanceof ShulkerBullet && shot instanceof LivingEntity) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> ((LivingEntity) shot).removePotionEffect(PotionEffectType.LEVITATION), 1);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    // 来自SlimefunWarfare
    // 子弹击中方块产生的效果
    @EventHandler
    public void onBulletHitBlock(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Entity entity = event.getEntity();

        if (!(entity instanceof ShulkerBullet) || block == null) return;

        if (entity.hasMetadata("DMG_GunBullet")) {
            block.getWorld().createExplosion(block.getLocation(), 1F);
        }
    }

    // 榴弹击中实体产生的效果
    @EventHandler
    public void onEntityGrenadeHit(EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();
        if (!(entity.getType() == EntityType.SNOWBALL) || !entity.hasMetadata("DMG_GunGrenade")) return;
        if (entity.hasMetadata("effect")) {
            try {
                Location loc = event.getEntity().getLocation();
                grenadeEffect(entity, loc);
            } catch (NullPointerException ignored) {
            }
        }
    }

    // 榴弹击中方块产生的效果
    @SuppressWarnings("all")
    @EventHandler
    public void onGrenadeHitBlock(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();

        if (!(projectile instanceof Snowball) || projectile.getType() != EntityType.SNOWBALL || !projectile.hasMetadata("DMG_GunGrenade") || block == null)
            return;

        if (projectile.hasMetadata("effect")) {
            try {
                Location loc = event.getHitBlock().getRelative(event.getHitBlockFace()).getLocation();
                grenadeEffect(projectile, loc);
            } catch (NullPointerException ignored) {
            }
        }
    }

    // 已修改，来自SlimefunWarfare
    // 榴弹爆炸产生的效果
    @SuppressWarnings("all")
    public void grenadeEffect(Entity grenade, Location loc) {
        String effect = grenade.getMetadata("effect").get(0).asString();
        AreaEffectCloud cloud;
        switch (effect) {
            case "normal" -> {
                grenade.getWorld().createExplosion(grenade.getLocation(), grenade.getMetadata("radius").get(0).asFloat());
                explosiveDamageEffect(grenade);
                cloud = (AreaEffectCloud) grenade.getWorld()
                        .spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                cloud.setDuration(grenade.getMetadata("keepTime").get(0).asInt());
                cloud.setDurationOnUse(0);
                cloud.setRadiusOnUse(0);
                cloud.setColor(Color.WHITE);
                cloud.setRadius(0);
            }
            //TODO:
            //可扩展其他功能
        }
    }

    // 已修改，来自粘液科技
    // 爆炸对周围生物造成伤害
    public void explosiveDamageEffect(Entity grenade) {
        double radius = grenade.hasMetadata("radius") ? grenade.getMetadata("radius").get(0).asFloat() : 5;

        for (Entity nearby : grenade.getWorld().getNearbyEntities(grenade.getLocation(), (double) radius, (double) radius, (double) radius, this::canDamage)) {
            LivingEntity entity = (LivingEntity) nearby;
            Vector distanceVector = entity.getLocation().toVector().subtract(grenade.getLocation().toVector()).add(new Vector((double) 0.0F, (double) 0.75F, (double) 0.0F));
            double distanceSquared = distanceVector.lengthSquared();
            double damage = grenade.getMetadata("damage").get(0).asInt() * ((double) 1.0F - distanceSquared / (double) (2 * radius * radius));
            //修复死循环重复触发的bug
            grenade.removeMetadata("DMG_GunGrenade", plugin);
            if (!entity.getUniqueId().equals(grenade.getUniqueId())) {
                EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(grenade, entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, damage);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    distanceVector.setY((double) 0.75F);
                    Vector knockback = distanceVector.normalize().multiply(2);
                    entity.setVelocity(entity.getVelocity().add(knockback));
                    entity.damage(event.getDamage());
                }
            }
        }
    }

    // 来自粘液科技
    // 判断实体是否可被造成伤害
    private boolean canDamage(@Nonnull Entity n) {
        return n instanceof LivingEntity && !(n instanceof ArmorStand) && n.isValid() && !(n instanceof Dropper);
    }
}
