package se.gory_moon.you_died.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.gory_moon.you_died.YouDied;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = YouDied.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenOpen(ScreenOpenEvent event) {
        Screen screen = event.getScreen();
        if (screen instanceof DeathScreen deathScreen && !(screen instanceof DeathSplashScreen)) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && !(Minecraft.getInstance().screen instanceof DeathScreen)) {
                event.setScreen(new DeathSplashScreen(new DeathScreenWrapper(deathScreen)));
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(Sounds.DEATH_SOUND.get(), 1.0F, 1.0F));
            }
        }
    }

}
