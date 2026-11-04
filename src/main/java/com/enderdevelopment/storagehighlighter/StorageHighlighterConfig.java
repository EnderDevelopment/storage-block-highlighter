package com.enderdevelopment.storagehighlighter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StorageHighlighterConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("storagehighlighter.json");

    private static ConfigData configData;

    public static class ConfigData {
        public int highlightColor = 0xFF00FF00; // Default green color
    }

    public static void loadConfig() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                configData = GSON.fromJson(json, ConfigData.class);
            } catch (IOException e) {
                e.printStackTrace();
                configData = new ConfigData();
            }
        } else {
            configData = new ConfigData();
            saveConfig();
        }
    }

    public static void saveConfig() {
        try {
            String json = GSON.toJson(configData);
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getHighlightColor() {
        return configData.highlightColor;
    }

    public static void setHighlightColor(int color) {
        configData.highlightColor = color;
        saveConfig();
    }
}