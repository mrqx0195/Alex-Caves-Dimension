package net.mrqx.alexcavedimensions.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightTexture.class)
public class MixinLightTexture {
    @Redirect(method = "updateLightTexture(F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z", ordinal = 0))
    private boolean redirectSetupColorHasEffect(LocalPlayer instance, MobEffect mobEffect) {
        if (!instance.hasEffect(MobEffects.NIGHT_VISION)
                && instance.level().dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
            return true;
        }
        return instance.hasEffect(MobEffects.NIGHT_VISION);
    }
}
