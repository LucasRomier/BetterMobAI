package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum WitherSkeletonAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    WITHER_SKULL_ATTACK("WITHER_SKULL_ATTACK"),
    SWORD_THROW_ATTACK("SWORD_THROW_ATTACK");

    private final String name;

    WitherSkeletonAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





