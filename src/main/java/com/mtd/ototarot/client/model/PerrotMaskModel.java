package com.mtd.ototarot.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.jetbrains.annotations.NotNull;

public class PerrotMaskModel<T extends LivingEntity> extends HumanoidModel<T> {

    public PerrotMaskModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        if (entity instanceof AbstractIllager || entity instanceof AbstractVillager) {
            // Forzamos el desplazamiento sumando a la posición calculada por Minecraft
            this.head.y += 3.5F;
            this.hat.y += 3.5F;
        }
    }
}