package com.mtd.ototarot.client;

import com.mtd.ototarot.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class WalletScreen extends Screen {
    private final int playerG;
    private final String leaderboardData;

    public WalletScreen(int playerG, String leaderboardData) {
        super(Component.translatable("screen.ototarot.wallet.title_wallet"));
        this.playerG = playerG;
        this.leaderboardData = leaderboardData;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 0xFFFFFF);

        guiGraphics.drawString(this.font, Component.translatable("screen.ototarot.wallet.you_have").append(Component.literal(String.valueOf(playerG))).append(Component.translatable("screen.ototarot.wallet.g")), 30, 40, 0x55FF55, true);

        guiGraphics.drawString(this.font, Component.translatable("screen.ototarot.wallet.value_of_coins_and_chips"), 30, 70, 0xFFAAAA, true);

        int startX = 30;
        int startY = 90;

        net.minecraft.world.item.ItemStack[] items = {
                new net.minecraft.world.item.ItemStack(ModItems.IRON_COIN.get()),
                new net.minecraft.world.item.ItemStack(ModItems.GOLDEN_COIN.get()),
                new net.minecraft.world.item.ItemStack(ModItems.DIAMOND_COIN.get()),
                new net.minecraft.world.item.ItemStack(ModItems.NETHERITE_COIN.get()),
                new net.minecraft.world.item.ItemStack(ModItems.BLUE_CASINO_CHIP.get()),
                new net.minecraft.world.item.ItemStack(ModItems.RED_CASINO_CHIP.get()),
                new net.minecraft.world.item.ItemStack(ModItems.GREEN_CASINO_CHIP.get()),
                new net.minecraft.world.item.ItemStack(ModItems.BLACK_CASINO_CHIP.get()),
        };

        String[] values = {
                "1§6G§r", "4§6G§r", "16§6G§r", "64§6G§r",
                "1§6G§r", "10§6G§r", "100§6G§r", "1.000§6G§r"
        };

        for (int i = 0; i < items.length; i++) {
            guiGraphics.renderItem(items[i], startX, startY + (i*18));
            guiGraphics.drawString(this.font,": "+values[i],startX + 18, startY + 4 + (i*18),0xFFFFFF,true);
        }

        int tableX = this.width - 210;
        int startY2 = 40;

        guiGraphics.drawString(this.font, Component.translatable("screen.ototarot.wallet.column.player"), tableX, startY2, 0xFFFF55, true);
        guiGraphics.drawString(this.font, Component.translatable("screen.ototarot.wallet.column.money"), tableX + 90, startY2, 0xFFFF55, true);
        guiGraphics.drawString(this.font, Component.translatable("screen.ototarot.wallet.column.lives"), tableX + 150, startY2, 0xFFFF55, true);

        guiGraphics.hLine(tableX, tableX + 185, startY2 + 11, 0xFFAAAAAA);
        startY2 += 16;

        if (this.leaderboardData != null && !this.leaderboardData.isEmpty()) {
            String[] lines = this.leaderboardData.split("\n");

            for (String line : lines) {
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;

                String colorName = parts[0];
                String playerName = parts[1];
                String gAmount = parts[2];
                String livesCount = parts[3];

                int rowY = startY2;

                // Renderizar la cara del jugador en la columna de Jugador
                net.minecraft.client.multiplayer.ClientPacketListener connection = Minecraft.getInstance().getConnection();
                if (connection != null) {
                    net.minecraft.client.multiplayer.PlayerInfo playerInfo = connection.getPlayerInfo(playerName);
                    if (playerInfo != null) {
                        ResourceLocation skinTexture = playerInfo.getSkin().texture();
                        guiGraphics.blit(skinTexture, tableX, rowY, 8, 8, 8.0F, 8.0F, 8, 8, 64, 64);
                        guiGraphics.blit(skinTexture, tableX, rowY, 8, 8, 40.0F, 8.0F, 8, 8, 64, 64);
                    }
                }

                // Nombre del jugador (desplazado 10 píxeles a la derecha de la cara) con el color de su equipo
                net.minecraft.ChatFormatting formatting = net.minecraft.ChatFormatting.getByName(colorName);
                if (formatting == null) formatting = net.minecraft.ChatFormatting.WHITE;

                Component nameComponent = Component.literal(playerName).withStyle(formatting);
                guiGraphics.drawString(this.font, nameComponent, tableX + 12, rowY + 1, 0xFFFFFF, true);

                // Columna de Dinero (G)
                Component moneyComponent = Component.literal(gAmount + "§6G").withStyle(net.minecraft.ChatFormatting.WHITE);
                guiGraphics.drawString(this.font, moneyComponent, tableX + 90, rowY + 1, 0xFFFFFF, true);

                // Columna de Vidas
                Component livesComponent = Component.literal(livesCount).withStyle(net.minecraft.ChatFormatting.RED);
                guiGraphics.drawString(this.font, livesComponent, tableX + 150, rowY + 1, 0xFFFFFF, true);

                startY2 += 14;
            }
        }

        int qX = this.width - 40;
        int qY = 15;
        guiGraphics.drawString(this.font, "?", qX, qY, 0xFFFFFF, true);
        if (mouseX >= qX && mouseX <= qX + 10 && mouseY >= qY && mouseY <= qY + 10) {
            guiGraphics.renderComponentTooltip(this.font, java.util.List.of(
                    Component.translatable("screen.ototarot.wallet.info_tooltip_1"),
                    Component.translatable("screen.ototarot.wallet.info_tooltip_2"),
                    Component.translatable("screen.ototarot.wallet.info_tooltip_3"),
                    Component.translatable("screen.ototarot.wallet.info_tooltip_4"),
                    Component.translatable("screen.ototarot.wallet.info_tooltip_5")
            ), mouseX, mouseY);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
