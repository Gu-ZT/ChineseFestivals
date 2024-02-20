package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(BlockModelShaper.class)
public abstract class BlockModelShaperMixin {
    @Shadow
    @Final
    private ModelManager modelManager;

    @Inject(method = "getBlockModel", at = @At("RETURN"), cancellable = true)
    private void getBlockModel(BlockState blockState, CallbackInfoReturnable<BakedModel> cir) {
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (feature.get().isNow()) {
                ModelResourceLocation location = feature.get().getBlockReplace(blockState);
                if (location == null) continue;
                BakedModel bakedmodel = this.modelManager.getModel(location);
                cir.setReturnValue(bakedmodel);
                return;
            }
        }
    }
}
