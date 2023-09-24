package dev.dubhe.chinesefestivals.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ChineseFestivals.MOD_ID)
public class ChineseFestivalsForge {
    public ChineseFestivalsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ChineseFestivals.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ChineseFestivals.init();
    }
}