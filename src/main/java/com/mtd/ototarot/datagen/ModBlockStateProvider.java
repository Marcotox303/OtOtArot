package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.block.custom.ATMBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OtOtArot.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile tetoModel = models().getExistingFile(modLoc("block/teto_plush"));
        horizontalBlock(ModBlocks.TETO_PLUSH.get(), tetoModel, 180);
        blockItem(ModBlocks.TETO_PLUSH);
        ModelFile neruModel = models().getExistingFile(modLoc("block/neru_plush"));
        horizontalBlock(ModBlocks.NERU_PLUSH.get(), neruModel, 180);
        blockItem(ModBlocks.NERU_PLUSH);
        ModelFile mikuModel = models().getExistingFile(modLoc("block/miku_plush"));
        horizontalBlock(ModBlocks.MIKU_PLUSH.get(), mikuModel, 180);
        blockItem(ModBlocks.MIKU_PLUSH);

        blockWithItem(ModBlocks.ROARING_STONE);
        StairBlock stairsBlock = (StairBlock) ModBlocks.ROARING_STONE_STAIRS.get();
        stairsBlock(stairsBlock,modLoc("block/roaring_stone"));
        itemModels().stairs("roaring_stone_stairs",modLoc("block/roaring_stone"),modLoc("block/roaring_stone"),modLoc("block/roaring_stone"));
        SlabBlock slabBlock = (SlabBlock) ModBlocks.ROARING_STONE_SLAB.get();
        slabBlock(slabBlock,modLoc("block/roaring_stone"),modLoc("block/roaring_stone"));
        itemModels().slab("roaring_stone_slab",modLoc("block/roaring_stone"),modLoc("block/roaring_stone"),modLoc("block/roaring_stone"));
        WallBlock wallBlock = (WallBlock) ModBlocks.ROARING_STONE_WALL.get();
        wallBlock(wallBlock,modLoc("block/roaring_stone"));
        itemModels().wallInventory("roaring_stone_wall",modLoc("block/roaring_stone"));

        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_OAK_TRAPDOOR.get(),
                mcLoc("block/oak_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_OAK_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_oak_trapdoor", mcLoc("block/oak_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_SPRUCE_TRAPDOOR.get(),
                mcLoc("block/spruce_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_SPRUCE_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_spruce_trapdoor", mcLoc("block/spruce_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_BIRCH_TRAPDOOR.get(),
                mcLoc("block/birch_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_BIRCH_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_birch_trapdoor", mcLoc("block/birch_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_JUNGLE_TRAPDOOR.get(),
                mcLoc("block/jungle_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_JUNGLE_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_jungle_trapdoor", mcLoc("block/jungle_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_ACACIA_TRAPDOOR.get(),
                mcLoc("block/acacia_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_ACACIA_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_acacia_trapdoor", mcLoc("block/acacia_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_DARK_OAK_TRAPDOOR.get(),
                mcLoc("block/dark_oak_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_DARK_OAK_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_dark_oak_trapdoor", mcLoc("block/dark_oak_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_MANGROVE_TRAPDOOR.get(),
                mcLoc("block/mangrove_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_MANGROVE_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_mangrove_trapdoor", mcLoc("block/mangrove_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_CHERRY_TRAPDOOR.get(),
                mcLoc("block/cherry_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_CHERRY_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_cherry_trapdoor", mcLoc("block/cherry_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_BAMBOO_TRAPDOOR.get(),
                mcLoc("block/bamboo_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_BAMBOO_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_bamboo_trapdoor", mcLoc("block/bamboo_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_CRIMSON_TRAPDOOR.get(),
                mcLoc("block/crimson_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_CRIMSON_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_crimson_trapdoor", mcLoc("block/crimson_trapdoor")));
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.JAMMED_WARPED_TRAPDOOR.get(),
                mcLoc("block/warped_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(ModBlocks.JAMMED_WARPED_TRAPDOOR.get(),
                models().trapdoorBottom("jammed_warped_trapdoor", mcLoc("block/warped_trapdoor")));



        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR.get(),
                mcLoc("block/oak_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_oak_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/oak_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR.get(),
                mcLoc("block/spruce_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_spruce_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/spruce_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR.get(),
                mcLoc("block/birch_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_birch_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/birch_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR.get(),
                mcLoc("block/jungle_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_jungle_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/jungle_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR.get(),
                mcLoc("block/acacia_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_acacia_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/acacia_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR.get(),
                mcLoc("block/dark_oak_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_dark_oak_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/dark_oak_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR.get(),
                mcLoc("block/mangrove_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_mangrove_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/mangrove_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR.get(),
                mcLoc("block/cherry_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_cherry_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/cherry_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR.get(),
                mcLoc("block/bamboo_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_bamboo_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/bamboo_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR.get(),
                mcLoc("block/crimson_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_crimson_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/crimson_trapdoor"))
        );
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR.get(),
                mcLoc("block/warped_trapdoor"),
                true,
                "cutout"
        );
        simpleBlockItem(
                ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR.get(),
                models().withExistingParent(
                        "open_jammed_warped_trapdoor",
                        modLoc("block/jagged_trapdoor_open_item")
                ).texture("0", mcLoc("block/warped_trapdoor"))
        );

        registerATMBlock(ModBlocks.ATM.get(), "atm");
        registerATMBlock(ModBlocks.CASINO_ATM.get(),"casino_atm");


    }

    private void registerATMBlock(net.minecraft.world.level.block.Block block, String name) {
        ModelFile bottomModel = models().withExistingParent(name+"_bottom",modLoc("block/atm_geom_bottom"))
                .texture("0", modLoc("block/"+name)).texture("particle", modLoc("block/"+name));
        ModelFile topModel = models().withExistingParent(name+"_top",modLoc("block/atm_geom_top"))
                .texture("0", modLoc("block/"+name)).texture("particle", modLoc("block/"+name));

        getVariantBuilder(block).forAllStates(state -> {
            int half = state.getValue(ATMBlock.HALF);
            net.minecraft.core.Direction dir = state.getValue(HorizontalDirectionalBlock.FACING);

            int rotY = switch (dir) {
                case SOUTH -> 180;
                case WEST -> 270;
                case EAST -> 90;
                default -> 0;
            };
            return net.neoforged.neoforge.client.model.generators.ConfiguredModel.builder()
                    .modelFile(half == 0 ? bottomModel : topModel)
                    .rotationY(rotY)
                    .build();
        });
        simpleBlockItem(
                block,
                models().withExistingParent(
                        name,
                        modLoc("block/atm_geom")
                ).texture("0", modLoc("block/"+name)).texture("particle", modLoc("block/"+name)));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("ototarot:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("ototarot:block/" + deferredBlock.getId().getPath() + appendix));
    }


}
