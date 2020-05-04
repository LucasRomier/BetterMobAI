package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum CreeperAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    CHARGED_CREEPER_ATTACK("CHARGED_CREEPER_ATTACK"),
    CHARGED_SUPER_CREEPER_ATTACK("CHARGED_SUPER_CREEPER_ATTACK"),
    IMPLOSION_EXPLOSION_ATTACK("IMPLOSION_EXPLOSION_ATTACK");

    private final String name;

    CreeperAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





