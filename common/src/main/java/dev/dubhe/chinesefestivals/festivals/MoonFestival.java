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

public class MoonFestival extends LunarFestival {
    public static final IFactory<Block> MOONCAKES = IFestival.createBlock("mooncakes", BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY), CakeBlock::new);
    public static final IFactory<Item> MOONCAKES_ITEM = IFestival.createBlockItem("mooncakes", MOONCAKES, new Item.Properties().stacksTo(1), BlockItem::new);
    public static final IFactory<Item> MOONCAKE_ITEM = IFestival.createItem("mooncake", new Item.Properties().food(Foods.PUMPKIN_PIE), Item::new);

    public MoonFestival() {
        super("moon", 8, 15);
    }

    @Override
    public Map<Item, IFactory<Item>> getItemReplace() {
        Map<Item, IFactory<Item>> map = super.getItemReplace();
        map.put(Items.CAKE, MOONCAKES_ITEM);
        map.put(Items.PUMPKIN_PIE, MOONCAKE_ITEM);
        return map;
    }

    @Override
    public Map<Block, IFactory<Block>> getBlockReplace() {
        Map<Block, IFactory<Block>> map = super.getBlockReplace();
        map.put(Blocks.CAKE, MOONCAKES);
        return map;
    }

    @Override
    public Map<String, IFactory<String>> getTranslationReplace() {
        Map<String, IFactory<String>> map = super.getTranslationReplace();
        map.put("block.minecraft.cake", () -> "block.chinesefestivals.mooncakes");
        map.put("item.minecraft.pumpkin_pie", () -> "item.chinesefestivals.mooncake");
        return map;
    }
}
