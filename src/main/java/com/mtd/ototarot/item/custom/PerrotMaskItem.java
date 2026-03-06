package com.mtd.ototarot.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class PerrotMaskItem extends ArmorItem {
    // El constructor ahora pide material y tipo para ser una armadura real
    public PerrotMaskItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.ototarot.perrot_mask.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide() && entity instanceof LivingEntity livingEntity) {
            // Comprobamos si el ítem en el slot de la cabeza es esta máscara
            ItemStack armorStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);

            if (armorStack.getItem() == this) {
                // Aplicamos Veneno II (el 1 en el amplificador significa nivel 2)
                // 40 ticks = 2 segundos, se refresca constantemente mientras la lleve
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, false, false, true));
            }
        }
    }
}