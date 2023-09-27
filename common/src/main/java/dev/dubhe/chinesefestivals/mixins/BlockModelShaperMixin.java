package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelShaper.class)
public abstract class BlockModelShaperMixin {
    @Shadow
    @Final
    private ModelManager modelManager;

    @Inject(method = "getBlockModel", at = @At("RETURN"), cancellable = true)
    private void getBlockModel(BlockState blockState, CallbackInfoReturnable<BakedModel> cir) {
        if (ChineseFestivals.MOON_FESTIVAL.isNow() && blockState.is(Blocks.CAKE)) {
            ResourceLocation location = ChineseFestivals.of("cake");
            BakedModel bakedmodel = this.modelManager.getModel(BlockModelShaper.stateToModelLocation(location, blockState));
            cir.setReturnValue(bakedmodel);
        }
    }
}
