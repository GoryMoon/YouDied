package se.gory_moon.you_died.fabric;

import net.fabricmc.api.ClientModInitializer;
import se.gory_moon.you_died.fabriclike.YouDiedFabricLike;

public class YouDiedFabric implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		YouDiedFabricLike.init();
	}
}
