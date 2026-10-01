package com.mtd.ototarot.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.ModAttachments;
import com.mtd.ototarot.economy.WithdrawAtmPayload;
import com.mtd.ototarot.world.inventory.AtmMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class AtmScreen extends AbstractContainerScreen<AtmMenu> implements net.minecraft.client.gui.screens.inventory.MenuAccess<AtmMenu> {

    // Usamos el fondo del dispensador de Minecraft por defecto para evitar el error rosa/negro
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/dispenser.png");

    private int errorMessageTicks = 0;
    private Component errorMessage = Component.empty();

    public AtmScreen(AtmMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        String[] options = menu.isCasino()
                ? new String[]{"1G", "10G", "100G", "1,000G"}
                : new String[]{"1G", "4G", "16G", "64G"};

        // Botones reubicados para que quepan dentro del nuevo tamaño del monitor
        // Ligeros retoques en la Y (34 y 54) para encajar en el nuevo rectángulo
        int[][] buttonPositions = {
                {this.leftPos + 48, this.topPos + 34},  // Arriba Izquierda
                {this.leftPos + 106, this.topPos + 34}, // Arriba Derecha
                {this.leftPos + 48, this.topPos + 54},  // Abajo Izquierda
                {this.leftPos + 106, this.topPos + 54}  // Abajo Derecha
        };

        for (int i = 0; i < 4; i++) {
            final int index = i;
            this.addRenderableWidget(Button.builder(Component.literal(options[i]), button -> {
                        double currentBalance = this.minecraft.player.getData(ModAttachments.WALLET.get()).getBalance();
                        double cost = 0;

                        if (menu.isCasino()) {
                            if (index == 0) cost = 1; else if (index == 1) cost = 10; else if (index == 2) cost = 100; else if (index == 3) cost = 1000;
                        } else {
                            if (index == 0) cost = 1; else if (index == 1) cost = 4; else if (index == 2) cost = 16; else if (index == 3) cost = 64;
                        }

                        if (currentBalance >= cost) {
                            PacketDistributor.sendToServer(new WithdrawAtmPayload(index));
                        } else {
                            triggerNotEnoughMoneyError();
                        }
                    })
                    .bounds(buttonPositions[i][0], buttonPositions[i][1], 54, 16)
                    .build());
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = this.leftPos;
        int y = this.topPos;

        // 1. Dibuja la textura base del inventario
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // 2. DIBUJA EL HUECO CLÁSICO DE MINECRAFT DE FONDO PARA EL SLOT (x=19, y=35)
        guiGraphics.blit(TEXTURE, x + 19, y + 35, 7, 83, 18, 18);

        // 3. Monitor bajado a la línea y=23 para que no toque el título de arriba
        // 3. Monitor subido a Y=15 para tapar los slots ocultos y altura ampliada
        int monitorX = x + 44;
        int monitorY = y + 15;
        int monitorWidth = 120;
        int monitorHeight = 60;

        int innerColor = menu.isCasino() ? 0xFF401010 : 0xFFE0E0E0;
        int borderColor = menu.isCasino() ? 0xFF000000 : 0xFF707070;

        guiGraphics.fill(monitorX - 1, monitorY - 1, monitorX + monitorWidth + 1, monitorY + monitorHeight + 1, borderColor);
        guiGraphics.fill(monitorX, monitorY, monitorX + monitorWidth, monitorY + monitorHeight, innerColor);

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        Component titleComponent = this.menu.isCasino()
                ? Component.translatable("container.ototarot.casino_atm")
                : Component.translatable("container.ototarot.atm");

        int titleWidth = this.font.width(titleComponent);
        // Título subido a Y=5 para que respire
        guiGraphics.drawString(this.font, titleComponent, (this.imageWidth - titleWidth) / 2, 5, 0x404040, false);

        double balance = this.minecraft.player.getData(ModAttachments.WALLET.get()).getBalance();
        Component balanceText = Component.translatable("screen.ototarot.atm.balance").append(String.format("%.1f G", balance));
        int balanceWidth = this.font.width(balanceText);

        // Balance subido a Y=20 para acomodarse a la nueva caja
        guiGraphics.drawString(this.font, balanceText, 44 + (120 - balanceWidth) / 2, 20, menu.isCasino() ? 0xFFAAAAAA : 0xFF202020, false);

        if (errorMessageTicks > 0) {
            int errorWidth = this.font.width(errorMessage);
            guiGraphics.drawString(this.font, errorMessage, (this.imageWidth - errorWidth) / 2, 76, 0xFFFF5555, true);
        }
    }

    public void triggerNotEnoughMoneyError() {
        this.errorMessage = Component.translatable("screen.ototarot.atm.error.not_enough");
        this.errorMessageTicks = 60;
    }

    @Override
    public void containerTick() {
        super.containerTick();
        if (errorMessageTicks > 0) {
            errorMessageTicks--;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }


    @Override
    public AtmMenu getMenu() {
        return this.menu;
    }
}