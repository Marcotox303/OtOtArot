package com.mtd.ototarot.client;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.custom.PerrotMaskItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = OtOtArot.MOD_ID, value = Dist.CLIENT)
public class ModClientOverlay {
    // Ruta a tu textura: src/main/resources/assets/ototarot/textures/misc/perrot_blur.png
    private static final ResourceLocation PERROT_OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "textures/misc/perrot_blur.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Si el jugador lleva la máscara puesta
        ItemStack headStack = mc.player.getItemBySlot(EquipmentSlot.HEAD);
        if (headStack.getItem() instanceof PerrotMaskItem) {
            renderTextureOverlay(event.getGuiGraphics());
        }
    }

    private static void renderTextureOverlay(GuiGraphics guiGraphics) {
        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        guiGraphics.setColor(1.0F, 1.0F, 1.0F, (float) 1.0);
        guiGraphics.blit(ModClientOverlay.PERROT_OVERLAY_TEXTURE, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}