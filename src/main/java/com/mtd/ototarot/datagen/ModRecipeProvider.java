package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> MUSIC_DISC_STONECUTTABLES = List.of(
                Items.MUSIC_DISC_5,
                Items.MUSIC_DISC_11,
                Items.MUSIC_DISC_13,
                Items.MUSIC_DISC_BLOCKS,
                Items.MUSIC_DISC_CAT,
                Items.MUSIC_DISC_CHIRP,
                Items.MUSIC_DISC_CREATOR,
                Items.MUSIC_DISC_CREATOR_MUSIC_BOX,
                Items.MUSIC_DISC_FAR,
                Items.MUSIC_DISC_MALL,
                Items.MUSIC_DISC_MELLOHI,
                Items.MUSIC_DISC_OTHERSIDE,
                Items.MUSIC_DISC_PIGSTEP,
                Items.MUSIC_DISC_PRECIPICE,
                Items.MUSIC_DISC_RELIC,
                Items.MUSIC_DISC_STAL,
                Items.MUSIC_DISC_STRAD,
                Items.MUSIC_DISC_WAIT,
                Items.MUSIC_DISC_WARD,
                ModItems.PPPP_MUSIC_DISC,
                ModItems.CUATRO_K_MUSIC_DISC,
                ModItems.SE_PREPARO_MUSIC_DISC,
                ModItems.LEMON_MELON_COOKIE_MUSIC_DISC,
                ModItems.LA_GOZADERA_MUSIC_DISC,
                ModItems.LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC,
                ModItems.CHUPA_LA_GAMBA_MUSIC_DISC,
                ModItems.CAN_YOU_SEND_ME_30K_MUSIC_DISC,
                ModItems.BATA_BOOM_MUSIC_DISC
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TERRAIN_CLAIMER.get())
                .pattern("CRC")
                .pattern("RIR")
                .pattern("CRC")
                .define('C', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('R', Items.REDSTONE)
                .define('I', Items.RAW_IRON)
                .unlockedBy("has_air", has(Items.AIR))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TERRAIN_INSPECTOR.get())
                .pattern("  G")
                .pattern(" I ")
                .pattern("R  ")
                .define('G', Items.GOLD_INGOT)
                .define('R', Items.REDSTONE)
                .define('I', Items.RAW_IRON)
                .unlockedBy("has_air", has(Items.AIR))
                .save(recipeOutput);

        // Ejemplo para modificar el IMS de SecurityCraft
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("securitycraft", "ims")))
                .pattern("BPB") // Tu nuevo patrón aquí
                .pattern(" D ")
                .pattern("B B")
                .define('P', BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("securitycraft", "portable_radar")))
                .define('D', BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("securitycraft", "reinforced_diamond_block")))
                .define('B', BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("securitycraft", "bouncing_betty")))
                .unlockedBy("has_air", has(Items.AIR))
                // ESTA ES LA CLAVE: El nombre debe ser igual al original
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("securitycraft", "ims"));

// Ejemplo para modificar la Bouncing Betty
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("securitycraft", "bouncing_betty")))
                .pattern(" P ")
                .pattern("DGD")
                .pattern("   ")
                .define('G', Items.GUNPOWDER)
                .define('D', Items.DIAMOND)
                .define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .unlockedBy("has_air", has(Items.AIR))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("securitycraft", "bouncing_betty"));

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(Items.RED_WOOL),
                        RecipeCategory.DECORATIONS,
                        ModBlocks.TETO_PLUSH.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":teto_plush_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(Items.LIGHT_BLUE_WOOL),
                        RecipeCategory.DECORATIONS,
                        ModBlocks.MIKU_PLUSH.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":miku_plush_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(Items.YELLOW_WOOL),
                        RecipeCategory.DECORATIONS,
                        ModBlocks.NERU_PLUSH.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":neru_plush_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.BATA_BOOM_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":bata_boom_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.CUATRO_K_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":4k_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.PPPP_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":pppp_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.LEMON_MELON_COOKIE_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":lemon_melon_cookie_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.CHUPA_LA_GAMBA_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":chupa_la_gamba_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.LA_GOZADERA_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":la_gozadera_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":la_vida_es_un_carrusel_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.CAN_YOU_SEND_ME_30K_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":can_you_send_me_30k_music_disc_from_stonecutting");

        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(MUSIC_DISC_STONECUTTABLES.stream().map(ItemStack::new)),
                        RecipeCategory.MISC,
                        ModItems.SE_PREPARO_MUSIC_DISC.get(),                // Ítem de salida
                        1                                          // Cantidad
                )
                .unlockedBy("has_item", has(Items.BARRIER))
                .save(recipeOutput, OtOtArot.MOD_ID + ":se_preparo_music_disc_from_stonecutting");


    }
}
