package net.mrqx.alexcavedimensions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FogRenderer.class)
public class MixinFogRenderer {
    @WrapOperation(method = "setupColor(Lnet/minecraft/client/Camera;FLnet/minecraft/client/multiplayer/ClientLevel;IF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z", ordinal = 0))
    private static boolean wrapSetupColorHasEffect(LivingEntity instance, MobEffect effect, Operation<Boolean> original) {
        if (!instance.hasEffect(MobEffects.NIGHT_VISION) && AlexCavesDimensions.shouldDimensionHasNightVision(instance.level().dimension())) {
            return true;
        }
        return original.call(instance, effect);
    }
}
