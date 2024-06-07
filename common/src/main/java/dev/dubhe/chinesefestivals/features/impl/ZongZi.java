package dev.dubhe.chinesefestivals.features.impl;

import dev.dubhe.chinesefestivals.features.Feature;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ZongZi extends Feature {
    public static final Supplier<Item> ZONG_ZI = IFeature.createItem("zong_zi", new Item.Properties().food(Foods.PUFFERFISH), Item::new);

    public ZongZi(String id, IFestival @NotNull ... enableTimes) {
        super(id, Festivals.DRAGON_BOAT_FESTIVAL);
        if (enableTimes.length > 0) {
            super.enableTimes.clear();
            super.enableTimes.addAll(List.of(enableTimes));
        }
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        return new HashMap<>() {{
            this.put(Items.PUMPKIN_PIE, ZongZi.ZONG_ZI);
        }};
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = Collections.synchronizedMap(new HashMap<>());
        map.put("item.minecraft.pumpkin_pie", () -> "item.chinesefestivals.zong_zi");
        return map;
    }
}
