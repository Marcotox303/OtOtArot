package com.mtd.ototarot.event;

import com.mtd.ototarot.ModAttachments;
import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = OtOtArot.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        ItemStack stack = event.getItemStack();

        // Verifica tu item "blueifier". Asegúrate de que ModItems.BLUEIFIER sea el correcto.
        if (stack.is(ModItems.BLUEIFIER.get()) && event.getTarget() instanceof Skeleton skeleton) {

            if (!event.getLevel().isClientSide()) {
                // 1. Cambiamos el dato
                skeleton.setData(ModAttachments.SKELETON_VARIANT.get(), 1);

                // 2. SINCRONIZACIÓN: En lugar de setDirty o sendPairingData
                // Esto obliga al servidor a reenviar los datos de la entidad a los clientes
                if (event.getLevel() instanceof ServerLevel serverLevel) {
                    // Tienes que meter 'skeleton' dentro del paréntesis
                    serverLevel.getChunkSource().broadcastAndSend(skeleton, skeleton.getAddEntityPacket(new ServerEntity(serverLevel, skeleton, 0, false, p -> {})));
                }
            }

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }
}