package me.Doctor_do.multifunctionalgun.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;

public final class DMG_SFItemStack extends SlimefunItemStack {
    public DMG_SFItemStack(String id, Material material, String name, String lore) {
        super("DMG_" + id, material, name, lore);
    }
}
