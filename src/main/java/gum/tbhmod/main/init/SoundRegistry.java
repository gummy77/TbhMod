package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry {
    public static SoundEvent YIPPEE, COLA, FORTNITE,
            TBH_AMBIENT, TBH_HURT, TBH_DIES;

    static {
        YIPPEE = register(Registry.SOUND_EVENT, "yippee");
        COLA = register(Registry.SOUND_EVENT, "cola");
        FORTNITE = register(Registry.SOUND_EVENT, "fortnite");

        TBH_AMBIENT = register(Registry.SOUND_EVENT, "tbh_ambient");
        TBH_HURT = register(Registry.SOUND_EVENT, "tbh_hurt");
        TBH_DIES = register(Registry.SOUND_EVENT, "tbh_dies");
    }

    public static void registerSounds() {}

    public static SoundEvent register (Registry<SoundEvent> Registrytype, String path) {
        Identifier _id = new Identifier(TbhMod.MODID, path);
        SoundEvent _soundEvent = new SoundEvent(_id);

        return Registry.register(Registrytype, _id, _soundEvent);
    }
}
