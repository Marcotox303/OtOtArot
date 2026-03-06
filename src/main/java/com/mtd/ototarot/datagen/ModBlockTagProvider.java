package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import net.minecraft.core.HolderLookup;
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

    }
}
