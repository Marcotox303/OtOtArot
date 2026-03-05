package com.mtd.ototarot.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class ClaimGeneratorItem extends Item {
    public ClaimGeneratorItem(Properties properties) {
        super(properties);
    }

    // Duración máxima de la animación (como el arco)
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    // Qué pasa cuando empiezas a pulsar el botón derecho
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    // Qué pasa cuando sueltas el botón
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!level.isClientSide() && entity instanceof Player player) {
             // Calculamos cuánto tiempo se ha cargado
            int i = this.getUseDuration(stack, entity) - timeLeft;

            // 20 ticks = 1 segundo. El arco suele tardar unos 20 ticks en cargar.
            if (i >= 20) {
                ejecutarFuncion(player);
            }
        }
    }

    private void ejecutarFuncion(Player player) {
        if (player.getServer() != null) {
            // Buscamos y ejecutamos la función: "tu_mod:mi_accion_especial"
            ResourceLocation functionLocation = ResourceLocation.fromNamespaceAndPath("ototarot", "create_claim_base");
            player.getServer().getFunctions().execute(
                    player.getServer().getFunctions().get(functionLocation).get(),
                    player.createCommandSourceStack().withSuppressedOutput().withPermission(2)
            );
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.ototarot.claim_generator.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}

