package com.mtd.ototarot.item.client;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterOneItem;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterTwoItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ClaimBlocksGranterOneModel extends GeoModel<ClaimBlocksGranterOneItem> {

    // Asume que tu archivo exportado se llama "granter_one.geo.json"
    @Override
    public ResourceLocation getModelResource(ClaimBlocksGranterOneItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "geo/granter_one.geo.json");
    }

    // Asume que tu textura se llama "granter_one.png"
    @Override
    public ResourceLocation getTextureResource(ClaimBlocksGranterOneItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/item/iron.png");
    }

    // Asume que tus animaciones se llaman "granter_one.animation.json"
    @Override
    public ResourceLocation getAnimationResource(ClaimBlocksGranterOneItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "animations/granter_one.animation.json");
    }
}