package com.matyrobbrt.volumekeybinds;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(VolumeKeybinds.MOD_ID)
public class VolumeKeybinds {

    public static final String MOD_ID = "volumekeybinds";

    public VolumeKeybinds() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "Hello there!", (a1, server) -> server));
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC, MOD_ID + "-client.toml");
    }
}
