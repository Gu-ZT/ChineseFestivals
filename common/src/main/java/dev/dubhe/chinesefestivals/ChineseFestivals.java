package dev.dubhe.chinesefestivals;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import net.minecraft.resources.ResourceLocation;

public class ChineseFestivals {
    public static final String MOD_ID = "chinesefestivals";
    public static String debugFestival = null;

    public static void init() {
        Festivals.init();
        Festivals.refresh();
    }

    public static ResourceLocation of(String id) {
        return new ResourceLocation(ChineseFestivals.MOD_ID, id);
    }
}
