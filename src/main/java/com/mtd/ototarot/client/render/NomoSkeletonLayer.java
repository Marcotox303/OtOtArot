package com.mtd.ototarot.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mtd.ototarot.OtOtArot;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Skeleton;
import org.jetbrains.annotations.NotNull;

// Asegúrate de que la cabecera sea esta:
// Cambia 'Skeleton' por 'AbstractSkeleton' en la definición de T
public class NomoSkeletonLayer<T extends net.minecraft.world.entity.monster.AbstractSkeleton, M extends net.minecraft.client.model.SkeletonModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/entity/skeleton/nomo_skeleton.png");

    // Usa RenderLayerParent para máxima compatibilidad
    public NomoSkeletonLayer(net.minecraft.client.renderer.entity.RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int packedLight,
            T skeleton,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        if (skeleton.getTags().contains("is_nomo_variant")) {

            // 🔹 copiar estado del modelo original
            this.getParentModel().copyPropertiesTo(this.getParentModel());

            renderColoredCutoutModel(
                    this.getParentModel(),
                    TEXTURE,
                    poseStack,
                    buffer,
                    packedLight,
                    skeleton,
                    -1
            );
        }
    }
}