package dev.dubhe.chinesefestivals.fabric;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.features.IFeature;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Supplier;

public class ChineseFestivalsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChineseFestivals.ChineseFestivalsContext context = new ChineseFestivals.ChineseFestivalsContext();
        context.configPath = FabricLoader.getInstance().getConfigDir();
        ChineseFestivals.init(context);
        for (Map.Entry<ResourceLocation, Supplier<Block>> entry : IFeature.BLOCK_REGISTER.entrySet()) {
            Registry.register(BuiltInRegistries.BLOCK, entry.getKey(), entry.getValue().get());
        }
        for (Map.Entry<ResourceLocation, Supplier<Item>> entry : IFeature.ITEM_REGISTER.entrySet()) {
            Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue().get());
        }
        for (Map.Entry<ResourceLocation, Supplier<PaintingVariant>> entry : IFeature.PAINTING_REGISTER.entrySet()) {
            Registry.register(BuiltInRegistries.PAINTING_VARIANT, entry.getKey(), entry.getValue().get());
        }
    }
}