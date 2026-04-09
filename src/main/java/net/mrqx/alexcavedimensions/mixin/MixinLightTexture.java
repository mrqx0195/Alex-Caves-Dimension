package net.mrqx.alexcavedimensions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightTexture.class)
public class MixinLightTexture {
    @WrapOperation(method = "updateLightTexture(F)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z", ordinal = 0))
    private boolean wrapSetupColorHasEffect(LocalPlayer instance, MobEffect mobEffect, Operation<Boolean> original) {
        if (!instance.hasEffect(MobEffects.NIGHT_VISION) && AlexCavesDimensions.shouldDimensionHasNightVision(instance.level().dimension())) {
            return true;
        }
        return original.call(instance, mobEffect);
    }
}
