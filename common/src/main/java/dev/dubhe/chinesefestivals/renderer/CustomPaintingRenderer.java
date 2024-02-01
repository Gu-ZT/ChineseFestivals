package dev.dubhe.chinesefestivals.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.dubhe.chinesefestivals.commands.DebugCommands;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.Painting;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class CustomPaintingRenderer {
    public static void render(PoseStack poseStack, VertexConsumer vertexConsumer, Painting painting, int i, int j, TextureAtlasSprite textureAtlasSprite, Vertex vertex) {
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();
        float f = (float) (-i) / 2.0F;
        float g = (float) (-j) / 2.0F;
        int w = i / 16;
        int x = j / 16;
        double d = 1.0 / (double) w;
        double e = 1.0 / (double) x;

        for (int y = 0; y < w; ++y) {
            for (int z = 0; z < x; ++z) {
                float aa = f + (float) ((y + 1) * 16);
                float ab = f + (float) (y * 16);
                float ac = g + (float) ((z + 1) * 16);
                float ad = g + (float) (z * 16);
                int ae = painting.getBlockX();
                int af = Mth.floor(painting.getY() + (double) ((ac + ad) / 2.0F / 16.0F));
                int ag = painting.getBlockZ();
                Direction direction = painting.getDirection();
                if (direction == Direction.NORTH) {
                    ae = Mth.floor(painting.getX() + (double) ((aa + ab) / 2.0F / 16.0F));
                }

                if (direction == Direction.WEST) {
                    ag = Mth.floor(painting.getZ() - (double) ((aa + ab) / 2.0F / 16.0F));
                }

                if (direction == Direction.SOUTH) {
                    ae = Mth.floor(painting.getX() - (double) ((aa + ab) / 2.0F / 16.0F));
                }

                if (direction == Direction.EAST) {
                    ag = Mth.floor(painting.getZ() + (double) ((aa + ab) / 2.0F / 16.0F));
                }

                int ah = LevelRenderer.getLightColor(painting.level(), new BlockPos(ae, af, ag));
                float ai = textureAtlasSprite.getU((float) (d * (double) (w - y)));
                float aj = textureAtlasSprite.getU((float) (d * (double) (w - (y + 1))));
                float ak = textureAtlasSprite.getV((float) (e * (double) (x - z)));
                float al = textureAtlasSprite.getV((float) (e * (double) (x - (z + 1))));
                vertex.run(matrix4f, matrix3f, vertexConsumer, aa, ad, aj, ak, DebugCommands.test, 0, 0, -1, ah);
                vertex.run(matrix4f, matrix3f, vertexConsumer, ab, ad, ai, ak, DebugCommands.test, 0, 0, -1, ah);
                vertex.run(matrix4f, matrix3f, vertexConsumer, ab, ac, ai, al, DebugCommands.test, 0, 0, -1, ah);
                vertex.run(matrix4f, matrix3f, vertexConsumer, aa, ac, aj, al, DebugCommands.test, 0, 0, -1, ah);
            }
        }
    }

    @FunctionalInterface
    public interface Vertex {
        void run(Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer vertexConsumer, float f, float g, float h, float i, float j, int k, int l, int m, int n);
    }
}
