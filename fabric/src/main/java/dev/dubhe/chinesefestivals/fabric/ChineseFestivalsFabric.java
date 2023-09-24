package dev.dubhe.chinesefestivals.fabric;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.fabricmc.api.ModInitializer;

public class ChineseFestivalsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ChineseFestivals.init();
    }
}