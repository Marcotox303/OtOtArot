package com.mtd.ototarot.item;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OtOtArot.MOD_ID);

    public static final DeferredItem<Item> GOLDEN_COIN = ITEMS.register("golden_coin",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_COIN = ITEMS.register("iron_coin",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MARTINBUTTER = ITEMS.register("martinbutter",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PERROT_COIN = ITEMS.register("perrot_coin",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}