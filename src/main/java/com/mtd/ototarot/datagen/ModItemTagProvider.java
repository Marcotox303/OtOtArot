package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, OtOtArot.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.PPPP_MUSIC_DISC.get())
                .add(ModItems.CUATRO_K_MUSIC_DISC.get())
                .add(ModItems.SE_PREPARO_MUSIC_DISC.get())
                .add(ModItems.LEMON_MELON_COOKIE_MUSIC_DISC.get())
                .add(ModItems.LA_GOZADERA_MUSIC_DISC.get())
                .add(ModItems.LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC.get())
                .add(ModItems.CHUPA_LA_GAMBA_MUSIC_DISC.get())
                .add(ModItems.CAN_YOU_SEND_ME_30K_MUSIC_DISC.get())
                .add(ModItems.BATA_BOOM_MUSIC_DISC.get());

        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "music_discs")))
                .add(Items.MUSIC_DISC_5)
                .add(Items.MUSIC_DISC_11)
                .add(Items.MUSIC_DISC_13)
                .add(Items.MUSIC_DISC_BLOCKS)
                .add(Items.MUSIC_DISC_CAT)
                .add(Items.MUSIC_DISC_CHIRP)
                .add(Items.MUSIC_DISC_CREATOR)
                .add(Items.MUSIC_DISC_CREATOR_MUSIC_BOX)
                .add(Items.MUSIC_DISC_FAR)
                .add(Items.MUSIC_DISC_MALL)
                .add(Items.MUSIC_DISC_MELLOHI)
                .add(Items.MUSIC_DISC_OTHERSIDE)
                .add(Items.MUSIC_DISC_PIGSTEP)
                .add(Items.MUSIC_DISC_PRECIPICE)
                .add(Items.MUSIC_DISC_RELIC)
                .add(Items.MUSIC_DISC_STAL)
                .add(Items.MUSIC_DISC_STRAD)
                .add(Items.MUSIC_DISC_WAIT)
                .add(Items.MUSIC_DISC_WARD)
                .add(ModItems.PPPP_MUSIC_DISC.get())
                .add(ModItems.CUATRO_K_MUSIC_DISC.get())
                .add(ModItems.SE_PREPARO_MUSIC_DISC.get())
                .add(ModItems.LEMON_MELON_COOKIE_MUSIC_DISC.get())
                .add(ModItems.LA_GOZADERA_MUSIC_DISC.get())
                .add(ModItems.LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC.get())
                .add(ModItems.CHUPA_LA_GAMBA_MUSIC_DISC.get())
                .add(ModItems.CAN_YOU_SEND_ME_30K_MUSIC_DISC.get())
                .add(ModItems.BATA_BOOM_MUSIC_DISC.get());

        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "jammers")))
                .add(Items.SLIME_BALL)
                .add(Items.HONEYCOMB);

        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "casino_chips")))
                .add(ModItems.BLUE_CASINO_CHIP.get())
                .add(ModItems.RED_CASINO_CHIP.get())
                .add(ModItems.GREEN_CASINO_CHIP.get())
                .add(ModItems.BLACK_CASINO_CHIP.get());
        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "coins")))
                .add(ModItems.IRON_COIN.get())
                .add(ModItems.GOLDEN_COIN.get())
                .add(ModItems.DIAMOND_COIN.get())
                .add(ModItems.NETHERITE_COIN.get());

        /// BLOCK TAGS

        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "plushies")))
                .add(ModBlocks.TETO_PLUSH.get().asItem())
                .add(ModBlocks.NERU_PLUSH.get().asItem())
                .add(ModBlocks.MIKU_PLUSH.get().asItem());

        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "roaring_stone")))
                .add(ModBlocks.ROARING_STONE.get().asItem())
                .add(ModBlocks.ROARING_STONE_SLAB.get().asItem())
                .add(ModBlocks.ROARING_STONE_STAIRS.get().asItem())
                .add(ModBlocks.ROARING_STONE_WALL.get().asItem());

        tag(ItemTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "jammed_trapdoor")))
                .add(ModBlocks.JAMMED_OAK_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_SPRUCE_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_BIRCH_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_JUNGLE_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_ACACIA_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_DARK_OAK_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_MANGROVE_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_CHERRY_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_BAMBOO_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_CRIMSON_TRAPDOOR.get().asItem())
                .add(ModBlocks.JAMMED_WARPED_TRAPDOOR.get().asItem())

                .add(ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR.get().asItem())
                .add(ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR.get().asItem());
    }
}
