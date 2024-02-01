package dev.dubhe.chinesefestivals.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.commands.DebugCommands;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;
import java.util.function.Supplier;

@Mod(ChineseFestivals.MOD_ID)
public class ChineseFestivalsForge {
    @OnlyIn(Dist.CLIENT)
    public ChineseFestivalsForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ChineseFestivals.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ChineseFestivals.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCommand(RegisterClientCommandsEvent event) {
        DebugCommands.register(event.getDispatcher());
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Event {
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void register(RegisterEvent event) {
            for (Map.Entry<ResourceLocation, Supplier<Block>> entry : IFestival.BLOCK_REGISTER.entrySet()) {
                event.register(ForgeRegistries.Keys.BLOCKS, entry.getKey(), entry.getValue());
            }
            for (Map.Entry<ResourceLocation, Supplier<Item>> entry : IFestival.ITEM_REGISTER.entrySet()) {
                event.register(ForgeRegistries.Keys.ITEMS, entry.getKey(), entry.getValue());
            }
            for (Map.Entry<ResourceLocation, Supplier<PaintingVariant>> entry : IFestival.PAINTING_REGISTER.entrySet()) {
                event.register(ForgeRegistries.Keys.PAINTING_VARIANTS, entry.getKey(), entry.getValue());
            }
        }
    }
}