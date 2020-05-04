package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum EndermanAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    JUMPER_ATTACK("JUMPER_ATTACK");

    private final String name;

    EndermanAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





