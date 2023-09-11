package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.entity.BtwEntity;
import gum.tbhmod.main.entity.TbhEntity;
import gum.tbhmod.main.entity.entitySettings;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {

    public static final EntityType TBH_ENTITY = registerEntity(TbhEntity.getSettings());
    public static final EntityType BTW_ENTITY = registerEntity(BtwEntity.getSettings());

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(TBH_ENTITY, TbhEntity.createTbhAttributes());
        FabricDefaultAttributeRegistry.register(BTW_ENTITY, BtwEntity.createBtwAttributes());
    }

    protected static EntityType registerEntity(entitySettings settings) {
        EntityType entityType = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(TbhMod.MODID, settings.path),
                FabricEntityTypeBuilder.create(settings.spawnGroup, settings.entityFactory)
                        .dimensions(EntityDimensions.fixed(settings.x, settings.y))
                        .build()
        );
        if (settings.spawnsNaturally) {
            BiomeModifications.addSpawn(
                    (biomeSelectionContext -> biomeSelectionContext.hasTag(settings.selectorTag)),
                    settings.spawnGroup,
                    entityType,
                    settings.spawnWeight,
                    settings.minGroupSize,
                    settings.maxGroupSize);
        }
        return entityType;
    }
}
