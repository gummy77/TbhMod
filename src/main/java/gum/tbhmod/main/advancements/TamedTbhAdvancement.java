package gum.tbhmod.main.advancements;

import com.google.gson.JsonObject;
import gum.tbhmod.main.TbhMod;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TamedTbhAdvancement extends AbstractCriterion<TamedTbhAdvancement.Condition> {
    public static final Identifier ID = new Identifier(TbhMod.MODID, "tamed_tbh");

    @Override
    protected Condition conditionsFromJson(JsonObject obj, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new Condition();
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity player) {
        trigger(player, condition -> {
            return true;
        });
    }

    public static class Condition extends AbstractCriterionConditions {

        public Condition() {
            super(ID, EntityPredicate.Extended.EMPTY);
        }
    }
}
