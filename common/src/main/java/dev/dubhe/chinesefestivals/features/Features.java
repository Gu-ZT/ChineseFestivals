package dev.dubhe.chinesefestivals.features;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.dubhe.chinesefestivals.features.impl.Dumplings;
import dev.dubhe.chinesefestivals.features.impl.SweetDumplings;
import dev.dubhe.chinesefestivals.festivals.IFestival;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Features {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(IFeature.class, new FeatureParser())
            .registerTypeAdapter(IFestival.class, new FeatureParser.FestivalParser())
            .create();
    public static final Map<String, BiFunction<String, IFestival[], IFeature>> FEATURE_GENERATORS = new ConcurrentHashMap<>();
    public static final Supplier<IFeature> DUMPLINGS = new FeatureGetter("dumplings", Dumplings::new);
    public static final Supplier<IFeature> SWEET_DUMPLINGS = new FeatureGetter("sweet_dumplings", SweetDumplings::new);

    public static class FeatureGetter implements Supplier<IFeature> {
        String id;
        BiFunction<String, IFestival[], IFeature> featureGenerator;
        IFeature feature = null;

        public FeatureGetter(String id, BiFunction<String, IFestival[], IFeature> feature) {
            this.id = id;
            this.featureGenerator = feature;
        }

        @Override
        public IFeature get() {
            return this.feature;
        }
    }
}
