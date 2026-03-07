package com.mtd.ototarot.block;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.custom.DiscoBallBlock;
import com.mtd.ototarot.block.custom.MikuPlushBlock;
import com.mtd.ototarot.block.custom.NeruPlushBlock;
import com.mtd.ototarot.block.custom.TetoPlushBlock;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
    // Dentro de tu clase ModBlocks.java
    public static final DeferredBlock<Block> DISCO_BALL = registerBlock("disco_ball",
            () -> new DiscoBallBlock(BlockBehaviour.Properties.of().noOcclusion().lightLevel(state -> 15)));
}
