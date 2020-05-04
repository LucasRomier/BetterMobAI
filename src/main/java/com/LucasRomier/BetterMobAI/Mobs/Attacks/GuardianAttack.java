package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum GuardianAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    SUFFOCATION_VIBES_ATTACK("SUFFOCATION_VIBES_ATTACK"),
    NAILING_VIBES_ATTACK("NAILING_VIBES_ATTACK"),
    INSTANT_LASER_ATTACK("INSTANT_LASER_ATTACK");

    private final String name;

    GuardianAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





