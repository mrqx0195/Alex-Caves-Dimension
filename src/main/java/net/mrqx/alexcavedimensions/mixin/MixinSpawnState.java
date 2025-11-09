package net.mrqx.alexcavedimensions.mixin;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LocalMobCapCalculator;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NaturalSpawner.SpawnState.class)
public abstract class MixinSpawnState {
    @Shadow
    @Final
    private int spawnableChunkCount;
    @Shadow
    @Final
    private Object2IntOpenHashMap<MobCategory> mobCategoryCounts;
    @Shadow
    @Final
    private LocalMobCapCalculator localMobCapCalculator;

    @Inject(method = "canSpawnForCategory(Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/world/level/ChunkPos;)Z", at = @At("HEAD"), cancellable = true)
    private void injectCanSpawnForCategory(MobCategory category, ChunkPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (category.equals(ACEntityRegistry.CAVE_CREATURE)) {
            int i = (int) (category.getMaxInstancesPerChunk() * this.spawnableChunkCount / Math.pow(17.0D, 2.0D)
                    * AlexsCaves.COMMON_CONFIG.caveCreatureSpawnCountModifier.get() * 28);
            if (this.mobCategoryCounts.getInt(category) >= i) {
                cir.setReturnValue(false);
            } else {
                cir.setReturnValue(this.localMobCapCalculator.canSpawn(category, pos));
            }
        }
    }
}
