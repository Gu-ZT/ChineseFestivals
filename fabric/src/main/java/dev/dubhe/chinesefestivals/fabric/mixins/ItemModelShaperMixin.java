package dev.dubhe.chinesefestivals.fabric.mixins;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(ItemModelShaper.class)
public abstract class ItemModelShaperMixin {
    @Shadow
    @Final
    private Int2ObjectMap<BakedModel> shapesCache;

    @Shadow
    private static int getIndex(Item item) {
        return 0;
    }

    @Inject(method = "getItemModel(Lnet/minecraft/world/item/Item;)Lnet/minecraft/client/resources/model/BakedModel;", at = @At("HEAD"), cancellable = true)
    private void getItemModel(Item item, CallbackInfoReturnable<BakedModel> cir) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                Supplier<Item> item1 = festival.getItemReplace().getOrDefault(item, null);
                if (item1 != null) {
                    cir.setReturnValue(this.shapesCache.get(getIndex(item1.get())));
                    return;
                }
            }
        }
    }
}
