package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Mooncakes extends Feature {
    public static final Supplier<Item> MOONCAKES_ITEM = IFeature.createItem("mooncakes", new Item.Properties().stacksTo(1), Item::new);
    public static final Supplier<Item> MOONCAKE_ITEM = IFeature.createItem("mooncake", new Item.Properties().food(Foods.PUMPKIN_PIE), Item::new);
    public static final ModelResourceLocation MOONCAKES_0 = IFeature.registerBlockModel(new BlockModelData("mooncakes"));
    public static final ModelResourceLocation MOONCAKES_1 = IFeature.registerBlockModel(new BlockModelData("mooncakes_slice1"));
    public static final ModelResourceLocation MOONCAKES_2 = IFeature.registerBlockModel(new BlockModelData("mooncakes_slice2"));
    public static final ModelResourceLocation MOONCAKES_3 = IFeature.registerBlockModel(new BlockModelData("mooncakes_slice3"));
    public static final ModelResourceLocation MOONCAKES_4 = IFeature.registerBlockModel(new BlockModelData("mooncakes_slice4"));
    public static final ModelResourceLocation MOONCAKES_5 = IFeature.registerBlockModel(new BlockModelData("mooncakes_slice5"));
    public static final ModelResourceLocation MOONCAKES_6 = IFeature.registerBlockModel(new BlockModelData("mooncakes_slice6"));

    public Mooncakes(String id, IFestival... enableTimes) {
        super(id, Festivals.MOON_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.CAKE, MOONCAKES_ITEM);
        map.put(Items.PUMPKIN_PIE, MOONCAKE_ITEM);
        return map;
    }

    @Override
    public ModelResourceLocation getBlockReplace(BlockState blockState) {
        if (blockState.is(Blocks.CAKE)) {
            return switch (blockState.getValue(CakeBlock.BITES)) {
                case 1 -> MOONCAKES_1;
                case 2 -> MOONCAKES_2;
                case 3 -> MOONCAKES_3;
                case 4 -> MOONCAKES_4;
                case 5 -> MOONCAKES_5;
                case 6 -> MOONCAKES_6;
                default -> MOONCAKES_0;
            };
        }
        return null;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("block.minecraft.cake", () -> "block.chinesefestivals.mooncakes");
        map.put("item.minecraft.pumpkin_pie", () -> "item.chinesefestivals.mooncake");
        return map;
    }
}
