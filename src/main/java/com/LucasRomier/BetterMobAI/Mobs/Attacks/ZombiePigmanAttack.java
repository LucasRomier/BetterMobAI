package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum ZombiePigmanAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    LIGHTNING_ATTACK("LIGHTNING_ATTACK"),
    SWORD_THROW_ATTACK("SWORD_THROW_ATTACK");

    private final String name;

    ZombiePigmanAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





