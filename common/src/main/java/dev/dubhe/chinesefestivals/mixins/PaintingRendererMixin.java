package dev.dubhe.chinesefestivals.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.client.renderer.CustomPaintingRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(PaintingRenderer.class)
public class PaintingRendererMixin {
    @Redirect(method = "render(Lnet/minecraft/world/entity/decoration/Painting;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/Painting;getVariant()Lnet/minecraft/core/Holder;"))
    private Holder<PaintingVariant> modify(Painting painting) {
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (feature.get().isNow()) {
                PaintingVariant variant = feature.get().getPaintingReplace(painting);
                if (variant == null) continue;
                return Holder.direct(variant);
            }
        }
        return painting.getVariant();
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/decoration/Painting;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entitySolid(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType modifyRenderer(ResourceLocation arg) {
        return RenderType.entityCutout(arg);
    }

    @Shadow
    private void vertex(Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer vertexConsumer, float f, float g, float h, float i, float j, int k, int l, int m, int n){}

    @Inject(method = "renderPainting", at = @At("HEAD"), cancellable = true)
    private void rewriteRenderer(PoseStack poseStack, VertexConsumer vertexConsumer, Painting painting, int i, int j, TextureAtlasSprite textureAtlasSprite, TextureAtlasSprite textureAtlasSprite2, CallbackInfo ci) {
        PaintingVariant variant = IFeature.execute(it -> it.getPaintingReplace(painting));
        if (variant == null) return;
        CustomPaintingRenderer.render(poseStack, vertexConsumer, painting, i, j, textureAtlasSprite, this::vertex);
        ci.cancel();
    }
}
