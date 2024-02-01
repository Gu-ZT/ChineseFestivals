package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
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
import java.util.function.Supplier;

@Mixin(BlockModelShaper.class)
public abstract class BlockModelShaperMixin {
    @Shadow
    @Final
    private ModelManager modelManager;

    @Inject(method = "getBlockModel", at = @At("RETURN"), cancellable = true)
    private void getBlockModel(BlockState blockState, CallbackInfoReturnable<BakedModel> cir) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                // 原实现方式
                Supplier<Block> supplier = festival.getBlockReplace().get(blockState.getBlock());
                if (supplier != null) {
                    ResourceLocation location = BuiltInRegistries.BLOCK.getKey(supplier.get());
                    BakedModel bakedmodel = this.modelManager.getModel(BlockModelShaper.stateToModelLocation(location, blockState));
                    cir.setReturnValue(bakedmodel);
                    return;
                }
                // 第二种实现方式
                ModelResourceLocation location = festival.getBlockReplace(blockState);
                if (location == null) return;
                BakedModel bakedmodel = this.modelManager.getModel(location);
                cir.setReturnValue(bakedmodel);
                return;
            }
        }
    }
}
