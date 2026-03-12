package com.mtd.ototarot;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, OtOtArot.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> SKELETON_VARIANT =
            ATTACHMENTS.register("skeleton_variant", () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT)
                    .copyOnDeath() // Esto es clave para la persistencia y sincronización inicial
                    .build());
}