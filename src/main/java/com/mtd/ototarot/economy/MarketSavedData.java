package com.mtd.ototarot.economy;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Random;

public class MarketSavedData extends SavedData {
    private static final String DATA_NAME = "ototarot_market_data";
    private int lastUpdatedDay = -1;

    public static MarketSavedData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(
                new Factory<>(MarketSavedData::new, MarketSavedData::load, null), DATA_NAME
        );
    }

    public void checkAndTickMarket(ServerLevel level) {
        int currentDay = (int) (level.getDayTime() / 24000L);
        if (currentDay > lastUpdatedDay) {
            lastUpdatedDay = currentDay;
            updateMarketPrices();
            setDirty();
        }
    }

    private void updateMarketPrices() {
        Random random = new Random();
        for (Currency currency : CurrencyRegistry.getAll()) {
            double percentChange = random.nextGaussian() * currency.getVolatility();
            double newValue = currency.getCurrentValue() * (1.0 + percentChange);
            currency.setCurrentValue(newValue);
        }
    }

    public static MarketSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        MarketSavedData data = new MarketSavedData();
        data.lastUpdatedDay = tag.getInt("lastUpdatedDay");
        CompoundTag prices = tag.getCompound("prices");
        for (Currency currency : CurrencyRegistry.getAll()) {
            if (prices.contains(currency.getId())) {
                currency.setCurrentValue(prices.getDouble(currency.getId()));
            }
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putInt("lastUpdatedDay", lastUpdatedDay);
        CompoundTag prices = new CompoundTag();
        for (Currency currency : CurrencyRegistry.getAll()) {
            prices.putDouble(currency.getId(), currency.getCurrentValue());
        }
        tag.put("prices", prices);
        return tag;
    }
}