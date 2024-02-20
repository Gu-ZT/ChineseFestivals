package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.features.Features;
import dev.dubhe.chinesefestivals.features.IFeature;
import net.minecraft.locale.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(Language.class)
public abstract class LanguageMixin {
    @Shadow
    public abstract String getOrDefault(String var1, String var2);

    @Inject(method = "getOrDefault(Ljava/lang/String;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    private void getOrDefault(String string, CallbackInfoReturnable<String> cir) {
        for (Supplier<IFeature> feature : Features.FEATURES) {
            if (feature.get().isNow()) {
                String string1 = feature.get().getTranslationReplace().getOrDefault(string, () -> null).get();
                if (string1 != null) {
                    cir.setReturnValue(this.getOrDefault(string1, string1));
                    return;
                }
            }
        }
    }
}
