package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class DongZhiFestival extends SolarTermFestival {
    public static final Supplier<Block> HOTPOT_N = IFestival.createBlock("hotpot_n", BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY), (properties) -> new CampfireBlock(false, 2, properties));
    public static final Supplier<Block> HOTPOT_S = IFestival.createBlock("hotpot_s", BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY), (properties) -> new CampfireBlock(true, 1, properties));
    public static final Supplier<Item> HOTPOT_N_ITEM = IFestival.createBlockItem("hotpot_n", HOTPOT_N, new Item.Properties(), BlockItem::new);
    public static final Supplier<Item> HOTPOT_S_ITEM = IFestival.createBlockItem("hotpot_s", HOTPOT_S, new Item.Properties(), BlockItem::new);
    public static final Supplier<Item> DUMPLINGS = IFestival.createItem("dumplings", new Item.Properties().food(Foods.RABBIT_STEW), Item::new);

    public DongZhiFestival() {
        super("dong_zhi", SolarTerm.DONG_ZHI);
    }

    @Override
    public Map<Block, Supplier<Block>> getBlockReplace() {
        Map<Block, Supplier<Block>> map = new ConcurrentHashMap<>();
        map.put(Blocks.CAMPFIRE, HOTPOT_S);
        map.put(Blocks.SOUL_CAMPFIRE, HOTPOT_N);
        return map;
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.CAMPFIRE, HOTPOT_S_ITEM);
        map.put(Items.SOUL_CAMPFIRE, HOTPOT_N_ITEM);
        map.put(Items.RABBIT_STEW, DUMPLINGS);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("item.minecraft.campfire", () -> "item.chinesefestivals.hotpot_s");
        map.put("item.minecraft.soul_campfire", () -> "item.chinesefestivals.hotpot_n");
        map.put("item.minecraft.rabbit_stew", () -> "item.chinesefestivals.dumplings");
        map.put("block.minecraft.campfire", () -> "block.chinesefestivals.hotpot_s");
        map.put("block.minecraft.soul_campfire", () -> "block.chinesefestivals.hotpot_n");
        return map;
    }
}
