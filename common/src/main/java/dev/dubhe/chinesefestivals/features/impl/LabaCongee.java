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

public class LabaCongee extends Feature {
    public static final Supplier<Item> LABA_CONGEE = IFeature.createItem("laba_congee", new Item.Properties().food(Foods.MUSHROOM_STEW), Item::new);

    public LabaCongee(String id, IFestival... enableTimes) {
        super(id, Festivals.LABA_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = Collections.synchronizedMap(new HashMap<>());
        map.put(Items.MUSHROOM_STEW, LABA_CONGEE);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = Collections.synchronizedMap(new HashMap<>());
        map.put("item.minecraft.mushroom_stew", () -> "item.chinesefestivals.laba_congee");
        return map;
    }
}
