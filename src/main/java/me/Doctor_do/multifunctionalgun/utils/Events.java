package me.Doctor_do.multifunctionalgun.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.items.weapons.EndlessWeapon;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dropper;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;

public class Events implements Listener {
    Plugin plugin = MultifunctionalGun.getInstance();

    // 来自FluffyMachines，监听器，用于取消有不可交互标记的对象的交互事件
    // This is used to make the non clickable GUI items non clickable
    @EventHandler(ignoreCancelled = true)
    public void onNonClickableClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkNonInteractable(item)) {
            e.setCancelled(true);
        }
    }

    // 打开界面时禁止交互
    @SuppressWarnings("all")
    @EventHandler
    public void onOpenInventoryItemClick(InventoryClickEvent event) {
        SlimefunItem sfItem1 = SlimefunItem.getByItem(event.getCurrentItem());
        SlimefunItem sfItem2 = SlimefunItem.getByItem(event.getCursor());
        assert Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName() != null;
        if ((sfItem1 instanceof EndlessWeapon || sfItem2 instanceof EndlessWeapon)
                && event.getWhoClicked().getOpenInventory().getTitle().contains(Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName())
        ) {
            event.setCancelled(true);
            Utils.sendMessage(
                    (Player) event.getWhoClicked(),
                    Gun_And_Bullet.ENDLESS_WEAPON.getDisplayName()
                            + "在打开"
                            + Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName()
                            + "时禁止交互"
            );
        }
    }

    // 用于监听修改提卡模式
    @EventHandler(ignoreCancelled = true)
    public void onChangeTIKAModeClickInteract(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkChangeTIKAModeInteract(item)) {
            event.setCancelled(true);
            Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeTIKAMode(event);
        }
    }

    // 用于监听修改激光瞄准器设置
    @EventHandler(ignoreCancelled = true)
    public void onChangeLaserClickInteract(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkChangeLaserSettingInteract(item)) {
            event.setCancelled(true);

            // 关闭功能分支
            if (Utils.checkChangeLaserSettingCloseInteract(item)) {
                Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, "OFF");
            }
            // 开启功能分支
            if (Utils.checkChangeLaserSettingOpenInteract(item)) {
                Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, "ON");
            }
            // 增加Red功能分支
            if (Utils.checkChangeLaserSettingIncreaseRedInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "add", 256);
                }
            }
            // 减少Red功能分支
            if (Utils.checkChangeLaserSettingDecreaseRedInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 1, "subtract", 256);
                }
            }
            // 增加Green功能分支
            if (Utils.checkChangeLaserSettingIncreaseGreenInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "add", 256);
                }
            }
            // 减少Green功能分支
            if (Utils.checkChangeLaserSettingDecreaseGreenInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 2, "subtract", 256);
                }
            }
            // 增加Blue功能分支
            if (Utils.checkChangeLaserSettingIncreaseBlueInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "add", 256);
                }
            }
            // 减少Blue功能分支
            if (Utils.checkChangeLaserSettingDecreaseBlueInteract(item)) {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 1);
                }
                if (event.isRightClick() && !event.isShiftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 8);
                }
                if (event.isShiftClick() && event.isLeftClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 32);
                }
                if (event.isShiftClick() && event.isRightClick()) {
                    Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeLaser(event, 3, "subtract", 256);
                }
            }
        }
    }

    // 用于监听修改能源模块生产模式
    @EventHandler(ignoreCancelled = true)
    public void onChangeGenerateModeClickInteract(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && Utils.checkChangeGenerateModeInteract(item)) {
            event.setCancelled(true);
            Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().changeGenerateMode(event);
        }
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
    @EventHandler
    public void onBulletHitBlock(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Entity entity = event.getEntity();

        if (!(entity instanceof ShulkerBullet) || block == null) return;

        if (entity.hasMetadata("DMG_GunBullet")) {
            block.getWorld().createExplosion(block.getLocation(), 1F);
        }
    }

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
    @SuppressWarnings("all")
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

    // 激光瞄准器击中实体产生的效果
    @SuppressWarnings("all")
    @EventHandler
    public void onLaserSightHitEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Projectile bullet) || !bullet.hasMetadata("DMG_LaserSight")) return;
        e.setCancelled(true);

        Entity shot = e.getEntity();
        Location shooterLoc = Utils.deserializeLocation(bullet.getMetadata("locInfo").get(0).asString());
        String[] split = CommonPatterns.COLON.split(bullet.getMetadata("rangeInfo").get(0).asString());
        double distance = shooterLoc.distance(e.getEntity().getLocation());
        if (distance <= Integer.parseInt(split[0]) && distance >= Integer.parseInt(split[1])) {
            if (shot instanceof LivingEntity) {
                ((LivingEntity) shot).addPotionEffect(PotionEffectType.GLOWING.createEffect(
                        bullet.getMetadata("keepTime").get(0).asInt(),
                        1
                ));
            }
        } else {
            e.setCancelled(true);
        }
    }
}
