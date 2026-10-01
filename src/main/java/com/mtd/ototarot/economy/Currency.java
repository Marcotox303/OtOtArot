package com.mtd.ototarot.economy;

import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public class Currency {
    private final String id;
    private final Supplier<Item> itemSupplier;
    private final double baseValueInEmeralds;
    private final double volatility;
    private double currentValue;

    public Currency(String id, Supplier<Item> itemSupplier, double baseValueInEmeralds, double volatility) {
        this.id = id;
        this.itemSupplier = itemSupplier;
        this.baseValueInEmeralds = baseValueInEmeralds;
        this.volatility = volatility;
        this.currentValue = baseValueInEmeralds;
    }

    public String getId() { return id; }
    public Item getItem() { return itemSupplier.get(); }
    public double getBaseValue() { return baseValueInEmeralds; }
    public double getVolatility() { return volatility; }
    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) {
        this.currentValue = Math.max(0.0001, currentValue);
    }
}


/// Blue < Red < Green < Black