package com.mtd.ototarot;

import com.mtd.ototarot.teams.TeamSelectionPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.neoforge.network.PacketDistributor;

public class TeamSelectionScreen extends Screen {
    public TeamSelectionScreen() {
        super(Component.literal("Selecciona tu Equipo"));
    }

    @Override
    protected void init() {
        int buttonWidth = 80;  // Un poco más estrechos para que quepan 4 columnas
        int buttonHeight = 20;
        int spacing = 4;

        // Calculamos el inicio para que el bloque de botones esté centrado
        int startX = this.width / 2 - (buttonWidth * 2 + spacing * 2);
        int startY = 40;

        int i = 0;
        for (OtOtArot.TeamColor color : OtOtArot.TeamColor.values()) {
            // Lógica para 4 columnas
            int column = i % 4;
            int row = i / 4;

            int x = startX + (column * (buttonWidth + spacing));
            int y = startY + (row * (buttonHeight + spacing));

            String status = getTeamStatus(color);

            this.addRenderableWidget(Button.builder(
                            Component.literal(color.name).withStyle(color.format).append(" " + status),
                            button -> handleSelection(color))
                    .bounds(x, y, buttonWidth, buttonHeight)
                    .build());
            i++;
        }
    }

    private String getTeamStatus(OtOtArot.TeamColor color) {
        Scoreboard sb = Minecraft.getInstance().level.getScoreboard();
        int count = 0;
        // Revisamos Color1 y Color2
        if (sb.getPlayerTeam(color.name + "1") != null) count += sb.getPlayerTeam(color.name + "1").getPlayers().size();
        if (sb.getPlayerTeam(color.name + "2") != null) count += sb.getPlayerTeam(color.name + "2").getPlayers().size();

        return "[" + count + "/2]";
    }

    private void handleSelection(OtOtArot.TeamColor color) {
        // Enviamos un paquete al servidor para procesar la entrada
        // No podemos meter al jugador al equipo desde el cliente por seguridad
        PacketDistributor.sendToServer(new TeamSelectionPayload(color.name));
        super.onClose();
    }
    @Override
    public boolean shouldCloseOnEsc() {
        // Solo permitimos ESC si ya tiene equipo.
        // Si no tiene, está obligado a elegir uno de la lista.
        Scoreboard sb = Minecraft.getInstance().level.getScoreboard();
        return sb.getPlayersTeam(Minecraft.getInstance().player.getScoreboardName()) != null;
    }

    @Override
    public void onClose() {
        // Si intentan cerrarla de otra forma pero no tienen equipo, la reabrimos
        if (!shouldCloseOnEsc()) {
            return;
        }
        super.onClose();
    }
}
