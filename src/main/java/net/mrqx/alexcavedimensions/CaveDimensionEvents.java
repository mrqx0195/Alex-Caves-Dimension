package net.mrqx.alexcavedimensions;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CaveDimensionEvents {
    @SubscribeEvent
    public static void onPositionCheck(MobSpawnEvent.PositionCheck event) {
        if (event.getLevel() instanceof Level level && level.dimension().location().getNamespace().equals(AlexCavesDimensions.MODID)) {
            if (EntityType.getKey(event.getEntity().getType()).getNamespace().equals(AlexsCaves.MODID)) {
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}
