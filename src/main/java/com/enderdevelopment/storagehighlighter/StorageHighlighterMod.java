package com.enderdevelopment.storagehighlighter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class StorageHighlighterMod implements ModInitializer {
    public static final String MOD_ID = "storagehighlighter";
    private static KeyBinding openConfigKey;

    @Override
    public void onInitialize() {
        // Register key binding for config
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.storagehighlighter.open_config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "category.storagehighlighter.config"
        ));

        // Register client tick events
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.wasPressed()) {
                // Open config screen
                client.setScreen(new StorageHighlighterConfigScreen());
            }
        });
    }
}