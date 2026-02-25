package com.mtd.ototarot;

import com.mojang.logging.LogUtils;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.dims.DimLogicHandler;
import com.mtd.ototarot.item.ModCreativeModeTabs;
import com.mtd.ototarot.item.ModItems;
import com.mtd.ototarot.teams.TeamSelectionPayload;
import net.minecraft.ChatFormatting;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

@Mod(OtOtArot.MOD_ID)
public class OtOtArot {
    public static final String MOD_ID = "ototarot";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OtOtArot(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Registros de carga (Bus del MOD)
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerNetworking); // Registro de red correcto

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);

        // 2. Registros de juego (Bus de FORGE)
        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {}

    // REGISTRO DE RED (Sin @SubscribeEvent porque usamos addListener)
    public void registerNetworking(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        // Hacia el SERVIDOR
        registrar.playToServer(TeamSelectionPayload.TYPE, TeamSelectionPayload.CODEC, (p1, c1) -> {
            c1.enqueueWork(() -> processTeamJoin((ServerPlayer) c1.player(), p1.colorName()));
        });

        // Hacia el CLIENTE
        registrar.playToClient(OpenTeamGuiPayload.TYPE, OpenTeamGuiPayload.CODEC, (p2, c2) -> {
            c2.enqueueWork(() -> {
                // Llamamos a una clase externa para no crashear el servidor
                DistHelper.openTeamScreen();
            });
        });
    }

    // Lógica de equipos
    public static void processTeamJoin(ServerPlayer player, String colorName) {
        Scoreboard sb = player.getScoreboard();
        String t1Name = colorName + "1";
        String t2Name = colorName + "2";

        PlayerTeam team1 = sb.getPlayerTeam(t1Name);
        PlayerTeam team2 = sb.getPlayerTeam(t2Name);

        if (team1 == null) team1 = sb.addPlayerTeam(t1Name);
        if (team2 == null) team2 = sb.addPlayerTeam(t2Name);

        if (team1.getPlayers().isEmpty()) {
            sb.addPlayerToTeam(player.getScoreboardName(), team1);
            player.sendSystemMessage(Component.literal("Unido a " + t1Name));
        } else if (team2.getPlayers().isEmpty()) {
            if (team1.getPlayers().contains(player.getScoreboardName())) return;
            sb.addPlayerToTeam(player.getScoreboardName(), team2);
            player.sendSystemMessage(Component.literal("Unido a " + t2Name));
        } else {
            player.sendSystemMessage(Component.literal("¡Equipo lleno!").withStyle(ChatFormatting.RED));
        }
    }

    // EVENTOS DE JUEGO (Con @SubscribeEvent porque están en NeoForge.EVENT_BUS)
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.tickCount % 60 == 0) { // Cada 3 segundos
                if (player.getScoreboard().getPlayersTeam(player.getScoreboardName()) == null) {
                    PacketDistributor.sendToPlayer(player, new OpenTeamGuiPayload());
                }
            }
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(net.minecraft.commands.Commands.literal("arotlobby")
                .executes(c -> { performTeleport(c.getSource().getPlayerOrException()); return 1; }));
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer().getScoreboard().getPlayersTeam(event.getPlayer().getScoreboardName()) == null) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntity().getScoreboard().getPlayersTeam(event.getEntity().getScoreboardName()) == null) {
            event.setCanceled(true);
        }
    }

    // --- TELEPORT LOGIC ---
    public static final ResourceKey<Level> miDimKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("ototarot", "the_lobby"));
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MOD_ID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<GlobalPos>> LAST_POS = ATTACHMENT_TYPES.register("last_pos", () -> AttachmentType.builder(() -> (GlobalPos)null).serialize(GlobalPos.CODEC).copyOnDeath().build());

    public void performTeleport(ServerPlayer player) {
        if (DimLogicHandler.isInCooldown(player)) return;
        ServerLevel destDim = player.server.getLevel(miDimKey);
        if (destDim == null) return;
        if (player.level().dimension() == miDimKey) {
            GlobalPos lastPos = player.getData(LAST_POS);
            if (lastPos != null) {
                ServerLevel originDim = player.server.getLevel(lastPos.dimension());
                if (originDim != null) player.teleportTo(originDim, lastPos.pos().getX(), lastPos.pos().getY(), lastPos.pos().getZ(), player.getYRot(), player.getXRot());
            }
        } else {
            player.setData(LAST_POS, GlobalPos.of(player.level().dimension(), player.blockPosition()));
            player.teleportTo(destDim, 0.5, 64.0, 0.5, 0, 0);
        }
    }

    public enum TeamColor {
        WHITE("White", ChatFormatting.WHITE),
        GOLD("Gold", ChatFormatting.GOLD),
        LIGHT_PURPLE("Light_Purple", ChatFormatting.LIGHT_PURPLE),
        AQUA("Aqua", ChatFormatting.AQUA),
        YELLOW("Yellow", ChatFormatting.YELLOW),
        GREEN("Green", ChatFormatting.GREEN),
        DARK_BLUE("Dark_Blue", ChatFormatting.DARK_BLUE),
        GRAY("Gray", ChatFormatting.GRAY),
        DARK_GRAY("Dark_Gray", ChatFormatting.DARK_GRAY),
        DARK_AQUA("Dark_Aqua", ChatFormatting.DARK_AQUA),
        DARK_PURPLE("Dark_Purple", ChatFormatting.DARK_PURPLE),
        BLUE("Blue", ChatFormatting.BLUE),
        DARK_RED("Dark_Red", ChatFormatting.DARK_RED),
        DARK_GREEN("Dark_Green", ChatFormatting.DARK_GREEN),
        RED("Red", ChatFormatting.RED),
        BLACK("Black", ChatFormatting.BLACK);

        public final String name;
        public final ChatFormatting format;

        TeamColor(String name, ChatFormatting format) {
            this.name = name;
            this.format = format;
        }
    }
}