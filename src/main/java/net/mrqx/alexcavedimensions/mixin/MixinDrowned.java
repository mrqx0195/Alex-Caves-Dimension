package net.mrqx.alexcavedimensions.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.level.ServerLevelAccessor;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Drowned.class)
public class MixinDrowned {
    @Inject(method = "checkDrownedSpawnRules(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z", at = @At("RETURN"), cancellable = true)
    private static void injectCheckMobSpawnRules(EntityType<Drowned> drowned, ServerLevelAccessor level, MobSpawnType mobSpawnType, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> cir) {
        if (mobSpawnType != MobSpawnType.SPAWNER
                && EntityType.getKey(drowned).getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)
                && level instanceof ServerLevel serverLevel) {
            if (serverLevel.dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
                cir.setReturnValue(false);
            }
        }
    }
}
