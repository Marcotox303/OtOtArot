package com.mtd.ototarot.economy;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import com.mtd.ototarot.OtOtArot;

public record WithdrawAtmPayload(int optionIndex) implements CustomPacketPayload {
    public static final Type<WithdrawAtmPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "withdraw_atm"));

    public static final StreamCodec<FriendlyByteBuf, WithdrawAtmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            WithdrawAtmPayload::optionIndex,
            WithdrawAtmPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}