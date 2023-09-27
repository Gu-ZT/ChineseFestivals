package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.MoonFestival;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        if (ChineseFestivals.MOON_FESTIVAL.isNow()) {
            if (item == Items.CAKE) item = MoonFestival.MOON_CAKE_ITEM;
            else if (item == Items.PUMPKIN_PIE) item = MoonFestival.MOON_CAKE_ONLY_ITEM;
            cir.setReturnValue(this.shapesCache.get(getIndex(item)));
        }
    }
}
