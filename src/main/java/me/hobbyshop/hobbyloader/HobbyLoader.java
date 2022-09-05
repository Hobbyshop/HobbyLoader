package me.hobbyshop.hobbyloader;

import lombok.Getter;
import me.hobbyshop.hobbyloader.mod.ModManager;

public class HobbyLoader {

    private static HobbyLoader instance;

    @Getter
    private ModManager modManager;

    public void start() {
        modManager = new ModManager();
        modManager.init();
    }

    public void stop() {

    }

    public static HobbyLoader getInstance() {
        if(instance == null)
            instance = new HobbyLoader();

        return instance;
    }
}
