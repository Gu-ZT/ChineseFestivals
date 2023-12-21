package dev.dubhe.chinesefestivals.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CampfireRenderer.class)
public class CampfireRendererMixin {
    @Shadow
    @Final
    private ItemRenderer itemRenderer;

    @Inject(method = "render(Lnet/minecraft/world/level/block/entity/CampfireBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"), cancellable = true)
    public void render(CampfireBlockEntity campfireBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, CallbackInfo ci) {
        Boolean bl = campfireBlockEntity.getBlockState().getValue(CampfireBlock.LIT);
        if (Festivals.DONG_ZHI_FESTIVAL.isNow() && bl) {
            Direction direction = campfireBlockEntity.getBlockState().getValue(CampfireBlock.FACING);
            NonNullList<ItemStack> nonNullList = campfireBlockEntity.getItems();
            int k = (int) campfireBlockEntity.getBlockPos().asLong();
            long gameTime = campfireBlockEntity.getLevel() != null ? campfireBlockEntity.getLevel().getGameTime() : 0;
            BlockPos pos = campfireBlockEntity.getBlockPos();
            gameTime += pos.asLong();
            for (int l = 0; l < nonNullList.size(); ++l) {
                double time = Math.floorMod(gameTime + l, 6) + f;
                double n = Math.sin(time);
                ItemStack itemStack = nonNullList.get(l);
                if (itemStack != ItemStack.EMPTY) {
                    poseStack.pushPose();
                    poseStack.translate(0.5F, 0.33921875F + n * 0.018F, 0.5F);
                    Direction direction2 = Direction.from2DDataValue((l + direction.get2DDataValue()) % 4);
                    float g = -direction2.toYRot();
                    poseStack.mulPose(Axis.YP.rotationDegrees(g));
                    poseStack.mulPose(Axis.XP.rotationDegrees((float) (90.0F + 2.5F * n)));
                    poseStack.mulPose(Axis.ZP.rotationDegrees((float) (2.5F * n)));
                    poseStack.translate(-0.2625F, -0.2625F, 0.0F);
                    poseStack.scale(0.375F, 0.375F, 0.375F);
                    this.itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, i, j, poseStack, multiBufferSource, campfireBlockEntity.getLevel(), k + l);
                    poseStack.popPose();
                }
            }
            ci.cancel();
        }
    }
}
