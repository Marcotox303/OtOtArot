package com.mtd.ototarot.economy;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import com.mtd.ototarot.OtOtArot;

public record SyncWalletBalancePayload(double balance) implements CustomPacketPayload {
    public static final Type<SyncWalletBalancePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OtOtArot.MOD_ID, "sync_wallet_balance"));

    public static final StreamCodec<FriendlyByteBuf, SyncWalletBalancePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, SyncWalletBalancePayload::balance, SyncWalletBalancePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}