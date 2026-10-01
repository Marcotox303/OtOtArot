package com.mtd.ototarot.item;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
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
                        output.accept(ModItems.DIAMOND_COIN);
                        output.accept(ModItems.NETHERITE_COIN);
                        output.accept(ModItems.BLUE_CASINO_CHIP);
                        output.accept(ModItems.RED_CASINO_CHIP);
                        output.accept(ModItems.GREEN_CASINO_CHIP);
                        output.accept(ModItems.BLACK_CASINO_CHIP);
                        output.accept(ModBlocks.ATM);
                        output.accept(ModBlocks.CASINO_ATM);
                        output.accept(ModItems.TERRAIN_CLAIMER);
                        output.accept(ModItems.TERRAIN_INSPECTOR);
                        output.accept(ModItems.CLAIM_GENERATOR);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_ONE);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_TWO);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_THREE);
                        output.accept(ModItems.CLAIM_BLOCKS_GRANTER_FOUR);
                        output.accept(ModBlocks.ROARING_STONE);
                        output.accept(ModBlocks.ROARING_STONE_SLAB);
                        output.accept(ModBlocks.ROARING_STONE_STAIRS);
                        output.accept(ModBlocks.ROARING_STONE_WALL);
                        output.accept(ModBlocks.JAMMED_OAK_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_OAK_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_SPRUCE_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_SPRUCE_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_BIRCH_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_BIRCH_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_JUNGLE_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_JUNGLE_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_ACACIA_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_ACACIA_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_DARK_OAK_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_DARK_OAK_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_MANGROVE_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_MANGROVE_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_CHERRY_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_CHERRY_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_BAMBOO_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_BAMBOO_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_CRIMSON_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_CRIMSON_TRAPDOOR);
                        output.accept(ModBlocks.JAMMED_WARPED_TRAPDOOR);
                        output.accept(ModBlocks.OPEN_JAMMED_WARPED_TRAPDOOR);
                    }))
                    .build());

    public static final Supplier<CreativeModeTab> OT_OT_AROT_ITEMS_FUNNY_TAB = CREATIVE_MODE_TAB.register("ot_ot_arot_items_funny_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.PERROT_MASK.get()))
                    .title(Component.translatable("creativetab.ototarot.ototarot_items_funny"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PERROT_COIN);
                        output.accept(ModItems.MARTINBUTTER);
                        output.accept(ModItems.PERROT_MASK);
                        output.accept(ModBlocks.MIKU_PLUSH);
                        output.accept(ModBlocks.TETO_PLUSH);
                        output.accept(ModBlocks.NERU_PLUSH);
                        output.accept(ModItems.BATA_BOOM_MUSIC_DISC);
                        output.accept(ModItems.CAN_YOU_SEND_ME_30K_MUSIC_DISC);
                        output.accept(ModItems.CHUPA_LA_GAMBA_MUSIC_DISC);
                        output.accept(ModItems.CUATRO_K_MUSIC_DISC);
                        output.accept(ModItems.LA_GOZADERA_MUSIC_DISC);
                        output.accept(ModItems.LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC);
                        output.accept(ModItems.LEMON_MELON_COOKIE_MUSIC_DISC);
                        output.accept(ModItems.PPPP_MUSIC_DISC);
                        output.accept(ModItems.SE_PREPARO_MUSIC_DISC);
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);

    }
}
