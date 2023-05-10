package gum.tbhmod.main.effect;

import gum.tbhmod.main.init.EffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class AutismEffect extends StatusEffect {
    public AutismEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xffffff);
    }
}
