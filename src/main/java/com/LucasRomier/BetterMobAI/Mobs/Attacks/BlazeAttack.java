package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum BlazeAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    HEAT_ATTACK("HEAT_ATTACK"),
    SMOKE_ATTACK("SMOKE_ATTACK");

    private final String name;

    BlazeAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





