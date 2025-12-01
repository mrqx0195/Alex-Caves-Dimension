package net.mrqx.alexcavedimensions.mixin;

import com.github.alexmodguy.alexscaves.server.level.structure.piece.OceanTrenchStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.WorldGenLevel;
import net.mrqx.alexcavedimensions.AlexCavesDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OceanTrenchStructurePiece.class)
public class MixinOceanTrenchStructurePiece {
    @Unique
    private WorldGenLevel alex_caves_dimensions$level = null;

    @Inject(method = "getRadiusSq(Lnet/minecraft/core/BlockPos$MutableBlockPos;)D", at = @At("RETURN"), cancellable = true, remap = false)
    private void injectGetRadiusSq(BlockPos.MutableBlockPos carve, CallbackInfoReturnable<Double> cir) {
        if (alex_caves_dimensions$level instanceof WorldGenRegion worldGenRegion) {
            if (worldGenRegion.getLevel().dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
                cir.setReturnValue(cir.getReturnValue() * 100);
            }
        }
    }

    @Inject(method = "shouldDig(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/core/BlockPos$MutableBlockPos;II)Z", at = @At("HEAD"), remap = false)
    private void injectShouldDig(WorldGenLevel level, BlockPos.MutableBlockPos carve, int seaLevel, int priorHeight, CallbackInfoReturnable<Boolean> cir) {
        this.alex_caves_dimensions$level = level;
    }
}
