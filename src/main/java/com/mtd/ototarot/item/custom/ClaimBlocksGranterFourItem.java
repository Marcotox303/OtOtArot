package com.mtd.ototarot.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;

import java.util.List;

public class ClaimBlocksGranterFourItem extends Item {

    public ClaimBlocksGranterFourItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // La lógica del comando y mensajes solo debe ejecutarse en el servidor
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            MinecraftServer server = serverLevel.getServer();
            PlayerTeam team = player.getTeam();

            if (team != null) {
                String teamName = team.getName();

                if (teamName.equals("Red1") || teamName.equals("Red2")) {
                    giveTheClaimBlocksPls(server, "ototarot.red_team", List.of("Red1", "Red2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Green1") || teamName.equals("Green2")) {
                    giveTheClaimBlocksPls(server, "ototarot.green_team", List.of("Green1", "Green2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Aqua1") || teamName.equals("Aqua2")) {
                    giveTheClaimBlocksPls(server, "ototarot.aqua_team", List.of("Aqua1", "Aqua2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Black1") || teamName.equals("Black2")) {
                    giveTheClaimBlocksPls(server, "ototarot.black_team", List.of("Black1", "Black2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Blue1") || teamName.equals("Blue2")) {
                    giveTheClaimBlocksPls(server, "ototarot.blue_team", List.of("Blue1", "Blue2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Dark_Aqua1") || teamName.equals("Dark_Aqua2")) {
                    giveTheClaimBlocksPls(server, "ototarot.dark_aqua_team", List.of("Dark_Aqua1", "Dark_Aqua2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Dark_Blue1") || teamName.equals("Dark_Blue2")) {
                    giveTheClaimBlocksPls(server, "ototarot.dark_blue_team", List.of("Dark_Blue1", "Dark_Blue2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Dark_Gray1") || teamName.equals("Dark_Gray2")) {
                    giveTheClaimBlocksPls(server, "ototarot.dark_gray_team", List.of("Dark_Gray1", "Dark_Gray2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Dark_Green1") || teamName.equals("Dark_Green2")) {
                    giveTheClaimBlocksPls(server, "ototarot.dark_green_team", List.of("Dark_Green1", "Dark_Green2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Dark_Purple1") || teamName.equals("Dark_Purple2")) {
                    giveTheClaimBlocksPls(server, "ototarot.dark_purple_team", List.of("Dark_Purple1", "Dark_Purple2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Dark_Red1") || teamName.equals("Dark_Red2")) {
                    giveTheClaimBlocksPls(server, "ototarot.dark_red_team", List.of("Dark_Red1", "Dark_Red2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Gold1") || teamName.equals("Gold2")) {
                    giveTheClaimBlocksPls(server, "ototarot.gold_team", List.of("Gold1", "Gold2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Gray1") || teamName.equals("Gray2")) {
                    giveTheClaimBlocksPls(server, "ototarot.gray_team", List.of("Gray1", "Gray2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Light_Purple1") || teamName.equals("Light_Purple2")) {
                    giveTheClaimBlocksPls(server, "ototarot.light_purple_team", List.of("Light_Purple1", "Light_Purple2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("White1") || teamName.equals("White2")) {
                    giveTheClaimBlocksPls(server, "ototarot.white_team", List.of("White1", "White2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);

                } else if (teamName.equals("Yellow1") || teamName.equals("Yellow2")) {
                    giveTheClaimBlocksPls(server, "ototarot.yellow_team", List.of("Yellow1", "Yellow2"));
                    itemStack.shrink(1);
                    return InteractionResultHolder.success(itemStack);
                }
            }
        }

        // Si no está en un equipo válido, no hace nada (puedes cambiarlo a fail si lo prefieres)
        return InteractionResultHolder.pass(itemStack);
    }

    private void giveTheClaimBlocksPls(MinecraftServer server, String flanTeam, List<String> targetTeams) {
        // Ejecuta el comando como si fuera la consola del servidor
        String command = "flan giveClaimBlocks " + flanTeam + " 729";
        server.getCommands().performPrefixedCommand(server.createCommandSourceStack(), command);

        // Envía el mensaje a todos los miembros de los equipos implicados
        Component message = Component.translatable("item.ototarot.claim_blocks_granter_four.message");
        for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
            PlayerTeam playerTeam = serverPlayer.getTeam();
            if (playerTeam != null && targetTeams.contains(playerTeam.getName())) {
                serverPlayer.sendSystemMessage(message);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.ototarot.claim_blocks_granter_four.desc"));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}