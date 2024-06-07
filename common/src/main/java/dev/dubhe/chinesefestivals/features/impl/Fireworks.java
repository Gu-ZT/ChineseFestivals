package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import dev.dubhe.chinesefestivals.util.BitMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Fireworks extends Feature {

    public Fireworks(String id, IFestival... enableTimes) {
        super(id, Festivals.CHINESE_SPRING_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public double[][] getFireworkParticle() {
        return BitMap.DRAGON;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = Collections.synchronizedMap(new HashMap<>());
        map.put("item.minecraft.firework_star.shape.creeper", () -> "item.firework_star.shape.dragon");
        return map;
    }
}
