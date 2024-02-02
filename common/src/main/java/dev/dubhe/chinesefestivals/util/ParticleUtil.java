package dev.dubhe.chinesefestivals.util;

import net.minecraft.util.RandomSource;

public class ParticleUtil {
    public static void createParticleSingleShape(double x, double y, double z, RandomSource random, CreateParticle createParticle, double[][] particles, int[] colors, int[] fadeColors, boolean trail, boolean flicker) {
        double rotate = random.nextFloat() * (float) Math.PI * 2;
        for (double[] particle : particles) {
            double dx = particle[0];
            double dy = particle[1];
            double dz = dx * Math.sin(rotate);
            dx *= Math.cos(rotate);
            createParticle.run(x, y, z, dx, dy, dz, colors, fadeColors, trail, flicker);
        }
    }

    @FunctionalInterface
    public interface CreateParticle {
        void run(double d, double e, double f, double g, double h, double i, int[] is, int[] js, boolean bl, boolean bl2);
    }

}
