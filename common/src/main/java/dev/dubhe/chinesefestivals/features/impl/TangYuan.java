package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class TangYuan extends Feature {
    public static final Supplier<Item> SWEET_DUMPLINGS = IFeature.createItem("tang_yuan", new Item.Properties().food(Foods.RABBIT_STEW), Item::new);

    public TangYuan(String id, IFestival ... enableTimes) {
        super(id, Festivals.LABA_FESTIVAL, Festivals.CHINESE_SPRING_FESTIVAL, Festivals.LANTERN_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }


    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.BEETROOT_SOUP, SWEET_DUMPLINGS);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("item.minecraft.beetroot_soup", () -> "item.chinesefestivals.tang_yuan");
        return map;
    }
}
