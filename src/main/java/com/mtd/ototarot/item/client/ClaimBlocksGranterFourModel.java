package com.mtd.ototarot.item.client;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterFourItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ClaimBlocksGranterFourModel extends GeoModel<ClaimBlocksGranterFourItem> {

    // Asume que tu archivo exportado se llama "granter_one.geo.json"
    @Override
    public ResourceLocation getModelResource(ClaimBlocksGranterFourItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "geo/granter_four.geo.json");
    }

    // Asume que tu textura se llama "granter_one.png"
    @Override
    public ResourceLocation getTextureResource(ClaimBlocksGranterFourItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/item/claim_blocks_granter.png");
    }

    // Asume que tus animaciones se llaman "granter_one.animation.json"
    @Override
    public ResourceLocation getAnimationResource(ClaimBlocksGranterFourItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "animations/granter_four.animation.json");
    }
}