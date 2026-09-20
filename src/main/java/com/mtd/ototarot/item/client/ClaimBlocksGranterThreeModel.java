package com.mtd.ototarot.item.client;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterOneItem;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterThreeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ClaimBlocksGranterThreeModel extends GeoModel<ClaimBlocksGranterThreeItem> {

    // Asume que tu archivo exportado se llama "granter_one.geo.json"
    @Override
    public ResourceLocation getModelResource(ClaimBlocksGranterThreeItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "geo/granter_three.geo.json");
    }

    // Asume que tu textura se llama "granter_one.png"
    @Override
    public ResourceLocation getTextureResource(ClaimBlocksGranterThreeItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/item/claim_blocks_granter.png");
    }

    // Asume que tus animaciones se llaman "granter_one.animation.json"
    @Override
    public ResourceLocation getAnimationResource(ClaimBlocksGranterThreeItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "animations/granter_three.animation.json");
    }
}