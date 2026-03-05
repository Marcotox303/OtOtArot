package com.mtd.ototarot.sound;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, OtOtArot.MOD_ID);


    public static final Supplier<SoundEvent> CHUPA_LA_GAMBA = registerSoundEvent("chupa_la_gamba");
    public static final Supplier<SoundEvent> BATA_BOOM = registerSoundEvent("bata_boom");
    public static final Supplier<SoundEvent> LEMON_MELON_COOKIE = registerSoundEvent("lemon_melon_cookie");
    public static final Supplier<SoundEvent> PPPP = registerSoundEvent("pppp");
    public static final Supplier<SoundEvent> CAN_YOU_SEND_ME_30K = registerSoundEvent("can_you_send_me_30k");
    public static final Supplier<SoundEvent> CUATRO_K = registerSoundEvent("4k");
    public static final Supplier<SoundEvent> LA_GOZADERA = registerSoundEvent("la_gozadera");
    public static final Supplier<SoundEvent> LA_VIDA_ES_UN_CARRUSEL = registerSoundEvent("la_vida_es_un_carrusel");
    public static final Supplier<SoundEvent> SE_PREPARO = registerSoundEvent("se_preparo");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, name));
    }

    public static final ResourceKey<JukeboxSong> CHUPA_LA_GAMBA_KEY = createSong("chupa_la_gamba");
    public static final ResourceKey<JukeboxSong> BATA_BOOM_KEY = createSong("bata_boom");
    public static final ResourceKey<JukeboxSong> LEMON_MELON_COOKIE_KEY = createSong("lemon_melon_cookie");
    public static final ResourceKey<JukeboxSong> PPPP_KEY = createSong("pppp");
    public static final ResourceKey<JukeboxSong> CAN_YOU_SEND_ME_30K_KEY = createSong("can_you_send_me_30k");
    public static final ResourceKey<JukeboxSong> CUATRO_K_KEY = createSong("4k");
    public static final ResourceKey<JukeboxSong> LA_GOZADERA_KEY = createSong("la_gozadera");
    public static final ResourceKey<JukeboxSong> LA_VIDA_ES_UN_CARRUSEL_KEY = createSong("la_vida_es_un_carrusel");
    public static final ResourceKey<JukeboxSong> SE_PREPARO_KEY = createSong("se_preparo");


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
