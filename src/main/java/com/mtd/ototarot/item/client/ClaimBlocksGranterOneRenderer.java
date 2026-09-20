package com.mtd.ototarot.item.client;

import com.mtd.ototarot.item.custom.ClaimBlocksGranterFourItem;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterOneItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ClaimBlocksGranterOneRenderer extends GeoItemRenderer<ClaimBlocksGranterOneItem> {
    public ClaimBlocksGranterOneRenderer() {
        super(new ClaimBlocksGranterOneModel());
    }
}