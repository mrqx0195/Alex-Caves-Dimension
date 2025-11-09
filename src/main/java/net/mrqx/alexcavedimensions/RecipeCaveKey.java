package net.mrqx.alexcavedimensions;

import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.item.CaveInfoItem;
import com.github.alexmodguy.alexscaves.server.item.CaveMapItem;
import com.github.alexmodguy.alexscaves.server.recipe.ACRecipeRegistry;
import com.github.alexthe666.citadel.recipe.SpecialRecipeInGuideBook;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

public class RecipeCaveKey extends ShapedRecipe implements SpecialRecipeInGuideBook {
    public RecipeCaveKey(ResourceLocation name, CraftingBookCategory category) {
        super(name, "", category, 3, 3, NonNullList.of(Ingredient.EMPTY,
                        Ingredient.of(Items.BLAZE_POWDER), Ingredient.of(Items.ENDER_PEARL), Ingredient.of(Items.BLAZE_POWDER),
                        Ingredient.of(Items.ENDER_PEARL), Ingredient.of(ACItemRegistry.CAVE_CODEX.get()), Ingredient.of(Items.ENDER_PEARL),
                        Ingredient.of(Items.BLAZE_POWDER), Ingredient.of(Items.ENDER_PEARL), Ingredient.of(Items.BLAZE_POWDER)),
                AlexCavesDimensions.ABYSSAL_CHASM_KEY.get().getDefaultInstance());
    }

    @Override
    public @NotNull ItemStack assemble(CraftingContainer container, @NotNull RegistryAccess registryAccess) {
        ItemStack scroll = ItemStack.EMPTY;

        for (int i = 0; i <= container.getContainerSize(); ++i) {
            if (!container.getItem(i).isEmpty() && container.getItem(i).is(ACItemRegistry.CAVE_CODEX.get()) && scroll.isEmpty()) {
                scroll = container.getItem(i);
            }
        }

        ResourceKey<Biome> key = CaveInfoItem.getCaveBiome(scroll);
        if (key != null) {
            return switch (key.location().toString()) {
                case "alexscaves:abyssal_chasm" -> AlexCavesDimensions.ABYSSAL_CHASM_KEY.get().getDefaultInstance();
                case "alexscaves:candy_cavity" -> AlexCavesDimensions.CANDY_CAVITY_KEY.get().getDefaultInstance();
                case "alexscaves:forlorn_hollows" -> AlexCavesDimensions.FORLORN_HOLLOWS_KEY.get().getDefaultInstance();
                case "alexscaves:magnetic_caves" -> AlexCavesDimensions.MAGNETIC_CAVES_KEY.get().getDefaultInstance();
                case "alexscaves:primordial_caves" ->
                        AlexCavesDimensions.PRIMORDIAL_CAVES_KEY.get().getDefaultInstance();
                case "alexscaves:toxic_caves" -> AlexCavesDimensions.TOXIC_CAVES_KEY.get().getDefaultInstance();
                default -> ItemStack.EMPTY;
            };
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ACRecipeRegistry.CAVE_MAP.get();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getDisplayIngredients() {
        return this.getIngredients();
    }

    @Override
    public ItemStack getDisplayResultFor(NonNullList<ItemStack> nonNullList) {
        ItemStack scroll = ItemStack.EMPTY;

        for (int i = 0; i <= nonNullList.size(); ++i) {
            if (!nonNullList.get(i).isEmpty() && nonNullList.get(i).is(ACItemRegistry.CAVE_CODEX.get()) && scroll.isEmpty()) {
                scroll = nonNullList.get(i);
            }
        }

        ResourceKey<Biome> key = CaveInfoItem.getCaveBiome(scroll);
        if (key != null) {
            return CaveMapItem.createMap(key);
        } else {
            return ItemStack.EMPTY;
        }
    }
}
