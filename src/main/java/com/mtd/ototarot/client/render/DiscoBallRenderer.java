package com.mtd.ototarot.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mtd.ototarot.blockentity.custom.DiscoBallBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class DiscoBallRenderer implements BlockEntityRenderer<DiscoBallBlockEntity> {

    public DiscoBallRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(DiscoBallBlockEntity be, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        // Nos situamos en el centro del bloque
        poseStack.translate(0.5, 0.5, 0.5);

        // Usamos un RenderType aditivo o translúcido para que los rayos se vean como luz
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.lightning());

        for (DiscoBallBlockEntity.BeamData beam : be.beams) {
            poseStack.pushPose();

            // Rotamos el espacio para apuntar en la dirección del rayo.
            // Sumamos el partialTick a la rotación global para que el giro sea 100% fluido
            float currentYaw = beam.yaw + be.globalRotation + (1.5f * partialTick);

            poseStack.mulPose(Axis.YP.rotationDegrees(-currentYaw));
            poseStack.mulPose(Axis.XP.rotationDegrees(beam.pitch));

            // Ahora que estamos apuntando hacia donde va el rayo, solo tenemos que
            // dibujar un rectángulo alargado hacia adelante (en el eje Z)
            Matrix4f matrix = poseStack.last().pose();
            float length = beam.currentLength;
            float thickness = 0.05f; // Grosor del rayo

            int r = beam.color[0];
            int g = beam.color[1];
            int b = beam.color[2];
            int alpha = 150; // Transparencia (0-255)

            // Dibujamos 4 caras para hacer un rayo 3D (un pequeño prisma)
            drawQuad(matrix, vertexConsumer, r, g, b, alpha, length, thickness, thickness, -thickness, -thickness); // Arriba
            drawQuad(matrix, vertexConsumer, r, g, b, alpha, length, -thickness, -thickness, -thickness, thickness); // Abajo
            drawQuad(matrix, vertexConsumer, r, g, b, alpha, length, -thickness, thickness, thickness, thickness); // Derecha
            drawQuad(matrix, vertexConsumer, r, g, b, alpha, length, thickness, -thickness, thickness, -thickness); // Izquierda

            poseStack.popPose();
        }

        poseStack.popPose();
    }

    private void drawQuad(Matrix4f matrix, VertexConsumer consumer, int r, int g, int b, int a, float length, float x1, float y1, float x2, float y2) {
        // Vértice base 1 (Centro de la bola)
        consumer.addVertex(matrix, x1, y1, 0).setColor(r, g, b, a);
        // Vértice base 2 (Centro de la bola)
        consumer.addVertex(matrix, x2, y2, 0).setColor(r, g, b, a);
        // Vértice punta 2 (Donde choca el rayo)
        consumer.addVertex(matrix, x2, y2, length).setColor(r, g, b, 0); // alpha 0 en la punta para que se difumine
        // Vértice punta 1 (Donde choca el rayo)
        consumer.addVertex(matrix, x1, y1, length).setColor(r, g, b, 0);
    }

    // NOTA: Para que el bloque siempre se renderice aunque lo mires desde lejos
    @Override
    public boolean shouldRenderOffScreen(@NotNull DiscoBallBlockEntity be) {
        return true;
    }
}