package com.mtd.ototarot.datagen;

import com.mtd.ototarot.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.TETO_PLUSH.get());
        dropSelf(ModBlocks.MIKU_PLUSH.get());
        dropSelf(ModBlocks.NERU_PLUSH.get());

        dropSelf(ModBlocks.ROARING_STONE.get());
        dropSelf(ModBlocks.ROARING_STONE_SLAB.get());
        dropSelf(ModBlocks.ROARING_STONE_STAIRS.get());
        dropSelf(ModBlocks.ROARING_STONE_WALL.get());

        dropSelf(ModBlocks.JAMMED_OAK_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_SPRUCE_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_BIRCH_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_JUNGLE_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_ACACIA_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_DARK_OAK_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_MANGROVE_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_CHERRY_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_BAMBOO_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_CRIMSON_TRAPDOOR.get());
        dropSelf(ModBlocks.JAMMED_WARPED_TRAPDOOR.get());

        dropSelf(ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR.get());
        dropSelf(ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR.get());

        dropSelf(ModBlocks.ATM.get());
        dropSelf(ModBlocks.CASINO_ATM.get());


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
