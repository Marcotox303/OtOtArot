package com.mtd.ototarot.economy;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenWalletGuiPayload(int playerG, String leaderboardData) implements CustomPacketPayload {
    public static final Type<OpenWalletGuiPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("ototarot","open_wallet_gui"));

    public static final StreamCodec<FriendlyByteBuf, OpenWalletGuiPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            OpenWalletGuiPayload::playerG,
            ByteBufCodecs.STRING_UTF8,
            OpenWalletGuiPayload::leaderboardData,
            OpenWalletGuiPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
