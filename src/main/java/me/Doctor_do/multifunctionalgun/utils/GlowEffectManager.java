package me.Doctor_do.multifunctionalgun.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发光效果管理器 - 全版本兼容版
 * 支持 1.16.5 - 1.21，自动适配 ProtocolLib 兼容性问题
 */
public class GlowEffectManager {

    private final Plugin plugin;
    private static GlowEffectManager instance;
    private final boolean useTeamSystem;
    private final boolean useNewParticleAPI;
    private final boolean hasProtocolLib;
    private final ProtocolManager protocolManager;

    // 存储正在发光的实体及其任务ID
    private final ConcurrentHashMap<Integer, Integer> activeGlowTasks = new ConcurrentHashMap<>();

    // 版本检测结果
    private final boolean isVersion1_19_4;
    private final boolean isVersion1_20Plus;

    public GlowEffectManager(Plugin plugin) {
        this.plugin = plugin;

        // 使用 VersionUtil 进行版本检测
        this.isVersion1_19_4 = VersionUtil.getMajorVersion() == 1 &&
                VersionUtil.getMinorVersion() == 19 &&
                VersionUtil.getVersion().contains("R3");
        this.isVersion1_20Plus = VersionUtil.isVersionAtLeast(1, 20);

        // 功能开关
        this.useTeamSystem = VersionUtil.isVersionAtLeast(1, 16);
        this.useNewParticleAPI = VersionUtil.isVersionAtLeast(1, 13);

        // 检查 ProtocolLib
        this.hasProtocolLib = Bukkit.getPluginManager().getPlugin("ProtocolLib") != null;
        this.protocolManager = hasProtocolLib ? ProtocolLibrary.getProtocolManager() : null;

        // 输出初始化信息
        plugin.getLogger().info("========================================");
        plugin.getLogger().info("GlowEffectManager initialized:");
        plugin.getLogger().info("  Server Version: " + VersionUtil.getVersion());
        plugin.getLogger().info("  Minecraft: " + VersionUtil.getMajorVersion() + "." + VersionUtil.getMinorVersion());
        plugin.getLogger().info("  ProtocolLib: " + (hasProtocolLib ? "✓ Available" : "✗ Not found"));
        plugin.getLogger().info("  Team System: " + (useTeamSystem ? "✓ Enabled" : "✗ Disabled"));
        plugin.getLogger().info("  1.19.4 Mode: " + (isVersion1_19_4 ? "✓ Active" : "✗"));
        plugin.getLogger().info("========================================");
    }

    /**
     * 获取 GlowEffectManager 实例（懒加载）
     */
    public static GlowEffectManager getInstance(Plugin plugin) {
        if (instance == null) {
            instance = new GlowEffectManager(plugin);
        }
        return instance;
    }

    /**
     * 获取 GlowEffectManager 实例（如果已初始化）
     */
    public static GlowEffectManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GlowEffectManager has not been initialized yet. Call getInstance(Plugin) first.");
        }
        return instance;
    }

    /**
     * 应用彩色发光效果和粒子效果
     */
    public void applyGlowEffect(LivingEntity entity, int duration, Color color) {
        if (entity == null || entity.isDead()) {
            return;
        }

        // 应用彩色发光效果
        if (hasProtocolLib && VersionUtil.isVersionAtLeast(1, 16) && !isVersion1_19_4) {
            // 1.16+ 且不是 1.19.4 时使用 ProtocolLib 精确颜色
            applyProtocolLibGlow(entity, duration, color);
        } else if (useTeamSystem) {
            // 其他情况使用团队系统（16色）
            applyTeamColorGlow(entity, duration, color);
        } else {
            // 1.15 及以下降级
            entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(duration, 1));
        }

        // 应用粒子效果
        startParticleEffect(entity, duration, color);
    }

    /**
     * ProtocolLib 精确颜色发光（修复 1.19.4 兼容性）
     */
    private void applyProtocolLibGlow(LivingEntity entity, int duration, Color color) {
        try {
            // 清理旧效果
            Integer oldTaskId = activeGlowTasks.remove(entity.getEntityId());
            if (oldTaskId != null) {
                Bukkit.getScheduler().cancelTask(oldTaskId);
                removeGlowEffect(entity);
            }

            // 应用基础发光
            entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(duration, 0));

            // 发送颜色数据包
            sendGlowPacketSafe(entity, color);

            // 延迟清理
            int taskId = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                removeGlowEffect(entity);
                activeGlowTasks.remove(entity.getEntityId());
            }, duration).getTaskId();

            activeGlowTasks.put(entity.getEntityId(), taskId);

        } catch (Exception e) {
            plugin.getLogger().warning("ProtocolLib glow failed, falling back to team system: " + e.getMessage());
            applyTeamColorGlow(entity, duration, color);
        }
    }

    /**
     * 安全发送发光数据包（避免 1.19.4 的 ClassCastException）
     */
    private void sendGlowPacketSafe(LivingEntity entity, Color color) {
        try {
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
            packet.getIntegers().write(0, entity.getEntityId());

            // 方法1：尝试使用 WrappedDataWatcher（适用于大多数版本）
            if (!isVersion1_19_4) {
                sendViaWatcher(packet, entity, color);
            } else {
                // 1.19.4 特殊处理：使用原始数据包构建方式
                sendViaRawPacket(packet, entity, color);
            }

            // 广播数据包
            protocolManager.broadcastServerPacket(packet);

        } catch (Exception e) {
            plugin.getLogger().warning("Failed to send glow packet: " + e.getMessage());
        }
    }

    /**
     * 通过 WrappedDataWatcher 发送（标准方式）
     */
    private void sendViaWatcher(PacketContainer packet, LivingEntity entity, Color color) {
        WrappedDataWatcher watcher = new WrappedDataWatcher();

        // 设置发光标记（索引 0）
        WrappedDataWatcher.Serializer byteSerializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setObject(0, byteSerializer, (byte) 0x40);

        // 设置颜色（索引 11 或 12）
        int colorIndex = getColorIndex();
        if (colorIndex != -1 && VersionUtil.isVersionAtLeast(1, 16)) {
            int rgb = (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
            try {
                WrappedDataWatcher.Serializer intSerializer = WrappedDataWatcher.Registry.get(Integer.class);
                watcher.setObject(colorIndex, intSerializer, rgb);
            } catch (Exception e) {
                // 忽略颜色设置失败
            }
        }

        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
    }

    /**
     * 通过原始数据包构建发送（1.19.4 专用）
     */
    private void sendViaRawPacket(PacketContainer packet, LivingEntity entity, Color color) {
        try {
            // 使用反射直接构建数据
            List<Object> watchableObjects = new ArrayList<>();

            // 获取 DataWatcher 的内部类
            Class<?> dataWatcherItemClass = Class.forName("net.minecraft.network.syncher.DataWatcher$Item");
            Class<?> dataWatcherSerializerClass = Class.forName("net.minecraft.network.syncher.DataWatcher$b");

            // 获取序列化器实例
            Object byteSerializer = getSerializerByClass(byte.class);
            Object intSerializer = getSerializerByClass(int.class);

            // 构建发光标记
            Object glowItem = createDataWatcherItem(0, byteSerializer, (byte) 0x40);
            if (glowItem != null) {
                watchableObjects.add(glowItem);
            }

            // 构建颜色数据
            int rgb = (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
            Object colorItem = createDataWatcherItem(11, intSerializer, rgb);
            if (colorItem != null) {
                watchableObjects.add(colorItem);
            }

            // 通过反射设置到数据包
            Field watchableListField = packet.getHandle().getClass().getDeclaredField("f");
            watchableListField.setAccessible(true);
            watchableListField.set(packet.getHandle(), watchableObjects);

        } catch (Exception e) {
            plugin.getLogger().warning("Raw packet construction failed: " + e.getMessage());
            // 降级：只发送基础发光标记
            fallbackSendBasicGlow(packet);
        }
    }

    /**
     * 降级方案：只发送基础发光标记
     */
    private void fallbackSendBasicGlow(PacketContainer packet) {
        try {
            List<Object> watchableObjects = new ArrayList<>();
            Object byteSerializer = getSerializerByClass(byte.class);
            Object glowItem = createDataWatcherItem(0, byteSerializer, (byte) 0x40);
            if (glowItem != null) {
                watchableObjects.add(glowItem);
            }

            Field watchableListField = packet.getHandle().getClass().getDeclaredField("f");
            watchableListField.setAccessible(true);
            watchableListField.set(packet.getHandle(), watchableObjects);

        } catch (Exception e) {
            // 最终失败，什么都不做
        }
    }

    /**
     * 创建 DataWatcher Item
     */
    private Object createDataWatcherItem(int index, Object serializer, Object value) {
        try {
            Class<?> dataWatcherItemClass = Class.forName("net.minecraft.network.syncher.DataWatcher$Item");
            Constructor<?> constructor = dataWatcherItemClass.getDeclaredConstructor(int.class, Object.class, Object.class);
            constructor.setAccessible(true);
            return constructor.newInstance(index, serializer, value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取序列化器
     */
    private Object getSerializerByClass(Class<?> type) {
        try {
            Class<?> dataWatcherRegistryClass = Class.forName("net.minecraft.network.syncher.DataWatcherRegistry");
            if (type == byte.class) {
                Field field = dataWatcherRegistryClass.getField("a");
                return field.get(null);
            } else if (type == int.class) {
                Field field = dataWatcherRegistryClass.getField("c");
                return field.get(null);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 移除发光效果
     */
    private void removeGlowEffect(LivingEntity entity) {
        try {
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
            packet.getIntegers().write(0, entity.getEntityId());

            if (!isVersion1_19_4) {
                // 标准方式
                WrappedDataWatcher watcher = new WrappedDataWatcher();
                WrappedDataWatcher.Serializer byteSerializer = WrappedDataWatcher.Registry.get(Byte.class);
                watcher.setObject(0, byteSerializer, (byte) 0);
                packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
            } else {
                // 1.19.4 特殊方式
                List<Object> watchableObjects = new ArrayList<>();
                Object byteSerializer = getSerializerByClass(byte.class);
                Object clearItem = createDataWatcherItem(0, byteSerializer, (byte) 0);
                if (clearItem != null) {
                    watchableObjects.add(clearItem);
                }

                Field watchableListField = packet.getHandle().getClass().getDeclaredField("f");
                watchableListField.setAccessible(true);
                watchableListField.set(packet.getHandle(), watchableObjects);
            }

            protocolManager.broadcastServerPacket(packet);

        } catch (Exception e) {
            plugin.getLogger().fine("Failed to remove glow effect: " + e.getMessage());
        }
    }

    /**
     * 获取颜色数据的索引
     */
    private int getColorIndex() {
        // 根据不同版本返回正确的索引
        if (isVersion1_20Plus) {
            return 12; // 1.20+ 使用索引 12
        } else if (isVersion1_19_4) {
            return 11; // 1.19.4 使用索引 11
        } else {
            return 11; // 默认
        }
    }

    /**
     * 团队系统实现彩色发光（16色）
     */
    private void applyTeamColorGlow(LivingEntity entity, int duration, Color color) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        String teamName = "glow_" + entity.getEntityId() + "_" + System.currentTimeMillis();

        cleanupOldTeams(entity, scoreboard);

        try {
            Team team = scoreboard.registerNewTeam(teamName);
            ChatColor chatColor = convertToChatColor(color);
            team.setColor(chatColor);

            try {
                team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
                team.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.NEVER);
            } catch (Exception e) {
                // 旧版本忽略
            }

            team.addEntry(entity.getUniqueId().toString());

            if (VersionUtil.isVersionAtLeast(1, 19)) {
                entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(duration, 0));
            } else {
                entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(duration, 1));
            }

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Team t = scoreboard.getTeam(teamName);
                if (t != null) t.unregister();
            }, duration);

        } catch (Exception e) {
            entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(duration, 1));
        }
    }

    /**
     * 清理旧团队
     */
    private void cleanupOldTeams(LivingEntity entity, Scoreboard scoreboard) {
        String entityIdPrefix = "glow_" + entity.getEntityId();
        scoreboard.getTeams().forEach(team -> {
            if (team.getName().startsWith(entityIdPrefix)) {
                team.unregister();
            }
        });
    }

    /**
     * 颜色转换（16色匹配）
     */
    private ChatColor convertToChatColor(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        int[][] rgbValues = {
                {0, 0, 0}, {0, 0, 170}, {0, 170, 0}, {0, 170, 170},
                {170, 0, 0}, {170, 0, 170}, {255, 170, 0}, {170, 170, 170},
                {85, 85, 85}, {85, 85, 255}, {85, 255, 85}, {85, 255, 255},
                {255, 85, 85}, {255, 85, 255}, {255, 255, 85}, {255, 255, 255}
        };

        ChatColor[] colors = {
                ChatColor.BLACK, ChatColor.DARK_BLUE,
                ChatColor.DARK_GREEN, ChatColor.DARK_AQUA,
                ChatColor.DARK_RED, ChatColor.DARK_PURPLE,
                ChatColor.GOLD, ChatColor.GRAY,
                ChatColor.DARK_GRAY, ChatColor.BLUE,
                ChatColor.GREEN, ChatColor.AQUA,
                ChatColor.RED, ChatColor.LIGHT_PURPLE,
                ChatColor.YELLOW, ChatColor.WHITE
        };

        ChatColor bestMatch = ChatColor.WHITE;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < rgbValues.length; i++) {
            double distance = Math.sqrt(
                    Math.pow(r - rgbValues[i][0], 2) +
                            Math.pow(g - rgbValues[i][1], 2) +
                            Math.pow(b - rgbValues[i][2], 2)
            );

            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = colors[i];
            }
        }

        return bestMatch;
    }

    /**
     * 粒子效果
     */
    private void startParticleEffect(LivingEntity entity, int duration, Color color) {
        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= duration || !entity.isValid() || entity.isDead()) {
                    Bukkit.getScheduler().cancelTask(this.hashCode());
                    return;
                }
                spawnParticles(entity, color);
                ticks += 5;
            }
        }, 0L, 5L);
    }

    private void spawnParticles(LivingEntity entity, Color color) {
        World world = entity.getWorld();
        Location center = entity.getLocation().add(0, entity.getHeight() / 2, 0);

        if (useNewParticleAPI) {
            spawnParticlesV1_13Plus(world, center, color, entity);
        } else {
            spawnParticlesV1_12AndBelow(world, center, entity);
        }
    }

    private void spawnParticlesV1_13Plus(World world, Location center, Color color, LivingEntity entity) {
        try {
            Class<?> particleClass = Class.forName("org.bukkit.Particle");
            Object redstoneParticle = particleClass.getField("REDSTONE").get(null);

            Class<?> dustOptionsClass = Class.forName("org.bukkit.Particle$DustOptions");
            Object dustOptions = dustOptionsClass.getConstructor(
                    Class.forName("org.bukkit.Color"), float.class
            ).newInstance(color, 1.0f);

            Method spawnParticle = World.class.getMethod("spawnParticle",
                    particleClass, Location.class, int.class, double.class, double.class, double.class, double.class, Object.class);

            for (int i = 0; i < 8; i++) {
                double angle = i * Math.PI * 2 / 8;
                double x = Math.cos(angle) * 0.6;
                double z = Math.sin(angle) * 0.6;
                Location particleLoc = center.clone().add(x, 0, z);
                spawnParticle.invoke(world, redstoneParticle, particleLoc, 1, 0.0, 0.0, 0.0, 0.0, dustOptions);
            }

            for (int i = 0; i < 3; i++) {
                double offsetX = (Math.random() - 0.5) * 0.8;
                double offsetY = (Math.random() - 0.5) * entity.getHeight();
                double offsetZ = (Math.random() - 0.5) * 0.8;
                Location particleLoc = center.clone().add(offsetX, offsetY, offsetZ);
                spawnParticle.invoke(world, redstoneParticle, particleLoc, 1, 0.0, 0.0, 0.0, 0.0, dustOptions);
            }

        } catch (Exception e) {
            spawnSimpleParticles(world, center);
        }
    }

    private void spawnParticlesV1_12AndBelow(World world, Location center, LivingEntity entity) {
        try {
            Class<?> effectClass = Class.forName("org.bukkit.Effect");
            Object redstoneEffect = effectClass.getField("REDSTONE").get(null);
            Method playEffect = World.class.getMethod("playEffect", Location.class, effectClass, int.class);

            for (int i = 0; i < 5; i++) {
                double offsetX = (Math.random() - 0.5) * 1.0;
                double offsetY = (Math.random() - 0.5) * entity.getHeight();
                double offsetZ = (Math.random() - 0.5) * 1.0;
                Location particleLoc = center.clone().add(offsetX, offsetY, offsetZ);
                playEffect.invoke(world, particleLoc, redstoneEffect, 0);
            }
        } catch (Exception e) {
            // 忽略
        }
    }

    private void spawnSimpleParticles(World world, Location center) {
        try {
            world.playEffect(center, Effect.STEP_SOUND, 152);
        } catch (Exception e) {
            // 忽略
        }
    }
}
