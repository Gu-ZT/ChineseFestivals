package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;

public class SpringFestival extends LunarFestival {
    private static final ModelResourceLocation DARK_PLATE = new ModelResourceLocation(ChineseFestivals.of("dark_plate"), "map=false");
    private static final ModelResourceLocation PLATE = new ModelResourceLocation(ChineseFestivals.of("plate"), "map=false");

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
}
