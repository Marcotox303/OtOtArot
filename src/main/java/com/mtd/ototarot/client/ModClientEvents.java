package com.mtd.ototarot.client; // Cambia esto por tu paquete

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.client.render.MySkeletonRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

// Usamos value = Dist.CLIENT para que este código solo se ejecute en el cliente
@EventBusSubscriber(modid = OtOtArot.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Registramos nuestro renderizador personalizado para la entidad Skeleto
        event.registerEntityRenderer(EntityType.SKELETON, MySkeletonRenderer::new);
    }
}