package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FlowerCake extends Feature {
    public static final Supplier<Item> FLOWER_CAKE = IFeature.createItem("flower_cake", new Item.Properties().food(Foods.PUMPKIN_PIE), Item::new);

    public FlowerCake(String id, IFestival... enableTimes) {
        super(id, Festivals.DOUBLE_NINTH_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = Collections.synchronizedMap(new HashMap<>());
        map.put(Items.COOKIE, FLOWER_CAKE);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = Collections.synchronizedMap(new HashMap<>());
        map.put("item.minecraft.cookie", () -> "item.chinesefestivals.flower_cake");
        return map;
    }
}
