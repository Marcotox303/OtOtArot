package com.mtd.ototarot.teams;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record TeamSelectionPayload(String colorName) implements CustomPacketPayload {
    // Definimos el ID del paquete (pon el nombre de tu mod en vez de "ototarot")
    public static final Type<TeamSelectionPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("ototarot", "team_selection"));

    // Codec para leer y escribir el nombre del color en la red
    public static final StreamCodec<FriendlyByteBuf, TeamSelectionPayload> CODEC = StreamCodec.of(
            (buf, payload) -> buf.writeUtf(payload.colorName),
            buf -> new TeamSelectionPayload(buf.readUtf())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}