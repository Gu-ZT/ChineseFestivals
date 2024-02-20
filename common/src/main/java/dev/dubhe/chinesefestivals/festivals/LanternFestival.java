package dev.dubhe.chinesefestivals.festivals;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class LanternFestival extends LunarFestival {
    public LanternFestival() {
        super("lantern", 1, 15);
    }

    @Override
    public ModelResourceLocation getBlockReplace(BlockState blockState) {
        if (blockState.is(Blocks.LANTERN) && blockState.getValue(LanternBlock.HANGING)) {
            return SpringFestival.LANTERN;
        }
        if (blockState.is(Blocks.SOUL_LANTERN) && blockState.getValue(LanternBlock.HANGING)) {
            return SpringFestival.TALL_LANTERN;
        }
        return null;
    }

    @Override
    public String getBlockTranslateReplace(Block block) {
        var key = BuiltInRegistries.BLOCK.getKey(block);
        if (!(block instanceof LanternBlock && "minecraft".equals(key.getNamespace()))) return null;
        return switch (key.getPath()) {
            case "lantern" -> "block.chinesefestivals.lantern";
            case "soul_lantern" -> "block.chinesefestivals.tall_lantern";
            default -> null;
        };
    }

    @Override
    public Map<Item, Supplier<Item>> getItemReplace() {
        Map<Item, Supplier<Item>> map = new ConcurrentHashMap<>();
        map.put(Items.BEETROOT_SOUP, LabaFestival.SWEET_DUMPLINGS);
        return map;
    }

    @Override
    public Map<String, Supplier<String>> getTranslationReplace() {
        return new ConcurrentHashMap<>() {{
            this.put("item.minecraft.beetroot_soup", () -> "item.chinesefestivals.sweet_dumplings");
        }};
    }
}
