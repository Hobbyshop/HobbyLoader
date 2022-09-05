package me.hobbyshop.hobbyloader.mod;

import lombok.Getter;
import me.hobbyshop.hobbyloader.api.mod.HobbyMod;

import java.io.File;
import java.util.List;

public class ModManager {

    public static final File modsDir = new File("mods");

    @Getter
    private List<HobbyMod> mods;

    public void init() {
        this.mods = JarLoader.loadMods();
    }

}
