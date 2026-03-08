package com.mtd.ototarot.event;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.client.render.NomoSkeletonLayer;
import com.mtd.ototarot.client.render.NomoSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


// IMPORTANTE: bus = EventBusSubscriber.Bus.MOD
@EventBusSubscriber(modid = OtOtArot.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(
                EntityType.SKELETON,
                NomoSkeletonRenderer::new
        );
    }
}