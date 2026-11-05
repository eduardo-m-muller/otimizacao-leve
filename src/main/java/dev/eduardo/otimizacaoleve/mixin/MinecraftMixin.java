package dev.eduardo.otimizacaoleve.mixin;

import net.minecraft.client.CloudStatus;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow public Options options;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void otimizacaoleve$aplicar(GameConfig config, CallbackInfo ci) {
        try {
            Options o = this.options;
            o.renderDistance().set(5);
            o.simulationDistance().set(5);
            o.graphicsMode().set(GraphicsStatus.FAST);
            o.cloudStatus().set(CloudStatus.OFF);
            o.particles().set(ParticleStatus.MINIMAL);
            o.entityShadows().set(false);
            o.ambientOcclusion().set(false);
            o.biomeBlendRadius().set(0);
            o.mipmapLevels().set(0);
            o.entityDistanceScaling().set(0.5);
            o.framerateLimit().set(60);
            o.bobView().set(false);
            o.screenEffectScale().set(0.0);
            o.fovEffectScale().set(0.0);
            o.save();
        } catch (Throwable t) {
            System.err.println("[otimizacao_leve] falha ao aplicar opcoes: " + t);
        }
    }
}
