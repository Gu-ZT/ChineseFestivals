package dev.dubhe.chinesefestivals.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.client.model.LoongBoatModel;
import dev.dubhe.chinesefestivals.features.Features;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatRenderer.class)
public class BoatRendererMixin {
    @Unique
    private static final String DEFAULT_LAYER = "main";
    @Unique
    private static ListModel<Boat> chineseFestivals$model = null;

    @Inject(
        method = "<init>",
        at = @At("RETURN")
    )
    private void init(EntityRendererProvider.@NotNull Context context, boolean bl, CallbackInfo ci) {
        ModelLayerLocation modelLayerLocation = LoongBoatModel.LAYER_LOCATION;
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);
        BoatRendererMixin.chineseFestivals$model = new LoongBoatModel(modelPart);
    }

    @SuppressWarnings("unchecked")
    @Redirect(
        method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/util/Pair;getSecond()Ljava/lang/Object;"),
        remap = false
    )
    private <S> S getSecond(Pair<?, S> instance) {
        if (Features.LOONG_BOAT.get().isNow()) {
            return (S) BoatRendererMixin.chineseFestivals$model;
        }
        return instance.getSecond();
    }

    @SuppressWarnings("unchecked")
    @Redirect(
        method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/util/Pair;getFirst()Ljava/lang/Object;"),
        remap = false
    )
    private <S> S getFirst(Pair<S, ?> instance) {
        if (Features.LOONG_BOAT.get().isNow()) {
            return (S) ChineseFestivals.of("textures/entity/loong_boat.png");
        }
        return instance.getFirst();
    }

    @Inject(
        method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V")
    )
    private void render(Boat boat, float f, float g, @NotNull PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (Features.LOONG_BOAT.get().isNow()) {
            poseStack.translate(0.0, 1.0, 0.0);
            poseStack.rotateAround(Axis.YP.rotationDegrees(90), 0.0f, 0.0f, 0.0f);
        }
    }
}
