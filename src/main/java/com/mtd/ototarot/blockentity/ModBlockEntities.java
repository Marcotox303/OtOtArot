package com.mtd.ototarot.blockentity;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.block.ModBlocks;
import com.mtd.ototarot.blockentity.custom.DiscoBallBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    // 1. Creamos el registro diferido para los BlockEntities
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OtOtArot.MOD_ID);

    // 2. Registramos nuestra entidad y la vinculamos al bloque físico
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DiscoBallBlockEntity>> DISCO_BALL =
            BLOCK_ENTITIES.register("disco_ball", () ->
                    BlockEntityType.Builder.of(DiscoBallBlockEntity::new, ModBlocks.DISCO_BALL.get()).build(null)
            );

    // 3. Método para añadir esto al bus de eventos de tu mod
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}