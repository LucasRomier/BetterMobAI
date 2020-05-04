package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum SkeletonAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    ARROW_RAIN_ATTACK("ARROW_RAIN_ATTACK"),
    BURNING_ARROW_ATTACK("BURNING_ARROW_ATTACK"),
    POISONED_ARROW_ATTACK("POISONED_ARROW_ATTACK"),
    NAILING_ARROW_ATTACK("NAILING_ARROW_ATTACK");

    private final String name;

    SkeletonAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





