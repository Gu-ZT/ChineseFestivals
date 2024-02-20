package dev.dubhe.chinesefestivals.mixins;

import com.google.common.collect.ImmutableMap;
import dev.dubhe.chinesefestivals.data.BlockModelData;
import dev.dubhe.chinesefestivals.features.IFeature;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap;"), remap = false)
    private static ImmutableMap<ResourceLocation, StateDefinition<Block, BlockState>> modify(Object k1, Object v1, Object k2, Object v2) {
        List<Map.Entry<ResourceLocation, StateDefinition<Block, BlockState>>> entries = new ArrayList<>();
        entries.add((Map.Entry) Map.entry(k1, v1));
        entries.add((Map.Entry) Map.entry(k2, v2));
        for (BlockModelData model : IFeature.BLOCK_MODELS) {
            StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(Blocks.AIR);
            model.properties.forEach(it -> builder.add(it.genProperty()));
            StateDefinition<Block, BlockState> definition = builder.create(Block::defaultBlockState, BlockState::new);
            entries.add(Map.entry(model.resourceLocation, definition));
        }
        return ImmutableMap.ofEntries(entries.toArray(new Map.Entry[0]));
    }
}
