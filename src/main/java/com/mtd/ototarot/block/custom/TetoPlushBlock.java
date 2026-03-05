package com.mtd.ototarot.block.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class TetoPlushBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // Mapa que pre-calcula las rotaciones al iniciar el juego
    private static final Map<Direction, VoxelShape> SHAPES = calculateAllShapes();

    public TetoPlushBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    private static Map<Direction, VoxelShape> calculateAllShapes() {
        VoxelShape baseShape = makeShape(); // Forma original (mirando al Norte)
        return ImmutableMap.of(
                Direction.NORTH, baseShape,
                Direction.SOUTH, rotateShape(Direction.NORTH, Direction.SOUTH, baseShape),
                Direction.EAST,  rotateShape(Direction.NORTH, Direction.EAST, baseShape),
                Direction.WEST,  rotateShape(Direction.NORTH, Direction.WEST, baseShape)
        );
    }

    // Algoritmo para rotar las coordenadas de los VoxelShapes
    private static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = { shape, Shapes.empty() };
        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.joinUnoptimized(buffer[1],
                    Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX), BooleanOp.OR));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }

    public static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.15625, 0.40625, 0.40625, 0.34375, 0.5, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.175, 0.30625, 0.425, 0.325, 0.4375, 0.575), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.2, 0.18125, 0.45, 0.3, 0.325, 0.55), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.7, 0.18125, 0.45, 0.8, 0.325, 0.55), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.675, 0.30625, 0.425, 0.825, 0.4375, 0.575), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.65625, 0.40625, 0.40625, 0.84375, 0.5, 0.59375), BooleanOp.OR);
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

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) { return true; }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) { return 1.0F; }

    @Override
    public boolean isOcclusionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) { return false; }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}