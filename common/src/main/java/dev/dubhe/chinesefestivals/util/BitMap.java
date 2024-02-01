package dev.dubhe.chinesefestivals.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.dubhe.chinesefestivals.ChineseFestivals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BitMap {
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    public static final double[][] DRAGON = register("dragon.json");


    private static double[][] register(String filename) {
        try (
                InputStream is = BitMap.class.getClassLoader().getResourceAsStream("assets/chinesefestivals/fireworks/" + filename);
                InputStreamReader isr = new InputStreamReader(is)
        ) {
            int[][] value = GSON.fromJson(isr, int[][].class);
            double[][] result = new double[value.length][2];
            for (int i = 0; i < value.length; i++) {
                int[] pos = value[i];
                result[i] = new double[] {0.02 * (pos[0] - 128), 0.02 * (pos[1] - 128)};
            }

            return result;
        } catch (IOException | NullPointerException e) {
            ChineseFestivals.LOGGER.error("Error to read bitmap", e);
            return new double[0][0];
        }
    }
}