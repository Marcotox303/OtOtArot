package com.mtd.ototarot.world.inventory;

import com.mtd.ototarot.OtOtArot;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, OtOtArot.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<AtmMenu>> ATM_MENU =
            MENUS.register("atm_menu", () -> IMenuTypeExtension.create((windowId, inventory, data) -> {
                // Leemos el booleano 'isCasino' que enviamos desde el servidor al abrir el menú
                boolean isCasino = data.readBoolean();
                return new AtmMenu(windowId, inventory, isCasino);
            }));
}