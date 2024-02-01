package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "getDescriptionId", at = @At("HEAD"), cancellable = true)
    private void modifyTranslate(CallbackInfoReturnable<String> cir) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                String translate = festival.getBlockTranslateReplace((Block) (Object) this);
                if (translate == null) continue;
                cir.setReturnValue(translate);
                return;
            }
        }
    }
}
