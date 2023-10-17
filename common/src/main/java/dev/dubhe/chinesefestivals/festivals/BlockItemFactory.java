package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class BlockItemFactory<B extends Block, T extends Item> implements Supplier<T> {
    public T item = null;
    public final Supplier<B> block;
    public final Item.Properties properties;
    public final ItemCreator<T> creator;

    public BlockItemFactory(Supplier<B> block, Item.Properties properties, ItemCreator<T> creator) {
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
