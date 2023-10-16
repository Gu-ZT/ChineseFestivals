package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IFestival {
    Map<ResourceLocation, IFactory<Block>> BLOCK_REGISTER = new HashMap<>();
    Map<ResourceLocation, IFactory<Item>> ITEM_REGISTER = new HashMap<>();

    String getId();

    boolean isNow();

    void refresh();

    default Map<Item, IFactory<Item>> getItemReplace() {
        return new ConcurrentHashMap<>();
    }

    default Map<Block, IFactory<Block>> getBlockReplace() {
        return new ConcurrentHashMap<>();
    }

    default Map<String, IFactory<String>> getTranslationReplace() {
        return new ConcurrentHashMap<>();
    }

    static IFactory<Block> createBlock(String id, BlockBehaviour.Properties properties, BlockFactory.BlockCreator<Block> creator) {
        BlockFactory<Block> blockFactory = new BlockFactory<>(properties, creator);
        BLOCK_REGISTER.put(ChineseFestivals.of(id), blockFactory);
        return blockFactory;
    }

    static IFactory<Item> createItem(String id, Item.Properties properties, ItemFactory.ItemCreator<Item> creator) {
        ItemFactory<Item> itemFactory = new ItemFactory<>(properties, creator);
        ITEM_REGISTER.put(ChineseFestivals.of(id), itemFactory);
        return itemFactory;
    }

    static IFactory<Item> createBlockItem(String id, IFactory<Block> block, Item.Properties properties, BlockItemFactory.ItemCreator<Item> creator) {
        BlockItemFactory<Block, Item> itemFactory = new BlockItemFactory<>(block, properties, creator);
        ITEM_REGISTER.put(ChineseFestivals.of(id), itemFactory);
        return itemFactory;
    }
}
