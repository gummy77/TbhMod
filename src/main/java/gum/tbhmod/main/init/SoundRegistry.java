package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry {
    public static SoundEvent YIPPEE, COLA, FORTNITE,
            TBH_AMBIENT, TBH_HURT, TBH_DIES,
            YAHOO,
            BTW_AMBIENT, BTW_HURT, BTW_DIES;

    static {
        YIPPEE = register("yippee");
        COLA = register("cola");
        FORTNITE = register("fortnite");

        TBH_AMBIENT = register( "tbh_ambient");
        TBH_HURT = register("tbh_hurt");
        TBH_DIES = register("tbh_dies");

        YAHOO = register( "yahoo");

        BTW_AMBIENT = register( "btw_ambient");
        BTW_HURT = register("btw_hurt");
        BTW_DIES = register("btw_dies");
    }

    public static void registerSounds() {}

    public static SoundEvent register (String path) {
        Identifier _id = new Identifier(TbhMod.MODID, path);
        SoundEvent _soundEvent = new SoundEvent(_id);

        return Registry.register(Registry.SOUND_EVENT, _id, _soundEvent);
    }
}
