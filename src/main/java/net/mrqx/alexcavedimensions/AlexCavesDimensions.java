package net.mrqx.alexcavedimensions;

import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(AlexCavesDimensions.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AlexCavesDimensions {
    public static final String MODID = "alex_caves_dimensions";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MODID);

    public static final RegistryObject<Item> ABYSSAL_CHASM_KEY = ITEMS.register("abyssal_chasm_key", () -> new ItemCaveKey(new Item.Properties()));
    public static final RegistryObject<Item> CANDY_CAVITY_KEY = ITEMS.register("candy_cavity_key", () -> new ItemCaveKey(new Item.Properties()));
    public static final RegistryObject<Item> FORLORN_HOLLOWS_KEY = ITEMS.register("forlorn_hollows_key", () -> new ItemCaveKey(new Item.Properties()));
    public static final RegistryObject<Item> TOXIC_CAVES_KEY = ITEMS.register("toxic_caves_key", () -> new ItemCaveKey(new Item.Properties()));
    public static final RegistryObject<Item> PRIMORDIAL_CAVES_KEY = ITEMS.register("primordial_caves_key", () -> new ItemCaveKey(new Item.Properties()));
    public static final RegistryObject<Item> MAGNETIC_CAVES_KEY = ITEMS.register("magnetic_caves_key", () -> new ItemCaveKey(new Item.Properties()));

    public static final RegistryObject<RecipeSerializer<?>> CAVE_KEY = RECIPE_SERIALIZERS.register("cave_key", () -> new SimpleCraftingRecipeSerializer<>(RecipeCaveKey::new));

    public AlexCavesDimensions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
    }

    @SubscribeEvent
    public static void onBuildCreativeModeTabContentsEvent(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(ACCreativeTabRegistry.ABYSSAL_CHASM.getKey())) {
            event.accept(ABYSSAL_CHASM_KEY.get());
        }
        if (event.getTabKey().equals(ACCreativeTabRegistry.CANDY_CAVITY.getKey())) {
            event.accept(CANDY_CAVITY_KEY.get());
        }
        if (event.getTabKey().equals(ACCreativeTabRegistry.FORLORN_HOLLOWS.getKey())) {
            event.accept(FORLORN_HOLLOWS_KEY.get());
        }
        if (event.getTabKey().equals(ACCreativeTabRegistry.TOXIC_CAVES.getKey())) {
            event.accept(TOXIC_CAVES_KEY.get());
        }
        if (event.getTabKey().equals(ACCreativeTabRegistry.PRIMORDIAL_CAVES.getKey())) {
            event.accept(PRIMORDIAL_CAVES_KEY.get());
        }
        if (event.getTabKey().equals(ACCreativeTabRegistry.MAGNETIC_CAVES.getKey())) {
            event.accept(MAGNETIC_CAVES_KEY.get());
        }
    }
}
