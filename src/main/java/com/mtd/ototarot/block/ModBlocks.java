package com.mtd.ototarot.block;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.custom.*;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(OtOtArot.MOD_ID);

    public static final DeferredBlock<Block> TETO_PLUSH = registerBlock("teto_plush",
            () -> new TetoPlushBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f)
                    .sound(SoundType.WOOL)
                    .mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .ignitedByLava()
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> MIKU_PLUSH = registerBlock("miku_plush",
            () -> new MikuPlushBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f)
                    .sound(SoundType.WOOL)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .ignitedByLava()
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> NERU_PLUSH = registerBlock("neru_plush",
            () -> new NeruPlushBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f)
                    .sound(SoundType.WOOL)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .ignitedByLava()
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> ROARING_STONE = registerBlock("roaring_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f)
                    .sound(SoundType.AMETHYST)
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.DIDGERIDOO)
                    .explosionResistance(6f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<SlabBlock> ROARING_STONE_SLAB = registerBlock("roaring_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.ROARING_STONE.get())));
    public static final DeferredBlock<StairBlock> ROARING_STONE_STAIRS = registerBlock("roaring_stone_stairs",
            () -> new StairBlock(ROARING_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ModBlocks.ROARING_STONE.get())));
    public static final DeferredBlock<WallBlock> ROARING_STONE_WALL = registerBlock("roaring_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.ROARING_STONE.get())));

    public static final DeferredBlock<Block> JAMMED_OAK_TRAPDOOR = registerBlock("jammed_oak_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK
            ));
    public static final DeferredBlock<Block> JAMMED_SPRUCE_TRAPDOOR = registerBlock("jammed_spruce_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR),
                    BlockSetType.SPRUCE
            ));
    public static final DeferredBlock<Block> JAMMED_BIRCH_TRAPDOOR = registerBlock("jammed_birch_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_TRAPDOOR),
                    BlockSetType.BIRCH
            ));
    public static final DeferredBlock<Block> JAMMED_JUNGLE_TRAPDOOR = registerBlock("jammed_jungle_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR),
                    BlockSetType.JUNGLE
            ));
    public static final DeferredBlock<Block> JAMMED_ACACIA_TRAPDOOR = registerBlock("jammed_acacia_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR),
                    BlockSetType.ACACIA
            ));
    public static final DeferredBlock<Block> JAMMED_DARK_OAK_TRAPDOOR = registerBlock("jammed_dark_oak_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_TRAPDOOR),
                    BlockSetType.DARK_OAK
            ));
    public static final DeferredBlock<Block> JAMMED_MANGROVE_TRAPDOOR = registerBlock("jammed_mangrove_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR),
                    BlockSetType.MANGROVE
            ));
    public static final DeferredBlock<Block> JAMMED_CHERRY_TRAPDOOR = registerBlock("jammed_cherry_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_TRAPDOOR),
                    BlockSetType.CHERRY
            ));
    public static final DeferredBlock<Block> JAMMED_BAMBOO_TRAPDOOR = registerBlock("jammed_bamboo_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_TRAPDOOR),
                    BlockSetType.BAMBOO
            ));
    public static final DeferredBlock<Block> JAMMED_CRIMSON_TRAPDOOR = registerBlock("jammed_crimson_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR),
                    BlockSetType.CRIMSON
            ));
    public static final DeferredBlock<Block> JAMMED_WARPED_TRAPDOOR = registerBlock("jammed_warped_trapdoor",
            () -> new JammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR),
                    BlockSetType.WARPED
            ));

    public static final DeferredBlock<Block> OPEN_JAMMED_OAK_TRAPDOOR = registerBlock("open_jammed_oak_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_SPRUCE_TRAPDOOR = registerBlock("open_jammed_spruce_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR),
                    BlockSetType.SPRUCE
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_BIRCH_TRAPDOOR = registerBlock("open_jammed_birch_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_TRAPDOOR),
                    BlockSetType.BIRCH
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_JUNGLE_TRAPDOOR = registerBlock("open_jammed_jungle_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR),
                    BlockSetType.JUNGLE
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_ACACIA_TRAPDOOR = registerBlock("open_jammed_acacia_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR),
                    BlockSetType.ACACIA
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_DARK_OAK_TRAPDOOR = registerBlock("open_jammed_dark_oak_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_TRAPDOOR),
                    BlockSetType.DARK_OAK
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_MANGROVE_TRAPDOOR = registerBlock("open_jammed_mangrove_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR),
                    BlockSetType.MANGROVE
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_CHERRY_TRAPDOOR = registerBlock("open_jammed_cherry_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_TRAPDOOR),
                    BlockSetType.CHERRY
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_BAMBOO_TRAPDOOR = registerBlock("open_jammed_bamboo_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_TRAPDOOR),
                    BlockSetType.BAMBOO
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_CRIMSON_TRAPDOOR = registerBlock("open_jammed_crimson_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR),
                    BlockSetType.CRIMSON
            ));
    public static final DeferredBlock<Block> OPEN_JAMMED_WARPED_TRAPDOOR = registerBlock("open_jammed_warped_trapdoor",
            () -> new OpenJammedTrapdoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR),
                    BlockSetType.WARPED
            ));

    public static final DeferredBlock<Block> ATM = registerBlock("atm",
            () -> new ATMBlock(BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> CASINO_ATM = registerBlock("casino_atm",
            () -> new ATMBlock(BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()
            ));





    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

    }
}
