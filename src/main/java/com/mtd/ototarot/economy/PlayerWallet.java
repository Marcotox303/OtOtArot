package com.mtd.ototarot.economy;

import com.mojang.serialization.Codec;

public class PlayerWallet {
    public static final Codec<PlayerWallet> CODEC = Codec.DOUBLE.xmap(PlayerWallet::new, PlayerWallet::getBalance);

    private double digitalBalance;

    public PlayerWallet() {
        this(0.0);
    }

    public PlayerWallet(double initialBalance) {
        this.digitalBalance = initialBalance;
    }


    public double getBalance() { return digitalBalance; }
    public void deposit(double amount) { this.digitalBalance += amount; }
    public boolean withdraw(double amount) {
        if (digitalBalance >= amount) {
            digitalBalance -= amount;
            return true;
        }
        return false;
    }
}