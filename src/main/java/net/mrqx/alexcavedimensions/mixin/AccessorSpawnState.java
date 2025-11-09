package net.mrqx.alexcavedimensions.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NaturalSpawner.SpawnState.class)
public interface AccessorSpawnState {
    @Invoker("canSpawn")
    boolean alex_caves_dimensions$canSpawn(EntityType<?> entityType, BlockPos pos, ChunkAccess chunk);

    @Invoker("afterSpawn")
    void alex_caves_dimensions$afterSpawn(Mob mob, ChunkAccess chunk);

    @Invoker("canSpawnForCategory")
    boolean alex_caves_dimensions$canSpawnForCategory(MobCategory category, ChunkPos pos);
}
