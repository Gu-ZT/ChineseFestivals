package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.item.Item;

public class ItemFactory<T extends Item> implements IFactory<T> {
    public T item = null;
    public final Item.Properties properties;
    public final ItemCreator<T> creator;

    public ItemFactory(Item.Properties properties, ItemCreator<T> creator) {
        this.properties = properties;
        this.creator = creator;
    }

    public T get() {
        return item == null ? item = creator.build(properties) : item;
    }

    public interface ItemCreator<T> {
        T build(Item.Properties properties);
    }
}
