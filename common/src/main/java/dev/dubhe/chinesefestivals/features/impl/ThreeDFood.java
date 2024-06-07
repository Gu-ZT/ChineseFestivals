package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ThreeDFood extends Feature {
    public static final TagKey<Item> HAS_PLATE = TagKey.create(Registries.ITEM, ChineseFestivals.of("has_plate"));
    public static final Supplier<Item> APPLE_3D = IFeature.createItem("apple_3d", new Item.Properties().food(Foods.APPLE), Item::new);
    public static final Supplier<Item> COOKIE_3D = IFeature.createItem("cookie_3d", new Item.Properties().food(Foods.COOKIE), Item::new);

    public ThreeDFood(String id, IFestival @NotNull ... enableTimes) {
        super(id, Festivals.QING_MING, Festivals.CHINESE_SPRING_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public Map<Item, Supplier<Item>> get3DFoodReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.APPLE, APPLE_3D);
        map.put(Items.COOKIE, COOKIE_3D);
        return map;
    }
}
