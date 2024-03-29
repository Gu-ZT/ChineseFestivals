package dev.dubhe.chinesefestivals.features;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.features.impl.*;
import dev.dubhe.chinesefestivals.festivals.IFestival;

import java.util.List;
import java.util.Map;
import java.util.Vector;
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
    public static final Supplier<IFeature> COUPLETS = new FeatureGetter("couplets", Couplets::new);
    public static final Supplier<IFeature> DUMPLINGS = new FeatureGetter("dumplings", Dumplings::new);
    public static final Supplier<IFeature> FIREWORKS = new FeatureGetter("fireworks", Fireworks::new);
    public static final Supplier<IFeature> FLOWER_CAKE = new FeatureGetter("flower_cake", FlowerCake::new);
    public static final Supplier<IFeature> HOTPOTS = new FeatureGetter("hotpots", Hotpots::new);
    public static final Supplier<IFeature> LABA_CONGEE = new FeatureGetter("laba_congee", LabaCongee::new);
    public static final Supplier<IFeature> LANTERNS = new FeatureGetter("lanterns", Lanterns::new);
    public static final Supplier<IFeature> MOONCAKES = new FeatureGetter("mooncakes", Mooncakes::new);
    public static final Supplier<IFeature> PLATES = new FeatureGetter("plates", Plates::new);
    public static final Supplier<IFeature> SWEET_DUMPLINGS = new FeatureGetter("sweet_dumplings", SweetDumplings::new);
    public static final List<Supplier<IFeature>> FEATURES = new Vector<>() {{
        add(COUPLETS);
        add(DUMPLINGS);
        add(FIREWORKS);
        add(FLOWER_CAKE);
        add(HOTPOTS);
        add(LABA_CONGEE);
        add(LANTERNS);
        add(MOONCAKES);
        add(PLATES);
        add(SWEET_DUMPLINGS);
    }};

    public static void refresh() {
        for (Supplier<IFeature> feature : FEATURES) {
            if (feature instanceof FeatureGetter getter) {
                getter.refresh();
            }
        }
    }

    public static class FeatureGetter implements Supplier<IFeature> {
        String id;
        BiFunction<String, IFestival[], IFeature> featureGenerator;
        IFeature feature = null;

        public FeatureGetter(String id, BiFunction<String, IFestival[], IFeature> featureGenerator) {
            this.id = id;
            this.featureGenerator = featureGenerator;
            Features.FEATURE_GENERATORS.put(id, featureGenerator);
        }

        @Override
        public IFeature get() {
            if (feature == null) this.feature = ChineseFestivals.getOrCreateFeature(this.id, this.featureGenerator);
            return this.feature;
        }

        public void refresh() {
            this.feature = ChineseFestivals.getOrCreateFeature(this.id, this.featureGenerator);
        }
    }
}
