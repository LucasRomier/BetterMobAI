package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum WitchAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    SLOWNESS_ATTACK("SLOWNESS_POTION_ATTACK"),
    POISON_ATTACK("POISON_POTION_ATTACK"),
    FIRE_CIRCLE_ATTACK("FIRE_CIRCLE_ATTACK"),
    BURN_PLAYER_ATTACK("BURN_PLAYER_ATTACK"),
    LAVA_ATTACK("LAVA_ATTACK"),
    BLACK_MAGIC_ATTACK("BLACK_MAGIC_ATTACK"),
    HEAL_EFFECT("HEAL_EFFECT"),
    ENDERMITES_ATTACK("ENDERMITES_ATTACK");

    private final String name;

    WitchAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





