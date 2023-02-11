package se.gory_moon.you_died.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import se.gory_moon.you_died.fabriclike.YouDiedFabricLike;

public class YouDiedQuilt implements ClientModInitializer
{
	@Override
	public void onInitializeClient(ModContainer mod)
	{
		YouDiedFabricLike.init();
	}
}
