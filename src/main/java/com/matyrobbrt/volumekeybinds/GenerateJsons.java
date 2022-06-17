package com.matyrobbrt.volumekeybinds;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.sounds.SoundSource;

import java.util.Locale;

public class GenerateJsons {
    public static void main(String[] args) {
        final var json = new JsonObject();
        for (SoundSource value : SoundSource.values()) {
            final var soundLow = value.toString().toLowerCase(Locale.ROOT);
            json.addProperty("key.volumekeybinds.increase." + soundLow, "Increase volume: " + soundLow);
            json.addProperty("key.volumekeybinds.decrease." + soundLow, "Decrease volume: " + soundLow);
            json.addProperty("key.volumekeybinds.mute." + soundLow, "Mute sound of type: " + soundLow);
        }
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(json));
    }
}
