package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.effect.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EffectRegistry {

    public static final StatusEffect AUTISM;

    static {
        AUTISM = register(Registry.STATUS_EFFECT, "autism", new AutismEffect());
    }

    public static void registerEffects() {

    }

    private static StatusEffect register(Registry<StatusEffect> registryType, String path, StatusEffect statusEffect) {
        return Registry.register(registryType, new Identifier(TbhMod.MODID, path), statusEffect);
    }
}