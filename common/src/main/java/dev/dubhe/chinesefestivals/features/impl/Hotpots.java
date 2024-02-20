package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Hotpots extends Feature {
    public static final Supplier<Item> HOTPOT_N_ITEM = IFeature.createItem("hotpot_n", new Item.Properties(), Item::new);
    public static final Supplier<Item> HOTPOT_S_ITEM = IFeature.createItem("hotpot_s", new Item.Properties(), Item::new);
    public static final ModelResourceLocation HOTPOT_N = IFeature.registerBlockModel(new BlockModelData("hotpot_n"));
    public static final ModelResourceLocation HOTPOT_S = IFeature.registerBlockModel(new BlockModelData("hotpot_s"));
    public Hotpots(String id, IFestival... enableTimes) {
        super(id, Festivals.DONG_ZHI_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public ModelResourceLocation getBlockReplace(BlockState blockState) {
        if (blockState.is(Blocks.CAMPFIRE)) {
            if (blockState.getValue(CampfireBlock.LIT)) {
                return HOTPOT_S;
            }
        } else if (blockState.is(Blocks.SOUL_CAMPFIRE)) {
            if (blockState.getValue(CampfireBlock.LIT)) {
                return HOTPOT_N;
            }

        }
        return null;
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.CAMPFIRE, HOTPOT_S_ITEM);
        map.put(Items.SOUL_CAMPFIRE, HOTPOT_N_ITEM);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("item.minecraft.campfire", () -> "item.chinesefestivals.hotpot_s");
        map.put("item.minecraft.soul_campfire", () -> "item.chinesefestivals.hotpot_n");
        map.put("item.minecraft.rabbit_stew", () -> "item.chinesefestivals.dumplings");
        map.put("block.minecraft.campfire", () -> "block.chinesefestivals.hotpot_s");
        map.put("block.minecraft.soul_campfire", () -> "block.chinesefestivals.hotpot_n");
        return map;
    }
}
