package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.util.BitMap;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.function.Supplier;

public class SpringFestival extends LunarFestival {
    private static final ModelResourceLocation DARK_PLATE = IFestival.registerBlockModel(new BlockModelData("plate").property("dark", "true", BooleanProperty::create));
    private static final ModelResourceLocation PLATE = IFestival.registerBlockModel(new BlockModelData("plate").property("dark", "false", BooleanProperty::create));
    private static final ModelResourceLocation LANTERN = IFestival.registerBlockModel(new BlockModelData("lantern_hanging"));
    private static final ModelResourceLocation TALL_LANTERN = IFestival.registerBlockModel(new BlockModelData("tall_lantern_hanging"));
    private static final PaintingVariant COUPLET_LEFT = IFestival.registerPainting("couplet_left", 16, 32);
    private static final PaintingVariant COUPLET_RIGHT = IFestival.registerPainting("couplet_right", 16, 32);
    private static final PaintingVariant COUPLET_TOP = IFestival.registerPainting("couplet_top", 16, 32);
    private static final PaintingVariant COUPLET_FU = IFestival.registerPainting("couplet_fu", 32, 32);


    public SpringFestival() {
        super("spring", 12, 23, 1, 8);
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

    @Override
    public PaintingVariant getPaintingReplace(Painting painting) {
        ResourceLocation location = painting.getVariant().unwrapKey().orElse(PaintingVariants.KEBAB).location();
        if ("minecraft".equals(location.getNamespace())) {
            switch (location.getPath()) {
                case "graham":
                    return COUPLET_LEFT;
                case "wanderer":
                    return COUPLET_RIGHT;
                case "creebet":
                    return COUPLET_TOP;
                case "bust":
                    return COUPLET_FU;
            }
        }
        return null;
    }

    @Override
    public double[][] getFireworkParticle() {
        return BitMap.DRAGON;
    }
}
