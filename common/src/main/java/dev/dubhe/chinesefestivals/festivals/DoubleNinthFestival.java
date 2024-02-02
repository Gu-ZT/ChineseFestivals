package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class DoubleNinthFestival extends LunarFestival {
    public static final Supplier<Item> FLOWER_CAKE = IFestival.createItem("flower_cake", new Item.Properties().food(Foods.PUMPKIN_PIE), Item::new);

    public DoubleNinthFestival() {
        super("double_ninth", 9, 9);
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.COOKIE, FLOWER_CAKE);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("item.minecraft.cookie", () -> "item.chinesefestivals.flower_cake");
        return map;
    }
}
