package com.mtd.ototarot.item;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OtOtArot.MOD_ID);

    public static final Supplier<CreativeModeTab> OT_OT_AROT_ITEMS_TAB = CREATIVE_MODE_TAB.register("ot_ot_arot_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.GOLDEN_COIN.get()))
                    .title(Component.translatable("creativetab.ototarot.ototarot_items"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.IRON_COIN);
                        output.accept(ModItems.GOLDEN_COIN);
                        output.accept(ModItems.PERROT_COIN);
                        output.accept(ModItems.MARTINBUTTER);
                        output.accept(ModItems.TERRAIN_CLAIMER);
                        output.accept(ModItems.TERRAIN_INSPECTOR);
                        output.accept(ModItems.CLAIM_GENERATOR);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_ONE);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_TWO);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_THREE);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_FOUR);
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);

    }
}
