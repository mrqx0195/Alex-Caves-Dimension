package net.mrqx.alexcavedimensions.mixin;

import com.github.alexmodguy.alexscaves.server.misc.CaveTabletLootModifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CaveTabletLootModifier.class)
public class MixinCaveTabletLootModifier {
    @Redirect(method = "getChance()F", at = @At(value = "INVOKE", target = "Lcom/github/alexmodguy/alexscaves/server/config/BiomeGenerationConfig;isBiomeDisabledCompletely(Lnet/minecraft/resources/ResourceKey;)Z"), remap = false)
    private boolean red(ResourceKey<Biome> biome) {
        return false;
    }
}
