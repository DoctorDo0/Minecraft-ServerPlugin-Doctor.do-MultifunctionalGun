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
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Gun_And_Bullet;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EndlessWeapon extends SlimefunItem implements NotPlaceable, Rechargeable, Listener {
    public static MultifunctionalGun plugin = MultifunctionalGun.getInstance();

    private final static float capacity_temp = 20000.0F;

    public static float getMaxItemCharge_Temp() {
        return capacity_temp;
    }

    private static final float COST = 1.0F;
    private final float GENERATE = 2048.0F;
    private final float CAPACITY;

    private ItemStack currentItem;
    private boolean isLoaded = false;

    private final NamespacedKey EndlessWeapon_Mode = new NamespacedKey(plugin, "ENDLESS_WEAPON_MODE");
    private final List<EndlessWeapon_Mode> modes = new ArrayList<>();

    private final NamespacedKey RifleBullet_Count_nsk = new NamespacedKey(plugin, "RifleBullet_Count");
    private int RifleBullet_Count = 0;
    private final NamespacedKey RifleBullet_Contain_Count_nsk = new NamespacedKey(plugin, "RifleBullet_Contain_Count");
    private int RifleBullet_Contain_Count = 0;
    private final NamespacedKey Grenade_Count_nsk = new NamespacedKey(plugin, "Grenade_Count");
    private int Grenade_Count = 0;
    private final NamespacedKey Grenade_Contain_Count_nsk = new NamespacedKey(plugin, "Grenade_Contain_Count");
    private int Grenade_Contain_Count = 0;
    private final NamespacedKey SteelBall_Count_nsk = new NamespacedKey(plugin, "SteelBall_Count");
    private int SteelBall_Count = 0;
    private final NamespacedKey SteelBall_Contain_Count_nsk = new NamespacedKey(plugin, "SteelBall_Contain_Count");
    private int SteelBall_Contain_Count = 0;
    private final NamespacedKey BurningSteelBall_Count_nsk = new NamespacedKey(plugin, "BurningSteelBall_Count");
    private int BurningSteelBall_Count = 0;
    private final NamespacedKey BurningSteelBall_Contain_Count_nsk = new NamespacedKey(plugin, "BurningSteelBall_Contain_Count");
    private int BurningSteelBall_Contain_Count = 0;
    private final NamespacedKey SpecialBullet_Count_nsk = new NamespacedKey(plugin, "SpecialBullet_Count");
    private int SpecialBullet_Count = 0;
    private final NamespacedKey SpecialBullet_Contain_Count_nsk = new NamespacedKey(plugin, "SpecialBullet_Contain_Count");
    private int SpecialBullet_Contain_Count = 0;
    private final NamespacedKey EnergyStorageCanFull_Count_nsk = new NamespacedKey(plugin, "EnergyStorageCanFull_Count");
    private int EnergyStorageCanFull_Count = 0;
    private final NamespacedKey EnergyStorageCanFull_Contain_Count_nsk = new NamespacedKey(plugin, "EnergyStorageCanFull_Contain_Count");
    private int EnergyStorageCanFull_Contain_Count = 0;
    private final NamespacedKey EnergyStorageCanEmpty_Count_nsk = new NamespacedKey(plugin, "EnergyStorageCanEmpty_Count");
    private int EnergyStorageCanEmpty_Count = 0;
    private final NamespacedKey EnergyStorageCanEmpty_Contain_Count_nsk = new NamespacedKey(plugin, "EnergyStorageCanEmpty_Contain_Count");
    private int EnergyStorageCanEmpty_Contain_Count = 0;
    private final NamespacedKey TIKA_mode_nsk = new NamespacedKey(plugin, "TIKA_Mode");
    private String TIKA_mode = Gun_And_Bullet.STEEL_BALL.getDisplayName();
    private final NamespacedKey Laser_state_nsk = new NamespacedKey(plugin, "Laser_state");
    private String Laser_state = "OFF";
    private final NamespacedKey Laser_color_nsk = new NamespacedKey(plugin, "Laser_color");
    private String Laser_color = "#FF_FF_FF";

    public EndlessWeapon(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, float capacity, String[] items) {
        super(itemGroup, item, recipeType, recipe);

        for (int i = 0; i < items.length; i++) {
            modes.add(new EndlessWeapon_Mode(this, i, items[i]));
        }

        if (capacity <= 0) {
            this.CAPACITY = capacity_temp;
        } else this.CAPACITY = capacity;

        useableInWorkbench = false;
        hidden = false;
        enchantable = false;
        disenchantable = false;
    }

    // 返回电容量
    public float getMaxItemCharge(ItemStack itemStack) {
        return CAPACITY;
    }

    // 循环模式
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

    // 跟随mode，刷新lore中的模式字段
    @SuppressWarnings("all")
    private void refreshItemLoreFromMode(ItemStack item, int index) {
        // 重新赋值，命名简化
        String assault_rifle_and_grenade_launcher_name = Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName();
        String tika_rifle_name = Gun_And_Bullet.TIKA_RIFLE.getDisplayName();
        String light_cone_name = Gun_And_Bullet.LIGHT_CONE.getDisplayName();
        String anti_materiel_sniper_rifle_name = Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName();
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
        String modeName = modes.get(index).getItem().getItemName();
        // 判断是否一致，如果不一致，则统一将name替换为%modes%
        if (
                listText.contains(assault_rifle_and_grenade_launcher_name) && !modeName.equals(assault_rifle_and_grenade_launcher_name)
        ) {
            listText = listText.replace(assault_rifle_and_grenade_launcher_name, "%modes%");
        } else if (
                listText.contains(tika_rifle_name) && !modeName.equals(tika_rifle_name)
        ) {
            listText = listText.replace(tika_rifle_name, "%modes%");
        } else if (
                listText.contains(light_cone_name) && !modeName.equals(light_cone_name)
        ) {
            listText = listText.replace(light_cone_name, "%modes%");
        } else if (
                listText.contains(anti_materiel_sniper_rifle_name) && !modeName.equals(anti_materiel_sniper_rifle_name)
        ) {
            listText = listText.replace(anti_materiel_sniper_rifle_name, "%modes%");
        } else {
            listText = "§7当前模式: %modes%";
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
    private void refreshItemLoreAndPDC(ItemStack item, NamespacedKey type, Object object) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        List<String> loreList = meta.getLore();
        int line_index = 0;

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
                RifleBullet_Count = (int) object;
            }
            if (Objects.equals(type, Grenade_Count_nsk)) {
                Grenade_Count = (int) object;
            }
            if (Objects.equals(type, RifleBullet_Contain_Count_nsk)) {
                RifleBullet_Contain_Count = (int) object;
            }
            if (Objects.equals(type, Grenade_Contain_Count_nsk)) {
                Grenade_Contain_Count = (int) object;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) object);
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
                SteelBall_Count = (int) object;
            }
            if (Objects.equals(type, BurningSteelBall_Count_nsk)) {
                BurningSteelBall_Count = (int) object;
            }
            if (Objects.equals(type, SteelBall_Contain_Count_nsk)) {
                SteelBall_Contain_Count = (int) object;
            }
            if (Objects.equals(type, BurningSteelBall_Contain_Count_nsk)) {
                BurningSteelBall_Contain_Count = (int) object;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) object);
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
                SpecialBullet_Count = (int) object;
            }
            if (Objects.equals(type, SpecialBullet_Contain_Count_nsk)) {
                SpecialBullet_Contain_Count = (int) object;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) object);
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
                EnergyStorageCanFull_Count = (int) object;
            }
            if (Objects.equals(type, EnergyStorageCanEmpty_Count_nsk)) {
                EnergyStorageCanEmpty_Count = (int) object;
            }
            if (Objects.equals(type, EnergyStorageCanFull_Contain_Count_nsk)) {
                EnergyStorageCanFull_Contain_Count = (int) object;
            }
            if (Objects.equals(type, EnergyStorageCanEmpty_Contain_Count_nsk)) {
                EnergyStorageCanEmpty_Contain_Count = (int) object;
            }
            // 设置pdc
            pdc.set(type, PersistentDataType.INTEGER, (int) object);
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
            TIKA_mode = (String) object;
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
                Laser_state = (String) object;
                pdc.set(Laser_state_nsk, PersistentDataType.STRING, Laser_state);
            }
            if (Objects.equals(type, Laser_color_nsk)) {
                Laser_color = (String) object;
                pdc.set(Laser_color_nsk, PersistentDataType.STRING, Laser_color);
            }
            String listText = "§7激光指示器与显示: " + Laser_state + "§7 - " + Laser_color;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
        }
        // 修改物品操作
        item.setItemMeta(meta);
    }

    // 按输入物品的电量与pdc进行计算，触发不同效果，进行修改物品操作，修改lore和pdc，修改电量，或输出提示
    @SuppressWarnings("all")
    private void refreshAndCheckEnergy(Player player, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        List<String> loreList = meta.getLore();
        int line_index = 0;
        // type = Rifle_Bullet
        // 取得包含模式信息的字符串位置
        for (String lore : loreList) {
            if (lore.contains(Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "/" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName())) {
                line_index = loreList.indexOf(lore);
            }
        }
        // 判断数量并转入不同结果
        EnergyStorageCanFull_Count = pdc.getOrDefault(EnergyStorageCanFull_Count_nsk, PersistentDataType.INTEGER, 0);
        EnergyStorageCanEmpty_Count = pdc.getOrDefault(EnergyStorageCanEmpty_Count_nsk, PersistentDataType.INTEGER, 0);
        // 满罐数量为空
        if (EnergyStorageCanFull_Count == 0) {
            Utils.sendMessage(player, Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "已耗尽");
        }
        // 空罐数量为满
        if (EnergyStorageCanEmpty_Count == EnergyStorageCanEmpty_Contain_Count) {
            Utils.sendMessage(player, Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName() + "已满");
        }
        // 正常情况
        if (EnergyStorageCanFull_Count > 0 && EnergyStorageCanEmpty_Count < EnergyStorageCanEmpty_Contain_Count) {
            // 数量操作
            EnergyStorageCanFull_Count = EnergyStorageCanFull_Count - 1;
            EnergyStorageCanEmpty_Count = EnergyStorageCanEmpty_Count + 1;
            // pdc更新
            pdc.set(EnergyStorageCanFull_Count_nsk, PersistentDataType.INTEGER, EnergyStorageCanFull_Count);
            pdc.set(EnergyStorageCanEmpty_Count_nsk, PersistentDataType.INTEGER, EnergyStorageCanEmpty_Count);
            // lore更新
            String listText = "    " + Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "/" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName()
                    + " §7> " + EnergyStorageCanFull_Count + "/" + EnergyStorageCanEmpty_Count;
            loreList.set(line_index, listText);
            meta.setLore(loreList);
            // 物品数据更新
            item.setItemMeta(meta);
            // 增加物品电量
            addItemCharge(item, GENERATE);
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
        // 同一个物品栈
//        ItemStack item1 = player.getInventory().getItemInMainHand();
//        ItemStack item2 = event.getItem();
//        ItemStack item3 = event.getPlayer().getInventory().getItemInMainHand();
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

    // 左键点击事件
    @SuppressWarnings("all")
    public void LeftClickEvent(Player player, PlayerInteractEvent event, ItemStack item) {
        currentItem = item;

        event.setUseInteractedBlock(Event.Result.DENY);
        event.setUseItemInHand(Event.Result.DENY);

        var im = item.getItemMeta();
        var pdc = im.getPersistentDataContainer();
        int index = pdc.getOrDefault(EndlessWeapon_Mode, PersistentDataType.INTEGER, 0);

        //判断是否已锁
        if (!isLoaded) {
            // 仅左键
            if (!player.isSneaking()) {
                refreshItemLoreFromMode(item, index);
                SlimefunItem sfItem = modes.get(index).getItem();

                // 步枪+榴弹模式
                if (Objects.equals(sfItem.getItemName(), Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
                    Grenade_Count = pdc.getOrDefault(Grenade_Count_nsk, PersistentDataType.INTEGER, 0);

                    if (Grenade_Count >= 1) {
                        refreshItemLoreAndPDC(item, Grenade_Count_nsk, Grenade_Count - 1);
                        Gun_And_Bullet_Item_Setup.getGrenadeLauncherInstance().CauseDamageToEntity(event, GrenadeLauncher.DAMAGE);
                    } else {
                        Utils.sendMessage(player, Gun_And_Bullet.GRENADE.getDisplayName() + "已耗尽");
                    }
                }

                // 其他模式
                if (!Objects.equals(sfItem.getItemName(), Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
                    Gun_And_Bullet_Item_Setup.getScopeInstance().ScopeUseEvent(event);
                }
            }

            // shift+左键
            else {
                refreshItemLoreFromMode(item, index);
                openFirearmExpansionBackpack(player, item);
            }
        } else {
            Utils.sendMessage(player, "等待装载中...");
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
        int index = pdc.getOrDefault(EndlessWeapon_Mode, PersistentDataType.INTEGER, 0);

        //判断是否已锁
        if (!isLoaded) {
            // 仅右键
            if (!player.isSneaking()) {
                refreshItemLoreFromMode(item, index);
                SlimefunItem sfItem = modes.get(index).getItem();

                // 步枪+榴弹模式
                if (Objects.equals(sfItem.getItemName(), Gun_And_Bullet.ASSAULT_RIFLE_AND_GRENADE_LAUNCHER.getDisplayName())) {
                    RifleBullet_Count = pdc.getOrDefault(RifleBullet_Count_nsk, PersistentDataType.INTEGER, 0);

                    if (RifleBullet_Count >= 1) {
                        refreshItemLoreAndPDC(item, RifleBullet_Count_nsk, RifleBullet_Count - 1);
                        Gun_And_Bullet_Item_Setup.getAssaultRifleInstance().CauseDamageToEntity(event, AssaultRifle.DAMAGE);
                    } else {
                        Utils.sendMessage(player, Gun_And_Bullet.RIFLE_BULLET.getDisplayName() + "已耗尽");
                    }
                }

                // 提卡模式
                if (Objects.equals(sfItem.getItemName(), Gun_And_Bullet.TIKA_RIFLE.getDisplayName())) {
                    // 获取当前提卡模式
                    TIKA_mode = pdc.getOrDefault(TIKA_mode_nsk, PersistentDataType.STRING, Gun_And_Bullet.STEEL_BALL.getDisplayName());
                    // 钢珠模式
                    if (Objects.equals(TIKA_mode, Gun_And_Bullet.STEEL_BALL.getDisplayName())) {
                        SteelBall_Count = pdc.getOrDefault(SteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
                        if (SteelBall_Count >= 1) {
                            refreshItemLoreAndPDC(item, SteelBall_Count_nsk, SteelBall_Count - 1);
                            // 有电状态
                            if (removeItemCharge(item, COST * 2)) {
                                Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().CauseDamageToEntity(event, TIKA_Rifle.DAMAGE * 3);
                            }
                            // 无电状态
                            else {
                                // 尝试更新电量
                                refreshAndCheckEnergy(player, item);
                                Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().CauseDamageToEntity(event, TIKA_Rifle.DAMAGE);
                            }
                        } else {
                            Utils.sendMessage(player, Gun_And_Bullet.STEEL_BALL.getDisplayName() + "已耗尽");
                        }
                    }
                    // 燃烧弹模式
                    if (Objects.equals(TIKA_mode, Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName())) {
                        BurningSteelBall_Count = pdc.getOrDefault(BurningSteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
                        if (BurningSteelBall_Count >= 1) {
                            refreshItemLoreAndPDC(item, BurningSteelBall_Count_nsk, BurningSteelBall_Count - 1);
                            // 有电状态
                            if (removeItemCharge(item, COST * 2)) {
                                Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().CauseDamageToEntity(event, TIKA_Rifle.DAMAGE * 3);
                            }
                            // 无电状态
                            else {
                                // 尝试更新电量
                                refreshAndCheckEnergy(player, item);
                                Gun_And_Bullet_Item_Setup.getTIKA_RifleInstance().CauseDamageToEntity(event, TIKA_Rifle.DAMAGE);
                            }
                        } else {
                            Utils.sendMessage(player, Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName() + "已耗尽");
                        }
                    }
                }

                // 光锥模式
                if (Objects.equals(sfItem.getItemName(), Gun_And_Bullet.LIGHT_CONE.getDisplayName())) {
                    if (removeItemCharge(item, COST * 40)) {
                        Gun_And_Bullet_Item_Setup.getLightConeInstance().CauseDamageToEntity(event, LightCone.DAMAGE);
                    } else {
                        // 尝试更新电量
                        refreshAndCheckEnergy(player, item);
                    }
                }

                // 反器材模式
                if (Objects.equals(sfItem.getItemName(), Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName())) {
                    SpecialBullet_Count = pdc.getOrDefault(SpecialBullet_Count_nsk, PersistentDataType.INTEGER, 0);
                    if (SpecialBullet_Count >= 1) {
                        refreshItemLoreAndPDC(item, SpecialBullet_Count_nsk, SpecialBullet_Count - 1);
//                        SlimefunItem sfItem = modes.get(index).getItem();

//                        if (sfItem != null) {
//                            sfItem.callItemHandler(ItemUseHandler.class, handler -> handler.onRightClick(new PlayerRightClickEvent(event)));
//                        }
                        Gun_And_Bullet_Item_Setup.getAntiMaterielSniperRifleInstance().CauseDamageToEntity(event, AntiMaterielSniperRifle.DAMAGE);
                    } else {
                        Utils.sendMessage(player, Gun_And_Bullet.SPECIAL_BULLET.getDisplayName() + "已耗尽");
                    }
                }
            }

            // shift+右键
            else {
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
        } else {
            Utils.sendMessage(player, "等待装载中...");
        }
    }

    // 打开背包界面功能
    @SuppressWarnings("all")
    public void openFirearmExpansionBackpack(Player player, ItemStack item) {
        currentItem = item;

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
        //物品类型限制
        final ItemStack ItemStack_RIFLE_BULLET = Gun_And_Bullet.RIFLE_BULLET.clone();
        final ItemStack ItemStack_GRENADE = Gun_And_Bullet.GRENADE.clone();
        final ItemStack ItemStack_STEEL_BALL = Gun_And_Bullet.STEEL_BALL.clone();
        final ItemStack ItemStack_BURNING_STEEL_BALL = Gun_And_Bullet.BURNING_STEEL_BALL.clone();
        final ItemStack ItemStack_SPECIAL_BULLET = Gun_And_Bullet.SPECIAL_BULLET.clone();
        final ItemStack ItemStack_ENERGY_STORAGE_CAN_FULL = Machine.ENERGY_STORAGE_CAN_FULL.clone();
        final ItemStack ItemStack_ENERGY_STORAGE_CAN_EMPTY = Machine.ENERGY_STORAGE_CAN_EMPTY.clone();

        // Create GUI Items
        Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, "" + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + Gun_And_Bullet.EXPANSION_BACKPACK.getDisplayName());

        //分割线
        ItemStack backgroundItem = new ItemStack(Material.AIR);
        ItemStack instructions_Item = Utils.buildNonInteractable(
                Material.BEACON,
                "&e使用说明",
                "&7&l&n使用前建议先仔细阅读本说明",
                "&7&n→ 第一次合成该物品并使用时，建议先打开一次背包并关闭界面，等待物品的数据保存，所有的描述变为正常文字后即为保存成功",
                "&7→ 本界面为背包界面，装填弹药、能源控制、激光瞄准器设置，都在本界面内进行操作",
                "&7→ 左下角空区可放置" + Gun_And_Bullet.RIFLE_BULLET.getDisplayName(),
                "&7→ 中间偏左的一个空位放置" + Gun_And_Bullet.GRENADE.getDisplayName(),
                "&7→ 左上角空区为" + Gun_And_Bullet.TIKA_RIFLE.getDisplayName() + "&7专用区域",
                "&7→ 其中左侧可放置" + Gun_And_Bullet.STEEL_BALL.getDisplayName() + "&7，右侧可放置" + Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName() + "&7中间用于切换当前模式",
                "&7→ 下方的两个空位为" + Gun_And_Bullet.ANTI_MATERIEL_SNIPER_RIFLE.getDisplayName() + "&7专用区域",
                "&7→ 可放置" + Gun_And_Bullet.SPECIAL_BULLET.getDisplayName(),
                "&7→ 右下方为能源区域，包含物品区域与电力信息。当能源不足时再次使用将自动消耗物资并转换为电力能源",
                "&7→ 能源区域可放置" + Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName() + "&7，或拿取" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName(),
                "&7→ 右上方为" + Gun_And_Bullet.LASER_SIGHT.getDisplayName() + "&7操作区域，包含开关与颜色设置功能",
                "&7→ 颜色设置与&cR&aG&9B&7设置相似，选择不同的&e功能按钮&7及&e按键方式&7以对某项属性值进行修改"
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
        ItemStack TIKA_modeItem = Utils.buildChangeMode(
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
        ItemStack powerItem = Utils.buildNonInteractable(
                Material.RESPAWN_ANCHOR,
                "&4电力",
                "&e←←←左侧放置" + Machine.ENERGY_STORAGE_CAN_FULL.getDisplayName(),
                "&e右侧输出" + Machine.ENERGY_STORAGE_CAN_EMPTY.getDisplayName() + "→→→",
                "&6&l当前剩余电力:&e" + this.getItemCharge(item) + "J",
                "&e每个生产" + GENERATE + "&eJ电力"
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
        fillInventory(inventory, ItemStack_RIFLE_BULLET_SLOT, ItemStack_RIFLE_BULLET, count);
        // Grenade
        count = pdc.getOrDefault(Grenade_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_GRENADE_SLOT, ItemStack_GRENADE, count);
        // SteelBall
        count = pdc.getOrDefault(SteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_STEEL_BALL_SLOT, ItemStack_STEEL_BALL, count);
        // BurningSteelBall
        count = pdc.getOrDefault(BurningSteelBall_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_BURNING_STEEL_BALL_SLOT, ItemStack_BURNING_STEEL_BALL, count);
        // SpecialBullet
        count = pdc.getOrDefault(SpecialBullet_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_SPECIAL_BULLET_SLOT, ItemStack_SPECIAL_BULLET, count);
        // EnergyStorageCanFull
        count = pdc.getOrDefault(EnergyStorageCanFull_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT, ItemStack_ENERGY_STORAGE_CAN_FULL, count);
        // EnergyStorageCanEmpty
        count = pdc.getOrDefault(EnergyStorageCanEmpty_Count_nsk, PersistentDataType.INTEGER, 0);
        fillInventory(inventory, ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT, ItemStack_ENERGY_STORAGE_CAN_EMPTY, count);

        // 打开容器界面
        player.openInventory(inventory);

        // Task that triggers every second
        new BukkitRunnable() {
            public void run() {

                // 检查非法物品类型，并返回至玩家
                boolean check = false;
                check = check || checkAndReturn(player, inventory, ItemStack_RIFLE_BULLET_SLOT, ItemStack_RIFLE_BULLET);
                check = check || checkAndReturn(player, inventory, ItemStack_GRENADE_SLOT, ItemStack_GRENADE);
                check = check || checkAndReturn(player, inventory, ItemStack_STEEL_BALL_SLOT, ItemStack_STEEL_BALL);
                check = check || checkAndReturn(player, inventory, ItemStack_BURNING_STEEL_BALL_SLOT, ItemStack_BURNING_STEEL_BALL);
                check = check || checkAndReturn(player, inventory, ItemStack_SPECIAL_BULLET_SLOT, ItemStack_SPECIAL_BULLET);
                check = check || checkAndReturn(player, inventory, ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT, ItemStack_ENERGY_STORAGE_CAN_FULL);
                check = check || checkAndReturn(player, inventory, ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT, ItemStack_ENERGY_STORAGE_CAN_EMPTY);
                if (check) {
                    Utils.sendMessage(player, "物品错误，禁止放置不允许类型的物品");
                    check = false;
                }

                // Check if GUI is no longer open
                if (!inventory.getViewers().contains(player)) {
                    cancel();

                    // 检查数量，并赋值
                    int count;
                    // RifleBullet_Count
                    count = 0;
                    for (int i : ItemStack_RIFLE_BULLET_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_RIFLE_BULLET.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    RifleBullet_Count = count;
                    refreshItemLoreAndPDC(item, RifleBullet_Count_nsk, RifleBullet_Count);
                    // Grenade_Count
                    count = 0;
                    for (int i : ItemStack_GRENADE_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_GRENADE.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    Grenade_Count = count;
                    refreshItemLoreAndPDC(item, Grenade_Count_nsk, Grenade_Count);
                    // SteelBall_Count
                    count = 0;
                    for (int i : ItemStack_STEEL_BALL_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_STEEL_BALL.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    SteelBall_Count = count;
                    refreshItemLoreAndPDC(item, SteelBall_Count_nsk, SteelBall_Count);
                    // BurningSteelBall_Count
                    count = 0;
                    for (int i : ItemStack_BURNING_STEEL_BALL_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_BURNING_STEEL_BALL.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    BurningSteelBall_Count = count;
                    refreshItemLoreAndPDC(item, BurningSteelBall_Count_nsk, BurningSteelBall_Count);
                    // SpecialBullet_Count
                    count = 0;
                    for (int i : ItemStack_SPECIAL_BULLET_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_SPECIAL_BULLET.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    SpecialBullet_Count = count;
                    refreshItemLoreAndPDC(item, SpecialBullet_Count_nsk, SpecialBullet_Count);
                    // EnergyStorageCanFull_Count
                    count = 0;
                    for (int i : ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_ENERGY_STORAGE_CAN_FULL.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    EnergyStorageCanFull_Count = count;
                    refreshItemLoreAndPDC(item, EnergyStorageCanFull_Count_nsk, EnergyStorageCanFull_Count);
                    // EnergyStorageCanEmpty_Count
                    count = 0;
                    for (int i : ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT) {
                        ItemStack item = inventory.getItem(i);
                        if (item != null && Objects.equals(item.clone().getItemMeta(), ItemStack_ENERGY_STORAGE_CAN_EMPTY.clone().getItemMeta())) {
                            count += item.getAmount();
                        }
                    }
                    EnergyStorageCanEmpty_Count = count;
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

//            // 检查非法物品类型，并返回至玩家(isSimilar和equals均无法正常比较判断，已弃用)
//            private void checkAndReturn() {
//                Boolean remind = false;
//                for (int slot : ItemStack_RIFLE_BULLET_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_RIFLE_BULLET.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                for (int slot : ItemStack_GRENADE_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_GRENADE.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                for (int slot : ItemStack_STEEL_BALL_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_STEEL_BALL.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                for (int slot : ItemStack_BURNING_STEEL_BALL_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_BURNING_STEEL_BALL.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                for (int slot : ItemStack_SPECIAL_BULLET_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_SPECIAL_BULLET.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                for (int slot : ItemStack_ENERGY_STORAGE_CAN_FULL_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_ENERGY_STORAGE_CAN_FULL.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                for (int slot : ItemStack_ENERGY_STORAGE_CAN_EMPTY_SLOT) {
//                    ItemStack item = inventory.getItem(slot);
//                    if (item != null && !item.isSimilar(ItemStack_ENERGY_STORAGE_CAN_EMPTY.clone())) {
//                        remind = true;
//                        Utils.giveOrDropItem(player, item);
//                    }
//                }
//                if (remind) {
//                    Utils.sendMessage(player, "???");
//                    remind = false;
//                }
//            }
        }.runTaskTimer(plugin, 0, 20);
    }

    // 检查非法物品类型，并返回至玩家
    private Boolean checkAndReturn(Player player, Inventory inventory, int[] slots, ItemStack itemStack) {
        boolean remind = false;
        for (int slot : slots) {
            ItemStack item = inventory.getItem(slot);

            if (item != null && !Objects.equals(item.clone().getItemMeta(), itemStack.clone().getItemMeta())) {
                remind = true;
                inventory.setItem(slot, new ItemStack(Material.AIR));
                Utils.giveOrDropItem(player, item);
            }
        }
        return remind;
    }

    // 按数量填充界面
    private void fillInventory(Inventory inventory, int[] slot, ItemStack item, int count) {
        for (int i = 0; count > 0 && i < slot.length; i++) {
            ItemStack itemStack = item.clone();
            if (count > 64) {
                itemStack.setAmount(64);
                inventory.setItem(slot[i], itemStack);
                count = count - 64;
            }
            if (count <= 64) {
                itemStack.setAmount(count);
                inventory.setItem(slot[i], itemStack);
                count = 0;
            }
        }
    }

    // changeMode接口，由监听器判断并调用
    @SuppressWarnings("all")
    public void changeMode(InventoryClickEvent event) {
        // 获取前置数据
        ItemStack item = currentItem;
        Inventory inventory = event.getInventory();
        // 更改mode并保存pdc数据
        TIKA_mode = item.getItemMeta().getPersistentDataContainer().getOrDefault(TIKA_mode_nsk, PersistentDataType.STRING, Gun_And_Bullet.STEEL_BALL.getDisplayName());
        TIKA_mode = Objects.equals(TIKA_mode, Gun_And_Bullet.STEEL_BALL.getDisplayName())
                ? Gun_And_Bullet.BURNING_STEEL_BALL.getDisplayName()
                : Gun_And_Bullet.STEEL_BALL.getDisplayName();
        refreshItemLoreAndPDC(item, TIKA_mode_nsk, TIKA_mode);

        int slot = event.getSlot();
        // 修改"mode修改"物品的lore，并替换界面内物品
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

        int LASERSIGHT_INFO_SLOT = 25;
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

        int LASERSIGHT_INFO_SLOT = 25;
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
}
