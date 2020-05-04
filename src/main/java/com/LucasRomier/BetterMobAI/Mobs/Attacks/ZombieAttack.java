package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum ZombieAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    BLOOD_RUSH_ATTACK("BLOOD_RUSH_ATTACK"),
    VAMPIRE_ATTACK("VAMPIRE_ATTACK"),
    MINIONS_ATTACK("MINIONS_ATTACK");

    private final String name;

    ZombieAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





