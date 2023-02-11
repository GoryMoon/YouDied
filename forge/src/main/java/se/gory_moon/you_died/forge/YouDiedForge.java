package se.gory_moon.you_died.forge;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import se.gory_moon.you_died.YouDied;

@Mod(YouDied.MOD_ID)
public class YouDiedForge
{
    public YouDiedForge() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "ANY", (remote, isServer) -> true));
    }
}
