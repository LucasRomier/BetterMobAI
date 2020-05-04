package com.LucasRomier.BetterMobAI.Mobs.Attacks;

public enum GiantAttack {
    THROW_MINI_ZOMBIE("ThrowMiniZombie"),
    THROW_BOULDER("ThrowBoulder"),
    EARTHQUAKE("Earthquake"),
    LIGHTNING_STRIKER("LightningStriker");
    private String name;

    GiantAttack(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}





