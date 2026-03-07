package com.mtd.ototarot.blockentity.custom;

import com.mtd.ototarot.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;

public class DiscoBallBlockEntity extends BlockEntity {
    public float globalRotation = 0;
    public final List<BeamData> beams = new ArrayList<>();
    private final RandomSource random = RandomSource.create();

    public DiscoBallBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISCO_BALL.get(), pos, state); // Asegúrate de tener este RegistryObject

        // Inicializamos 12 rayos con direcciones y colores aleatorios
        for (int i = 0; i < 12; i++) {
            beams.add(new BeamData(
                    random.nextFloat() * 360f, // Yaw inicial
                    (random.nextFloat() * 180f) - 90f, // Pitch inicial
                    randomColor()
            ));
        }
    }

    public void clientTick() {
        if (level == null) return;

        // 1. Girar la bola
        globalRotation += 1.5f;
        if (globalRotation >= 360f) globalRotation = 0f;

        Vec3 centerPos = Vec3.atCenterOf(this.getBlockPos());

        // 2. Actualizar cada rayo (movimiento aleatorio y Raycast)
        for (BeamData beam : beams) {
            // Hacemos que los rayos "bailen" un poco cambiando su ángulo
            beam.yaw += (random.nextFloat() - 0.5f) * 4f;
            beam.pitch += (random.nextFloat() - 0.5f) * 4f;

            // Limitamos el pitch para que no den la vuelta entera
            beam.pitch = Mth.clamp(beam.pitch, -90f, 90f);

            // Calculamos el vector de dirección basado en el Yaw y Pitch
            // Sumamos el globalRotation al yaw para que los rayos giren con la bola
            Vec3 direction = Vec3.directionFromRotation(beam.pitch, beam.yaw + globalRotation);

            // Calculamos el impacto (alcance máximo de 15 bloques)
            Vec3 endPos = centerPos.add(direction.scale(15.0));
            BlockHitResult hit = level.clip(new ClipContext(
                    centerPos,
                    endPos,
                    ClipContext.Block.VISUAL,
                    ClipContext.Fluid.NONE,
                    net.minecraft.world.phys.shapes.CollisionContext.empty() // <--- CAMBIO AQUÍ
            ));

            // Guardamos la distancia exacta hasta el bloque impactado
            beam.currentLength = (float) centerPos.distanceTo(hit.getLocation());
        }
    }

    private int[] randomColor() {
        // Devuelve RGB aleatorio brillante
        return new int[]{random.nextInt(155) + 100, random.nextInt(155) + 100, random.nextInt(155) + 100};
    }

    // Clase interna para guardar los datos de cada rayo
    public static class BeamData {
        public float yaw, pitch;
        public int[] color;
        public float currentLength = 0;

        public BeamData(float yaw, float pitch, int[] color) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.color = color;
        }
    }
}