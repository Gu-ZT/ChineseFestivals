package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.List;

public class Plates extends Feature {
    private static final ModelResourceLocation DARK_PLATE = IFeature.registerBlockModel(new BlockModelData("plate").property("dark", "true", BooleanProperty::create));
    private static final ModelResourceLocation PLATE = IFeature.registerBlockModel(new BlockModelData("plate").property("dark", "false", BooleanProperty::create));
    public Plates(String id, IFestival... enableTimes) {
        super(id, Festivals.CHINESE_SPRING_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public ModelResourceLocation getItemFrameReplace(ItemFrame itemFrame, ItemStack innerItem) {
        if (itemFrame.getXRot() == -90.0 && innerItem.getItem().getFoodProperties() != null) {
            return itemFrame instanceof GlowItemFrame ? PLATE : DARK_PLATE;
        }
        return null;
    }

    @Override
    public Component getItemFrameTypeReplace(ItemFrame itemFrame) {
        if (itemFrame.getXRot() == -90.0 && itemFrame.getItem().getItem().getFoodProperties() != null) {
            return Component.translatable("entity.chinesefestivals.plate" + (itemFrame instanceof GlowItemFrame ? "" : "_dark"));
        }
        return null;
    }
}
