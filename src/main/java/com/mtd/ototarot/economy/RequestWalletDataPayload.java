package com.mtd.ototarot.economy;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RequestWalletDataPayload() implements CustomPacketPayload {
    public static final Type<RequestWalletDataPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "request_wallet_data"));

    public static final StreamCodec<FriendlyByteBuf, RequestWalletDataPayload> STREAM_CODEC = StreamCodec.unit(new RequestWalletDataPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
