package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum GhastAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    HIGH_SPEED_FIRE_BALL_ATTACK("HIGH_SPEED_FIRE_BALL_ATTACK"),
    MULTY_FIRE_BALL_ATTACK("MULTY_FIRE_BALL_ATTACK");

    private final String name;

    GhastAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





