package com.mtd.ototarot;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenTeamGuiPayload() implements CustomPacketPayload {
    public static final Type<OpenTeamGuiPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("ototarot", "open_team_gui"));

    // Como no envía datos, el codec es vacío
    public static final StreamCodec<FriendlyByteBuf, OpenTeamGuiPayload> CODEC = StreamCodec.unit(new OpenTeamGuiPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}