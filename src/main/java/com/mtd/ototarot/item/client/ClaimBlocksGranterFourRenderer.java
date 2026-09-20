package com.mtd.ototarot.item.client;

import com.mtd.ototarot.item.custom.ClaimBlocksGranterFourItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ClaimBlocksGranterFourRenderer extends GeoItemRenderer<ClaimBlocksGranterFourItem> {
    public ClaimBlocksGranterFourRenderer() {
        super(new ClaimBlocksGranterFourModel());
    }
}