package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.data.BlockModelData;
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
import java.util.function.Function;
import java.util.function.Supplier;

public interface IFestival {
    Map<ResourceLocation, Supplier<Block>> BLOCK_REGISTER = new HashMap<>();
    Map<ResourceLocation, Supplier<Item>> ITEM_REGISTER = new HashMap<>();
    Map<ResourceLocation, Supplier<PaintingVariant>> PAINTING_REGISTER = new HashMap<>();
    Collection<BlockModelData> BLOCK_MODELS = new HashSet<>();

    String getId();

    boolean isNow();

    void refresh();

    default Map<Item, Supplier<Item>> getItemReplace() {
        return Collections.emptyMap();
    }

    @Deprecated
    default Map<Block, Supplier<Block>> getBlockReplace() {
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

    default double[][] getFireworkParticle() { return null; }

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

    static ModelResourceLocation registerBlockModel(BlockModelData model) {
        if (BLOCK_MODELS.stream().parallel().noneMatch(it -> it.resourceLocation.equals(model.resourceLocation))) {
            BLOCK_MODELS.add(model);
        }
        return model.model();
    }

    static PaintingVariant registerPainting(String id, int x, int y) {
        PaintingVariant variant = new PaintingVariant(x, y);
        PAINTING_REGISTER.put(ChineseFestivals.of(id), () -> variant);
        return variant;
    }

    static <T> T execute(Function<IFestival, T> supplier) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                T data = supplier.apply(festival);
                if (data == null) continue;
                return data;
            }
        }
        return null;
    }

}
