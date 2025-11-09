package net.mrqx.alexcavedimensions.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowSquid.class)
public class MixinGlowSquid {
    @Inject(method = "checkGlowSquideSpawnRules(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z", at = @At("HEAD"), cancellable = true)
    private static void inject(EntityType<? extends LivingEntity> type, ServerLevelAccessor level,
                               MobSpawnType spawnType, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> cir) {
        if (spawnType != MobSpawnType.SPAWNER
                && EntityType.getKey(type).getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)
                && level instanceof ServerLevel serverLevel) {
            if (serverLevel.dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
                cir.setReturnValue(false);
            }
        }
    }
}
