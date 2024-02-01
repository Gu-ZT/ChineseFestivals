package dev.dubhe.chinesefestivals.util;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class ParticleUtil {
    public static void createParticleSingleShape(double x, double y, double z, RandomSource random, CreateParticle createParticle, double density, double[][] particles, int[] colors, int[] fadeColors, boolean trail, boolean flicker) {

        for (double[] particle : particles) {

            createParticle.run(x, y, z, particle[0], particle[1], 1, colors, fadeColors, trail, flicker);

        }


//        System.out.println("a");
//        double d0 = particles[0][0];
//        double d1 = particles[0][1];
//        createParticle.run(x, y, z, d0 * density, d1 * density, 0.0D, colors, fadeColors, trail, flicker);
//        double d3 = random.nextFloat() * (float) Math.PI * 0.034D;
//
//        for (int j = 1; j < particles.length; ++j) {
//            double d6 = particles[j][0];
//            double d7 = particles[j][1];
//
//            for (double d8 = 0.25D; d8 <= 1.0D; d8 += 0.25D) {
//                double d9 = d6;
//                double d10 = d7;
//                double d11 = d9 * Math.sin(d3);
//                d9 *= Math.cos(d3);
//
//                for (double d12 = -1.0D; d12 <= 1.0D; d12 += 2.0D) {
//                    createParticle.run(x, y, z, d9 * d12, d10, d11 * d12, colors, fadeColors, trail, flicker);
//                }
//            }
//
//        }
    }

    @FunctionalInterface
    public interface CreateParticle {
        void run(double d, double e, double f, double g, double h, double i, int[] is, int[] js, boolean bl, boolean bl2);
    }

}
