package io.ncbpfluffybear.fluffymachines.machines;

import dev.j3fftw.extrautils.objects.NonHopperableBlock;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class WarpPad extends NonHopperableBlock implements HologramOwner {


    public WarpPad(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(onPlace(), onUse());

        registerBlockHandler(item.getItemId(), (p, b, tool, reason) -> {
            removeHologram(b);
            return true;
        });
    }

    private ItemHandler onPlace() {
        return new BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlockPlaced(), "type", "none");
                updateHologram(e.getBlockPlaced(), "&4&lX");
            }
        };
    }

    private ItemHandler onUse() {
        return (BlockUseHandler) PlayerRightClickEvent::cancel;
    }
}
