package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlockItemFactory<B extends Block, T extends Item> implements IFactory<T> {
    public T item = null;
    public final IFactory<B> block;
    public final Item.Properties properties;
    public final ItemCreator<T> creator;

    public BlockItemFactory(IFactory<B> block, Item.Properties properties, ItemCreator<T> creator) {
        this.block = block;
        this.properties = properties;
        this.creator = creator;
    }

    public T get() {
        return item == null ? item = creator.build(block.get(), properties) : item;
    }

    public interface ItemCreator<T> {
        T build(Block block, Item.Properties properties);
    }
}
