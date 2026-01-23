package me.Doctor_do.multifunctionalgun.items.electric.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Basic_Materials;
import me.Doctor_do.multifunctionalgun.setup.slimefun_items.Machine;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Energy_Converter extends AContainer implements RecipeDisplayItem/*, EnergyNetProvider*/ {
    public static final Integer ENERGY_CONSUMPTION = 4096;
    public static final Integer CAPACITY = ENERGY_CONSUMPTION * 4;
    public static final Integer SPEED = 4;

    public Energy_Converter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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
    protected void registerDefaultRecipes() {
        registerRecipe(4,
                new ItemStack[]{
                        new CustomItemStack(Basic_Materials.ANTI_GRAVITY_VOID_PARTICLE, 4),
                        new CustomItemStack(Machine.ENERGY_STORAGE_CAN_EMPTY)},
                new ItemStack[]{new CustomItemStack(Machine.ENERGY_STORAGE_CAN_FULL)});
        registerRecipe(16,
                new ItemStack[]{new CustomItemStack(Machine.ENERGY_STORAGE_CAN_EMPTY)},
                new ItemStack[]{new CustomItemStack(Machine.ENERGY_STORAGE_CAN_FULL)});
    }

    // 修改菜单中，机器下方的展示内容，注释后引发实现接口警告
    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

        for (MachineRecipe recipe : recipes) {
            for (int i = 0; i < recipe.getInput().length; i++) {
                if (i == 0) {
                    displayRecipes.add(recipe.getInput()[i]);
                    displayRecipes.add(recipe.getOutput()[recipe.getOutput().length - 1]);
                } else {
                    displayRecipes.add(recipe.getInput()[i]);
                    displayRecipes.add(new CustomItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, ""));
                }
            }
        }

        return displayRecipes;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.RESPAWN_ANCHOR);
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return Objects.requireNonNull(Machine.ENERGY_CONVERTER.getDisplayName());
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "ENERGY_CONVERTER";
    }
}
