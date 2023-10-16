package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

public class DoubleNinthFestival extends LunarFestival {
    public static final IFactory<Item> FLOWER_CAKE = IFestival.createItem("flower_cake", new Item.Properties().food(Foods.PUMPKIN_PIE), Item::new);

    public DoubleNinthFestival() {
        super("double_ninth", 9, 9);
    }

    @Override
    public Map<Item, IFactory<Item>> getItemReplace() {
        Map<Item, IFactory<Item>> map = super.getItemReplace();
        map.put(Items.COOKIE, FLOWER_CAKE);
        return map;
    }

    @Override
    public Map<String, IFactory<String>> getTranslationReplace() {
        Map<String, IFactory<String>> map = super.getTranslationReplace();
        map.put("item.minecraft.cookie", () -> "item.chinesefestivals.flower_cake");
        return map;
    }
}
