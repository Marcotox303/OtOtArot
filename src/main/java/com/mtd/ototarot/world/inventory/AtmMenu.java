package com.mtd.ototarot.world.inventory;

import com.mtd.ototarot.ModAttachments;
import com.mtd.ototarot.economy.PlayerWallet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AtmMenu extends AbstractContainerMenu {
    private final Container container;
    private final Player player;
    private final boolean isCasino;

    public AtmMenu(int containerId, Inventory playerInventory, boolean isCasino) {
        this(containerId, playerInventory, new SimpleContainer(1), isCasino);
    }

    public AtmMenu(int containerId, Inventory playerInventory, Container container, boolean isCasino) {
        super(ModMenuTypes.ATM_MENU.get(), containerId);
        this.container = container;
        this.player = playerInventory.player;
        this.isCasino = isCasino;

        checkContainerSize(container, 1);
        container.startOpen(playerInventory.player);

        // Slot de depósito en el lado izquierdo (ej: coordenadas x=35, y=35)
        this.addSlot(new Slot(container, 0, 20, 36) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return getInputValue(stack) > 0;
            }
            @Override
            public void set(ItemStack stack) {
                super.set(stack);
                if (!player.level().isClientSide() && !stack.isEmpty()) {
                    processDeposit(stack);
                }
            }
        });

        // Inventario del jugador (para que pueda mover items si lo necesita)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    // Lógica cuando se deposita un item para convertirlo en G
    private void processDeposit(ItemStack stack) {
        double valuePerItem = getInputValue(stack);
        if (valuePerItem > 0) {
            int count = stack.getCount();
            double totalValue = valuePerItem * count;

            PlayerWallet wallet = player.getData(ModAttachments.WALLET.get());
            wallet.deposit(totalValue);

            // Consumimos el item depositado
            stack.setCount(0);
            this.broadcastChanges();
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer((ServerPlayer) player, new com.mtd.ototarot.economy.SyncWalletBalancePayload(wallet.getBalance()));
        }
    }

    // Define el valor en G de cada moneda o ficha
    private double getInputValue(ItemStack stack) {
        // Aquí evalúas tus items de monedas y fichas
        // Ejemplo:
        if (isCasino) {
            if (stack.is(com.mtd.ototarot.item.ModItems.BLUE_CASINO_CHIP.get())) return 1.0;
            if (stack.is(com.mtd.ototarot.item.ModItems.RED_CASINO_CHIP.get())) return 10.0;
            if (stack.is(com.mtd.ototarot.item.ModItems.GREEN_CASINO_CHIP.get())) return 100.0;
            if (stack.is(com.mtd.ototarot.item.ModItems.BLACK_CASINO_CHIP.get())) return 1000.0;
        } else {
            if (stack.is(com.mtd.ototarot.item.ModItems.IRON_COIN.get())) return 1.0;
            if (stack.is(com.mtd.ototarot.item.ModItems.GOLDEN_COIN.get())) return 4.0;
            if (stack.is(com.mtd.ototarot.item.ModItems.DIAMOND_COIN.get())) return 16.0;
            if (stack.is(com.mtd.ototarot.item.ModItems.NETHERITE_COIN.get())) return 64.0;
        }
        return 0.0;
    }

    public boolean isCasino() { return isCasino; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }
}