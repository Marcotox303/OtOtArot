package com.mtd.ototarot.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public class ModKeybinds {
    public static final KeyMapping OPEN_MARKET_KEY = new KeyMapping(
            "key.ototarot.open_market",
            InputConstants.KEY_G, // Tecla 'M' asignada por defecto
            "key.categories.ototarot"
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(OPEN_MARKET_KEY);
    }
}