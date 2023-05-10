package gum.tbhmod.main.init;

import gum.tbhmod.main.advancements.TamedTbhAdvancement;
import net.minecraft.advancement.criterion.Criteria;

public class AdvancementRegistry {

    public static TamedTbhAdvancement TAMED_TBH;

    static {
        TAMED_TBH = Criteria.register(new TamedTbhAdvancement());
    }

    public static void registerAdvancements() {

    }
}