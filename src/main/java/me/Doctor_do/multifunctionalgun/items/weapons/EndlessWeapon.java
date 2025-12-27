package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.items_register.items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.items_register.items_setup.Gun_And_Bullet_Item_Register;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class EndlessWeapon extends SlimefunItem implements NotPlaceable, Rechargeable {
    public static Plugin plugin = MultifunctionalGun.getInstance();

    private final static Float capacity_temp = 20000.0F;

    public static float getMaxItemCharge_Temp() {
        return capacity_temp;
    }

    //    private final float COST = 0.3F;
    private final Float COST = 1.0F;
    private final NamespacedKey EndlessWeapon_Mode = new NamespacedKey(plugin, "ENDLESS_WEAPON_MODE");
    private final List<EndlessWeapon_Mode> modes = new ArrayList<>();
    private final Float capacity;

    public EndlessWeapon(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, float capacity, String... items) {
        super(itemGroup, item, recipeType, recipe);

        for (int i = 0; i < items.length; i++) {
            modes.add(new EndlessWeapon_Mode(this, i, items[i]));
        }

        if (capacity <= 0) {
            this.capacity = capacity_temp;
        } else this.capacity = capacity;

        useableInWorkbench = false;
        hidden = false;
        enchantable = false;
        disenchantable = false;
    }

    public float getMaxItemCharge(ItemStack itemStack) {
        return capacity;
    }

    private int nextIndex(int i) {
        int index = i;
        do {
            index++;
            if (index >= modes.size()) {
                index = 0;
            }
        } while (index != i && !modes.get(index).isEnabled());
        return index;
    }

    private void refreshItemLoreFromMode(ItemStack item, int index) {
        String assault_rifle_and_grenade_launcher_name = Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName();
        String tika_rifle_name = Gun_And_Bullet.TIKA_RIFLE.getDisplayName();
        String light_cone_name = Gun_And_Bullet.LIGHT_CONE.getDisplayName();
        String anti_materiel_sniper_rifle_name = Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName();
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lorelist = itemMeta.getLore();
        String textlore = lorelist.get(4);
        // 判断是否一致，如果不一致，则统一将name替换为%modes%
        if (
                item.getItemMeta().getLore().get(4).contains(assault_rifle_and_grenade_launcher_name)
                        && modes.get(index).getItem().getItemName() != assault_rifle_and_grenade_launcher_name
        ) {
            textlore = textlore.replace(assault_rifle_and_grenade_launcher_name, "%modes%");
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        } else if (
                item.getItemMeta().getLore().get(4).contains(tika_rifle_name)
                        && modes.get(index).getItem().getItemName() != tika_rifle_name
        ) {
            textlore = textlore.replace(tika_rifle_name, "%modes%");
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        } else if (
                item.getItemMeta().getLore().get(4).contains(light_cone_name)
                        && modes.get(index).getItem().getItemName() != light_cone_name
        ) {
            textlore = textlore.replace(light_cone_name, "%modes%");
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        } else if (
                item.getItemMeta().getLore().get(4).contains(anti_materiel_sniper_rifle_name)
                        && modes.get(index).getItem().getItemName() != anti_materiel_sniper_rifle_name
        ) {
            textlore = textlore.replace(anti_materiel_sniper_rifle_name, "%modes%");
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        } else {
            textlore = "§7当前模式: %modes%";
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        }
        // 收尾工作，将%modes%替换为应有的name
        if (item.getItemMeta().getLore().get(4).contains("%modes%")) {
            textlore = textlore.replace("%modes%", modes.get(index).getItem().getItemName());
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        } else {
            textlore = "§7当前模式: %modes%";
            lorelist.set(4, textlore);
            itemMeta.setLore(lorelist);
            item.setItemMeta(itemMeta);
        }
    }

    @Override
    public void preRegister() {
        super.preRegister();
//        BlockUseHandler blockUseHandler = this::onBlockRightClick;
//        addItemHandler(blockUseHandler);

//        ItemUseHandler itemUseHandler_rightClick = this::onItemUseRightClick;
//        addItemHandler(itemUseHandler_rightClick);


//        ItemHandler itemUseHandler_leftClick = this::Test_LeftClickEvent;
//        addItemHandler(itemUseHandler_leftClick);

//        ItemUseHandler itemUseHandler_Left = this::onItemUseLeftClick;
//        addItemHandler(itemUseHandler_Left);
    }

//    private void onBlockRightClick(PlayerRightClickEvent event) {
//        // 这会阻止玩家食用该蛋糕
//        event.cancel();
//        // 现在，设置玩家着火5秒
//        event.getPlayer().setFireTicks(5 * 20);
//    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        // 如果在这里调用 event.cancel() 会阻止玩家放置蛋糕
//        event.getPlayer().giveExpLevels(1);
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        event.cancel();
        // Permission 权限区分
//        if (player.hasPermission("minecraft.admin")) {}
        // MainHand 玩家主手物品类型判断
//        if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {}
        // Sneaking+Initializer 玩家潜行判断+物品模式初始化(?)
//        int index = (Integer)this.selectedMode.getOrDefault(p.getUniqueId(), 0);
//        if (!p.isSneaking()) {
//            if (this.removeItemCharge(item, 0.3F)) {
//                SlimefunItem sfItem = ((MultiToolMode)this.modes.get(index)).getItem();
//                if (sfItem != null) {
//                    sfItem.callItemHandler(ItemUseHandler.class, (handler) -> handler.onRightClick(e));
//                }
//            }
//        } else {
//            index = this.nextIndex(index);
//            SlimefunItem selectedItem = ((MultiToolMode)this.modes.get(index)).getItem();
//            String itemName = selectedItem != null ? selectedItem.getItemName() : "Unknown";
//            Slimefun.getLocalization().sendMessage(p, "messages.multi-tool.mode-change", true, (msg) -> msg.replace("%device%", "Multi Tool").replace("%mode%", ChatColor.stripColor(itemName)));
//            this.selectedMode.put(p.getUniqueId(), index);
//        }

//        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
//            if (player.isSneaking()) {
//                player.sendMessage(ChatColors.color("&7测试:&fGun:&f右键+潜行 正常"));
//            }
//            if (!player.isSneaking()) {
//                player.sendMessage(ChatColors.color("&7测试:&fGun:&f右键+!潜行 正常"));
//            }
//        }
//        if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
//            if (player.isSneaking()) {
//                player.sendMessage(ChatColors.color("&7测试:&fGun:&f左键+潜行 正常"));
//            }
//            if (!player.isSneaking()) {
//                player.sendMessage(ChatColors.color("&7测试:&fGun:&f左键+!潜行 正常"));
//            }
//        }
    }

    public void LeftClickEvent(Player player, PlayerInteractEvent event, ItemStack item) {
        event.setUseInteractedBlock(Event.Result.DENY);
        event.setUseItemInHand(Event.Result.DENY);

        var im = item.getItemMeta();
        var pdc = im.getPersistentDataContainer();
        int index = pdc.getOrDefault(EndlessWeapon_Mode, PersistentDataType.INTEGER, 0);

        if (!player.isSneaking()) {
            refreshItemLoreFromMode(item, index);

            if (modes.get(index).getItem().getItemName().equals(Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
//                SlimefunItem sfItem = modes.get(index).getItem();
                Gun_And_Bullet_Item_Register.getGrenadeLauncherInstance().CauseDamageToEntity(event, GrenadeLauncher.damage_temp);
            }

            if (!modes.get(index).getItem().getItemName().equals(Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
//                SlimefunItem sfItem = modes.get(index).getItem();
                Gun_And_Bullet_Item_Register.getScopeInstance().ScopeUseEvent(event);
                Gun_And_Bullet_Item_Register.getLaserSightInstance().LaserSightUseEvent(event);
            }

        } else {
            refreshItemLoreFromMode(item, index);
            Gun_And_Bullet_Item_Register.getFirearmExpansionBackpackInstance().UseFirearmExpansionBackpack(player);
        }
    }

    public void RightClickEvent(Player player, PlayerInteractEvent event, ItemStack item) {
        event.setUseInteractedBlock(Event.Result.DENY);
        event.setUseItemInHand(Event.Result.DENY);

        var im = item.getItemMeta();
        var pdc = im.getPersistentDataContainer();
        int index = pdc.getOrDefault(EndlessWeapon_Mode, PersistentDataType.INTEGER, 0);

        if (!player.isSneaking()) {
            refreshItemLoreFromMode(item, index);

            if (modes.get(index).getItem().getItemName().equals(Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
//                SlimefunItem sfItem = modes.get(index).getItem();
                Gun_And_Bullet_Item_Register.getAssaultRifleInstance().CauseDamageToEntity(event, GrenadeLauncher.damage_temp);
            }

            if (modes.get(index).getItem().getItemName().equals(Gun_And_Bullet.TIKA_RIFLE.getDisplayName())) {
                if (removeItemCharge(item, COST * 2)) {
//                    SlimefunItem sfItem = modes.get(index).getItem();

//                    if (sfItem != null) {
//                        sfItem.callItemHandler(ItemUseHandler.class, handler -> handler.onRightClick(new PlayerRightClickEvent(event)));
//                    }
                    Gun_And_Bullet_Item_Register.getTIKA_RifleInstance().CauseDamageToEntity(event, TIKA_Rifle.damage_temp * 3);
                } else {
                    Gun_And_Bullet_Item_Register.getTIKA_RifleInstance().CauseDamageToEntity(event, TIKA_Rifle.damage_temp);
                    lowPower(event.getPlayer());
                }
            }

            if (modes.get(index).getItem().getItemName().equals(Gun_And_Bullet.LIGHT_CONE.getDisplayName())) {
                if (removeItemCharge(item, COST * 40)) {
//                    SlimefunItem sfItem = modes.get(index).getItem();

//                    if (sfItem != null) {
//                        sfItem.callItemHandler(ItemUseHandler.class, handler -> handler.onRightClick(new PlayerRightClickEvent(event)));
//                    }
                    Gun_And_Bullet_Item_Register.getLightConeInstance().CauseDamageToEntity(event, LightCone.damage_temp);
                } else {
                    lowPower(event.getPlayer());
                }
            }

            if (modes.get(index).getItem().getItemName().equals(Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName())) {
//                SlimefunItem sfItem = modes.get(index).getItem();
                Gun_And_Bullet_Item_Register.getAntiMaterielSniperRifleInstance().CauseDamageToEntity(event, AntiMaterielSniperRifle.damage_temp);
            }

        } else {
            index = nextIndex(index);
            SlimefunItem selectedItem = modes.get(index).getItem();

            String itemName = selectedItem != null ? selectedItem.getItemName() : "Unknown";
//            Slimefun.getLocalization()
//                    .sendMessage(player, "messages.EndlessWeapon.mode-change", true, msg -> msg.replace("%device%", "多功能工具")
//                            .replace("%mode%", ChatColor.stripColor(itemName)));
            Slimefun.getLocalization()
                    .sendMessage(player, Gun_And_Bullet.ENDLESS_WEAPON.getDisplayName() + " 的模式已切换为: " + itemName, true, msg -> msg.replace("! Missing string \"", "")
                            .replace("\"", ""));

            pdc.set(EndlessWeapon_Mode, PersistentDataType.INTEGER, index);
            item.setItemMeta(im);

            refreshItemLoreFromMode(item, index);
        }
    }

    public void lowPower(Player player) {
        Slimefun.getLocalization().sendMessage(player, "电力不足！");
    }
}
