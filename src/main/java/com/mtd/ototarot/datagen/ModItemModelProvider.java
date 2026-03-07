package com.mtd.ototarot.datagen;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OtOtArot.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModItems.GOLDEN_COIN.get());
        basicItem(ModItems.IRON_COIN.get());
        basicItem(ModItems.PERROT_COIN.get());
        basicItem(ModItems.MARTINBUTTER.get());
        basicItem(ModItems.CLAIM_GENERATOR.get());
        basicItem(ModItems.TERRAIN_CLAIMER.get());
        basicItem(ModItems.TERRAIN_INSPECTOR.get());
        basicItem(ModItems.BATA_BOOM_MUSIC_DISC.get());
        basicItem(ModItems.CAN_YOU_SEND_ME_30K_MUSIC_DISC.get());
        basicItem(ModItems.CUATRO_K_MUSIC_DISC.get());
        basicItem(ModItems.CHUPA_LA_GAMBA_MUSIC_DISC.get());
        basicItem(ModItems.LA_GOZADERA_MUSIC_DISC.get());
        basicItem(ModItems.PPPP_MUSIC_DISC.get());
        basicItem(ModItems.LEMON_MELON_COOKIE_MUSIC_DISC.get());
        basicItem(ModItems.LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC.get());
        basicItem(ModItems.SE_PREPARO_MUSIC_DISC.get());
        basicItem(ModItems.PERROT_MASK.get());
    }
}
