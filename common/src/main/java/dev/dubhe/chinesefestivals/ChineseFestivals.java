package dev.dubhe.chinesefestivals;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.resources.ResourceLocation;

public class ChineseFestivals {
    public static final String MOD_ID = "chinesefestivals";
    public static IFestival debugFestival = null;

    public static void init() {
        Festivals.init();
        Festivals.refresh();
    }

    public static ResourceLocation of(String id) {
        return new ResourceLocation(ChineseFestivals.MOD_ID, id);
    }
}
