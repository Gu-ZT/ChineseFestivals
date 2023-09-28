package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(BlockModelShaper.class)
public abstract class BlockModelShaperMixin {
    @Shadow
    @Final
    private ModelManager modelManager;

    @Inject(method = "getBlockModel", at = @At("RETURN"), cancellable = true)
    private void getBlockModel(BlockState blockState, CallbackInfoReturnable<BakedModel> cir) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) for (Map.Entry<Block, Block> entry : festival.getBlockReplace().entrySet()) {
                if (blockState.is(entry.getKey())) {
                    ResourceLocation location = BuiltInRegistries.BLOCK.getKey(entry.getValue());
                    BakedModel bakedmodel = this.modelManager.getModel(BlockModelShaper.stateToModelLocation(location, blockState));
                    cir.setReturnValue(bakedmodel);
                    return;
                }
            }
        }
    }
}
