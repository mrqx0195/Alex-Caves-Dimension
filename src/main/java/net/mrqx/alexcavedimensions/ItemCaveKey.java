package net.mrqx.alexcavedimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

public class ItemCaveKey extends Item {
    public ItemCaveKey(Properties properties) {
        super(properties);
    }
    
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        pPlayer.swing(pUsedHand);
        return InteractionResultHolder.consume(itemStack);
    }
    
    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        MinecraftServer server = pLivingEntity.getServer();
        if (server == null) {
            return pStack;
        }
        if (pStack.is(AlexCavesDimensions.ABYSSAL_CHASM_KEY.get())) {
            pLivingEntity.changeDimension(Objects.requireNonNull(
                    server.getLevel(pLevel.dimension().equals(AlexCavesDimensions.ABYSSAL_CHASM_RESOURCE_KEY) ? Level.OVERWORLD : AlexCavesDimensions.ABYSSAL_CHASM_RESOURCE_KEY)),
                getTeleporter());
        }
        if (pStack.is(AlexCavesDimensions.CANDY_CAVITY_KEY.get())) {
            pLivingEntity.changeDimension(Objects.requireNonNull(
                    server.getLevel(pLevel.dimension().equals(AlexCavesDimensions.CANDY_CAVITY_RESOURCE_KEY) ? Level.OVERWORLD : AlexCavesDimensions.CANDY_CAVITY_RESOURCE_KEY)),
                getTeleporter());
        }
        if (pStack.is(AlexCavesDimensions.FORLORN_HOLLOWS_KEY.get())) {
            pLivingEntity.changeDimension(Objects.requireNonNull(
                    server.getLevel(pLevel.dimension().equals(AlexCavesDimensions.FORLORN_HOLLOWS_RESOURCE_KEY) ? Level.OVERWORLD : AlexCavesDimensions.FORLORN_HOLLOWS_RESOURCE_KEY)),
                getTeleporter());
        }
        if (pStack.is(AlexCavesDimensions.TOXIC_CAVES_KEY.get())) {
            pLivingEntity.changeDimension(Objects.requireNonNull(
                    server.getLevel(pLevel.dimension().equals(AlexCavesDimensions.TOXIC_CAVES_RESOURCE_KEY) ? Level.OVERWORLD : AlexCavesDimensions.TOXIC_CAVES_RESOURCE_KEY)),
                getTeleporter());
        }
        if (pStack.is(AlexCavesDimensions.PRIMORDIAL_CAVES_KEY.get())) {
            pLivingEntity.changeDimension(Objects.requireNonNull(
                    server.getLevel(pLevel.dimension().equals(AlexCavesDimensions.PRIMORDIAL_CAVES_RESOURCE_KEY) ? Level.OVERWORLD : AlexCavesDimensions.PRIMORDIAL_CAVES_RESOURCE_KEY)),
                getTeleporter());
        }
        if (pStack.is(AlexCavesDimensions.MAGNETIC_CAVES_KEY.get())) {
            pLivingEntity.changeDimension(Objects.requireNonNull(
                    server.getLevel(pLevel.dimension().equals(AlexCavesDimensions.MAGNETIC_CAVES_RESOURCE_KEY) ? Level.OVERWORLD : AlexCavesDimensions.MAGNETIC_CAVES_RESOURCE_KEY)),
                getTeleporter());
        }
        return pStack;
    }
    
    private static @NotNull ITeleporter getTeleporter() {
        return new ITeleporter() {
            @Override
            public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
                
                for (int i = destWorld.getHeight() + destWorld.getMinBuildHeight(); i > destWorld.getMinBuildHeight(); i--) {
                    BlockPos pos = new BlockPos((int) entity.getX(), i, (int) entity.getZ());
                    if (destWorld.getBlockState(pos).isValidSpawn(destWorld, pos, entity.getType())) {
                        return new PortalInfo(entity.position().add(0, i + 1 - entity.position().y, 0),
                            Vec3.ZERO, entity.getYRot(), entity.getXRot());
                    }
                }
                return ITeleporter.super.getPortalInfo(entity, destWorld, defaultPortalInfo);
            }
        };
    }
    
    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 1;
    }
}
