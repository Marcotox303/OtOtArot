package com.mtd.ototarot.client.render;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.jetbrains.annotations.NotNull;

public class NomoSkeletonRenderer extends SkeletonRenderer {

    public NomoSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(AbstractSkeleton skeleton) {

        if (skeleton.getTags().contains("is_nomo_skeleton")) {
            return ResourceLocation.fromNamespaceAndPath(
                    OtOtArot.MOD_ID,
                    "textures/entity/nomo_skeleton.png"
            );
        }

        return super.getTextureLocation(skeleton);
    }
}