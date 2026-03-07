package com.mtd.ototarot;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

// Usamos el bus GAME porque el spawn ocurre durante el juego normal
@EventBusSubscriber(modid = OtOtArot.MOD_ID)
public class ModArmorSpawnHandler {

    @SubscribeEvent
    public static void onEntitySpawn(FinalizeSpawnEvent event) {
        Mob mob = event.getEntity();// Solo mobs que pueden usar cabeza
        if (mob instanceof Zombie || mob instanceof AbstractSkeleton ||
                mob instanceof AbstractPiglin || mob instanceof AbstractIllager ||
                mob instanceof AbstractVillager || mob instanceof IronGolem  ||
                mob instanceof Witch) {

            // 1.1 para pruebas (110%), cámbialo a 0.05 (5%) después
            if (mob.getRandom().nextDouble() < 0.002) {
                mob.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.PERROT_MASK.get()));
                mob.setDropChance(EquipmentSlot.HEAD, 1.0f); // 100% de drop para pruebas
            }
        }
    }
}