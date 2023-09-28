package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IFestival {
    Map<ResourceLocation, Block> BLOCK_REGISTER = new HashMap<>();
    Map<ResourceLocation, Item> ITEM_REGISTER = new HashMap<>();

    String getId();

    boolean isNow();

    void refresh();

    default Map<Item, Item> getItemReplace() {
        return new ConcurrentHashMap<>();
    }

    default Map<Block, Block> getBlockReplace() {
        return new ConcurrentHashMap<>();
    }

    default Map<String, String> getTranslationReplace() {
        return new ConcurrentHashMap<>();
    }

    static Block createBlock(String id, Block block) {
        BLOCK_REGISTER.put(ChineseFestivals.of(id), block);
        return block;
    }

    static Item createItem(String id, Item item) {
        ITEM_REGISTER.put(ChineseFestivals.of(id), item);
        return item;
    }
}
