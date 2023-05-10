package gum.tbhmod.main;

import net.fabricmc.api.ModInitializer;
import gum.tbhmod.main.init.*;

public class TbhMod implements ModInitializer {
    public static final String MODID = "tbhmod";

    @Override
    public void onInitialize() {
        EntityRegistry.registerEntityAttributes();
        ItemRegistry.registerItems();
        SoundRegistry.registerSounds();
        AdvancementRegistry.registerAdvancements();
    }
    //TODO website - modrinth/curseforge + gumgeon
    //TODO create Icon
    //TODO take screenshots
    //TODO fix mob effect autism
}
