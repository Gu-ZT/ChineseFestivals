package dev.dubhe.chinesefestivals.mixins;

import net.minecraft.client.particle.FireworkParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FireworkParticles.Starter.class)
public interface FireworkParticles$StarterAccessor {

    @Invoker("createParticleShape")
    void invokeCreateParticleShape(double d, double[][] ds, int[] is, int[] js, boolean bl, boolean bl2, boolean bl3);

}
