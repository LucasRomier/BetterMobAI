package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum SpiderAttack {
    NORMAL_ATTACK("NORMAL_ATTACK"),
    SKY_ATTACK("SKY_ATTACK"),
    POISON_ATTACK("POISON_ATTACK"),
    WEB_ATTACK("WEB_ATTACK");

    private final String name;

    SpiderAttack(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}





