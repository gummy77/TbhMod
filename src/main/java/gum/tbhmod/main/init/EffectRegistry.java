package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.effect.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EffectRegistry {

    public static final StatusEffect AUTISM;

    static {
        AUTISM = register("autism", new AutismEffect());
    }

    public static void registerEffects(){

    }

    private static StatusEffect register(String path, StatusEffect statusEffect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(TbhMod.MODID, path), statusEffect);
    }
}