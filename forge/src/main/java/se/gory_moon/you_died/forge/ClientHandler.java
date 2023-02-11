package se.gory_moon.you_died.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent.Opening;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.gory_moon.you_died.YouDied;
import se.gory_moon.you_died.client.DeathScreenWrapper;
import se.gory_moon.you_died.client.DeathSplashScreen;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = YouDied.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenOpen(Opening event) {
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
