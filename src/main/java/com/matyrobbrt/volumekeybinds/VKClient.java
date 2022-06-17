package com.matyrobbrt.volumekeybinds;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = VolumeKeybinds.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VKClient {
    @SubscribeEvent
    static void onClientSetup(final FMLClientSetupEvent event) {
        final var category = "key.categories." + VolumeKeybinds.MOD_ID;
        for (final var sound : SoundSource.values()) {
            final var soundLowercase = sound.toString().toLowerCase(Locale.ROOT);
            final var increase = new KeyMapping("key.volumekeybinds.increase." + soundLowercase, -1, category) {
                @Override
                public void setDown(boolean pValue) {
                    if (pValue)
                        executeAction(Action.INCREASE, sound);
                    super.setDown(pValue);
                }
            };
            final var decrease = new KeyMapping("key.volumekeybinds.decrease." + soundLowercase, -1, category) {
                @Override
                public void setDown(boolean pValue) {
                    if (pValue)
                        executeAction(Action.DECREASE, sound);
                    super.setDown(pValue);
                }
            };
            final var mute = new KeyMapping("key.volumekeybinds.mute." + soundLowercase, -1, category) {
                @Override
                public void setDown(boolean pValue) {
                    if (pValue)
                        executeAction(Action.MUTE, sound);
                    super.setDown(pValue);
                }
            };
            ClientRegistry.registerKeyBinding(increase);
            ClientRegistry.registerKeyBinding(decrease);
            ClientRegistry.registerKeyBinding(mute);
        }
    }

    private static void executeAction(Action action, SoundSource source) {
        switch (action) {
            case MUTE -> {
                final var isMuted = Minecraft.getInstance().options.getSoundSourceVolume(source) < 0.01;
                Minecraft.getInstance().options.setSoundCategoryVolume(source, isMuted ? 1 : 0);
            }
            case DECREASE -> updateVolume(-Config.VOLUME_STEP.get(), source);
            case INCREASE -> updateVolume(Config.VOLUME_STEP.get(), source);
        }
        Minecraft.getInstance().options.save();
    }

    private static void updateVolume(double amount, SoundSource source) {
        final var current = Minecraft.getInstance().options.getSoundSourceVolume(source);
        var newAmount = current + ((float) amount / 100);
        if (newAmount > 1)
            newAmount = 1;
        else if (newAmount < 0)
            newAmount = 0;
        Minecraft.getInstance().options.setSoundCategoryVolume(source, newAmount);
    }

    enum Action {
        MUTE,
        DECREASE,
        INCREASE
    }
}
