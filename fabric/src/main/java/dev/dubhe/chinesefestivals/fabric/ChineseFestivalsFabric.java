package dev.dubhe.chinesefestivals.fabric;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.MoonFestival;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class ChineseFestivalsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChineseFestivals.init();
        Registry.register(BuiltInRegistries.BLOCK, ChineseFestivals.of("cake"), MoonFestival.MOON_CAKE);
        Registry.register(BuiltInRegistries.ITEM, ChineseFestivals.of("cake"), MoonFestival.MOON_CAKE_ITEM);
        Registry.register(BuiltInRegistries.ITEM, ChineseFestivals.of("pumpkin_pie"), MoonFestival.MOON_CAKE_ONLY_ITEM);
    }
}