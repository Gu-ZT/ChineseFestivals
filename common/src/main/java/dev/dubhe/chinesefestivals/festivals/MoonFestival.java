package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class MoonFestival extends LunarFestival {
    public static final Supplier<Block> MOONCAKES = IFestival.createBlock("mooncakes", BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY), CakeBlock::new);
    public static final Supplier<Item> MOONCAKES_ITEM = IFestival.createBlockItem("mooncakes", MOONCAKES, new Item.Properties().stacksTo(1), BlockItem::new);
    public static final Supplier<Item> MOONCAKE_ITEM = IFestival.createItem("mooncake", new Item.Properties().food(Foods.PUMPKIN_PIE), Item::new);

    public MoonFestival() {
        super("moon", 8, 15);
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.CAKE, MOONCAKES_ITEM);
        map.put(Items.PUMPKIN_PIE, MOONCAKE_ITEM);
        return map;
    }

    @Override
    public Map<Block, Supplier<Block>> getBlockReplace() {
        Map<Block, Supplier<Block>> map = new ConcurrentHashMap<>();
        map.put(Blocks.CAKE, MOONCAKES);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        Map<String, Supplier<String>> map = new ConcurrentHashMap<>();
        map.put("block.minecraft.cake", () -> "block.chinesefestivals.mooncakes");
        map.put("item.minecraft.pumpkin_pie", () -> "item.chinesefestivals.mooncake");
        return map;
    }
}
