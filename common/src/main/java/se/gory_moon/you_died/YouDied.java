package se.gory_moon.you_died;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class YouDied
{
	public static final String MOD_ID = "you_died";

	public static SoundEvent DEATH_SOUND = new SoundEvent(new ResourceLocation(YouDied.MOD_ID, "death"));
}
