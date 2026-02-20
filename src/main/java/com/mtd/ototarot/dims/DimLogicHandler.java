package com.mtd.ototarot.dims;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = "ototarot")
public class DimLogicHandler {
    // Mapa de UUID -> Tiempo en ticks de cuándo expira el cooldown
    private static final Map<UUID, Long> COOLDOWNS = new HashMap<>();

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            // Establece que no puede viajar hasta: tiempo actual + 600 ticks (30 segundos)
            COOLDOWNS.put(player.getUUID(), player.level().getGameTime() + 1200);
            player.displayClientMessage(Component.literal("¡Cooldown activado por daño! (30s)"), true);
        }
    }

    public static boolean isInCooldown(Player player) {
        return player.level().getGameTime() < COOLDOWNS.getOrDefault(player.getUUID(), 0L);
    }
}