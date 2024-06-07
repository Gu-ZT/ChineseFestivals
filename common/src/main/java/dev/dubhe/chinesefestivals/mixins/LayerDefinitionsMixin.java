package dev.dubhe.chinesefestivals.mixins;

import com.google.common.collect.ImmutableMap;
import dev.dubhe.chinesefestivals.client.model.LoongBoatModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(LayerDefinitions.class)
public class LayerDefinitionsMixin {
    @Unique
    private static final String DEFAULT_LAYER = "main";

    @Contract(pure = true)
    @Inject(
        method = "createRoots",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/BoatModel;createBodyModel()Lnet/minecraft/client/model/geom/builders/LayerDefinition;"),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void createRoots(CallbackInfoReturnable<Map<ModelLayerLocation, LayerDefinition>> cir, ImmutableMap.@NotNull Builder<ModelLayerLocation, LayerDefinition> builder) {
        builder.put(LoongBoatModel.LAYER_LOCATION, LoongBoatModel.createBodyModel());
    }
}
