package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameRenderer.class)
public class ItemFrameRenderMixin {
    @Inject(
            method = "getFrameModelResourceLoc",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getRenderModel(ItemFrame itemFrame, ItemStack itemStack, CallbackInfoReturnable<ModelResourceLocation> cir) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                ModelResourceLocation replace = festival.getItemFrameReplace(itemFrame, itemStack);
                if (replace != null) cir.setReturnValue(replace);
            }
        }

    }
}