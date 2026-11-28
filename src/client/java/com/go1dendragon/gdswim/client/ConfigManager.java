package com.go1dendragon.gdswim.client;

import com.go1dendragon.gdswim.client.gui.Feature;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.Path;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.nio.file.Files;
import java.io.IOException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.JsonParseException;

public class ConfigManager {

    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("gd-swim.json");

    // Allows real IDs and states to be saved to file
    public static void save() {
        HashMap<String, Boolean> settings = new HashMap<>();

        for (Feature feature : FeatureManager.getFeatures()) {
            settings.put(feature.getId(), feature.isEnabled());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(settings);
        try {
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            IO.println("Error Saving GD SWIM Config: " + e.getMessage());
        }
    }

    // Features not in an outdated version of the mod will have their default
    // value populated (usually false), when the settings are updated they will
    // be written to the file.
    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            return;
        }
        try {
            String json = Files.readString(CONFIG_PATH);

            Gson gson = new Gson();
            Type settingsType = new TypeToken<HashMap<String, Boolean>>() {}.getType();
            HashMap<String, Boolean> settings = gson.fromJson(json, settingsType);

            if (settings == null) {
                return;
            }

            for (Feature feature : FeatureManager.getFeatures()) {
                Boolean savedState = settings.get(feature.getId());
                if (savedState != null) {
                    feature.setEnabled(savedState);
                }

            }
        } catch (IOException  | JsonParseException e) {
            IO.println("Error Reading GD SWIM Config: " + e.getMessage());
        }
    }
}
