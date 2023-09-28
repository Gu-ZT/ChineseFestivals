package dev.dubhe.chinesefestivals.fabric;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class ChineseFestivalsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChineseFestivals.init();
        for (Map.Entry<ResourceLocation, Block> entry : IFestival.BLOCK_REGISTER.entrySet()) {
            Registry.register(BuiltInRegistries.BLOCK, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<ResourceLocation, Item> entry : IFestival.ITEM_REGISTER.entrySet()) {
            Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue());
        }
    }
}