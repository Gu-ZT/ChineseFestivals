package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class MoonFestival {
    public static final Block MOONCAKES = new CakeBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY));
    public static final Item MOONCAKES_ITEM = new BlockItem(MOONCAKES, new Item.Properties().stacksTo(1));
    public static final Item MOONCAKE_ITEM = new Item(new Item.Properties().food(Foods.PUMPKIN_PIE));
}
