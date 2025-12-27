package me.Doctor_do.multifunctionalgun;

import me.Doctor_do.multifunctionalgun.setup.Setup;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

import javax.annotation.Nonnull;

public class MultifunctionalGun extends JavaPlugin implements SlimefunAddon {

    private static MultifunctionalGun instance;
    private final Plugin plugin = MultifunctionalGun.getInstance();

    public static MultifunctionalGun getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        getLogger().info("Doctor.do-MultifunctionalGun插件已成功载入！");

        if (getServer().getPluginManager().getPlugin("Slimefun") == null) {
            getLogger().warning("Slimefun plugin not found!");
            getLogger().warning("未找到粘液科技插件！");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {

        instance = this;

        // 从 config.yml 中读取插件配置
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // 你可以在这里添加自动更新功能
        }

        //方法调用，函数调用，类实例化，方法重写，方法重载，单例模式，无对象类实例化，类继承，子类父类调用，向上转型，...不定多行输入，方法回调实现链式调用，接口，接口实现，static静态代码块

        // 使用设置类进行初始化
        Setup.SetupAll(cfg);

        getLogger().info("Doctor.do-MultifunctionalGun插件已成功启用！");
    }

//    @Override
//    public void onEnable() {
//        // 从 config.yml 中读取插件配置
//        Config cfg = new Config(this);
//
//        if (cfg.getBoolean("options.auto-update")) {
//            // 你可以在这里添加自动更新功能
//        }
//
//        /*
//         * 1. 创建分类
//         * 分类的显示物品将使用以下物品
//         */
//        ItemStack itemGroupItem = new CustomItemStack(Material.REPEATER, "&4Doctor.do的枪械");
//
//        // 给你的分类提供一个独一无二的ID
//        NamespacedKey itemGroupId = new NamespacedKey(this, "addon_category");
//        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);
//
//        /*
//         * 2. 创建一个 SlimefunItemStack
//         * 这个类是 ItemStack 的扩展，拥有多个构造函数
//         * 重要：每个物品都得有一个独一无二的ID
//         */
//        SlimefunItemStack slimefunItem = new SlimefunItemStack("COOL_DIAMOND", Material.DIAMOND, "&4炫酷的钻石", "&c+20% 炫酷");
//
//        /*
//         * 3. 创建配方
//         * 这个配方是一个拥有9个ItemStack的数组。
//         * 它代表了一个3x3的有序合成配方。
//         * 该配方所需的机器将在后面通过RecipeType指定。
//         */
//        ItemStack[] recipe = {new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD), null, new ItemStack(Material.DIAMOND), null, new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD)};
//
//        /*
//         * 4. 注册物品
//         * 现在，你只需要注册物品
//         * RecipeType.ENHANCED_CRAFTING_TABLE 代表
//         * 该物品将在增强型工作台中合成。
//         * 来自粘液科技本体的配方类型将会自动将配方添加到对应的机器中。
//         */
//        SlimefunItem item = new SlimefunItem(itemGroup, slimefunItem, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
//        item.register(this);
//
//        getLogger().info("Doctor.do-Gun插件已成功启用！");
//    }

    @Override
    public void onDisable() {
        // 禁用插件的逻辑...

        getLogger().info("Doctor.do-MultifunctionalGun插件已卸载！");
    }

    @Override
    public String getBugTrackerURL() {
        // 你可以在这里返回你的问题追踪器的网址，而不是 null
        return null;
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * 你需要返回对你插件的引用。
         * 如果这是你插件的主类，只需要返回 "this" 即可。
         */
        return this;
    }

}
