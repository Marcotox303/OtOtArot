package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import net.minecraft.data.PackOutput;
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
        blockWithItem(ModBlocks.DISCO_BALL);

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
