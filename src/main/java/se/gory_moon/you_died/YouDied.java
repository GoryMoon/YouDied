package se.gory_moon.you_died;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import se.gory_moon.you_died.client.Sounds;

@Mod(YouDied.MOD_ID)
public class YouDied {
    public static final String MOD_ID = "you_died";

    public YouDied() {
        Sounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "ANY", (remote, isServer) -> true));
    }
}
