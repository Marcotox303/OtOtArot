package com.mtd.ototarot;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.logging.LogUtils;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.client.AtmScreen;
import com.mtd.ototarot.client.ModKeybinds;
import com.mtd.ototarot.dims.DimLogicHandler;
import com.mtd.ototarot.economy.*;
import com.mtd.ototarot.item.ModCreativeModeTabs;
import com.mtd.ototarot.item.ModItems;
import com.mtd.ototarot.sound.ModSounds;
import com.mtd.ototarot.teams.TeamSelectionPayload;
import com.mtd.ototarot.world.inventory.ModMenuTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
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
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
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

import java.util.Collection;

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
        ModSounds.register(modEventBus);
        ModAttachments.ATTACHMENTS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);

        // Registrar Teclas
        modEventBus.addListener(ModKeybinds::registerKeys);

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
            c1.enqueueWork(() -> {
                processTeamJoin((ServerPlayer) c1.player(), p1.colorName());
            });
        });

        // Hacia el CLIENTE
        registrar.playToClient(OpenTeamGuiPayload.TYPE, OpenTeamGuiPayload.CODEC, (p2, c2) -> {
            // Llamamos a una clase externa para no crashear el servidor
            c2.enqueueWork(DistHelper::openTeamScreen);
        });

        registrar.playToClient(OpenWalletGuiPayload.TYPE, OpenWalletGuiPayload.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        new com.mtd.ototarot.client.WalletScreen(payload.playerG(), payload.leaderboardData())
                );
            });
        });

        registrar.playToServer(
                WithdrawAtmPayload.TYPE,
                WithdrawAtmPayload.STREAM_CODEC,
                (payload,context) -> {
                    context.enqueueWork(() -> {
                        ServerPlayer player = (ServerPlayer) context.player();

                        if (player.containerMenu instanceof com.mtd.ototarot.world.inventory.AtmMenu atmMenu) {
                            int option = payload.optionIndex();
                            boolean isCasino = atmMenu.isCasino();
                            double cost = 0;
                            net.minecraft.world.item.Item itemToGive = null;

                            // Determinamos qué cuesta y qué entrega cada botón
                            if (isCasino) {
                                if (option == 0) { cost = 1; itemToGive = ModItems.BLUE_CASINO_CHIP.get(); }
                                else if (option == 1) { cost = 10; itemToGive = ModItems.RED_CASINO_CHIP.get(); }
                                else if (option == 2) { cost = 100; itemToGive = ModItems.GREEN_CASINO_CHIP.get(); }
                                else if (option == 3) { cost = 1000; itemToGive = ModItems.BLACK_CASINO_CHIP.get(); }
                            } else {
                                if (option == 0) { cost = 1; itemToGive = ModItems.IRON_COIN.get(); }
                                else if (option == 1) { cost = 4; itemToGive = ModItems.GOLDEN_COIN.get(); }
                                else if (option == 2) { cost = 16; itemToGive = ModItems.DIAMOND_COIN.get(); }
                                else if (option == 3) { cost = 64; itemToGive = ModItems.NETHERITE_COIN.get(); }
                            }

                            PlayerWallet wallet = player.getData(ModAttachments.WALLET.get());

                            // Verificamos por seguridad en el servidor que tenga saldo
                            if (wallet.getBalance() >= cost && itemToGive != null) {
                                wallet.withdraw(cost);

                                // Entregamos el item
                                net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(itemToGive);
                                if (!player.getInventory().add(stack)) {
                                    player.drop(stack, false); // Si el inventario está lleno, lo tira al suelo
                                }
                                PacketDistributor.sendToPlayer(player, new com.mtd.ototarot.economy.SyncWalletBalancePayload(wallet.getBalance()));
                            }
                        }
                    });
                }
        );

        registrar.playToServer(
                RequestWalletDataPayload.TYPE,
                RequestWalletDataPayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        ServerPlayer player = (ServerPlayer) context.player();

                        int playerG = (int) player.getData(ModAttachments.WALLET.get()).getBalance();

                        Scoreboard scoreboard = player.getServer().getScoreboard();
                        java.util.List<ServerPlayer> allPlayers = player.getServer().getPlayerList().getPlayers();

                        java.util.List<PlayerScoreData> leaderboardList = new java.util.ArrayList<>();

                        for (ServerPlayer p : allPlayers) {
                            int g = (int) p.getData(ModAttachments.WALLET.get()).getBalance();

                            int lives = 0;
                            net.minecraft.world.scores.Objective livesObjective = scoreboard.getObjective("lives_remaining");
                            if (livesObjective != null) {
                                net.minecraft.world.scores.ReadOnlyScoreInfo scoreInfo = scoreboard.getPlayerScoreInfo(p, livesObjective);
                                if (scoreInfo != null) {
                                    lives = scoreInfo.value();
                                }
                            }

                            ChatFormatting teamColor = ChatFormatting.WHITE;
                            PlayerTeam team = scoreboard.getPlayersTeam(p.getScoreboardName());

                            if (team != null) {
                                for (OtOtArot.TeamColor colorEnum : OtOtArot.TeamColor.values()) {
                                    if (team.getName().startsWith(colorEnum.name)) {
                                        teamColor = colorEnum.format;
                                        break;
                                    }
                                }
                            }
                            leaderboardList.add(new PlayerScoreData(p.getName().getString(), g, lives, teamColor));
                        }

                        leaderboardList.sort((a,b) -> {
                            if (b.g != a.g) return Integer.compare(b.g, a.g);
                            return Integer.compare(b.lives, a.lives);
                        });

                        StringBuilder sbLeaderboard = new StringBuilder();
                        for (PlayerScoreData data : leaderboardList) {
                            sbLeaderboard.append(data.teamColor.name())
                                    .append("|")
                                    .append(data.name)
                                    .append("|")
                                    .append(data.g)
                                    .append("|")
                                    .append(data.lives)
                                    .append("\n");
                        }

                        PacketDistributor.sendToPlayer(player, new OpenWalletGuiPayload(playerG, sbLeaderboard.toString()));
                    });
                }
        );
        registrar.playToClient(
                SyncWalletBalancePayload.TYPE,
                SyncWalletBalancePayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        net.minecraft.world.entity.player.Player player = context.player();
                        if (player != null) {
                            com.mtd.ototarot.economy.PlayerWallet wallet = player.getData(ModAttachments.WALLET.get());
                            // Vaciamos el monedero local del cliente y le ponemos el saldo real que manda el servidor
                            wallet.withdraw(wallet.getBalance());
                            wallet.deposit(payload.balance());
                        }
                    });
                }
        );
    }

    private static class PlayerScoreData {
        String name;
        int g;
        int lives;
        ChatFormatting teamColor;

        public PlayerScoreData(String name, int g, int lives, ChatFormatting teamColor) {
            this.name = name;
            this.g = g;
            this.lives = lives;
            this.teamColor = teamColor;
        }
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
            // Aumentamos el intervalo a 5 segundos (100 ticks) para dar margen
            if (player.tickCount % 100 == 0) {
                Scoreboard sb = player.getScoreboard();
                // Solo abrimos si realmente no tiene equipo
                if (sb.getPlayersTeam(player.getScoreboardName()) == null) {
                    PacketDistributor.sendToPlayer(player, new OpenTeamGuiPayload());
                }
            }
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(net.minecraft.commands.Commands.literal("lobby_inicio")
                .executes(c -> { performTeleport(c.getSource().getPlayerOrException()); return 1; }));

        event.getDispatcher().register(
                Commands.literal("gabrielas")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("add")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0))
                                                .executes(context -> {
                                                    Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "targets");
                                                    double amount = DoubleArgumentType.getDouble(context, "amount");

                                                    for (ServerPlayer target : targets) {
                                                        PlayerWallet wallet = target.getData(ModAttachments.WALLET.get());
                                                        wallet.deposit(amount);
                                                        double newBalance = wallet.getBalance();

                                                        context.getSource().sendSuccess(() ->
                                                                Component.translatable("command.ototarot.gabrielas.add", amount, target.getName(), newBalance), true);
                                                    }
                                                    return targets.size();
                                                })
                                        )
                                )
                        )

                        .then(Commands.literal("set")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0))
                                                .executes(context -> {
                                                    Collection<ServerPlayer> targets = EntityArgument.getPlayers(context,"targets");
                                                    double amount = DoubleArgumentType.getDouble(context,"amount");

                                                    for (ServerPlayer target : targets) {
                                                        PlayerWallet wallet = target.getData(ModAttachments.WALLET.get());
                                                        double oldBalance = wallet.getBalance();

                                                        wallet.withdraw(oldBalance);
                                                        wallet.deposit(amount);

                                                        context.getSource().sendSuccess(() ->
                                                                Component.translatable("command.ototarot.gabrielas.set",target.getName(), amount, oldBalance),true);
                                                    }
                                                    return targets.size();
                                                })
                                        )
                                )
                        )

                        .then(Commands.literal("remove")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0))
                                                .executes(context -> {
                                                    Collection<ServerPlayer> targets = EntityArgument.getPlayers(context,"targets");
                                                    double amount = DoubleArgumentType.getDouble(context,"amount");

                                                    for (ServerPlayer target : targets) {
                                                        PlayerWallet wallet = target.getData(ModAttachments.WALLET.get());
                                                        wallet.withdraw(amount);
                                                        double newBalance = wallet.getBalance();


                                                        context.getSource().sendSuccess(() ->
                                                                Component.translatable("command.ototarot.gabrielas.remove",amount, target.getName(), newBalance),true);
                                                    }
                                                    return targets.size();
                                                })
                                        )
                                )
                        )

                        .then(Commands.literal("get")
                                .then(Commands.argument("targets", EntityArgument.players())
                                                .executes(context -> {
                                                    Collection<ServerPlayer> targets = EntityArgument.getPlayers(context,"targets");

                                                    for (ServerPlayer target : targets) {
                                                        double balance = target.getData(ModAttachments.WALLET.get()).getBalance();

                                                        context.getSource().sendSuccess(() ->
                                                                Component.translatable("command.ototarot.gabrielas.get",target.getName(), balance),true);
                                                    }
                                                    return targets.size();
                                                })
                                        )
                                )


                        .then(Commands.literal("empty")
                                .then(Commands.argument("targets", EntityArgument.players())
                                                .executes(context -> {
                                                    Collection<ServerPlayer> targets = EntityArgument.getPlayers(context,"targets");

                                                    for (ServerPlayer target : targets) {
                                                        PlayerWallet wallet = target.getData(ModAttachments.WALLET.get());
                                                        double previousBalance = wallet.getBalance();

                                                        wallet.withdraw(previousBalance);

                                                        context.getSource().sendSuccess(() ->
                                                                Component.translatable("command.ototarot.gabrielas.empty",target.getName(), previousBalance),true);
                                                    }
                                                    return targets.size();
                                                })
                                        )
                                )

        );
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
            ServerLevel originDim = player.server.getLevel(lastPos.dimension());
            if (originDim != null) player.teleportTo(originDim, lastPos.pos().getX(), lastPos.pos().getY(), lastPos.pos().getZ(), player.getYRot(), player.getXRot());
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