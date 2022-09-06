package me.hobbyshop.hobbyloader.mod;

import com.google.gson.Gson;
import me.hobbyshop.hobbyloader.api.mod.HobbyMod;
import me.hobbyshop.hobbyloader.api.mod.ModInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoader {

    public static ArrayList<HobbyMod> loadMods() {
        ArrayList<HobbyMod> mods = new ArrayList<>();
        if (!ModManager.modsDir.exists())
            ModManager.modsDir.mkdirs();

        try {
            Path modPath = Paths.get(ModManager.modsDir.toURI());
            for (Path file : Files.newDirectoryStream(modPath, entry -> entry.getFileName().toString().endsWith(".jar"))) {
                URLClassLoader loader = new URLClassLoader(new URL[] { file.toUri().toURL() }, ClassLoader.getSystemClassLoader());
                Optional<ModInfo> info = getModInfo(file);

                if (!info.isPresent()) {
                    System.err.println("Error loading mod info of " + file.getFileName());
                    continue;
                }
                Class<?> mod = loader.loadClass(info.get().getEntry());
                try {
                    HobbyMod instance = (HobbyMod) mod.getConstructor(ModInfo.class).newInstance(info.get());
                    System.out.println("heheheha");
                    mods.add(instance);
                } catch (ClassCastException e) {
                    System.err.println("Main class of module " + file.getFileName() + " does not implement HobbyMod");
                }
            }

        } catch (Exception e) {
            if (e instanceof IOException)            System.err.println("Could not load mods dir");
            if (e instanceof ClassNotFoundException) System.err.println("Unable to find main class");
            if (e instanceof InstantiationException) System.err.println("Error creating main class instance");
            if (e instanceof IllegalAccessException) System.err.println("Constructor of main class is private");
        }
        return mods;
    }

    private static Optional<ModInfo> getModInfo(Path path) {
        try {
            Gson gson = new Gson();
            JarFile jar = new JarFile(path.toString());
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry e = entries.nextElement();

                if (e.getName().equalsIgnoreCase("module.json")) {
                    InputStream input = jar.getInputStream(e);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    StringBuilder fileContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null)
                        fileContent.append(line);

                    reader.close();
                    input.close();

                    ModInfo info = gson.fromJson(fileContent.toString(), ModInfo.class);
                    if (info.getName() == null || info.getEntry() == null) {
                        System.err.println("Invalid module config");
                        return Optional.empty();
                    }
                    return Optional.of(info);
                }
            }
            System.err.println("Jar file does not contain module.json");
            return Optional.empty();

        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
