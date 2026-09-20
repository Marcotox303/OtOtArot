package com.mtd.ototarot.item.client;

import com.mtd.ototarot.item.custom.ClaimBlocksGranterFourItem;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterThreeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ClaimBlocksGranterThreeRenderer extends GeoItemRenderer<ClaimBlocksGranterThreeItem> {
    public ClaimBlocksGranterThreeRenderer() {
        super(new ClaimBlocksGranterThreeModel());
    }
}