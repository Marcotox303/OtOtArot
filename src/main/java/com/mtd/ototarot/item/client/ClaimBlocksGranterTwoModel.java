package com.mtd.ototarot.item.client;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterFourItem;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterTwoItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ClaimBlocksGranterTwoModel extends GeoModel<ClaimBlocksGranterTwoItem> {

    // Asume que tu archivo exportado se llama "granter_one.geo.json"
    @Override
    public ResourceLocation getModelResource(ClaimBlocksGranterTwoItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "geo/granter_two.geo.json");
    }

    // Asume que tu textura se llama "granter_one.png"
    @Override
    public ResourceLocation getTextureResource(ClaimBlocksGranterTwoItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/item/claim_blocks_granter.png");
    }

    // Asume que tus animaciones se llaman "granter_one.animation.json"
    @Override
    public ResourceLocation getAnimationResource(ClaimBlocksGranterTwoItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "animations/granter_two.animation.json");
    }
}