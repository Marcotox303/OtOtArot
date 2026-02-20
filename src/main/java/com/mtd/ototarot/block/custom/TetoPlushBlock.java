package com.mtd.ototarot.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TetoPlushBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public TetoPlushBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, net.minecraft.core.Direction.NORTH));
    }
    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true; // Permite que pase la luz
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
        return 1.0F; // Evita sombras raras dentro del modelo
    }

    // Lo más importante: esto dice que el bloque NO es un cubo completo
    @Override
    public boolean isOcclusionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }
    // Paso B: Determinar la dirección basada en la mirada del jugador al colocarlo
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // Paso C: Registrar la propiedad en el sistema de estados del bloque
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.15625, 0.40625, 0.40625, 0.34375, 0.5, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.45625, 0.325, 0.3375, 0.51875, 0.4875, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5375, 0.29375, 0.3375, 0.6, 0.45, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.175, 0.30625, 0.425, 0.325, 0.4375, 0.575), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.2, 0.18125, 0.45, 0.3, 0.325, 0.55), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.59375, 0.3, 0.3375, 0.65625, 0.44375, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.7, 0.18125, 0.45, 0.8, 0.325, 0.55), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.675, 0.30625, 0.425, 0.825, 0.4375, 0.575), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.65625, 0.40625, 0.40625, 0.84375, 0.5, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.325, 0.31875, 0.3375, 0.3875, 0.45, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4875, 0.425, 0.4875, 0.53125, 0.5375, 0.4875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.38125, 0.3125, 0.3375, 0.44375, 0.45, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.1875, 0.34375, 0.65625, 0.5, 0.65625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.59375, 0.0625, 0.46875, 0.65625, 0.125, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.0625, 0.46875, 0.40625, 0.125, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6, 0.05625, 0.4625, 0.64375, 0.13125, 0.5375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.35625, 0.05625, 0.4625, 0.4, 0.13125, 0.5375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.40625, 0, 0.40625, 0.59375, 0.1875, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5375, 0.09375, 0.4, 0.575, 0.125, 0.4125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.425, 0.09375, 0.4, 0.4625, 0.125, 0.4125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.40625, 0, 0.34375, 0.46875, 0.0625, 0.40625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.53125, 0, 0.34375, 0.59375, 0.0625, 0.40625), BooleanOp.OR);

        return shape;
    }
}
