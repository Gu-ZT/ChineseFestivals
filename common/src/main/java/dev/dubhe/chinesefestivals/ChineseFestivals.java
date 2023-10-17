package dev.dubhe.chinesefestivals;

import com.mojang.logging.LogUtils;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class ChineseFestivals {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "chinesefestivals";
    public static IFestival debugFestival = null;

    public static void init() {
        Festivals.init();
        Festivals.refresh();
        LOGGER.info("ChineseFestivals initialized");
    }

    public static ResourceLocation of(String id) {
        return new ResourceLocation(ChineseFestivals.MOD_ID, id);
    }
}
