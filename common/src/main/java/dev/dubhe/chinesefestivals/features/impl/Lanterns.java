package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class Lanterns extends Feature {
    public static final ModelResourceLocation LANTERN = IFeature.registerBlockModel(new BlockModelData("lantern_hanging"));
    public static final ModelResourceLocation TALL_LANTERN = IFeature.registerBlockModel(new BlockModelData("tall_lantern_hanging"));

    public Lanterns(String id, IFestival... enableTimes) {
        super(id, Festivals.CHINESE_SPRING_FESTIVAL, Festivals.LANTERN_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public ModelResourceLocation getBlockReplace(BlockState blockState) {
        if (blockState.is(Blocks.LANTERN) && blockState.getValue(LanternBlock.HANGING)) {
            return LANTERN;
        }
        if (blockState.is(Blocks.SOUL_LANTERN) && blockState.getValue(LanternBlock.HANGING)) {
            return TALL_LANTERN;
        }
        return null;
    }

    @Override
    public String getBlockTranslateReplace(Block block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        if (block instanceof LanternBlock && "minecraft".equals(key.getNamespace())) {
            switch (key.getPath()) {
                case "lantern":
                    return "block.chinesefestivals.lantern";
                case "soul_lantern":
                    return "block.chinesefestivals.tall_lantern";
            }
        }
        return null;
    }
}
