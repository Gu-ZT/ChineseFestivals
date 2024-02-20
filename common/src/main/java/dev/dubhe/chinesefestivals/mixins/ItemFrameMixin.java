package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Supplier;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity {
    private ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected @NotNull Component getTypeName() {
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (feature.get().isNow()) {
                Component component = feature.get().getItemFrameTypeReplace((ItemFrame) (Object) this);
                if (component != null) {
                    return component;
                }
            }
        }
        return super.getTypeName();
    }
}
