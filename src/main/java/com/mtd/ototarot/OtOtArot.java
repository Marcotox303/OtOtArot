package com.mtd.ototarot;

import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.dims.DimLogicHandler;
import com.mtd.ototarot.item.ModCreativeModeTabs;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(OtOtArot.MOD_ID)
public class OtOtArot {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "ototarot";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public OtOtArot(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ATTACHMENT_TYPES.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onRegisterCommands(net.neoforged.neoforge.event.RegisterCommandsEvent event) {
        event.getDispatcher().register(
                net.minecraft.commands.Commands.literal("arotlobby")
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            performTeleport(player); // Llama a tu metodo de teletransporte
                            return 1;
                        })
        );
    }

    // 1. Registro del sistema de "Attachments"
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "ototarot");

    // 2. Definición del Attachment para guardar la posición (GlobalPos)
// Usamos .builder en lugar de .serializable y le pasamos el CODEC de GlobalPos
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<GlobalPos>> LAST_POS =
            ATTACHMENT_TYPES.register("last_pos", () -> AttachmentType.builder(() -> (GlobalPos)null)
                    .serialize(GlobalPos.CODEC) // Esto permite que se guarde en el disco
                    .copyOnDeath()              // Esto hace que la posición no se borre si mueres
                    .build());

    // Pon esto junto a tus otros registros (cambia "tu_dimension" por el nombre de tu archivo JSON)
    public static final ResourceKey<Level> miDimKey = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath("ototarot", "the_lobby")
    );


    public void performTeleport(ServerPlayer player) {
        // 1. Verificar Cooldown (Asegúrate de que la clase DimLogicHandler existe)
        if (DimLogicHandler.isInCooldown(player)) {
            player.sendSystemMessage(Component.literal("§cAún no puedes viajar..."));
            return;
        }

        ServerLevel destDim = player.server.getLevel(miDimKey);
        if (destDim == null) return; // Seguridad por si la dimensión no carga

        if (player.level().dimension() == miDimKey) {
            // --- VOLVER AL OVERWORLD ---
            GlobalPos lastPos = player.getData(LAST_POS);
            if (lastPos != null) {
                ServerLevel originDim = player.server.getLevel(lastPos.dimension());
                if (originDim != null) {
                    // Usamos teleportTo que es más estable en 1.21
                    player.teleportTo(originDim,
                            lastPos.pos().getX() + 0.5,
                            lastPos.pos().getY(),
                            lastPos.pos().getZ() + 0.5,
                            player.getYRot(), player.getXRot());
                }
            }
        } else {
            // --- IR A LA DIMENSIÓN ---
            // Guardamos la posición actual antes de irnos
            player.setData(LAST_POS, GlobalPos.of(player.level().dimension(), player.blockPosition()));

            // Teletransportar al centro (0, 64, 0)
            player.teleportTo(destDim, 0.5, 64.0, 0.5, 0, 0);
        }
    }

}
