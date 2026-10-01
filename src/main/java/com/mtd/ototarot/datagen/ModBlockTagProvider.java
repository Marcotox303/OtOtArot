package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OtOtArot.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "plushies")))
                .add(ModBlocks.TETO_PLUSH.get())
                .add(ModBlocks.NERU_PLUSH.get())
                .add(ModBlocks.MIKU_PLUSH.get());

        tag(BlockTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "roaring_stone")))
                .add(ModBlocks.ROARING_STONE.get())
                .add(ModBlocks.ROARING_STONE_SLAB.get())
                .add(ModBlocks.ROARING_STONE_STAIRS.get())
                .add(ModBlocks.ROARING_STONE_WALL.get());

        tag(BlockTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "jammed_trapdoors")))
                .add(ModBlocks.JAMMED_OAK_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_SPRUCE_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_BIRCH_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_JUNGLE_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_ACACIA_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_DARK_OAK_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_MANGROVE_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_CHERRY_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_BAMBOO_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_CRIMSON_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_WARPED_TRAPDOOR.get())

                .add(ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ROARING_STONE.get())
                .add(ModBlocks.ROARING_STONE_SLAB.get())
                .add(ModBlocks.ROARING_STONE_STAIRS.get())
                .add(ModBlocks.ROARING_STONE_WALL.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.JAMMED_OAK_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_SPRUCE_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_BIRCH_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_JUNGLE_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_ACACIA_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_DARK_OAK_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_MANGROVE_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_CHERRY_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_BAMBOO_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_CRIMSON_TRAPDOOR.get())
                .add(ModBlocks.JAMMED_WARPED_TRAPDOOR.get())

                .add(ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR.get())
                .add(ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR.get());

    }
}
