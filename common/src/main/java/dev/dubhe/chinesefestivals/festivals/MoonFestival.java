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
    public static final Block MOONCAKES = IFestival.createBlock("mooncakes", new CakeBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));
    public static final Item MOONCAKES_ITEM = IFestival.createItem("mooncakes", new BlockItem(MOONCAKES, new Item.Properties().stacksTo(1)));
    public static final Item MOONCAKE_ITEM = IFestival.createItem("mooncake", new Item(new Item.Properties().food(Foods.PUMPKIN_PIE)));

    public MoonFestival() {
        super("moon", 8, 15);
    }

    @Override
    public Map<Item, Item> getItemReplace() {
        Map<Item, Item> map = super.getItemReplace();
        map.put(Items.CAKE, MoonFestival.MOONCAKES_ITEM);
        map.put(Items.PUMPKIN_PIE, MoonFestival.MOONCAKE_ITEM);
        return map;
    }

    @Override
    public Map<Block, Block> getBlockReplace() {
        Map<Block, Block> map = super.getBlockReplace();
        map.put(Blocks.CAKE, MoonFestival.MOONCAKES);
        return map;
    }
}
