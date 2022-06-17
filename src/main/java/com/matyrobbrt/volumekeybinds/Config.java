package com.matyrobbrt.volumekeybinds;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue VOLUME_STEP;

    static {
        final var builder = new ForgeConfigSpec.Builder();
        VOLUME_STEP = builder.comment("The percentage a keybind click should increase / decrease volume by.")
                .defineInRange("volume_step", 5D, 0D, 100D);
        SPEC = builder.build();
    }
}
