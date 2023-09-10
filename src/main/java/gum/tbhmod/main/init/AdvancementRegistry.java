package gum.tbhmod.main.init;

import gum.tbhmod.main.advancements.TamedBtwAdvancement;
import gum.tbhmod.main.advancements.TamedTbhAdvancement;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.registry.Registry;


public class AdvancementRegistry {

    public static TamedTbhAdvancement TAMED_TBH;
    public static TamedBtwAdvancement TAMED_BTW;

    static {
        TAMED_TBH = Criteria.register(new TamedTbhAdvancement());
        TAMED_BTW = Criteria.register(new TamedBtwAdvancement());
    }

    public static void registerAdvancements() {

    }
}