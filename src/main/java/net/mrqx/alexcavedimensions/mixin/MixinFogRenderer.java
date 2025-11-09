package net.mrqx.alexcavedimensions.mixin;

import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FogRenderer.class)
public class MixinFogRenderer {
    @Redirect(method = "setupColor(Lnet/minecraft/client/Camera;FLnet/minecraft/client/multiplayer/ClientLevel;IF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z", ordinal = 0))
    private static boolean redirectSetupColorHasEffect(LivingEntity instance, MobEffect effect) {
        if (!instance.hasEffect(MobEffects.NIGHT_VISION)
                && instance.level().dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
            return true;
        }
        return instance.hasEffect(MobEffects.NIGHT_VISION);
    }
}
