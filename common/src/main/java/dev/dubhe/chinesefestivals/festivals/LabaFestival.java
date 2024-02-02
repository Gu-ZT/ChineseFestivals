package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class LabaFestival extends LunarFestival {
    public static final Supplier<Item> LABA_CONGEE = IFestival.createItem("laba_congee", new Item.Properties().food(Foods.MUSHROOM_STEW), Item::new);
    public static final Supplier<Item> SWEET_DUMPLINGS = IFestival.createItem("sweet_dumplings", new Item.Properties().food(Foods.RABBIT_STEW), Item::new);

    public LabaFestival() {
        super("laba", 12, 8);
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.MUSHROOM_STEW, LABA_CONGEE);
        map.put(Items.RABBIT_STEW, SWEET_DUMPLINGS);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("item.minecraft.mushroom_stew", () -> "item.chinesefestivals.laba_congee");
        map.put("item.minecraft.rabbit_stew", () -> "item.chinesefestivals.sweet_dumplings");
        return map;
    }
}
