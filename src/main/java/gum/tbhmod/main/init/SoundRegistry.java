package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.item.Cola;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.sound.Sound;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry {
    public static SoundEvent YIPPEE, TBH_HURT, TBH_DIES;

    static {
        YIPPEE = register(Registry.SOUND_EVENT, "yippee");
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
