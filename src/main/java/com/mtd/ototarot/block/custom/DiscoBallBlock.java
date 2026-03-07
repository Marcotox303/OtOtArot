package com.mtd.ototarot.block.custom;

import com.mojang.serialization.MapCodec;
import com.mtd.ototarot.blockentity.custom.DiscoBallBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiscoBallBlock extends BaseEntityBlock {

    public DiscoBallBlock(Properties properties) {
        super(properties);
    }

    // Le decimos que el bloque en sí es invisible o usa el modelo estándar,
    // pero el renderizado principal lo hará nuestro BER.
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new DiscoBallBlockEntity(pos, state); // Necesitarás registrar esto en tu DeferredRegister
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        // Solo nos interesa que haga "tick" en el cliente para los efectos visuales
        if (level.isClientSide()) {
            return (lvl, pos, st, be) -> {
                if (be instanceof DiscoBallBlockEntity discoBE) {
                    discoBE.clientTick();
                }
            };
        }
        return null;
    }
    // 1. Definimos el MapCodec para nuestra clase
    public static final MapCodec<DiscoBallBlock> CODEC = simpleCodec(DiscoBallBlock::new);

    // 2. Implementamos el método abstracto que te pide el error
    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}