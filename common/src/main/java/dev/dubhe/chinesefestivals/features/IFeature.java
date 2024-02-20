package dev.dubhe.chinesefestivals.features;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.festivals.BlockFactory;
import dev.dubhe.chinesefestivals.festivals.BlockItemFactory;
import dev.dubhe.chinesefestivals.festivals.ItemFactory;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Supplier;

public interface IFeature {
    Map<ResourceLocation, Supplier<Block>> BLOCK_REGISTER = new HashMap<>();
    Map<ResourceLocation, Supplier<Item>> ITEM_REGISTER = new HashMap<>();
    Map<ResourceLocation, Supplier<PaintingVariant>> PAINTING_REGISTER = new HashMap<>();
    Collection<BlockModelData> BLOCK_MODELS = new HashSet<>();
    String getId();

    boolean isNow();

    default Map<Item, Supplier<Item>> getItemReplace() {
        return Collections.emptyMap();
    }

    default ModelResourceLocation getBlockReplace(BlockState blockState) {
        return null;
    }

    default Map<String, Supplier<String>> getTranslationReplace() {
        return Collections.emptyMap();
    }

    default String getBlockTranslateReplace(Block block) {
        return null;
    }

    default ModelResourceLocation getItemFrameReplace(ItemFrame itemFrame, ItemStack innerItem) {
        return null;
    }

    default Component getItemFrameTypeReplace(ItemFrame itemFrame) {
        return null;
    }

    default PaintingVariant getPaintingReplace(Painting painting) {
        return null;
    }

    default double[][] getFireworkParticle() {
        return null;
    }

    static Supplier<Block> createBlock(String id, BlockBehaviour.Properties properties, BlockFactory.BlockCreator<Block> creator) {
        BlockFactory<Block> blockFactory = new BlockFactory<>(properties, creator);
        BLOCK_REGISTER.put(ChineseFestivals.of(id), blockFactory);
        return blockFactory;
    }

    static Supplier<Item> createItem(String id, Item.Properties properties, ItemFactory.ItemCreator<Item> creator) {
        ItemFactory<Item> itemFactory = new ItemFactory<>(properties, creator);
        ITEM_REGISTER.put(ChineseFestivals.of(id), itemFactory);
        return itemFactory;
    }

    static Supplier<Item> createBlockItem(String id, Supplier<Block> block, Item.Properties properties, BlockItemFactory.ItemCreator<Item> creator) {
        BlockItemFactory<Block, Item> itemFactory = new BlockItemFactory<>(block, properties, creator);
        ITEM_REGISTER.put(ChineseFestivals.of(id), itemFactory);
        return itemFactory;
    }
}
