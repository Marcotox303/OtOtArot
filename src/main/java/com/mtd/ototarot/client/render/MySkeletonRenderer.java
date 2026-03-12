package com.mtd.ototarot.client.render; // Cambia esto por tu paquete

import com.mtd.ototarot.ModAttachments;
import com.mtd.ototarot.OtOtArot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Skeleton;
// Asegúrate de importar tus Attachments

// Fíjate en los diamantes <Skeleton> añadidos después de SkeletonRenderer
public class MySkeletonRenderer extends SkeletonRenderer<Skeleton> {

    private static final ResourceLocation VARIANT_1_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/entity/skeleton/nomo_skeleton.png");

    public MySkeletonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    // En MySkeletonRenderer.java
    @Override
    public ResourceLocation getTextureLocation(Skeleton entity) {
        // Es vital usar .get() aquí
        Integer variant = entity.getData(ModAttachments.SKELETON_VARIANT.get());

        if (variant != null && variant == 1) {
            return VARIANT_1_TEXTURE;
        }

        return super.getTextureLocation(entity);
    }
}