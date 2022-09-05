package me.hobbyshop.hobbyloader.api.mod;

import lombok.Getter;

public abstract class HobbyMod {

    @Getter
    private ModInfo info;

    public HobbyMod(ModInfo info) {
        this.info = info;
    }

    public abstract void onInitialize();
    public abstract void onKill();

}
