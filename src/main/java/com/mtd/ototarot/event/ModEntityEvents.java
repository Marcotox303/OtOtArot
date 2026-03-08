package com.mtd.ototarot.event;

import com.mtd.ototarot.OtOtArot;
import com.mtd.ototarot.item.ModItems;
import com.mtd.ototarot.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.PlayLevelSoundEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = OtOtArot.MOD_ID)
public class ModEntityEvents {

    @SubscribeEvent
    public static void onSkeletonSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Skeleton skeleton)) return;

        // solo servidor
        if (skeleton.level().isClientSide()) return;

        // 5%
        if (skeleton.getRandom().nextFloat() < 0.05f) {

            // guardamos variante en NBT (se sincroniza mejor)
            skeleton.getPersistentData().putBoolean("nomo_variant", true);

            skeleton.setItemSlot(EquipmentSlot.HEAD, Items.BLUE_BANNER.getDefaultInstance());
            skeleton.setItemSlot(EquipmentSlot.MAINHAND, Items.BONE.getDefaultInstance());
        }
    }
    @SubscribeEvent
    public static void onSkeletonSound(PlayLevelSoundEvent.AtEntity event) {
        if (event.getEntity() instanceof Skeleton skeleton && skeleton.getTags().contains("is_nomo_variant")) {

            // Obtenemos el sonido actual para comparar
            Holder<SoundEvent> currentSound = event.getSound();
            if (currentSound == null) return;

            // 1. Cambiar sonido ambiente
            if (currentSound.value().equals(SoundEvents.SKELETON_AMBIENT)) {
                // Holder.direct() envuelve tu SoundEvent en el formato que el evento necesita
                event.setSound(Holder.direct(ModSounds.NOMO_SKELETON_AMBIENT.get()));
            }

            // 2. Cambiar sonido de daño
            if (currentSound.value().equals(SoundEvents.SKELETON_HURT)) {
                event.setSound(Holder.direct(ModSounds.NOMO_SKELETON_HURT.get()));
            }

            // 3. Cambiar sonido de muerte
            if (currentSound.value().equals(SoundEvents.SKELETON_DEATH)) {
                event.setSound(Holder.direct(ModSounds.NOMO_SKELETON_DEATH.get()));
            }
        }
    }
    @SubscribeEvent
    public static void onSkeletonHurt(LivingDamageEvent event) {

        if (!(event.getEntity() instanceof Skeleton skeleton)) return;

        if (!skeleton.getPersistentData().getBoolean("nomo_variant")) return;

        skeleton.level().playSound(
                null,
                skeleton.blockPosition(),
                ModSounds.NOMO_SKELETON_HURT.get(),
                skeleton.getSoundSource(),
                1f,
                1f
        );
    }
    @SubscribeEvent
    public static void onSkeletonDeath(LivingDeathEvent event) {

        if (!(event.getEntity() instanceof Skeleton skeleton)) return;

        if (!skeleton.getPersistentData().getBoolean("nomo_variant")) return;

        skeleton.level().playSound(
                null,
                skeleton.blockPosition(),
                ModSounds.NOMO_SKELETON_DEATH.get(),
                skeleton.getSoundSource(),
                1f,
                1f
        );
    }

}