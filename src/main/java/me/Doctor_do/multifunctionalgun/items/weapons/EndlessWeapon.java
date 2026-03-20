package me.Doctor_do.multifunctionalgun.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Auxiliary;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Bullet;
import me.Doctor_do.multifunctionalgun.items.general.ItemType_Gun;
import me.Doctor_do.multifunctionalgun.items.weapons.weapon.*;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Machine_Item_Setup;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class EndlessWeapon extends SlimefunItem implements NotPlaceable, Rechargeable, Listener {
    MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    private static float CAPACITY = 20000.0F;
    private static float GENERATE = 2048.0F;
    private static float COST = 1.0F;

    private static double multiplier = 1.5;

    private ItemStack currentItem;
    private Inventory currentInventory;
    private boolean isLoaded = false;

    private final NamespacedKey EndlessWeapon_Mode_nsk = new NamespacedKey(plugin, "ENDLESS_WEAPON_MODE");
    private final NamespacedKey LAST_USE_nsk = Utils.createKey("Last_Use");
    private long Last_Use = 0;

    private final NamespacedKey RifleBullet_Count_nsk = Utils.createKey("RifleBullet_Count");
    private int RifleBullet_Count = 0;
    private final NamespacedKey RifleBullet_Contain_Count_nsk = Utils.createKey("RifleBullet_Contain_Count");
    private int RifleBullet_Contain_Count = 0;
    private final NamespacedKey Grenade_Count_nsk = Utils.createKey("Grenade_Count");
    private int Grenade_Count = 0;
    private final NamespacedKey Grenade_Contain_Count_nsk = Utils.createKey("Grenade_Contain_Count");
    private int Grenade_Contain_Count = 0;
    private final NamespacedKey SteelBall_Count_nsk = Utils.createKey("SteelBall_Count");
    private int SteelBall_Count = 0;
    private final NamespacedKey SteelBall_Contain_Count_nsk = Utils.createKey("SteelBall_Contain_Count");
    private int SteelBall_Contain_Count = 0;
    private final NamespacedKey BurningSteelBall_Count_nsk = Utils.createKey("BurningSteelBall_Count");
    private int BurningSteelBall_Count = 0;
    private final NamespacedKey BurningSteelBall_Contain_Count_nsk = Utils.createKey("BurningSteelBall_Contain_Count");
    private int BurningSteelBall_Contain_Count = 0;
    private final NamespacedKey SpecialBullet_Count_nsk = Utils.createKey("SpecialBullet_Count");
    private int SpecialBullet_Count = 0;
    private final NamespacedKey SpecialBullet_Contain_Count_nsk = Utils.createKey("SpecialBullet_Contain_Count");
    private int SpecialBullet_Contain_Count = 0;
    private final NamespacedKey EnergyStorageCanFull_Count_nsk = Utils.createKey("EnergyStorageCanFull_Count");
    private int EnergyStorageCanFull_Count = 0;
    private final NamespacedKey EnergyStorageCanFull_Contain_Count_nsk = Utils.createKey("EnergyStorageCanFull_Contain_Count");
    private int EnergyStorageCanFull_Contain_Count = 0;
    private final NamespacedKey EnergyStorageCanEmpty_Count_nsk = Utils.createKey("EnergyStorageCanEmpty_Count");
    private int EnergyStorageCanEmpty_Count = 0;
    private final NamespacedKey EnergyStorageCanEmpty_Contain_Count_nsk = Utils.createKey("EnergyStorageCanEmpty_Contain_Count");
    private int EnergyStorageCanEmpty_Contain_Count = 0;
    private final NamespacedKey TIKA_mode_nsk = Utils.createKey("TIKA_Mode");
    private String TIKA_mode = Gun_And_Bullet.STEEL_BALL.getDisplayName();
    private final NamespacedKey Laser_state_nsk = Utils.createKey("Laser_state");
    private String Laser_state = "OFF";
    private final NamespacedKey Laser_color_nsk = Utils.createKey("Laser_color");
    private String Laser_color = "#FF_FF_FF";
    private final NamespacedKey Power_generate_mode_nsk = Utils.createKey("Power_generate_mode");
    private String Power_generate_mode = "ON";

    private int Global_LASERSIGHT_INFO_SLOT;

    public EndlessWeapon(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        useableInWorkbench = false;
        hidden = false;
        enchantable = false;
        disenchantable = false;
    }

    // 返回电容量
    public float getMaxItemCharge(ItemStack itemStack) {
        return CAPACITY;
    }

    public static float getCAPACITY() {
        return CAPACITY;
    }

    public static double getMultiplier() {
        return multiplier;
    }

    public static void setCAPACITY(float CAPACITY) {
        if (CAPACITY <= 100.0F) {
            CAPACITY = 100.0F;
        }
        EndlessWeapon.CAPACITY = CAPACITY;
    }

    public static void setGENERATE(float GENERATE) {
        if (GENERATE <= 0.0F) {
            GENERATE = 0.0F;
        }
        EndlessWeapon.GENERATE = GENERATE;
    }

    public static void setCOST(float COST) {
        if (COST <= 0.0F) {
            COST = 0.0F;
        }
        EndlessWeapon.COST = COST;
    }

    public static void setMultiplier(double multiplier) {
        if (multiplier <= 1.0F) {
            multiplier = 1.0F;
        }
        EndlessWeapon.multiplier = multiplier;
    }

    // 循环模式
    private int nextIndex(int i) {
        int index = i;
        do {
            index++;
            if (index >= EndlessWeapon_Mode.getCount()) {
                index = 0;
            }
        } while (index != i && !EndlessWeapon_Mode.get(index).isEnabled());
        return index;
    }

    // 跟随mode，刷新lore中的模式字段
    @SuppressWarnings("all")
    private void refreshItemLoreFromMode(ItemStack item, int index) {
        // 操作修改模式信息
        ItemMeta itemMeta = item.getItemMeta();
        List<String> loreList = itemMeta.getLore();
        // 取得包含模式信息的字符串位置
        int line_index = 0;
        for (String lore : loreList) {
            if (lore.contains("§7当前模式: ")) {
                line_index = loreList.indexOf(lore);
            }
        }
        String listText = loreList.get(line_index);
        String modeName = EndlessWeapon_Mode.get(index).getModeName();
        // 判断是否一致，如果不一致，则统一将name替换为%modes%
        for (int i = 0; i < EndlessWeapon_Mode.getCount(); i++) {
            if (listText.contains(EndlessWeapon_Mode.get(i).getModeName()) && !modeName.equals(EndlessWeapon_Mode.get(i).getModeName())) {
                listText = listText.replace(EndlessWeapon_Mode.get(i).getModeName(), "%modes%");
            }
            // 保险措施
            if (i == EndlessWeapon_Mode.getCount() - 1 && !listText.contains("%modes%")) {
                listText = "§7当前模式: %modes%";
            }
        }
        // 收尾工作，将%modes%替换为应有的name
        if (listText.contains("%modes%")) {
            listText = listText.replace("%modes%", modeName);
        } else {
            listText = "§7当前模式: %modes%";
        }
        loreList.set(line_index, listText);
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
    }

    // 按输入字符串内容进行匹配，将输入对象赋值给pdc，修改lore和pdc，再修改物品
    @SuppressWarnings("all")
    private void refreshItemLoreAndPDC(ItemStack item, NamespacedKey type, Object value) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        List<String> loreList = meta.getLore();
        int line_index = 0;

        // type = LAST_USE_nsk
        if (Objects.equals(type, LAST_USE_nsk)) {
            // 设置pdc
            Last_Use = (long) value;
            pdc.set(LAST_USE_nsk, PersistentDataType.LONG, Last_Use);
        }

        // type = RifleBullet_Count_nsk || type = Grenade_Count_nsk || type = RifleBullet_Contain_Count_nsk || type = Grenade_Contain_Count_nsk
        if (Objects.equals(type, RifleBullet_Count_nsk)
                || Objects.equals(type, Grenade_Count_nsk)
                || Objects.equals(type, RifleBullet_Contain_Count_nsk)
                || Objects.equals(type, Grenade_Contain_Count_nsk)) {
            // 取得包含模式信息的字符串位置
            for (String lore : loreList) {
                if (lore.contains(Gun_And_Bullet.RIFLE_BULLET.getDisplayName() + "/" + Gun_And_Bullet.GRENADE.getDisplayName())) {
                    line_index = loreList.indexOf(lore);
                }
            }
            // 设置值
            if (Objects.equals(type, RifleBullet_Count_nsk)) {
                RifleBullet_Count = (int) value;
            }
            if (Objects.equals(type, Grenade_Count_nsk)) {
                Grenade_Count = (int) value;
            }
            if (Objects.equals(type, RifleBullet_Contain_Count_nsk)) {
                RifleBullet_Contain_Count = (int) value;
            }
            if (Objects.equals(type, Grenade_Contain_Count_nsk)) {
                Grenade_Contain_Count = (int) value;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) value);
            // 设置lore
            String listText = "    " + Gun_And_Bullet.RIFLE_BULLET.getDisplayName() + "/" + Gun_And_Bullet.GRENADE.getDisplayName()
                    + " §7> " + RifleBullet_Count + "/" + RifleBullet_Contain_Count + " : " + Grenade_Count + "/" + Grenade_Contain_Count;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }

        // type = SteelBall_Count_nsk || type = BurningSteelBall_Count_nsk || type = SteelBall_Contain_Count_nsk || type = BurningSteelBall_Contain_Count_nsk
        if (Objects.equals(type, SteelBall_Count_nsk)
                || Objects.equals(type, BurningSteelBall_Count_nsk)
                || Objects.equals(type, SteelBall_Contain_Count_nsk)
                || Objects.equals(type, BurningSteelBall_Contain_Count_nsk)) {
            // 取得包含模式信息的字符串位置
            for (String lore : loreList) {
                if (lore.contains(Gun_And_Bullet.STEEL_BALL.getDisplayName() + "/" + Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName())) {
                    line_index = loreList.indexOf(lore);
                }
            }
            // 设置值
            if (Objects.equals(type, SteelBall_Count_nsk)) {
                SteelBall_Count = (int) value;
            }
            if (Objects.equals(type, BurningSteelBall_Count_nsk)) {
                BurningSteelBall_Count = (int) value;
            }
            if (Objects.equals(type, SteelBall_Contain_Count_nsk)) {
                SteelBall_Contain_Count = (int) value;
            }
            if (Objects.equals(type, BurningSteelBall_Contain_Count_nsk)) {
                BurningSteelBall_Contain_Count = (int) value;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) value);
            // 设置lore
            String listText = "    " + Gun_And_Bullet.STEEL_BALL.getDisplayName() + "/" + Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName()
                    + " §7> " + SteelBall_Count + "/" + SteelBall_Contain_Count + " : " + BurningSteelBall_Count + "/" + BurningSteelBall_Contain_Count;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }

        // type = SpecialBullet || type = SpecialBullet_Contain_Count_nsk
        if (Objects.equals(type, SpecialBullet_Count_nsk)
                || Objects.equals(type, SpecialBullet_Contain_Count_nsk)) {
            // 取得包含模式信息的字符串位置
            for (String lore : loreList) {
                if (lore.contains(Gun_And_Bullet.SPECIAL_BULLET.getDisplayName())) {
                    line_index = loreList.indexOf(lore);
                }
            }
            // 设置值
            if (Objects.equals(type, SpecialBullet_Count_nsk)) {
                SpecialBullet_Count = (int) value;
            }
            if (Objects.equals(type, SpecialBullet_Contain_Count_nsk)) {
                SpecialBullet_Contain_Count = (int) value;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) value);
            // 设置lore
            String listText = "    " + Gun_And_Bullet.SPECIAL_BULLET.getDisplayName() + " §7: " + SpecialBullet_Count + "/" + SpecialBullet_Contain_Count;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }

        // type = EnergyStorageCanFull_Count_nsk || type = EnergyStorageCanEmpty_Count_nsk || type = EnergyStorageCanFull_Contain_Count_nsk || type = EnergyStorageCanEmpty_Contain_Count_nsk
        if (Objects.equals(type, EnergyStorageCanFull_Count_nsk)
                || Objects.equals(type, EnergyStorageCanEmpty_Count_nsk)
                || Objects.equals(type, EnergyStorageCanFull_Contain_Count_nsk)
                || Objects.equals(type, EnergyStorageCanEmpty_Contain_Count_nsk)) {
            // 取得包含模式信息的字符串位置
            for (String lore : loreList) {
                if (lore.contains(Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "/" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName())) {
                    line_index = loreList.indexOf(lore);
                }
            }
            // 设置pdc
            if (Objects.equals(type, EnergyStorageCanFull_Count_nsk)) {
                EnergyStorageCanFull_Count = (int) value;
            }
            if (Objects.equals(type, EnergyStorageCanEmpty_Count_nsk)) {
                EnergyStorageCanEmpty_Count = (int) value;
            }
            if (Objects.equals(type, EnergyStorageCanFull_Contain_Count_nsk)) {
                EnergyStorageCanFull_Contain_Count = (int) value;
            }
            if (Objects.equals(type, EnergyStorageCanEmpty_Contain_Count_nsk)) {
                EnergyStorageCanEmpty_Contain_Count = (int) value;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) value);
            // 设置lore
            String listText = "    " + Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "/" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName()
                    + " §7> " + EnergyStorageCanFull_Count + "/" + EnergyStorageCanFull_Contain_Count + " : " + EnergyStorageCanEmpty_Count + "/" + EnergyStorageCanEmpty_Contain_Count;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }

        // type = TIKA_mode_nsk
        if (Objects.equals(type, TIKA_mode_nsk)) {
            // 取得包含模式信息的字符串位置
            for (String lore : loreList) {
                if (lore.contains("提卡模式")) {
                    line_index = loreList.indexOf(lore);
                }
            }
            // 设置pdc与lore
            TIKA_mode = (String) value;
            pdc.set(TIKA_mode_nsk, PersistentDataType.STRING, TIKA_mode);
            String listText = "§7提卡模式: " + TIKA_mode;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }

        // type = Laser_state_nsk || type = Laser_color_nsk
        if (Objects.equals(type, Laser_state_nsk) || Objects.equals(type, Laser_color_nsk)) {
            // 取得包含模式信息的字符串位置
            for (String lore : loreList) {
                if (lore.contains("激光指示器与显示")) {
                    line_index = loreList.indexOf(lore);
                }
            }
            // 设置pdc与lore
            if (Objects.equals(type, Laser_state_nsk)) {
                Laser_state = (String) value;
                pdc.set(Laser_state_nsk, PersistentDataType.STRING, Laser_state);
            }
            if (Objects.equals(type, Laser_color_nsk)) {
                Laser_color = (String) value;
                pdc.set(Laser_color_nsk, PersistentDataType.STRING, Laser_color);
            }
            String listText = "§7激光指示器与显示: " + Laser_state + "§7 - " + Laser_color;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }

        // type = Power_generate_mode_nsk
        if (Objects.equals(type, Power_generate_mode_nsk)) {
            // 设置pdc与lore
            Power_generate_mode = (String) value;
            pdc.set(Power_generate_mode_nsk, PersistentDataType.STRING, Power_generate_mode);
            meta.setLore(loreList);
        }
        // 修改物品操作
        item.setItemMeta(meta);
    }

    // 按输入物品的电量与pdc进行计算，触发不同效果，进行修改物品操作，修改lore和pdc，修改电量，或输出提示
    private void refreshAndCheckEnergy(Player player, ItemStack item) {
        if (Objects.equals(Power_generate_mode, "OFF")) {
            return;
        }
        float delta = getMaxItemCharge(item) - getItemCharge(item);
        if (delta < GENERATE) {
            return;
        }
        int needCount = (int) (delta / GENERATE);
        int realCount;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        // 获取当前存储值
        EnergyStorageCanFull_Count = pdc.getOrDefault(EnergyStorageCanFull_Count_nsk, PersistentDataType.INTEGER, 0);
        EnergyStorageCanEmpty_Count = pdc.getOrDefault(EnergyStorageCanEmpty_Count_nsk, PersistentDataType.INTEGER, 0);
        // 判断应该消耗的数量并计算
        realCount = Math.min(needCount, EnergyStorageCanFull_Count);
        realCount = Math.min(realCount, EnergyStorageCanEmpty_Contain_Count - EnergyStorageCanEmpty_Count);
        // 满罐数量为空
        if (EnergyStorageCanFull_Count == 0) {
            Utils.sendMessage(player, Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "已耗尽");
            return;
        }
        // 空罐数量为满
        if (EnergyStorageCanEmpty_Count == EnergyStorageCanEmpty_Contain_Count) {
            Utils.sendMessage(player, Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName() + "已满");
            return;
        }
        // 正常情况
        if (EnergyStorageCanFull_Count > 0 && EnergyStorageCanEmpty_Count < EnergyStorageCanEmpty_Contain_Count) {
            // 数量操作
            EnergyStorageCanFull_Count = EnergyStorageCanFull_Count - realCount;
            EnergyStorageCanEmpty_Count = EnergyStorageCanEmpty_Count + realCount;
            // lore与pdc更新
            refreshItemLoreAndPDC(item, EnergyStorageCanFull_Count_nsk, EnergyStorageCanFull_Count);
            refreshItemLoreAndPDC(item, EnergyStorageCanEmpty_Count_nsk, EnergyStorageCanEmpty_Count);
            // 增加物品电量
            addItemCharge(item, realCount * GENERATE);
        }
    }

    //TODO:
//    @Override
//    public void preRegister() {
//        super.preRegister();
//        //ItemUseHandler itemUseHandler_rightClick = this::onItemUseRightClick;
//        //addItemHandler(itemUseHandler_rightClick);
//    }

//    private void onBlockRightClick(PlayerRightClickEvent event) {
//        // 这会阻止玩家食用该蛋糕
//        event.cancel();
//        // 现在，设置玩家着火5秒
//        event.getPlayer().setFireTicks(5 * 20);
//    }

//    private void onItemUseRightClick(PlayerRightClickEvent event) {
//        // 如果在这里调用 event.cancel() 会阻止玩家放置蛋糕
//        event.getPlayer().giveExpLevels(1);
//        Player player = event.getPlayer();
//        ItemStack item = event.getItem();
//        //event.cancel();
//        // 同一个物品栈
//        ItemStack item1 = player.getInventory().getItemInMainHand();
//        ItemStack item2 = event.getItem();
//        ItemStack item3 = event.getPlayer().getInventory().getItemInMainHand();
//        // Permission 权限区分
//        if (player.hasPermission("minecraft.admin")) {}
//        // MainHand 玩家主手物品类型判断
//        if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {}
//        // Sneaking+Initializer 玩家潜行判断+物品模式初始化(?)
//        int index = (Integer)this.selectedMode.getOrDefault(p.getUniqueId(), 0);
//        // 来自slimefun原版的多功能工具的逻辑
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

//        // 最初始的判断逻辑
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
//    }

    // 左键点击事件
    @SuppressWarnings("all")
    public void LeftClickEvent(Player player, PlayerInteractEvent event, ItemStack item) {
        currentItem = item;

        event.setUseInteractedBlock(Event.Result.DENY);
        event.setUseItemInHand(Event.Result.DENY);

        var im = item.getItemMeta();
        var pdc = im.getPersistentDataContainer();
        int index = pdc.getOrDefault(EndlessWeapon_Mode_nsk, PersistentDataType.INTEGER, 0);

        // 尝试更新电量
        refreshAndCheckEnergy(player, item);

        // 判断是否已锁
        if (isLoaded) {
            Utils.sendMessage(player, ChatColor.GRAY + "等待装载中...");
            return;
        }

        // 仅左键，使用副武器
        if (!player.isSneaking()) {
            refreshItemLoreFromMode(item, index);
            SlimefunItem secondaryItem = EndlessWeapon_Mode.get(index).getSecondaryItem();

            // 对象类型为ItemType_Gun类型
            if (secondaryItem instanceof ItemType_Gun) {
                GunEventChain(player, secondaryItem, pdc);
            }

            // 对象类型为ItemType_Auxiliary类型
            if (secondaryItem instanceof ItemType_Auxiliary) {
                AuxEventChain(player, secondaryItem);
            }
        }

        // shift+左键，打开背包
        else {
            refreshItemLoreFromMode(item, index);
            openFirearmExpansionBackpack(player, item);
        }
    }

    // 右键点击事件
    @SuppressWarnings("all")
    public void RightClickEvent(Player player, PlayerInteractEvent event, ItemStack item) {
        currentItem = item;

        event.setUseInteractedBlock(Event.Result.DENY);
        event.setUseItemInHand(Event.Result.DENY);

        var im = item.getItemMeta();
        var pdc = im.getPersistentDataContainer();
        int index = pdc.getOrDefault(EndlessWeapon_Mode_nsk, PersistentDataType.INTEGER, 0);

        // 尝试更新电量
        refreshAndCheckEnergy(player, item);

        // 判断是否已锁
        if (isLoaded) {
            Utils.sendMessage(player, ChatColor.GRAY + "等待装载中...");
            return;
        }

        // 仅右键，使用主武器
        if (!player.isSneaking()) {
            refreshItemLoreFromMode(item, index);
            SlimefunItem primaryItem = EndlessWeapon_Mode.get(index).getPrimaryItem();

            // 对象类型为ItemType_Gun类型
            if (primaryItem instanceof ItemType_Gun) {
                GunEventChain(player, primaryItem, pdc);
            }

            // 对象类型为ItemType_Auxiliary类型
            if (primaryItem instanceof ItemType_Auxiliary) {
                AuxEventChain(player, primaryItem);
            }

//            SlimefunItem sfItem = modes.get(index).getItem();
//
//            if (sfItem != null) {
//                sfItem.callItemHandler(ItemUseHandler.class, handler -> handler.onRightClick(new PlayerRightClickEvent(event)));
//            }
        }

        // shift+右键，切换模式
        else {
            index = nextIndex(index);
            String itemName = EndlessWeapon_Mode.get(index).getModeName() != null ? EndlessWeapon_Mode.get(index).getModeName() : "Unknown";
//            Slimefun.getLocalization()
//                    .sendMessage(p, "messages.multi-tool.mode-change", true, msg -> msg.replace("%device%", "多功能工具")
//                            .replace("%mode%", ChatColor.stripColor(itemName)));
            Slimefun.getLocalization()
                    .sendMessage(player, Gun_And_Bullet.ENDLESS_WEAPON.getDisplayName() + " 的模式已切换为: " + itemName, true, msg -> msg.replace("! Missing string \"", "")
                            .replace("\"", ""));

            pdc.set(EndlessWeapon_Mode_nsk, PersistentDataType.INTEGER, index);
            item.setItemMeta(im);

            refreshItemLoreFromMode(item, index);
        }
    }

    public void GunEventChain(Player player, SlimefunItem tools, PersistentDataContainer pdc) {
        ItemType_Gun gun = (ItemType_Gun) tools;

        Last_Use = pdc.getOrDefault(LAST_USE_nsk, PersistentDataType.LONG, 0L);
        long currentTime = System.currentTimeMillis();
        if ((currentTime - Last_Use) < gun.getCooldown() * 1000) {
            player.sendMessage(ChatColor.YELLOW + "换弹中!");
            return;
        }
        Last_Use = currentTime;
        refreshItemLoreAndPDC(currentItem, LAST_USE_nsk, Last_Use);

        // 武器为步枪
        if (Objects.equals(gun, Gun_And_Bullet_Item_Setup.AssaultRifle)) {
            RifleBullet_Count = pdc.getOrDefault(RifleBullet_Count_nsk, PersistentDataType.INTEGER, 0);

            if (RifleBullet_Count >= 1) {
                refreshItemLoreAndPDC(currentItem, RifleBullet_Count_nsk, RifleBullet_Count - 1);
                Gun_And_Bullet_Item_Setup.getAssaultRifleInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.RifleBullet, AssaultRifle.multiplier * multiplier);
            } else {
                Utils.sendMessage(player, Gun_And_Bullet.RIFLE_BULLET.getDisplayName() + ChatColor.RED + "已耗尽");
            }
        }

        // 武器为榴弹
        if (Objects.equals(gun, Gun_And_Bullet_Item_Setup.GrenadeLauncher)) {
            Grenade_Count = pdc.getOrDefault(Grenade_Count_nsk, PersistentDataType.INTEGER, 0);

            if (Grenade_Count >= 1) {
                refreshItemLoreAndPDC(currentItem, Grenade_Count_nsk, Grenade_Count - 1);
                Gun_And_Bullet_Item_Setup.getGrenadeLauncherInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.Grenade, GrenadeLauncher.multiplier * multiplier);
            } else {
                Utils.sendMessage(player, Gun_And_Bullet.GRENADE.getDisplayName() + ChatColor.RED + "已耗尽");
            }
        }

        // 武器为TIKA步枪
        if (Objects.equals(gun, Gun_And_Bullet_Item_Setup.TIKA_Rifle)) {
            // 获取当前提卡模式
            TIKA_mode = pdc.getOrDefault(TIKA_mode_nsk, PersistentDataType.STRING, Objects.requireNonNull(Gun_And_Bullet.STEEL_BALL.getDisplayName()));
            // 钢珠模式
            if (Objects.equals(TIKA_mode, Gun_And_Bullet.STEEL_BALL.getDisplayName())) {
                SteelBall_Count = pdc.getOrDefault(SteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
                if (SteelBall_Count >= 1) {
                    refreshItemLoreAndPDC(currentItem, SteelBall_Count_nsk, SteelBall_Count - 1);
                    // 有电状态
                    if (removeItemCharge(currentItem, COST * 2)) {
                        Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.SteelBall, TIKA_Rifle.multiplier * multiplier * 3);
                    }
                    // 无电状态
                    else {
                        Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.SteelBall, TIKA_Rifle.multiplier * multiplier);
                    }
                } else {
                    Utils.sendMessage(player, Gun_And_Bullet.STEEL_BALL.getDisplayName() + ChatColor.RED + "已耗尽");
                }
            }
            // 燃烧弹模式
            if (Objects.equals(TIKA_mode, Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName())) {
                BurningSteelBall_Count = pdc.getOrDefault(BurningSteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
                if (BurningSteelBall_Count >= 1) {
                    refreshItemLoreAndPDC(currentItem, BurningSteelBall_Count_nsk, BurningSteelBall_Count - 1);
                    // 有电状态
                    if (removeItemCharge(currentItem, COST * 2)) {
                        Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.BurningSteelBall, TIKA_Rifle.multiplier * multiplier * 3);
                    }
                    // 无电状态
                    else {
                        Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.BurningSteelBall, TIKA_Rifle.multiplier * multiplier);
                    }
                } else {
                    Utils.sendMessage(player, Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName() + ChatColor.RED + "已耗尽");
                }
            }
        }

        // 武器为光锥
        if (Objects.equals(gun, Gun_And_Bullet_Item_Setup.LightCone)) {
            if (removeItemCharge(currentItem, COST * 40)) {
                Gun_And_Bullet_Item_Setup.getLightConeInstance().shoot(player, LightCone.multiplier * multiplier);
            }
        }

        // 武器为反器材狙击步枪
        if (Objects.equals(gun, Gun_And_Bullet_Item_Setup.AntiMaterielSniperRifle)) {
            SpecialBullet_Count = pdc.getOrDefault(SpecialBullet_Count_nsk, PersistentDataType.INTEGER, 0);

            if (SpecialBullet_Count >= 1) {
                refreshItemLoreAndPDC(currentItem, SpecialBullet_Count_nsk, SpecialBullet_Count - 1);
                Gun_And_Bullet_Item_Setup.getAntiMaterielSniperRifleInstance().shoot(player, (ItemType_Bullet) Gun_And_Bullet_Item_Setup.SpecialBullet, AntiMaterielSniperRifle.multiplier * multiplier);
            } else {
                Utils.sendMessage(player, Gun_And_Bullet.SPECIAL_BULLET.getDisplayName() + ChatColor.RED + "已耗尽");
            }
        }
    }

    public void AuxEventChain(Player player, SlimefunItem tools) {
        ItemType_Auxiliary aux = (ItemType_Auxiliary) tools;

        // 辅助为瞄准镜
        if (Objects.equals(aux, Gun_And_Bullet_Item_Setup.Scope)) {
            Gun_And_Bullet_Item_Setup.getScopeInstance().effect(player);
        }

        // 辅助为激光瞄准器
        if (Objects.equals(aux, Gun_And_Bullet_Item_Setup.LaserSight)) {
            Gun_And_Bullet_Item_Setup.getLaserSightInstance().effect(player);
        }
    }

    // 打开背包界面功能
    @SuppressWarnings("all")
    public void openFirearmExpansionBackpack(Player player, ItemStack item) {
        isLoaded = true;

        // Get variables
        final ItemMeta meta = item.getItemMeta();
        final Rechargeable charger = (Rechargeable) SlimefunItem.getByItem(item);
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();

        // Inventory Config
        //大小
        final int INVENTORY_SIZE = 54;
        //分割边界位置
        final int[] INSTRUCTIONS = {32};
        final int[] BORDER_YELLOW = {};
        final int[] BORDER_RED = {};
        final int[] BORDER_GREEN = {};
        final int[] BORDER_ORANGE = {};
        final int[] BORDER_BLACK = {3, 5, 14, 21, 23, 27, 28, 29, 31, 33, 34, 35, 39, 41, 43, 48, 50};
        final int[] BORDER_BLUE = {};
        final int[] BORDER_PURPLE = {};
        //特殊功能位置
        final int TIKA_MODE_SLOT = 12;
        final int LASERSIGHT_INFO_SLOT = 25;
        final int LASERSIGHT_OFF_SLOT = 24;
        final int LASERSIGHT_ON_SLOT = 26;
        final int LASERSIGHT_RED_INCREASE_SLOT = 6;
        final int LASERSIGHT_RED_DECREASE_SLOT = 15;
        final int LASERSIGHT_GREEN_INCREASE_SLOT = 7;
        final int LASERSIGHT_GREEN_DECREASE_SLOT = 16;
        final int LASERSIGHT_BLUE_INCREASE_SLOT = 8;
        final int LASERSIGHT_BLUE_DECREASE_SLOT = 17;
        final int POWER_INFO_SLOT = 52;
        //物品放置位置
        final int[] ItemStack_RIFLE_BULLET_SLOT = {36, 37, 38, 45, 46, 47};
        final int[] ItemStack_GRENADE_SLOT = {30};
        final int[] ItemStack_STEEL_BALL_SLOT = {0, 1, 2, 9, 10, 11, 18, 19, 20};
        final int[] ItemStack_BURNING_STEEL_BALL_SLOT = {4, 13, 22};
        final int[] ItemStack_SPECIAL_BULLET_SLOT = {40, 49};
        final int[] ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT = {42, 51};
        final int[] ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT = {44, 53};
        //全局参数初始化
        Global_LASERSIGHT_INFO_SLOT = LASERSIGHT_INFO_SLOT;

        Inventory inventory;
        if (currentInventory == null) {
            // Create GUI Items
            inventory = Bukkit.createInventory(null, INVENTORY_SIZE, "" + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName());
            currentInventory = inventory;
        } else {
            inventory = currentInventory;
        }

        //分割线
        ItemStack backgroundItem = new ItemStack(Material.AIR);
        ItemStack instructions_Item = Utils.buildNonInteractable(
                Material.BEACON,
                "&e使用说明",
                "&7&l&n使用前建议先仔细阅读本说明",
                "&7&n→ 第一次合成该物品时，建议先打开背包后再关闭背包界面，等待数据保存，所有描述变为正常文字后即为保存成功",
                "&7→ 本界面为背包界面，装填弹药、能源控制、激光瞄准器设置，都在本界面内进行操作",
                "&7→ 左下角空区放置" + Gun_And_Bullet.RIFLE_BULLET.getDisplayName() + "&7中间偏左的一个空位放置" + Gun_And_Bullet.GRENADE.getDisplayName(),
                "&7→ 左上角空区为" + Gun_And_Bullet.TIKA_RIFLE.getDisplayName() + "&7专用区域",
                "&7→ 其中左侧放置" + Gun_And_Bullet.STEEL_BALL.getDisplayName() + "&7，右侧放置" + Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName() + "&7，中间用于切换当前模式",
                "&7→ 下方的两个空位为" + Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName() + "&7专用区域，可放置" + Gun_And_Bullet.SPECIAL_BULLET.getDisplayName(),
                "&7→ 右下方为能源区域，包含物品区域与电力信息。当能源不足时再次使用将自动消耗物资并转换为电力能源，也可将该功能关闭",
                "&7→ 能源区域可放置" + Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "&7，或拿取" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName(),
                "&7→ 右上方为" + Gun_And_Bullet.LASER_SIGHT.getDisplayName() + "&7操作区域，包含开关与颜色设置功能，颜色设置与&cR&aG&9B&7设置相似，选择不同的&e功能按钮&7及&e按键方式&7以对某项属性值进行修改"
        );
        ItemStack border_yellow_Item = Utils.buildNonInteractable(
                Material.YELLOW_STAINED_GLASS_PANE,
                "&e分割线",
                "&e←←←左侧放置" + Gun_And_Bullet.RIFLE_BULLET.getDisplayName(),
                "&e右侧放置" + Gun_And_Bullet.GRENADE.getDisplayName() + "&e→→→"
        );
        ItemStack border_red_Item = Utils.buildNonInteractable(
                Material.RED_STAINED_GLASS_PANE,
                "&a分割线",
                "&e↑↑↑↑↑↑↑↑↑",
                "&e上方放置" + Gun_And_Bullet.RIFLE_BULLET.getDisplayName()
        );
        ItemStack border_green_Item = Utils.buildNonInteractable(
                Material.LIME_STAINED_GLASS_PANE,
                "&b分割线",
                "&e↑↑↑↑↑↑↑↑↑",
                "&e上方放置" + Gun_And_Bullet.GRENADE.getDisplayName()
        );
        ItemStack border_orange_Item = Utils.buildNonInteractable(
                Material.ORANGE_STAINED_GLASS_PANE,
                "&c分割线",
                "&e←←←左侧放置" + Gun_And_Bullet.STEEL_BALL.getDisplayName(),
                "&e右侧放置" + Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName() + "→→→"
        );
        ItemStack border_black_Item = Utils.buildNonInteractable(
                Material.BLACK_STAINED_GLASS_PANE,
                "&8分割线"
        );
        ItemStack border_blue_Item = Utils.buildNonInteractable(
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                "&2分割线",
                "&e←←←左侧放置" + Gun_And_Bullet.SPECIAL_BULLET.getDisplayName(),
                "&e右侧为" + Gun_And_Bullet.LASER_SIGHT.getDisplayName() + "&e专用区域" + "&e→→→"
        );
        ItemStack border_purple_Item = Utils.buildNonInteractable(
                Material.PURPLE_STAINED_GLASS_PANE,
                "&4分割线",
                "&e↓↓↓↓↓↓↓↓↓",
                "&e下方为" + Machine.MASS_ENERGY_ENGINE_GENERATOR.getDisplayName() + "&e专用区域"
        );

        // 功能物品，读取pdc数据，并置入展示物品中
        TIKA_mode = pdc.getOrDefault(TIKA_mode_nsk, PersistentDataType.STRING, Gun_And_Bullet.STEEL_BALL.getDisplayName());
        ItemStack TIKA_modeItem = Utils.buildChangeTIKAMode(
                Material.ITEM_FRAME,
                "&e切换模式",
                "&7点击切换 " + Gun_And_Bullet.STEEL_BALL.getDisplayName() + " 与 " + Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName() + " 模式",
                "&e当前选中类型:" + TIKA_mode
        );
        ItemStack LaserSight_Info_Item = Utils.buildEnchantGlow(
                Material.TINTED_GLASS,
                "&f激光瞄准器状态",
                "&fState: " + Laser_state,
                "&fColor: " + Laser_color
        );
        ItemStack LaserSight_Off_Item = Utils.buildChangeLaser(
                "off",
                Material.BLACK_STAINED_GLASS,
                "&f关闭",
                "&7点击触发"
        );
        ItemStack LaserSight_On_Item = Utils.buildChangeLaser(
                "on",
                Material.WHITE_STAINED_GLASS,
                "&f开启",
                "&7点击触发"
        );
        ItemStack LaserSight_Red_Increase_Item = Utils.buildChangeLaser(
                "increase-red",
                Material.RED_WOOL,
                "&f增加&cR&f值",
                "&e左键&7 +1",
                "&e右键&7 +8",
                "&7按住 &eShift + 左键&7 +32",
                "&7按住 &eShift + 右键&7 +256"
        );
        ItemStack LaserSight_Red_Decrease_Item = Utils.buildChangeLaser(
                "decrease-red",
                Material.BLACK_WOOL,
                "&f减少&cR&f值",
                "&e左键&7 -1",
                "&e右键&7 -8",
                "&7按住 &eShift + 左键&7 -32",
                "&7按住 &eShift + 右键&7 -256"
        );
        ItemStack LaserSight_Green_Increase_Item = Utils.buildChangeLaser(
                "increase-green",
                Material.LIME_WOOL,
                "&f增加&aG&f值",
                "&e左键&7 +1",
                "&e右键&7 +8",
                "&7按住 &eShift + 左键&7 +32",
                "&7按住 &eShift + 右键&7 +256"
        );
        ItemStack LaserSight_Green_Decrease_Item = Utils.buildChangeLaser(
                "decrease-green",
                Material.BLACK_WOOL,
                "&f减少&aG&f值",
                "&e左键&7 -1",
                "&e右键&7 -8",
                "&7按住 &eShift + 左键&7 -32",
                "&7按住 &eShift + 右键&7 -256"
        );
        ItemStack LaserSight_Blue_Increase_Item = Utils.buildChangeLaser(
                "increase-blue",
                Material.BLUE_WOOL,
                "&f增加&9B&f值",
                "&e左键&7 +1",
                "&e右键&7 +8",
                "&7按住 &eShift + 左键&7 +32",
                "&7按住 &eShift + 右键&7 +256"
        );
        ItemStack LaserSight_Blue_Decrease_Item = Utils.buildChangeLaser(
                "decrease-blue",
                Material.BLACK_WOOL,
                "&f减少&9B&f值",
                "&e左键&7 -1",
                "&e右键&7 -8",
                "&7按住 &eShift + 左键&7 -32",
                "&7按住 &eShift + 右键&7 -256"
        );
        ItemStack powerItem = Utils.buildChangeGenerateMode(
                Material.RESPAWN_ANCHOR,
                "&4电力",
                "&e←←←左侧放置" + Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName(),
                "&e右侧输出" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName() + "→→→",
                "&6&l当前剩余电力:&e" + this.getItemCharge(item) + "J",
                "&e每个生产" + GENERATE + "&eJ电力",
                "&f&l当前自动生产状态: " + Power_generate_mode
        );

        // Build and open GUI
        for (int i = 0; i < INVENTORY_SIZE; i++)
            inventory.setItem(i, backgroundItem);

        for (int slot : INSTRUCTIONS)
            inventory.setItem(slot, instructions_Item);

        for (int slot : BORDER_YELLOW)
            inventory.setItem(slot, border_yellow_Item);

        for (int slot : BORDER_RED)
            inventory.setItem(slot, border_red_Item);

        for (int slot : BORDER_GREEN)
            inventory.setItem(slot, border_green_Item);

        for (int slot : BORDER_ORANGE)
            inventory.setItem(slot, border_orange_Item);

        for (int slot : BORDER_BLACK)
            inventory.setItem(slot, border_black_Item);

        for (int slot : BORDER_BLUE)
            inventory.setItem(slot, border_blue_Item);

        for (int slot : BORDER_PURPLE)
            inventory.setItem(slot, border_purple_Item);

        inventory.setItem(POWER_INFO_SLOT, powerItem);
        inventory.setItem(TIKA_MODE_SLOT, TIKA_modeItem);
        inventory.setItem(LASERSIGHT_INFO_SLOT, LaserSight_Info_Item);
        inventory.setItem(LASERSIGHT_OFF_SLOT, LaserSight_Off_Item);
        inventory.setItem(LASERSIGHT_ON_SLOT, LaserSight_On_Item);
        inventory.setItem(LASERSIGHT_RED_INCREASE_SLOT, LaserSight_Red_Increase_Item);
        inventory.setItem(LASERSIGHT_RED_DECREASE_SLOT, LaserSight_Red_Decrease_Item);
        inventory.setItem(LASERSIGHT_GREEN_INCREASE_SLOT, LaserSight_Green_Increase_Item);
        inventory.setItem(LASERSIGHT_GREEN_DECREASE_SLOT, LaserSight_Green_Decrease_Item);
        inventory.setItem(LASERSIGHT_BLUE_INCREASE_SLOT, LaserSight_Blue_Increase_Item);
        inventory.setItem(LASERSIGHT_BLUE_DECREASE_SLOT, LaserSight_Blue_Decrease_Item);
        for (int i : ItemStack_RIFLE_BULLET_SLOT)
            inventory.clear(i);
        for (int i : ItemStack_GRENADE_SLOT)
            inventory.clear(i);
        for (int i : ItemStack_STEEL_BALL_SLOT)
            inventory.clear(i);
        for (int i : ItemStack_BURNING_STEEL_BALL_SLOT)
            inventory.clear(i);
        for (int i : ItemStack_SPECIAL_BULLET_SLOT)
            inventory.clear(i);
        for (int i : ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT)
            inventory.clear(i);
        for (int i : ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT)
            inventory.clear(i);

        // 回显，读取pdc数据，转换数据内容并按数量放置物品进入界面中
        int count;
        // RifleBullet
        count = pdc.getOrDefault(RifleBullet_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_RIFLE_BULLET_SLOT, Gun_And_Bullet.RIFLE_BULLET, count);
        // Grenade
        count = pdc.getOrDefault(Grenade_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_GRENADE_SLOT, Gun_And_Bullet.GRENADE, count);
        // SteelBall
        count = pdc.getOrDefault(SteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_STEEL_BALL_SLOT, Gun_And_Bullet.STEEL_BALL, count);
        // BurningSteelBall
        count = pdc.getOrDefault(BurningSteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_BURNING_STEEL_BALL_SLOT, Gun_And_Bullet.BURNING_STEEL_BALL, count);
        // SpecialBullet
        count = pdc.getOrDefault(SpecialBullet_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_SPECIAL_BULLET_SLOT, Gun_And_Bullet.SPECIAL_BULLET, count);
        // EnergyStorageCanFull
        count = pdc.getOrDefault(EnergyStorageCanFull_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT, Machine.ENERGY_STORAGE_CAN_FULL, count);
        // EnergyStorageCanEmpty
        count = pdc.getOrDefault(EnergyStorageCanEmpty_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT, Machine.ENERGY_STORAGE_CAN_EMPTY, count);

        // 打开容器界面
        player.openInventory(inventory);

        // Task that triggers every second
        new BukkitRunnable() {
            public void run() {

                // 检查非法物品类型，并返回至玩家
                boolean check = false;
                check = check || checkAndReturn(player, inventory, ItemStack_RIFLE_BULLET_SLOT, Gun_And_Bullet_Item_Setup.RifleBullet);
                check = check || checkAndReturn(player, inventory, ItemStack_GRENADE_SLOT, Gun_And_Bullet_Item_Setup.Grenade);
                check = check || checkAndReturn(player, inventory, ItemStack_STEEL_BALL_SLOT, Gun_And_Bullet_Item_Setup.SteelBall);
                check = check || checkAndReturn(player, inventory, ItemStack_BURNING_STEEL_BALL_SLOT, Gun_And_Bullet_Item_Setup.BurningSteelBall);
                check = check || checkAndReturn(player, inventory, ItemStack_SPECIAL_BULLET_SLOT, Gun_And_Bullet_Item_Setup.SpecialBullet);
                check = check || checkAndReturn(player, inventory, ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT, Machine_Item_Setup.EnergyStorageCanFull);
                check = check || checkAndReturn(player, inventory, ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT, Machine_Item_Setup.EnergyStorageCanEmpty);
                if (check) {
                    Utils.sendMessage(player, "物品错误，禁止放置不允许类型的物品");
                    check = false;
                }

                // Check if GUI is no longer open
                if (!inventory.getViewers().contains(player)) {
                    cancel();

                    // 检查数量，并赋值
                    // RifleBullet_Count
                    RifleBullet_Count = getCount(inventory, ItemStack_RIFLE_BULLET_SLOT, Gun_And_Bullet_Item_Setup.RifleBullet);
                    refreshItemLoreAndPDC(item, RifleBullet_Count_nsk, RifleBullet_Count);
                    // Grenade_Count
                    Grenade_Count = getCount(inventory, ItemStack_GRENADE_SLOT, Gun_And_Bullet_Item_Setup.Grenade);
                    refreshItemLoreAndPDC(item, Grenade_Count_nsk, Grenade_Count);
                    // SteelBall_Count
                    SteelBall_Count = getCount(inventory, ItemStack_STEEL_BALL_SLOT, Gun_And_Bullet_Item_Setup.SteelBall);
                    refreshItemLoreAndPDC(item, SteelBall_Count_nsk, SteelBall_Count);
                    // BurningSteelBall_Count
                    BurningSteelBall_Count = getCount(inventory, ItemStack_BURNING_STEEL_BALL_SLOT, Gun_And_Bullet_Item_Setup.BurningSteelBall);
                    refreshItemLoreAndPDC(item, BurningSteelBall_Count_nsk, BurningSteelBall_Count);
                    // SpecialBullet_Count
                    SpecialBullet_Count = getCount(inventory, ItemStack_SPECIAL_BULLET_SLOT, Gun_And_Bullet_Item_Setup.SpecialBullet);
                    refreshItemLoreAndPDC(item, SpecialBullet_Count_nsk, SpecialBullet_Count);
                    // EnergyStorageCanFull_Count
                    EnergyStorageCanFull_Count = getCount(inventory, ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT, Machine_Item_Setup.EnergyStorageCanFull);
                    refreshItemLoreAndPDC(item, EnergyStorageCanFull_Count_nsk, EnergyStorageCanFull_Count);
                    // EnergyStorageCanEmpty_Count
                    EnergyStorageCanEmpty_Count = getCount(inventory, ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT, Machine_Item_Setup.EnergyStorageCanEmpty);
                    refreshItemLoreAndPDC(item, EnergyStorageCanEmpty_Count_nsk, EnergyStorageCanEmpty_Count);

                    // 检查当前物品功能配置，并初始化赋值，写入当前物品pdc数据
                    PersistentDataContainer currentItemPDC = currentItem.getItemMeta().getPersistentDataContainer();
                    TIKA_mode = currentItemPDC.getOrDefault(TIKA_mode_nsk, PersistentDataType.STRING, "null");
                    if (TIKA_mode.equals("null")) {
                        TIKA_mode = Gun_And_Bullet.STEEL_BALL.getDisplayName();
                        refreshItemLoreAndPDC(item, TIKA_mode_nsk, TIKA_mode);
                    }
                    Laser_state = currentItemPDC.getOrDefault(Laser_state_nsk, PersistentDataType.STRING, "null");
                    if (Laser_state.equals("null")) {
                        Laser_state = "§8OFF";
                        refreshItemLoreAndPDC(item, Laser_state_nsk, Laser_state);
                    }
                    Laser_color = currentItemPDC.getOrDefault(Laser_color_nsk, PersistentDataType.STRING, "null");
                    if (Laser_color.equals("null")) {
                        Laser_color = "§f#FF_FF_FF";
                        refreshItemLoreAndPDC(item, Laser_color_nsk, Laser_color);
                    }

                    // 容量数据刷新
                    RifleBullet_Contain_Count = ItemStack_RIFLE_BULLET_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, RifleBullet_Contain_Count_nsk, RifleBullet_Contain_Count);
                    Grenade_Contain_Count = ItemStack_GRENADE_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, Grenade_Contain_Count_nsk, Grenade_Contain_Count);
                    SteelBall_Contain_Count = ItemStack_STEEL_BALL_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, SteelBall_Contain_Count_nsk, SteelBall_Contain_Count);
                    BurningSteelBall_Contain_Count = ItemStack_BURNING_STEEL_BALL_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, BurningSteelBall_Contain_Count_nsk, BurningSteelBall_Contain_Count);
                    SpecialBullet_Contain_Count = ItemStack_SPECIAL_BULLET_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, SpecialBullet_Contain_Count_nsk, SpecialBullet_Contain_Count);
                    EnergyStorageCanFull_Contain_Count = ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, EnergyStorageCanFull_Contain_Count_nsk, EnergyStorageCanFull_Contain_Count);
                    EnergyStorageCanEmpty_Contain_Count = ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT.length * 64;
                    refreshItemLoreAndPDC(item, EnergyStorageCanEmpty_Contain_Count_nsk, EnergyStorageCanEmpty_Contain_Count);

                    isLoaded = false;
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    // 检查非法物品类型，并返回至玩家
    private boolean checkAndReturn(Player player, Inventory inventory, int[] slots, SlimefunItem slimefunItem) {
        boolean remind = false;
        for (int slot : slots) {
            ItemStack item = inventory.getItem(slot);
            SlimefunItem sfItem = SlimefunItem.getByItem(item);

            if (item != null && !Objects.equals(sfItem, slimefunItem)) {
                remind = true;
                inventory.setItem(slot, new ItemStack(Material.AIR));
                Utils.giveOrDropItem(player, item);
            }
        }
        return remind;
    }

    // 检查(弹药能源)数量，并返回该值，不包含刷新数据与pdc
    private int getCount(Inventory inventory, int[] slots, SlimefunItem origin) {
        int count = 0;
        for (int slot : slots) {
            ItemStack item = inventory.getItem(slot);
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (item != null && Objects.equals(sfItem, origin)) {
                count += item.getAmount();
            }
        }
        return count;
    }

    // 按数量填充界面，用于回显
    private void fillInventory(Inventory inventory, int[] slot, ItemStack item, int count) {
        for (int i = 0; count > 0 && i < slot.length; i++) {
            ItemStack itemStack = item.clone();
            if (count > 64) {
                itemStack.setAmount(64);
                inventory.setItem(slot[i], itemStack);
                count = count - 64;
            } else {
                itemStack.setAmount(count);
                inventory.setItem(slot[i], itemStack);
                count = 0;
            }
        }
    }

    // changeTIKAMode接口，由监听器判断并调用
    @SuppressWarnings("all")
    public void changeTIKAMode(InventoryClickEvent event) {
        // 获取前置数据
        ItemStack item = currentItem;
        Inventory inventory = event.getInventory();
        // 更改TIKAmode并保存pdc数据
        TIKA_mode = item.getItemMeta().getPersistentDataContainer().getOrDefault(TIKA_mode_nsk, PersistentDataType.STRING, Gun_And_Bullet.STEEL_BALL.getDisplayName());
        TIKA_mode = Objects.equals(TIKA_mode, Gun_And_Bullet.STEEL_BALL.getDisplayName())
                ? Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName()
                : Gun_And_Bullet.STEEL_BALL.getDisplayName();
        refreshItemLoreAndPDC(item, TIKA_mode_nsk, TIKA_mode);

        int slot = event.getSlot();
        // 修改"TIKAmode修改"物品的lore，并替换界面内物品
        ItemStack modeChangeItem = inventory.getItem(slot);
        ItemMeta modeChangeItemMeta = modeChangeItem.getItemMeta();
        List<String> lore = modeChangeItemMeta.getLore();
        for (String loreLine : lore) {
            if (loreLine.contains("当前选中类型")) {
                int lineIndex = lore.indexOf(loreLine);
                loreLine = "§e当前选中类型:" + TIKA_mode;
                lore.set(lineIndex, loreLine);
            }
        }
        modeChangeItemMeta.setLore(lore);
        modeChangeItem.setItemMeta(modeChangeItemMeta);
        inventory.setItem(slot, modeChangeItem);
    }

    // changeLaserSetting的接口，统一化接口，不直接调用
    @SuppressWarnings("all")
    public void changeLaser(InventoryClickEvent event, String value) {
        // 获取前置数据
        ItemStack item = currentItem;
        Inventory inventory = event.getInventory();

        int LASERSIGHT_INFO_SLOT = Global_LASERSIGHT_INFO_SLOT;
        ItemStack laserConfigItem = inventory.getItem(LASERSIGHT_INFO_SLOT);
        ItemMeta laserConfigItemMeta = laserConfigItem.getItemMeta();
        List<String> lore = laserConfigItemMeta.getLore();

        lore = changeLaserState(item, lore, value);

        laserConfigItemMeta.setLore(lore);
        laserConfigItem.setItemMeta(laserConfigItemMeta);
        inventory.setItem(LASERSIGHT_INFO_SLOT, laserConfigItem);
    }

    // changeLaserSetting的接口，统一化接口，不直接调用
    @SuppressWarnings("all")
    public void changeLaser(InventoryClickEvent event, int index, String operation, int value) {
        // 获取前置数据
        ItemStack item = currentItem;
        Inventory inventory = event.getInventory();

        int LASERSIGHT_INFO_SLOT = Global_LASERSIGHT_INFO_SLOT;
        ItemStack laserConfigItem = inventory.getItem(LASERSIGHT_INFO_SLOT);
        ItemMeta laserConfigItemMeta = laserConfigItem.getItemMeta();
        List<String> lore = laserConfigItemMeta.getLore();

        lore = changeLaserColor(item, lore, index, operation, value);

        laserConfigItemMeta.setLore(lore);
        laserConfigItem.setItemMeta(laserConfigItemMeta);
        inventory.setItem(LASERSIGHT_INFO_SLOT, laserConfigItem);
    }

    // 修改laserState开关状态用
    public List<String> changeLaserState(ItemStack item, List<String> lore, String value) {
        // 更改laser配置信息并保存pdc数据
        Laser_state = value;
        if (Laser_state.equals("OFF")) {
            Laser_state = "§8" + Laser_state;
        }
        if (Laser_state.equals("ON")) {
            Laser_state = "§f" + Laser_state;
        }
        refreshItemLoreAndPDC(item, Laser_state_nsk, Laser_state);
        // 修改"laser信息"物品的lore，并替换界面内物品
        for (String loreLine : lore) {
            if (loreLine.contains("State")) {
                int lineIndex = lore.indexOf(loreLine);
                loreLine = "§fState: ";
                loreLine = loreLine + Laser_state;
                lore.set(lineIndex, loreLine);
            }
        }
        return lore;
    }

    // 修改laserColor颜色配置用
    public List<String> changeLaserColor(ItemStack item, List<String> lore, int index, String operation, int value) {
        // 更改laser配置信息并保存pdc数据
        assert item.getItemMeta() != null;
        Laser_color = item.getItemMeta().getPersistentDataContainer().getOrDefault(Laser_color_nsk, PersistentDataType.STRING, "#FF_FF_FF");
        String[] color = Laser_color.split("[#_]");
        if (operation.equals("add")) {
            color[index] = String.format("%02X", Math.min(Integer.parseInt(color[index], 16) + value, 255));
        }
        if (operation.equals("subtract")) {
            color[index] = String.format("%02X", Math.max(Integer.parseInt(color[index], 16) - value, 0));
        }
        Laser_color = "§x"
                + "§" + color[1].charAt(0) + "§" + color[1].charAt(1)
                + "§" + color[2].charAt(0) + "§" + color[2].charAt(1)
                + "§" + color[3].charAt(0) + "§" + color[3].charAt(1)
                + "#" + color[1].toUpperCase() + "_" + color[2].toUpperCase() + "_" + color[3].toUpperCase();
        refreshItemLoreAndPDC(item, Laser_color_nsk, Laser_color);
        // 修改"laser信息"物品的lore，并替换界面内物品
        for (String loreLine : lore) {
            if (loreLine.contains("Color")) {
                int lineIndex = lore.indexOf(loreLine);
                loreLine = "§fColor: " + Laser_color;
                lore.set(lineIndex, loreLine);
            }
        }
        return lore;
    }

    // changeGenerateMode接口，由监听器判断并调用
    @SuppressWarnings("all")
    public void changeGenerateMode(InventoryClickEvent event) {
        // 获取前置数据
        ItemStack item = currentItem;
        Inventory inventory = event.getInventory();
        // 更改generateMode并保存pdc数据
        Power_generate_mode = item.getItemMeta().getPersistentDataContainer().getOrDefault(Power_generate_mode_nsk, PersistentDataType.STRING, "ON");
        Power_generate_mode = Objects.equals(Power_generate_mode, "ON") ? "OFF" : "ON";
        refreshItemLoreAndPDC(item, Power_generate_mode_nsk, Power_generate_mode);

        int slot = event.getSlot();
        // 修改"generateMode修改"物品的lore，并替换界面内物品
        ItemStack modeChangeItem = inventory.getItem(slot);
        ItemMeta modeChangeItemMeta = modeChangeItem.getItemMeta();
        List<String> lore = modeChangeItemMeta.getLore();
        for (String loreLine : lore) {
            if (loreLine.contains("当前自动生产状态")) {
                int lineIndex = lore.indexOf(loreLine);
                loreLine = "§f§l当前自动生产状态: " + Power_generate_mode;
                lore.set(lineIndex, loreLine);
            }
        }
        modeChangeItemMeta.setLore(lore);
        modeChangeItem.setItemMeta(modeChangeItemMeta);
        inventory.setItem(slot, modeChangeItem);
    }
}
