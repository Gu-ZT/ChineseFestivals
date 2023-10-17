package dev.dubhe.chinesefestivals.forge.mixins;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.ForgeItemModelShaper;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Supplier;

@Mixin(ForgeItemModelShaper.class)
public abstract class ForgeItemModelShaperMixin extends ItemModelShaper {
    @Shadow(remap = false)
    @Final
    private Map<Holder.Reference<Item>, BakedModel> models;

    public ForgeItemModelShaperMixin(ModelManager arg) {
        super(arg);
    }

    @Inject(method = "getItemModel", at = @At("HEAD"), cancellable = true)
    private void getItemModel(Item item, CallbackInfoReturnable<BakedModel> cir) {
        for (IFestival festival : Festivals.FESTIVALS) {
            if (festival.isNow()) {
                Supplier<Item> item1 = festival.getItemReplace().getOrDefault(item, null);
                if (item1 != null) {
                    cir.setReturnValue(this.models.get(ForgeRegistries.ITEMS.getDelegateOrThrow(item1.get())));
                    return;
                }
            }
        }
    }
}
