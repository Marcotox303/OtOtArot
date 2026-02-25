package com.mtd.ototarot;

import net.minecraft.client.Minecraft;

public class DistHelper {
    public static void openTeamScreen() {
        Minecraft.getInstance().setScreen(new TeamSelectionScreen());
    }
}