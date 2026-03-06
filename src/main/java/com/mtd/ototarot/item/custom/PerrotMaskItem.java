package com.mtd.ototarot.item.custom;

import com.mtd.ototarot.client.model.PerrotMaskModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
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
    public void initializeClient(java.util.function.Consumer<net.neoforged.neoforge.client.extensions.common.IClientItemExtensions> consumer) {
        consumer.accept(new net.neoforged.neoforge.client.extensions.common.IClientItemExtensions() {
            @Override
            public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(net.minecraft.world.entity.LivingEntity entity, net.minecraft.world.item.ItemStack itemStack, net.minecraft.world.entity.EquipmentSlot armorSlot, net.minecraft.client.model.HumanoidModel<?> _default) {
                return new PerrotMaskModel<>(net.minecraft.client.Minecraft.getInstance().getEntityModels().bakeLayer(net.minecraft.client.model.geom.ModelLayers.PLAYER_OUTER_ARMOR));
            }
        });
    }
}