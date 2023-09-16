package gum.tbhmod.main.item;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class TbhMeat extends Item {
    public TbhMeat(Settings _settings, StatusEffect _statusEffect) {
        super(_settings.food(new FoodComponent.Builder()
                .hunger(3)
                .saturationModifier(1.2f)
                .meat()
                .statusEffect(new StatusEffectInstance(
                        _statusEffect,
                        999999999, 0,
                        false, false, true
                        ),
                        100
                )
                .build()));
    }
}
