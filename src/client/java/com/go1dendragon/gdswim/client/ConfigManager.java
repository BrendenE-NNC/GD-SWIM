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
    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            return;
        }
        try {
            String json = Files.readString(CONFIG_PATH);

            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, Boolean>>() {}.getType();
            HashMap<String, Boolean> settings = gson.fromJson(json, type);

            if (settings == null) {
                return;
            }

            for (Feature feature : FeatureManager.getFeatures()) {
                Boolean value = settings.get(feature.getId());
                if (value != null) {
                    feature.setEnabled(value);
                }

            }
        } catch (IOException  | JsonParseException e) {
            IO.println("Error Reading GD SWIM Config: " + e.getMessage());
        }
    }
}
