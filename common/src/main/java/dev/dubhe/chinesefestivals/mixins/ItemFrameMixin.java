package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity {
    private ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected @NotNull Component getTypeName() {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                Component component = festival.getItemFrameTypeReplace((ItemFrame) (Object) this);
                if (component != null) {
                    return component;
                }
            }
        }
        return super.getTypeName();
    }
}
