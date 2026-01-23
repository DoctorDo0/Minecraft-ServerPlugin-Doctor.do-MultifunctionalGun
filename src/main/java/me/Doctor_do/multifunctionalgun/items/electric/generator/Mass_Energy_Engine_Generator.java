package me.Doctor_do.multifunctionalgun.items.electric.generator;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Basic_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class Mass_Energy_Engine_Generator extends AGenerator implements EnergyNetProvider/*, EnergyNetComponent*/ {
    public static final Integer ENERGY_PRODUCTION = 2048;
    public static final Integer CAPACITY = ENERGY_PRODUCTION * 16;
    public static final Integer SPEED = 2;

    public Mass_Energy_Engine_Generator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBreak());
    }

    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
                Block block = blockBreakEvent.getBlock();
                BlockMenu inventory = BlockStorage.getInventory(block);

                if (inventory != null) {
                    inventory.dropItems(block.getLocation(), getInputSlots());
                    inventory.dropItems(block.getLocation(), getOutputSlots());
                }
            }
        };
    }

    @Override
    protected void registerDefaultFuelTypes() {
        super.registerFuel(new MachineFuel(2, Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE));
        super.registerFuel(new MachineFuel(8, Machine.ENERGY_STORAGE_CAN_FULL));
    }

    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.RESPAWN_ANCHOR);
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return Objects.requireNonNull(Machine.MASS_ENERGY_ENGINE_GENERATOR.getDisplayName());
    }

    @Override
    public int getEnergyProduction() {
        return ENERGY_PRODUCTION;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}
