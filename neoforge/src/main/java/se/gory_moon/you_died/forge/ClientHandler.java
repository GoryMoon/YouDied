package se.gory_moon.you_died.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import se.gory_moon.you_died.YouDied;
import se.gory_moon.you_died.client.DeathScreenWrapper;
import se.gory_moon.you_died.client.DeathSplashScreen;

@EventBusSubscriber(value = Dist.CLIENT, modid = YouDied.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ClientHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenOpen(ScreenEvent.Opening event) {
        Screen screen = event.getScreen();
        if (screen instanceof DeathScreen deathScreen && !(screen instanceof DeathSplashScreen)) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && !(Minecraft.getInstance().screen instanceof DeathScreen)) {
                event.setNewScreen(new DeathSplashScreen(new DeathScreenWrapper(deathScreen)));
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(YouDied.DEATH_SOUND, 1.0F, 1.0F));
            }
        }
    }

}
