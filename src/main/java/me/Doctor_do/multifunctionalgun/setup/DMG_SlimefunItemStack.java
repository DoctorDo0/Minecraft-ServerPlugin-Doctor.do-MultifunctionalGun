package me.Doctor_do.multifunctionalgun.setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;

public class DMG_SlimefunItemStack extends SlimefunItemStack {
    public DMG_SlimefunItemStack(String id, Material material, String name, String... lore) {
        super("DMG_" + id, material, name, lore);
    }
}
