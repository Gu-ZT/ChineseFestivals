package dev.dubhe.chinesefestivals.mixins;

import dev.dubhe.chinesefestivals.festivals.IFestival;
import dev.dubhe.chinesefestivals.util.ParticleUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkParticles.Starter.class)
public abstract class FireworkParticles$StarterMixin extends Particle {
    protected FireworkParticles$StarterMixin(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f);
    }

    @Shadow
    private void createParticle(double d, double e, double f, double g, double h, double i, int[] is, int[] js, boolean bl, boolean bl2) {}

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/FireworkParticles$Starter;createParticleShape(D[[D[I[IZZZ)V", ordinal = 1))
    public void modifyParticle(FireworkParticles.Starter instance, double d9, double[][] d10, int[] d11, int[] d8, boolean d6, boolean d7, boolean j) {
        double[][] particles = IFestival.execute(IFestival::getFireworkParticle);
        if (particles == null) {
            ((FireworkParticles$StarterAccessor) instance).invokeCreateParticleShape(d9, d10, d11, d8, d6, d7, j);
        } else {
            ParticleUtil.createParticleSingleShape(this.x, this.y, this.z, this.random, this::createParticle, particles, d11, d8, d6, d7);
        }
    }
}
