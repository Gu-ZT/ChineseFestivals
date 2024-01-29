package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.data.BlockModelData;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class SpringFestival extends LunarFestival {
    private static final ModelResourceLocation DARK_PLATE = IFestival.registerBlockModel(new BlockModelData("plate").property("dark", "true", BooleanProperty::create));
    private static final ModelResourceLocation PLATE = IFestival.registerBlockModel(new BlockModelData("plate").property("dark", "false", BooleanProperty::create));

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
}
