package me.Doctor_do.multifunctionalgun.utils;

import org.bukkit.Bukkit;

public class VersionUtil {
    private static final String SERVER_VERSION;
    private static final int MAJOR_VERSION;
    private static final int MINOR_VERSION;

    static {
        String version = Bukkit.getServer().getClass().getPackage().getName();
        SERVER_VERSION = version.substring(version.lastIndexOf('.') + 1);

        // 解析版本号，如 "v1_16_R3" -> 1.16
        String[] parts = SERVER_VERSION.split("_");
        MAJOR_VERSION = Integer.parseInt(parts[0].substring(1));
        MINOR_VERSION = Integer.parseInt(parts[1]);
    }

    public static boolean isVersionAtLeast(int major, int minor) {
        return MAJOR_VERSION > major || (MAJOR_VERSION == major && MINOR_VERSION >= minor);
    }

    public static boolean isVersionBetween(int minMajor, int minMinor, int maxMajor, int maxMinor) {
        return (MAJOR_VERSION > minMajor || (MAJOR_VERSION == minMajor && MINOR_VERSION >= minMinor)) &&
                (MAJOR_VERSION < maxMajor || (MAJOR_VERSION == maxMajor && MINOR_VERSION <= maxMinor));
    }

    public static String getVersion() {
        return SERVER_VERSION;
    }

    public static int getMajorVersion() {
        return MAJOR_VERSION;
    }

    public static int getMinorVersion() {
        return MINOR_VERSION;
    }
}
