package com.mtd.ototarot.item;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.custom.ClaimGeneratorItem;
import com.mtd.ototarot.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

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

    public static final DeferredItem<Item> TERRAIN_CLAIMER = ITEMS.register("terrain_claimer",
            () -> new Item(new Item.Properties().stacksTo(1)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.ototarot.terrain_claimer.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> TERRAIN_INSPECTOR = ITEMS.register("terrain_inspector",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.ototarot.terrain_inspector.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> SE_PREPARO_MUSIC_DISC = ITEMS.register("se_preparo_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SE_PREPARO_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> PPPP_MUSIC_DISC = ITEMS.register("pppp_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.PPPP_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> LEMON_MELON_COOKIE_MUSIC_DISC = ITEMS.register("lemon_melon_cookie_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.LEMON_MELON_COOKIE_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> BATA_BOOM_MUSIC_DISC = ITEMS.register("bata_boom_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BATA_BOOM_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> CUATRO_K_MUSIC_DISC = ITEMS.register("4k_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CUATRO_K_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> LA_VIDA_ES_UN_CARRUSEL_MUSIC_DISC = ITEMS.register("la_vida_es_un_carrusel_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.LA_VIDA_ES_UN_CARRUSEL_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> LA_GOZADERA_MUSIC_DISC = ITEMS.register("la_gozadera_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.LA_GOZADERA_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> CHUPA_LA_GAMBA_MUSIC_DISC = ITEMS.register("chupa_la_gamba_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CHUPA_LA_GAMBA_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> CAN_YOU_SEND_ME_30K_MUSIC_DISC = ITEMS.register("can_you_send_me_30k_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CAN_YOU_SEND_ME_30K_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> CLAIM_GENERATOR = ITEMS.register("claim_generator",
            () -> new ClaimGeneratorItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}