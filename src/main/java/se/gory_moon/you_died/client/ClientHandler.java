package se.gory_moon.you_died.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.gory_moon.you_died.YouDied;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = YouDied.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientHandler {

    public static SoundEvent DEATH_SOUND = new SoundEvent(new ResourceLocation(YouDied.MOD_ID, "death"));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenOpen(GuiOpenEvent event) {
        Screen screen = event.getGui();
        if (screen instanceof DeathScreen && !(screen instanceof DeathSplashScreen)) {
            DeathScreen deathScreen = (DeathScreen) screen;
            ClientPlayerEntity player = Minecraft.getInstance().player;
            if (player != null && !(Minecraft.getInstance().screen instanceof DeathScreen)) {
                event.setGui(new DeathSplashScreen(new DeathScreenWrapper(deathScreen)));
                Minecraft.getInstance().getSoundManager().play(SimpleSound.forUI(DEATH_SOUND, 1.0F, 1.0F));
            }
        }
    }

}
