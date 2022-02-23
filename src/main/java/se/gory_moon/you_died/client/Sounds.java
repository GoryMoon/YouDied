package se.gory_moon.you_died.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import se.gory_moon.you_died.YouDied;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, YouDied.MOD_ID);

    public static RegistryObject<SoundEvent> DEATH_SOUND = SOUNDS.register("death", () -> new SoundEvent(new ResourceLocation(YouDied.MOD_ID, "death")));
}
