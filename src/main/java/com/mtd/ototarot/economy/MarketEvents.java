package com.mtd.ototarot.economy;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = OtOtArot.MOD_ID)
public class MarketEvents {
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel && serverLevel.dimension() == Level.OVERWORLD) {
            MarketSavedData.get(serverLevel).checkAndTickMarket(serverLevel);
        }
    }
}