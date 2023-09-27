package dev.dubhe.chinesefestivals.fabric;

import dev.dubhe.chinesefestivals.ChineseFestivals;
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
        Registry.register(BuiltInRegistries.BLOCK, ChineseFestivals.of("cake"), new CakeBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));
    }
}