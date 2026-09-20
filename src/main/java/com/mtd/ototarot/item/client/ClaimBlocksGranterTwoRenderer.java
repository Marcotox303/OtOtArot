package com.mtd.ototarot.item.client;

import com.mtd.ototarot.item.custom.ClaimBlocksGranterFourItem;
import com.mtd.ototarot.item.custom.ClaimBlocksGranterTwoItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ClaimBlocksGranterTwoRenderer extends GeoItemRenderer<ClaimBlocksGranterTwoItem> {
    public ClaimBlocksGranterTwoRenderer() {
        super(new ClaimBlocksGranterTwoModel());
    }
}