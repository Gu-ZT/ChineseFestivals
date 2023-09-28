package dev.dubhe.chinesefestivals.fabric;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.MoonFestival;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ChineseFestivalsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChineseFestivals.init();
        Registry.register(BuiltInRegistries.BLOCK, ChineseFestivals.of("mooncakes"), MoonFestival.MOONCAKES);
        Registry.register(BuiltInRegistries.ITEM, ChineseFestivals.of("mooncakes"), MoonFestival.MOONCAKES_ITEM);
        Registry.register(BuiltInRegistries.ITEM, ChineseFestivals.of("mooncake"), MoonFestival.MOONCAKE_ITEM);
    }
}