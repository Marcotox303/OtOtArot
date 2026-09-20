package com.mtd.ototarot.client; // Cambia esto por tu paquete

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.client.render.MySkeletonRenderer;
import com.mtd.ototarot.item.ModItems;
import com.mtd.ototarot.item.client.ClaimBlocksGranterFourRenderer;
import com.mtd.ototarot.item.client.ClaimBlocksGranterOneRenderer;
import com.mtd.ototarot.item.client.ClaimBlocksGranterThreeRenderer;
import com.mtd.ototarot.item.client.ClaimBlocksGranterTwoRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

// Usamos value = Dist.CLIENT para que este código solo se ejecute en el cliente
@EventBusSubscriber(modid = OtOtArot.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerRenderers(RegisterClientExtensionsEvent event) {
        // Registramos nuestro renderizador personalizado para la entidad Skeleto
        event.registerItem(new IClientItemExtensions() {
            private ClaimBlocksGranterOneRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new ClaimBlocksGranterOneRenderer();
                }
                return this.renderer;
            }
        }, ModItems.CLAIM_BLOCKS_GRANTER_ONE.get());
        event.registerItem(new IClientItemExtensions() {
            private ClaimBlocksGranterTwoRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new ClaimBlocksGranterTwoRenderer();
                }
                return this.renderer;
            }
        }, ModItems.CLAIM_BLOCKS_GRANTER_TWO.get());
        event.registerItem(new IClientItemExtensions() {
            private ClaimBlocksGranterThreeRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new ClaimBlocksGranterThreeRenderer();
                }
                return this.renderer;
            }
        }, ModItems.CLAIM_BLOCKS_GRANTER_THREE.get());
        event.registerItem(new IClientItemExtensions() {
            private ClaimBlocksGranterFourRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new ClaimBlocksGranterFourRenderer();
                }
                return this.renderer;
            }
        }, ModItems.CLAIM_BLOCKS_GRANTER_FOUR.get());
    }
}