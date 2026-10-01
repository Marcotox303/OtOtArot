package com.mtd.ototarot.economy;

import com.mtd.ototarot.item.ModItems; // Reemplaza si tu clase de ítems tiene otro nombre o paquete
import net.minecraft.world.item.Item;
import java.util.*;
import java.util.function.Supplier;

public class CurrencyRegistry {
    private static final Map<String, Currency> CURRENCIES = new LinkedHashMap<>();

    // AQUÍ REGISTRAS TUS MONEDAS (Para añadir una nueva en el futuro, solo agregas una línea aquí)
    public static final Currency GOLD_COIN = register("golden_coin", () -> ModItems.GOLDEN_COIN.get(), 1.0, 0.05);
    public static final Currency IRON_COIN = register("iron_coin", () -> ModItems.IRON_COIN.get(), 0.25, 0.04);
    public static final Currency CASINO_CHIP = register("casino_chip", () -> ModItems.BLUE_CASINO_CHIP.get(), 0.01, 0.08);

    public static Currency register(String id, Supplier<Item> itemSupplier, double baseValue, double volatility) {
        Currency currency = new Currency(id, itemSupplier, baseValue, volatility);
        CURRENCIES.put(id, currency);
        return currency;
    }

    public static Collection<Currency> getAll() { return CURRENCIES.values(); }
    public static Currency getById(String id) { return CURRENCIES.get(id); }
    public static Currency getByItem(Item item) {
        return CURRENCIES.values().stream()
                .filter(c -> c.getItem() == item)
                .findFirst().orElse(null);
    }
}