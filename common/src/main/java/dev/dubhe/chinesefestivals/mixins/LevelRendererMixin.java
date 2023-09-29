package dev.dubhe.chinesefestivals.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Unique
    private static final ResourceLocation chineseFestivals$MOON_LOCATION = ChineseFestivals.of("textures/environment/moon_phases.png");

    @Shadow
    private @Nullable ClientLevel level;
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "renderLevel", at = @At("RETURN"))
    private void renderLevel(PoseStack poseStack, float f, long l, boolean bl, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo ci) {
        if (this.level == null) return;
        if (this.level.getGameTime() % 600 == 0) {
            new Thread(ChineseFestivals::refresh).start();
        }
        if (ChineseFestivals.hasChanged) {
            ChineseFestivals.hasChanged = false;
            this.minecraft.levelRenderer.allChanged();
        }
    }

    @Inject(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferUploader;drawWithShader(Lcom/mojang/blaze3d/vertex/BufferBuilder$RenderedBuffer;)V", ordinal = 2), locals = LocalCapture.CAPTURE_FAILHARD)
    private void renderSky(PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci, FogType fogType, Vec3 vec3, float g, float h, float i, BufferBuilder bufferBuilder, ShaderInstance shaderInstance, float[] fs, float j, Matrix4f matrix4f3, float l, int s, int t, int n, float u, float p, float q, float r) {
        if (
                !ChineseFestivals.MOON_FESTIVAL.isNow() ||
                        this.level == null
        ) return;
        RenderSystem.setShaderTexture(0, chineseFestivals$MOON_LOCATION);
        int moonPhase = this.level.getMoonPhase();
        int dayTime = (int) (this.level.getDayTime() % 24000);
        int col = moonPhase % 4;
        int row = moonPhase / 4 % 2;
        float x1 = (float) col / 4.0f;
        float y1 = (float) row / 2.0f;
        float x2 = (float) (col + 1) / 4.0f;
        float y2 = (float) (row + 1) / 2.0f;
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        l = 25.0f;
        if (dayTime <= 18000) {
            bufferBuilder.vertex(matrix4f3, -l, -100.0f, l).uv(x2, y2).endVertex();
            bufferBuilder.vertex(matrix4f3, l, -100.0f, l).uv(x1, y2).endVertex();
            bufferBuilder.vertex(matrix4f3, l, -100.0f, -l).uv(x1, y1).endVertex();
            bufferBuilder.vertex(matrix4f3, -l, -100.0f, -l).uv(x2, y1).endVertex();
        } else {
            bufferBuilder.vertex(matrix4f3, l, -100.0f, -l).uv(x2, y2).endVertex();
            bufferBuilder.vertex(matrix4f3, -l, -100.0f, -l).uv(x1, y2).endVertex();
            bufferBuilder.vertex(matrix4f3, -l, -100.0f, l).uv(x1, y1).endVertex();
            bufferBuilder.vertex(matrix4f3, l, -100.0f, l).uv(x2, y1).endVertex();
        }
        BufferUploader.drawWithShader(bufferBuilder.end());
    }

    @ModifyConstant(method = "renderSky", constant = @Constant(floatValue = 20.0f))
    private float injected(float x) {
        return ChineseFestivals.MOON_FESTIVAL.isNow() ? 0.0f : x;
    }
}
