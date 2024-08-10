package se.gory_moon.you_died.fabric;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import se.gory_moon.you_died.YouDied;
import se.gory_moon.you_died.client.DeathScreenWrapper;
import se.gory_moon.you_died.client.DeathSplashScreen;

public class YouDiedFabric implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		ClientGuiEvent.SET_SCREEN.register(screen -> {
			if (screen instanceof DeathScreen deathScreen && !(screen instanceof DeathSplashScreen)) {
				LocalPlayer player = Minecraft.getInstance().player;
				if (player != null && !(Minecraft.getInstance().screen instanceof DeathScreen)) {
					Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(YouDied.DEATH_SOUND, 1.0F, 1.0F));
					return CompoundEventResult.interruptTrue(new DeathSplashScreen(new DeathScreenWrapper(deathScreen)));
				}
			}
			return CompoundEventResult.pass();
		});
	}
}
