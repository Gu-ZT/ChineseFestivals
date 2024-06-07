package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.features.impl.ThreeDFood;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Mixin(ItemFrameRenderer.class)
public class ItemFrameRenderMixin {
    @Inject(
            method = "getFrameModelResourceLoc",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getRenderModel(ItemFrame itemFrame, ItemStack itemStack, CallbackInfoReturnable<ModelResourceLocation> cir) {
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (feature.get().isNow()) {
                ModelResourceLocation replace = feature.get().getItemFrameReplace(itemFrame, itemStack);
                if (replace != null) cir.setReturnValue(replace);
            }
        }

    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/decoration/ItemFrame;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Sheets;solidBlockSheet()Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType frameToCutout() {
        return Sheets.cutoutBlockSheet();
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/decoration/ItemFrame;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ItemFrame;getItem()Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack render3DFood(ItemFrame instance) {
        ItemStack itemStack = instance.getItem();
        if (!Features.PLATES.get().isNow() || instance.getXRot() != -90.0) return itemStack;
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (!feature.get().isNow()) continue;
            Map<Item, Supplier<Item>> itemSupplierMap = feature.get().get3DFoodReplace();
            for (Map.Entry<Item, Supplier<Item>> entry : itemSupplierMap.entrySet()) {
                if (!itemStack.is(entry.getKey())) continue;
                itemStack = new ItemStack(Holder.direct(entry.getValue().get()), itemStack.getCount(), Optional.of(itemStack.getOrCreateTag()));
            }
        }
        return itemStack;
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/decoration/ItemFrame;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ItemFrame;isInvisible()Z"))
    private boolean noRenderPlates(ItemFrame instance) {
        if (Features.PLATES.get().isNow() && instance.getXRot() == -90.0 && instance.getItem().is(ThreeDFood.HAS_PLATE)) {
            return true;
        }
        return instance.isInvisible();
    }
}
