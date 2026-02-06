package me.Doctor_do.multifunctionalgun.items.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.Doctor_do.multifunctionalgun.MultifunctionalGun;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items_setup.Gun_And_Bullet_Item_Setup;
import me.Doctor_do.multifunctionalgun.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Test_Item extends SlimefunItem implements NotPlaceable {

    public Test_Item(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        hidden = true;
        enchantable = false;
        disenchantable = false;
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler_Right = this::onItemUseRightClick;
        addItemHandler(itemUseHandler_Right);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        event.cancel();

        spawnParticle(player);

        changeLore(item);

        Utils.sendMessage(player, "Test_Item_RightClick");
    }

    private void spawnParticle(Player player) {
        Location playerLocation = player.getLocation();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        Color[] randomColors = new Color[]{Color.fromRGB(red, green, blue)};

        for (Color color : randomColors) {
            for (int i = 0; i < 25; i++) {
                float xRand = (random.nextFloat() - 0.5F) * 3.2F;
                float yRand = (random.nextFloat() - 0.5F) * 3.2F;
                float zRand = (random.nextFloat() - 0.5F) * 3.2F;

                player.getWorld().spawnParticle(Particle.REDSTONE,
                        playerLocation.getX() + (double) xRand,
                        playerLocation.getY() + 2.0D + (double) yRand,
                        playerLocation.getZ() + (double) zRand,
                        i,
                        new Particle.DustOptions(color, 1));
            }
        }
        Utils.sendMessage(player, "RGB: " + red + " " + green + " " + blue);
        String redFormattedHexString = String.format("%02X", red);
        String greenFormattedHexString = String.format("%02X", green);
        String blueFormattedHexString = String.format("%02X", blue);
        Utils.sendMessage(player,
                "§x" +
                        "§" + redFormattedHexString.toLowerCase().charAt(0) +
                        "§" + redFormattedHexString.toLowerCase().charAt(1) +
                        "§" + greenFormattedHexString.toLowerCase().charAt(0) +
                        "§" + greenFormattedHexString.toLowerCase().charAt(1) +
                        "§" + blueFormattedHexString.toLowerCase().charAt(0) +
                        "§" + blueFormattedHexString.toLowerCase().charAt(1) +
                        "#" +
                        redFormattedHexString.toUpperCase().charAt(0) +
                        redFormattedHexString.toUpperCase().charAt(1) +
                        "_" +
                        greenFormattedHexString.toUpperCase().charAt(0) +
                        greenFormattedHexString.toUpperCase().charAt(1) +
                        "_" +
                        blueFormattedHexString.toUpperCase().charAt(0) +
                        blueFormattedHexString.toUpperCase().charAt(1)
        );
    }

    private void changeLore(ItemStack item) {
        var itemMeta = item.getItemMeta();
        assert itemMeta != null;
        List<String> lore = itemMeta.getLore();
        assert lore != null;
        if (lore.contains("§f-->右键增加标签并覆盖本标签<--")) {
            lore.remove("§f-->右键增加标签并覆盖本标签<--");
            lore.add("§7Plugin:" + MultifunctionalGun.getInstance().toString());
            lore.add("§7Plugin:Version:" + MultifunctionalGun.getInstance().getDescription().getVersion());
            lore.add("§7Hash Code:" + MultifunctionalGun.getInstance().hashCode());
            lore.add("§7Item: " + Gun_And_Bullet_Item_Setup.getEndlessWeaponInstance().toString());
            lore.add("§8TEST_ITEM");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
        }
    }
}
