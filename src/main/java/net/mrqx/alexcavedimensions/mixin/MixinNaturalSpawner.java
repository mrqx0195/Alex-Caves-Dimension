package net.mrqx.alexcavedimensions.mixin;

import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NaturalSpawner.class)
public class MixinNaturalSpawner {
    @Inject(method = "spawnForChunk(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/LevelChunk;Lnet/minecraft/world/level/NaturalSpawner$SpawnState;ZZZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private static void injectSpawnForChunk(ServerLevel level, LevelChunk chunk, NaturalSpawner.SpawnState spawnState, boolean spawnFriendlies, boolean spawnMonsters, boolean forcedDespawn, CallbackInfo ci) {
        if (spawnState instanceof AccessorSpawnState accessorSpawnState && level.dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
            boolean friendly = spawnFriendlies || !ACEntityRegistry.CAVE_CREATURE.isFriendly();
            boolean monster = spawnMonsters || ACEntityRegistry.CAVE_CREATURE.isFriendly();
            if (friendly && monster && accessorSpawnState.alex_caves_dimensions$canSpawnForCategory(ACEntityRegistry.CAVE_CREATURE, chunk.getPos())) {
                level.getProfiler().push("alex_caves_dimensions$spawner");
                NaturalSpawner.spawnCategoryForChunk(ACEntityRegistry.CAVE_CREATURE, level, chunk,
                        accessorSpawnState::alex_caves_dimensions$canSpawn, accessorSpawnState::alex_caves_dimensions$afterSpawn);
                level.getProfiler().pop();
            }
        }
    }
}
