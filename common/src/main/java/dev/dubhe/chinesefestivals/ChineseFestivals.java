package dev.dubhe.chinesefestivals;

import com.mojang.logging.LogUtils;
import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiFunction;

public class ChineseFestivals {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "chinesefestivals";
    public static final String FEATURES_CONFIG = "features";
    public static IFestival debugFestival = null;
    public static ChineseFestivalsContext context = new ChineseFestivalsContext();

    public static void init(ChineseFestivalsContext context) {
        ChineseFestivals.context = context;
        Festivals.refresh();
        LOGGER.info("ChineseFestivals initialized");
    }

    public static ResourceLocation of(String id) {
        return new ResourceLocation(ChineseFestivals.MOD_ID, id);
    }

    public static IFeature getOrCreateFeature(String id, BiFunction<String, IFestival[], IFeature> featureGenerator) {
        File featureFile = getFeatureFile(id);
        if (!featureFile.isFile()) {
            IFeature feature = featureGenerator.apply(id, new IFestival[]{});
            try {
                if (!featureFile.createNewFile()) {
                    throw new IOException();
                }
                try (FileWriter writer = new FileWriter(featureFile)) {
                    Features.GSON.toJson(feature, IFeature.class, writer);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create feature file", e);
            }
            return feature;
        }
        try (FileReader reader = new FileReader(featureFile)) {
            return Features.GSON.fromJson(reader, IFeature.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read feature file", e);
        }
    }

    @NotNull
    private static File getFeatureFile(String id) {
        if (ChineseFestivals.context.configPath == null)
            throw new RuntimeException("ChineseFestivals context not initialized");
        Path featurePath = ChineseFestivals.context.configPath.resolve(MOD_ID).resolve(ChineseFestivals.FEATURES_CONFIG);
        File featurePathFile = featurePath.toFile();
        if (!featurePathFile.isDirectory() && !featurePath.toFile().mkdirs()) {
            throw new RuntimeException("Failed to create feature directory");
        }
        return featurePath.resolve("%s.json".formatted(id)).toFile();
    }

    public static class ChineseFestivalsContext {
        public Path configPath = new File(".").toPath().resolve("config");
    }
}
