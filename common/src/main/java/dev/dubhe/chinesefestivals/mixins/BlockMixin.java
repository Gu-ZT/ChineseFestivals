package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "getDescriptionId", at = @At("HEAD"), cancellable = true)
    private void modifyTranslate(CallbackInfoReturnable<String> cir) {
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (feature.get().isNow()) {
                String translate = feature.get().getBlockTranslateReplace((Block) (Object) this);
                if (translate == null) continue;
                cir.setReturnValue(translate);
                return;
            }
        }
    }
}
