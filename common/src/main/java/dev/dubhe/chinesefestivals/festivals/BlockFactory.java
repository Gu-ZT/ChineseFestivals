package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BlockFactory<T extends Block> implements Supplier<T> {
    public T block = null;
    public final BlockBehaviour.Properties properties;
    public final BlockCreator<T> creator;

    public BlockFactory(BlockBehaviour.Properties properties, BlockCreator<T> creator) {
        this.properties = properties;
        this.creator = creator;
    }

    public T get() {
        return block == null ? block = creator.build(properties) : block;
    }

    public interface BlockCreator<T> {
        T build(BlockBehaviour.Properties properties);
    }
}
